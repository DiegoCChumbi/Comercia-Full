using ComerziaBO.ComerziaWS;
using ComerziaRecursosHumanosBO;
using System;
using System.Text;
using System.Web.UI;
using VendedorValidation;

namespace ComerziaWA
{
    public partial class EditarVendedor : System.Web.UI.Page
    {
        private VendedorBO boVendedor;
        private vendedor vendedorEditable;

        public EditarVendedor()
        {
            boVendedor = new VendedorBO();
            vendedorEditable = new vendedor();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Obtener el idVendedor de la sesión
                if (
                    Session["idVendedorEditable"] != null
                    && int.TryParse(
                        Session["idVendedorEditable"].ToString(),
                        out int idVendedorEditable
                    )
                )
                {
                    // Llamar al método para obtener los datos del vendedor
                    vendedorEditable = this.boVendedor.obtenerPorId(idVendedorEditable);
                    // Guardar el objeto en ViewState para mantener su estado
                    ViewState["vendedorEditable"] = vendedorEditable;

                    if (vendedorEditable == null)
                    {
                        System.Diagnostics.Debug.WriteLine("No hay idVendedor en la sesión.");
                    }

                    // Asignar los valores del vendedor a los controles del formulario
                    txtDniVendedor.Text = vendedorEditable.dni;
                    txtNombreCompletoVendedor.Text = vendedorEditable.nombre;
                    txtTelefonoVendedor.Text = vendedorEditable.telefono;
                    txtCorreoVendedor.Text = vendedorEditable.correo;
                    txtDireccionVendedor.Text = vendedorEditable.direccion;

                    if (estadoEmpleadoEnum.ACTIVO == vendedorEditable.estado)
                    {
                        ddlEstadoVendedor.SelectedValue = "Activo";
                    }
                    else
                    {
                        ddlEstadoVendedor.SelectedValue = "Inactivo";
                    }

                    txtSalarioVendedor.Text = vendedorEditable.salario.ToString("F2"); // Formato de dos decimales
                    txtIngresosVentas.Text = vendedorEditable.ingresosVentas.ToString("F2");
                    txtPorcentajeComision.Text = vendedorEditable.porcentajeComision.ToString("F2");
                }
            }
            else
            {
                // Recuperar vendedorEditable desde ViewState en un postback
                vendedorEditable = (vendedor)ViewState["vendedorEditable"];
            }
        }

        protected void btnGuardarVendedor_Click(object sender, EventArgs e)
        {
            // Parsear Salario y IngresosVentas como double
            double salarioL = 0;
            double ingresosVentasL = 0;
            double porcentajeComisionL = 0;

            // Si todas las validaciones son correctas,  crear el vendedor
            var nuevoVendedor = new VendedorVali
            {
                Dni = txtDniVendedor.Text.Trim(),
                NombreCompleto = txtNombreCompletoVendedor.Text.Trim(),
                Telefono = txtTelefonoVendedor.Text.Trim(),
                Correo = txtCorreoVendedor.Text.Trim(),
                Direccion = txtDireccionVendedor.Text.Trim(),
                NombreUsuario = vendedorEditable.nombreUsuario,
                Contrasenha = vendedorEditable.contrasenha,
                Salario = txtSalarioVendedor.Text.Trim(),
                FechaContratacion = DateTime.MinValue.ToString("yyyy-MM-dd"),
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

            // Convertir los valores
            // Convertir los valores
            double.TryParse(txtSalarioVendedor.Text.Trim(), out salarioL);
            double.TryParse(txtIngresosVentas.Text.Trim(), out ingresosVentasL);
            double.TryParse(txtPorcentajeComision.Text.Trim(), out porcentajeComisionL);

            // Estado
            estadoEmpleadoEnum estado;
            if (ddlEstadoVendedor.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }

            this.boVendedor.modificar(
                vendedorEditable.id,
                nuevoVendedor.Dni,
                nuevoVendedor.NombreCompleto,
                nuevoVendedor.Telefono,
                nuevoVendedor.Correo,
                nuevoVendedor.Direccion,
                estado,
                vendedorEditable.nombreUsuario,
                vendedorEditable.contrasenha,
                salarioL,
                vendedorEditable.fechaContratacion,
                ingresosVentasL,
                porcentajeComisionL
            );

            Session.Remove("idVendedorEditable");
            Response.Redirect("ListarVendedores.aspx");
        }
    }
}
