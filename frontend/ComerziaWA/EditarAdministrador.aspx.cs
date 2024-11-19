using System;
using System.ComponentModel;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class EditarAdministrador : System.Web.UI.Page
    {
        private AdministradorBO boAdministrador;
        private administrador administradorEditable;
        private AlmacenBO boAlmacen;

        public EditarAdministrador()
        {
            boAdministrador = new AdministradorBO();
            administradorEditable = new administrador();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Llamamos al método listarTodos para obtener la lista de almacenes
                BindingList<almacen> almacenes = this.boAlmacen.listarTodos();

                // Configuramos la fuente de datos del DropDownList
                ddlElegirAlmacenAdministrador.DataSource = almacenes;

                // Mostramos solo el nombre del almacen
                ddlElegirAlmacenAdministrador.DataValueField = "id"; // Opcionalmente, puedes usar el ID como valor
                ddlElegirAlmacenAdministrador.DataTextField = "nombre"; // Nombre es la propiedad que contiene el nombre del administrador

                // Enlazamos los datos
                ddlElegirAlmacenAdministrador.DataBind();

                // Obtener el idVendedor de la sesión
                if (
                    Session["idAdministradorEditable"] != null
                    && int.TryParse(
                        Session["idAdministradorEditable"].ToString(),
                        out int idAdministradorEditable
                    )
                )
                {
                    // Llamar al método para obtener los datos del vendedor
                    administradorEditable = this.boAdministrador.obtenerPorId(
                        idAdministradorEditable
                    );
                    // Guardar el objeto en ViewState para mantener su estado
                    ViewState["administradorEditable"] = administradorEditable;

                    if (administradorEditable == null)
                    {
                        System.Diagnostics.Debug.WriteLine("No hay idAdministrador en la sesión.");
                    }

                    // Asignar los valores del vendedor a los controles del formulario


                    txtDniAdministrador.Text = administradorEditable.dni;
                    txtNombreCompletoAdministrador.Text = administradorEditable.nombre;
                    txtTelefonoAdministrador.Text = administradorEditable.telefono;
                    txtCorreoAdministrador.Text = administradorEditable.correo;
                    txtDireccionAdministrador.Text = administradorEditable.direccion;
                    txtSalarioAdministrador.Text = administradorEditable.salario.ToString("F2"); // Formato de dos decimales

                    if (estadoEmpleadoEnum.ACTIVO == administradorEditable.estado)
                    {
                        ddlEstadoAdministrador.SelectedValue = "Activo";
                    }
                    else
                    {
                        ddlEstadoAdministrador.SelectedValue = "Inactivo";
                    }

                    ListItem item = ddlElegirAlmacenAdministrador.Items.FindByValue(
                        administradorEditable.idAlmacen.ToString()
                    );
                    if (item != null)
                    {
                        // Selecciona el almacén que coincide con el idAlmacen
                        ddlElegirAlmacenAdministrador.ClearSelection();
                        item.Selected = true;
                    }
                }
            }
            else
            {
                // Recuperar trabajadorEditable desde ViewState en un postback
                administradorEditable = (administrador)ViewState["administradorEditable"];
            }
        }

        protected void btnGuardarVendedor_Click(object sender, EventArgs e)
        {
            // Convertir los valores
            string dni = txtDniAdministrador.Text.Trim();
            string nombreCompleto = txtNombreCompletoAdministrador.Text.Trim();
            string telefono = txtTelefonoAdministrador.Text.Trim();
            string correo = txtCorreoAdministrador.Text.Trim();
            string direccion = txtDireccionAdministrador.Text.Trim();
            double salario = double.Parse(txtSalarioAdministrador.Text.Trim());

            // Estado
            estadoEmpleadoEnum estado;
            if (ddlEstadoAdministrador.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }

            //Id Almacenado
            int idAlmacenSeleccionado = Int32.Parse(ddlElegirAlmacenAdministrador.SelectedValue);

            this.boAdministrador.modificar(
                administradorEditable.id,
                dni,
                nombreCompleto,
                telefono,
                correo,
                direccion,
                estado,
                administradorEditable.nombreUsuario,
                administradorEditable.contrasenha,
                salario,
                administradorEditable.fechaContratacion,
                idAlmacenSeleccionado
            );

            Session.Remove("idAdministradorEditable");
            Response.Redirect("ListarAdministradores.aspx");
        }
    }
}
