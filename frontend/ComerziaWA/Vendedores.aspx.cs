using System;
using System.Globalization;
using System.Web.UI;
using ComerziaBO.ComerziaWS;
using ComerziaRecursosHumanosBO;

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
            // Obtener los valores de los controles de vendedor
            string dni;
            int dniInt;
            string nombreCompleto = txtNombreCompletoVendedor.Text.Trim();
            string telefono = txtTelefonoVendedor.Text.Trim();
            string correo = txtCorreoVendedor.Text.Trim();
            string direccion = txtDireccionVendedor.Text.Trim();
            string nombreUsuario = txtNombreUsuarioVendedor.Text.Trim();
            string contrasenha = txtContrasenhaVendedor.Text.Trim();

            // Convertir los valores
            double salario;
            DateTime fechaContratacion;
            double ingresosVentas;
            double porcentajeComision;
            // Convertir los valores


            try
            {
                salario = double.Parse(txtSalarioVendedor.Text.Trim());
            }
            catch (FormatException)
            {
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showErrorPopup",
                    "showPopup('Formato de salario  incorrecto.');",
                    true
                );
                return;
            }

            try
            {
                dniInt = Int32.Parse(txtDniVendedor.Text.Trim());
            }
            catch (FormatException)
            {
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showErrorPopup",
                    "showPopup('Formato de dni incorrecto.');",
                    true
                );
                return;
            }

            string fechaTexto = txtFechaContratacionVendedor.Text.Trim();

            fechaContratacion = DateTime.ParseExact(
                fechaTexto,
                "yyyy-MM-dd",
                CultureInfo.InvariantCulture
            );

            try
            {
                ingresosVentas = double.Parse(txtIngresosVentas.Text.Trim());
            }
            catch (FormatException)
            {
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showErrorPopup",
                    "showPopup('Formato de ingresos de ventas incorrecto.');",
                    true
                );
                return;
            }

            try
            {
                porcentajeComision = double.Parse(txtPorcentajeComision.Text.Trim());
            }
            catch (FormatException)
            {
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showErrorPopup",
                    "showPopup('Formato de porcentaje de comisión incorrecto.');",
                    true
                );
                return;
            }

            dni = txtDniVendedor.Text.Trim();
            estadoEmpleadoEnum estado;
            if (ddlEstadoVendedor.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }

            //Llamar al método insertar y obtener el resultado
            int resultadoVendedor = this.boVendedor.insertar(
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
                ingresosVentas,
                porcentajeComision
            );

            // Verificar el resultado para mostrar un mensaje al usuario
            if (resultadoVendedor > 0)
            {
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
