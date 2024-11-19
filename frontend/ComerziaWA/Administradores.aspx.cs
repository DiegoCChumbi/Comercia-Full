using System;
using System.ComponentModel;
using System.Globalization;
using System.Linq;
using System.Web.UI;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class Administradores : System.Web.UI.Page
    {
        private AdministradorBO boAdministrador;
        private AlmacenBO boAlmacen;

        public Administradores()
        {
            boAdministrador = new AdministradorBO();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Llamamos al método listarTodos para obtener la lista de administradores
                BindingList<almacen> almacenes = this.boAlmacen.listarTodos();

                // Configuramos la fuente de datos del DropDownList
                ddlElegirAlmacenAdministrador.DataSource = almacenes;

                // Mostramos solo el nombre del administrador
                ddlElegirAlmacenAdministrador.DataValueField = "id"; // Opcionalmente, puedes usar el ID como valor
                ddlElegirAlmacenAdministrador.DataTextField = "nombre"; // Nombre es la propiedad que contiene el nombre del administrador

                // Enlazamos los datos
                ddlElegirAlmacenAdministrador.DataBind();
            }
        }

        // Método para guardar un Administrador
        protected void btnGuardarAdministrador_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener los valores de los controles de administrador
                string dni = txtDniAdministrador.Text.Trim();
                string nombreCompleto = txtNombreCompletoAdministrador.Text.Trim();
                string telefono = txtTelefonoAdministrador.Text.Trim();
                string correo = txtCorreoAdministrador.Text.Trim();
                string direccion = txtDireccionAdministrador.Text.Trim();
                string nombreUsuario = txtNombreUsuarioAdministrador.Text.Trim();
                string contrasenha = txtContrasenhaAdministrador.Text.Trim();
                string salario = txtSalarioAdministrador.Text.Trim();
                DateTime fechaContratacion;
                // Validar que el DNI sea de 8 dígitos numéricos
                if (dni.Length != 8 || !dni.All(char.IsDigit))
                {
                    throw new FormatException("DNI debe contener exactamente 8 dígitos numéricos.");
                }

                // Validar que el nombre completo no esté vacío
                if (string.IsNullOrEmpty(nombreCompleto))
                {
                    throw new FormatException("El nombre completo no puede estar vacío.");
                }

                // Validar que el teléfono solo contenga números
                if (string.IsNullOrEmpty(telefono) || telefono.Length != 9)
                {
                    throw new FormatException(
                        "El teléfono debe contener solo números con 9 dígitos."
                    );
                }

                // Validar formato básico del correo electrónico
                if (string.IsNullOrEmpty(correo) || !correo.Contains("@") || !correo.Contains("."))
                {
                    throw new FormatException("Formato de correo electrónico incorrecto.");
                }

                if (string.IsNullOrEmpty(direccion))
                {
                    throw new FormatException("La dirección no debe estar vacía.");
                }

                if (
                    !double.TryParse(
                        txtSalarioAdministrador.Text.Trim(),
                        out double salarioConvertido
                    )
                    || salarioConvertido <= 0
                )
                {
                    throw new FormatException(
                        "El salario debe ser un número válido mayor que cero."
                    );
                }

                // Validar que el nombre de usuario no esté vacío
                if (string.IsNullOrEmpty(nombreUsuario))
                {
                    throw new FormatException("El nombre de usuario no puede estar vacío.");
                }

                // Validar que la contraseña no esté vacía y tenga al menos 8 caracteres
                if (string.IsNullOrEmpty(contrasenha) || contrasenha.Length < 6)
                {
                    throw new FormatException("La contraseña debe tener al menos 6 caracteres.");
                }

                string fechaTexto = txtFechaContratacionAdministrador.Text.Trim();
                fechaContratacion = DateTime.ParseExact(
                    fechaTexto,
                    "yyyy-MM-dd",
                    CultureInfo.InvariantCulture
                );

                estadoEmpleadoEnum estado;
                if (ddlEstadoAdministrador.SelectedValue == "Activo")
                {
                    estado = estadoEmpleadoEnum.ACTIVO;
                }
                else
                {
                    estado = estadoEmpleadoEnum.INACTIVO;
                }

                int idAlmacenSeleccionado = Int32.Parse(
                    ddlElegirAlmacenAdministrador.SelectedValue
                );

                // Llamar al método insertar y obtener el resultado
                int resultadoAdministrador = this.boAdministrador.insertar(
                    dni,
                    nombreCompleto,
                    telefono,
                    correo,
                    direccion,
                    estado,
                    nombreUsuario,
                    contrasenha,
                    salarioConvertido,
                    fechaContratacion,
                    idAlmacenSeleccionado
                );

                // Verificar el resultado para mostrar un mensaje al usuario
                if (resultadoAdministrador > 0)
                {
                    // Registro exitoso
                    txtDniAdministrador.Text = string.Empty;
                    txtNombreCompletoAdministrador.Text = string.Empty;
                    txtTelefonoAdministrador.Text = string.Empty;
                    txtCorreoAdministrador.Text = string.Empty;
                    txtDireccionAdministrador.Text = string.Empty;
                    txtNombreUsuarioAdministrador.Text = string.Empty;
                    txtContrasenhaAdministrador.Text = string.Empty;
                    txtSalarioAdministrador.Text = string.Empty;
                    txtFechaContratacionAdministrador.Text = string.Empty;
                    ddlElegirAlmacenAdministrador.SelectedIndex = 0;
                    ddlEstadoAdministrador.SelectedIndex = 0;

                    // Mostrar mensaje de éxito
                    ScriptManager.RegisterStartupScript(
                        this,
                        this.GetType(),
                        "showSuccessPopup",
                        "showPopup('Administrador guardado exitosamente.');",
                        true
                    );
                }
                else
                {
                    // Error en el registro
                    ScriptManager.RegisterStartupScript(
                        this,
                        this.GetType(),
                        "showSuccessPopup",
                        "showPopup('Error al registrar el administrador.');",
                        true
                    );
                }
            }
            catch (FormatException ex)
            {
                // Mostrar un mensaje de error específico basado en la excepción capturada
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showErrorPopup",
                    $"showPopup('{ex.Message}');",
                    true
                );
            }
            catch (Exception ex)
            {
                // Captura cualquier otra excepción no específica
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showGenericErrorPopup",
                    $"showPopup('Ocurrió un error inesperado: {ex.Message}');",
                    true
                );
            }
        }
    }
}
