using System;
using ComerziaBO.ComerziaWS;
using ComerziaRecursosHumanosBO;

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
            string dni = txtDniVendedor.Text.Trim();
            string nombreCompleto = txtNombreCompletoVendedor.Text.Trim();
            string telefono = txtTelefonoVendedor.Text.Trim();
            string correo = txtCorreoVendedor.Text.Trim();
            string direccion = txtDireccionVendedor.Text.Trim();

            // Convertir los valores
            double salario = double.Parse(txtSalarioVendedor.Text.Trim());
            double ingresosVentas = double.Parse(txtIngresosVentas.Text.Trim());
            double porcentajeComision = double.Parse(txtPorcentajeComision.Text.Trim());

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
                dni,
                nombreCompleto,
                telefono,
                correo,
                direccion,
                estado,
                vendedorEditable.nombreUsuario,
                vendedorEditable.contrasenha,
                salario,
                vendedorEditable.fechaContratacion,
                ingresosVentas,
                porcentajeComision
            );

            Session.Remove("idVendedorEditable");
            Response.Redirect("ListarVendedores.aspx");
        }
    }
}
