using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;
using OtrosValidation;
using ServiciosEmail;
using System;
using System.ComponentModel;
using System.Text;
using System.Web.UI;

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
            double salarioL = 0;
            DateTime fechaContratacionL;

            // Si todas las validaciones son correctas, puedes proceder a crear el vendedor
            var nuevoAdministrador = new OtrosVali
            {
                Dni = txtDniAdministrador.Text.Trim(),
                NombreCompleto = txtNombreCompletoAdministrador.Text.Trim(),
                Telefono = txtTelefonoAdministrador.Text.Trim(),
                Correo = txtCorreoAdministrador.Text.Trim(),
                Direccion = txtDireccionAdministrador.Text.Trim(),
                NombreUsuario = txtNombreUsuarioAdministrador.Text.Trim(),
                Contrasenha = txtContrasenhaAdministrador.Text.Trim(),
                Salario = txtSalarioAdministrador.Text.Trim(),
                FechaContratacion = txtFechaContratacionAdministrador.Text.Trim(),
            };

            // Aquí seguiría el proceso de validación con el `VendedorValidator`
            var validator = new OtrosValidator();
            var result = validator.Validate(nuevoAdministrador);

            if (!result.IsValid)
            {
                StringBuilder errores = new StringBuilder();
                foreach (var error in result.Errors)
                {
                    errores.AppendLine(error.ErrorMessage);
                }

                if (errores.Length > 0)
                {
                    string mensajeErrores = errores
                        .ToString()
                        .Replace("\n", "\\n")
                        .Replace("\r", "")
                        .Replace("'", "\\'");

                    ScriptManager.RegisterStartupScript(
                        this,
                        this.GetType(),
                        "showErrorPopup",
                        $"showPopup('{mensajeErrores}');",
                        true
                    );
                }

                return; // Detener el proceso
            }

            estadoEmpleadoEnum estado;
            if (ddlEstadoAdministrador.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }
            double.TryParse(txtSalarioAdministrador.Text.Trim(), out salarioL);
            DateTime.TryParse(
                txtFechaContratacionAdministrador.Text.Trim(),
                out fechaContratacionL
            );
            int idAlmacenSeleccionado = Int32.Parse(ddlElegirAlmacenAdministrador.SelectedValue);

            // Llamar al método insertar y obtener el resultado
            int resultadoAdministrador = this.boAdministrador.insertar(
                nuevoAdministrador.Dni,
                nuevoAdministrador.NombreCompleto,
                nuevoAdministrador.Telefono,
                nuevoAdministrador.Correo,
                nuevoAdministrador.Direccion,
                estado,
                nuevoAdministrador.NombreUsuario,
                nuevoAdministrador.Contrasenha,
                salarioL,
                fechaContratacionL,
                idAlmacenSeleccionado
            );

            // Verificar el resultado para mostrar un mensaje al usuario
            if (resultadoAdministrador > 0)
            {
                var cuerpoCorreo = new Cuerpo();

                // Llamar al método ObtenerCuerpoCorreo para obtener el cuerpo del mensaje
                string usuario = txtNombreUsuarioAdministrador.Text.Trim();
                string contrasenha = txtContrasenhaAdministrador.Text.Trim();
                string cuerpoCorreoGenerado = cuerpoCorreo.ObtenerCuerpoCorreo(
                    usuario,
                    contrasenha
                );

                var servicioCorreo = new ServicioCorreo();
                servicioCorreo.EnviarCorreoConMailKit(
                    txtCorreoAdministrador.Text.Trim(),
                    "Credenciales para el acceso a Comerzia",
                    cuerpoCorreoGenerado
                );

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
    }
}
