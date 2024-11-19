using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;
using System;
using System.ComponentModel;
using System.Globalization;
using System.Web.UI;

namespace ComerziaWA
{
    public partial class TrabajadorDeAlmacen : System.Web.UI.Page
    {
        private TrabajadorDeAlmacenBO boTrabajadorDeAlmacen;
        private AlmacenBO boAlmacen;

        public TrabajadorDeAlmacen()
        {
            boTrabajadorDeAlmacen = new TrabajadorDeAlmacenBO();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Llamamos al método listarTodos para obtener la lista de administradores
                BindingList<almacen> almacenes = this.boAlmacen.listarTodos();

                // Configuramos la fuente de datos del DropDownList
                ddlElegirAlmacenTrabajador.DataSource = almacenes;

                // Mostramos solo el nombre del administrador
                ddlElegirAlmacenTrabajador.DataValueField = "id"; // Opcionalmente, puedes usar el ID como valor
                ddlElegirAlmacenTrabajador.DataTextField = "nombre"; // Nombre es la propiedad que contiene el nombre del administrador

                // Enlazamos los datos
                ddlElegirAlmacenTrabajador.DataBind();
            }
        }

        // Método para guardar un Trabajador
        protected void btnGuardarAdministrador_Click(object sender, EventArgs e)
        {
            // Obtener los valores de los controles de administrador
            string dni = txtDniTrabajador.Text.Trim();
            string nombreCompleto = txtNombreCompletoTrabajador.Text.Trim();
            string telefono = txtTelefonoTrabajador.Text.Trim();
            string correo = txtCorreoTrabajador.Text.Trim();
            string direccion = txtDireccionTrabajador.Text.Trim();

            string nombreUsuario = txtNombreUsuarioTrabajador.Text.Trim();
            string contrasenha = txtContrasenhaTrabajador.Text.Trim();

            double salario;
            DateTime fechaContratacion;

            try
            {
                salario = double.Parse(txtSalarioTrabajador.Text.Trim());
            }
            catch (FormatException)
            {
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showErrorPopup",
                    "showPopup('Formato de salario incorrecto.');",
                    true
                );
                return;
            }

            string fechaTexto = txtFechaContratacionTrabajador.Text.Trim();
            fechaContratacion = DateTime.ParseExact(
                fechaTexto,
                "yyyy-MM-dd",
                CultureInfo.InvariantCulture
            );
            bool permisoTrabajador = bool.Parse(ddlPermisoTrabajador.SelectedValue);
            estadoEmpleadoEnum estado;
            if (ddlEstadoTrabajador.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }

            int idAlmacenSeleccionado = Int32.Parse(ddlElegirAlmacenTrabajador.SelectedValue);

            // Llamar al método insertar y obtener el resultado
            int resultadoAdministrador = this.boTrabajadorDeAlmacen.insertar(
                dni,
                nombreCompleto,
                telefono,
                correo,
                direccion,
                estado,
                nombreUsuario,
                contrasenha,
                salario,
                fechaContratacion,
                idAlmacenSeleccionado,
                permisoTrabajador
            );

            // Verificar el resultado para mostrar un mensaje al usuario
            if (resultadoAdministrador > 0)
            {
                // Registro exitoso
                txtDniTrabajador.Text = string.Empty;
                txtNombreCompletoTrabajador.Text = string.Empty;
                txtTelefonoTrabajador.Text = string.Empty;
                txtCorreoTrabajador.Text = string.Empty;
                txtDireccionTrabajador.Text = string.Empty;
                txtNombreUsuarioTrabajador.Text = string.Empty;
                txtContrasenhaTrabajador.Text = string.Empty;

                txtSalarioTrabajador.Text = string.Empty;
                txtFechaContratacionTrabajador.Text = string.Empty;
                ddlElegirAlmacenTrabajador.SelectedIndex = 0;
                ddlEstadoTrabajador.SelectedIndex = 0;
                ddlPermisoTrabajador.SelectedIndex = 0;

                // Mostrar mensaje de éxito
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showSuccessPopup",
                    "showPopup('Trabajador de Almacen guardado exitosamente.');",
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
                    "showPopup('Error al registrar el Trabajador de Almacen.');",
                    true
                );
            }
        }
    }
}
