using ComerziaBO.ComerziaWS;
using ComerziaRecursosHumanosBO;
using ServiciosEmail; // Importa el namespace donde tienes el validador
using System;
using System.Text;
using System.Web.UI;
using VendedorValidation;

namespace ComerziaWA
{
    public partial class Vendedores : System.Web.UI.Page
    {
        private VendedorBO boVendedor;

        public Vendedores()
        {
            boVendedor = new VendedorBO();
        }

        protected void Page_Load(object sender, EventArgs e) { }

        // Método para guardar un Vendedor
        protected void btnGuardarVendedor_Click(object sender, EventArgs e)
        {
            // Parsear Salario y IngresosVentas como double
            double salarioL = 0;
            double ingresosVentasL = 0;
            double porcentajeComisionL = 0;
            DateTime fechaContratacionL;

            // Si todas las validaciones son correctas,  crear el vendedor
            var nuevoVendedor = new VendedorVali
            {
                Dni = txtDniVendedor.Text.Trim(),
                NombreCompleto = txtNombreCompletoVendedor.Text.Trim(),
                Telefono = txtTelefonoVendedor.Text.Trim(),
                Correo = txtCorreoVendedor.Text.Trim(),
                Direccion = txtDireccionVendedor.Text.Trim(),
                NombreUsuario = txtNombreUsuarioVendedor.Text.Trim(),
                Contrasenha = txtContrasenhaVendedor.Text.Trim(),
                Salario = txtSalarioVendedor.Text.Trim(),
                FechaContratacion = txtFechaContratacionVendedor.Text.Trim(),
                IngresosVentas = txtIngresosVentas.Text.Trim(),
                PorcentajeComision = txtPorcentajeComision.Text.Trim()
            };

            // Aquí seguiría el proceso de validación con el `VendedorValidator`
            var validator = new VendedorValidator();
            var result = validator.Validate(nuevoVendedor);

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
            if (ddlEstadoVendedor.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }
            double.TryParse(txtSalarioVendedor.Text.Trim(), out salarioL);
            double.TryParse(txtIngresosVentas.Text.Trim(), out ingresosVentasL);
            double.TryParse(txtPorcentajeComision.Text.Trim(), out porcentajeComisionL);
            DateTime.TryParse(txtFechaContratacionVendedor.Text.Trim(), out fechaContratacionL);

            //Llamar al método insertar y obtener el resultado
            int resultadoVendedor = this.boVendedor.insertar(
                nuevoVendedor.Dni,
                nuevoVendedor.NombreCompleto,
                nuevoVendedor.Telefono,
                nuevoVendedor.Correo,
                nuevoVendedor.Direccion,
                estado,
                nuevoVendedor.NombreUsuario,
                nuevoVendedor.Contrasenha,
                salarioL,
                fechaContratacionL,
                ingresosVentasL,
                porcentajeComisionL
            );

            // Verificar el resultado para mostrar un mensaje al usuario
            if (resultadoVendedor > 0)
            {
                var cuerpoCorreo = new Cuerpo();

                // Llamar al método ObtenerCuerpoCorreo para obtener el cuerpo del mensaje
                string usuario = txtNombreUsuarioVendedor.Text.Trim();
                string contrasenha = txtContrasenhaVendedor.Text.Trim();
                string cuerpoCorreoGenerado = cuerpoCorreo.ObtenerCuerpoCorreo(
                    usuario,
                    contrasenha
                );

                var servicioCorreo = new ServicioCorreo();
                servicioCorreo.EnviarCorreoConMailKit(
                    txtCorreoVendedor.Text.Trim(),
                    "Credenciales para el acceso a Comerzia",
                    cuerpoCorreoGenerado
                );

                // Registro exitoso
                txtDniVendedor.Text = string.Empty;
                txtNombreCompletoVendedor.Text = string.Empty;
                txtTelefonoVendedor.Text = string.Empty;
                txtCorreoVendedor.Text = string.Empty;
                txtDireccionVendedor.Text = string.Empty;
                txtNombreUsuarioVendedor.Text = string.Empty;
                txtContrasenhaVendedor.Text = string.Empty;
                txtSalarioVendedor.Text = string.Empty;
                txtFechaContratacionVendedor.Text = string.Empty;
                txtIngresosVentas.Text = string.Empty;
                txtPorcentajeComision.Text = string.Empty;
                ddlEstadoVendedor.SelectedIndex = 0;

                // Mostrar mensaje de éxito
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showSuccessPopup",
                    "showPopup('Vendedor guardado exitosamente.');",
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
                    "showPopup('Error al registrar el vendedor.');",
                    true
                );
            }
        }
    }
}
