using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml.Linq;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;
using OtrosValidation;
using ServiciosEmail;
using VendedorValidation;

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
                // método listarTodos para obtener la lista de administradores
                BindingList<almacen> almacenes = this.boAlmacen.listarTodos();

                // Configuramos la fuente de datos del DropDownList
                ddlElegirAlmacenTrabajador.DataSource = almacenes;

                // Mostramos solo el nombre del administrador
                ddlElegirAlmacenTrabajador.DataValueField = "id"; //
                ddlElegirAlmacenTrabajador.DataTextField = "nombre"; // Nombre es la propiedad que contiene el nombre del trabajador

                // Enlazamos los datos
                ddlElegirAlmacenTrabajador.DataBind();
            }
        }

        // Método para guardar un Trabajador
        protected void btnGuardarAdministrador_Click(object sender, EventArgs e)
        {
            // Parsear Salario  como double
            double salarioL = 0;
            DateTime fechaContratacionL;

            // Si todas las validaciones son correctas,  crear el vendedor
            var nuevoTrabajador = new OtrosVali
            {
                Dni = txtDniTrabajador.Text.Trim(),
                NombreCompleto = txtNombreCompletoTrabajador.Text.Trim(),
                Telefono = txtTelefonoTrabajador.Text.Trim(),
                Correo = txtCorreoTrabajador.Text.Trim(),
                Direccion = txtDireccionTrabajador.Text.Trim(),
                NombreUsuario = txtNombreUsuarioTrabajador.Text.Trim(),
                Contrasenha = txtContrasenhaTrabajador.Text.Trim(),
                Salario = txtSalarioTrabajador.Text.Trim(),
                FechaContratacion = txtFechaContratacionTrabajador.Text.Trim(),
            };

            // Aquí seguiría el proceso de validación con el `OtrosValidator`
            var validator = new OtrosValidator();
            var result = validator.Validate(nuevoTrabajador);

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

            double.TryParse(txtSalarioTrabajador.Text.Trim(), out salarioL);
            DateTime.TryParse(txtFechaContratacionTrabajador.Text.Trim(), out fechaContratacionL);

            int idAlmacenSeleccionado = Int32.Parse(ddlElegirAlmacenTrabajador.SelectedValue);

            int resultadoTrabajador = this.boTrabajadorDeAlmacen.insertar(
                nuevoTrabajador.Dni,
                nuevoTrabajador.NombreCompleto,
                nuevoTrabajador.Telefono,
                nuevoTrabajador.Correo,
                nuevoTrabajador.Direccion,
                estado,
                nuevoTrabajador.NombreUsuario,
                nuevoTrabajador.Contrasenha,
                salarioL,
                fechaContratacionL,
                idAlmacenSeleccionado,
                permisoTrabajador
            );

            // Verificar el resultado
            if (resultadoTrabajador > 0)
            {
                var cuerpoCorreo = new Cuerpo();
                // Llamar al método ObtenerCuerpoCorreo para obtener el cuerpo del mensaje
                string usuario = txtNombreUsuarioTrabajador.Text.Trim();
                string contrasenha = txtContrasenhaTrabajador.Text.Trim();
                string cuerpoCorreoGenerado = cuerpoCorreo.ObtenerCuerpoCorreo(
                    usuario,
                    contrasenha
                );

                var servicioCorreo = new ServicioCorreo();
                servicioCorreo.EnviarCorreoConMailKit(
                    txtCorreoTrabajador.Text.Trim(),
                    "Credenciales para el acceso a Comerzia",
                    cuerpoCorreoGenerado
                );

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
