using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;
using System;
using System.ComponentModel;
using System.Web.UI.WebControls;

namespace ComerziaWA
{
    public partial class EditarTrabajadorAlmacen : System.Web.UI.Page
    {
        private TrabajadorDeAlmacenBO boTrabajador;
        private trabajadorDeAlmacen trabajadorEditable;
        private AlmacenBO boAlmacen;

        public EditarTrabajadorAlmacen()
        {
            boTrabajador = new TrabajadorDeAlmacenBO();
            trabajadorEditable = new trabajadorDeAlmacen();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Llamamos al método listarTodos para obtener la lista de almacenes
                BindingList<almacen> almacenes = this.boAlmacen.listarTodos();

                // Configuramos la fuente de datos del DropDownList
                ddlElegirAlmacenTrabajador.DataSource = almacenes;

                // Mostramos solo el nombre del almacen
                ddlElegirAlmacenTrabajador.DataValueField = "id"; // Opcionalmente, puedes usar el ID como valor
                ddlElegirAlmacenTrabajador.DataTextField = "nombre"; // Nombre es la propiedad que contiene el nombre del administrador

                // Enlazamos los datos
                ddlElegirAlmacenTrabajador.DataBind();

                // Obtener el idVendedor de la sesión
                if (
                    Session["idTrabajadorEditable"] != null
                    && int.TryParse(
                        Session["idTrabajadorEditable"].ToString(),
                        out int idTrabajadorEditable
                    )
                )
                {
                    // Llamar al método para obtener los datos del vendedor
                    trabajadorEditable = this.boTrabajador.obtenerPorId(idTrabajadorEditable);
                    // Guardar el objeto en ViewState para mantener su estado
                    ViewState["trabajadorEditable"] = trabajadorEditable;

                    if (trabajadorEditable == null)
                    {
                        System.Diagnostics.Debug.WriteLine("No hay idTrabajador en la sesión.");
                    }

                    // Asignar los valores del vendedor a los controles del formulario


                    txtDniTrabajador.Text = trabajadorEditable.dni;
                    txtNombreCompletoTrabajador.Text = trabajadorEditable.nombre;
                    txtTelefonoTrabajador.Text = trabajadorEditable.telefono;
                    txtCorreoTrabajador.Text = trabajadorEditable.correo;
                    txtDireccionTrabajador.Text = trabajadorEditable.direccion;
                    txtSalarioTrabajador.Text = trabajadorEditable.salario.ToString("F2"); // Formato de dos decimales

                    if (estadoEmpleadoEnum.ACTIVO == trabajadorEditable.estado)
                    {
                        ddlEstadoTrabajador.SelectedValue = "Activo";
                    }
                    else
                    {
                        ddlEstadoTrabajador.SelectedValue = "Inactivo";
                    }

                    if (trabajadorEditable.licenciaMontacarga)
                    {
                        ddlPermisoTrabajador.SelectedValue = "true";
                    }
                    else
                    {
                        ddlPermisoTrabajador.SelectedValue = "false";
                    }

                    ListItem item = ddlElegirAlmacenTrabajador.Items.FindByValue(
                        trabajadorEditable.idAlmacen.ToString()
                    );
                    if (item != null)
                    {
                        // Selecciona el almacén que coincide con el idAlmacen
                        ddlElegirAlmacenTrabajador.ClearSelection();
                        item.Selected = true;
                    }
                }
            }
            else
            {
                // Recuperar trabajadorEditable desde ViewState en un postback
                trabajadorEditable = (trabajadorDeAlmacen)ViewState["trabajadorEditable"];
            }
        }

        protected void btnGuardarVendedor_Click(object sender, EventArgs e)
        {
            // Convertir los valores
            string dni = txtDniTrabajador.Text.Trim();
            string nombreCompleto = txtNombreCompletoTrabajador.Text.Trim();
            string telefono = txtTelefonoTrabajador.Text.Trim();
            string correo = txtCorreoTrabajador.Text.Trim();
            string direccion = txtDireccionTrabajador.Text.Trim();
            double salario = double.Parse(txtSalarioTrabajador.Text.Trim());

            // Estado
            estadoEmpleadoEnum estado;
            if (ddlEstadoTrabajador.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }

            //Id Almacenado
            int idAlmacenSeleccionado = Int32.Parse(ddlElegirAlmacenTrabajador.SelectedValue);
            //Tiene licencia
            bool tieneLicenciaMontacarga = ddlPermisoTrabajador.SelectedValue == "true";

            this.boTrabajador.modificar(
                trabajadorEditable.id,
                dni,
                nombreCompleto,
                telefono,
                correo,
                direccion,
                estado,
                trabajadorEditable.nombreUsuario,
                trabajadorEditable.contrasenha,
                salario,
                trabajadorEditable.fechaContratacion,
                idAlmacenSeleccionado,
                tieneLicenciaMontacarga
            );

            Session.Remove("idTrabajadorEditable");
            Response.Redirect("ListarTrabajadoresAlmacen.aspx");
        }
    }
}
