using System;
using System.ComponentModel;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class ListarTrabajadoresAlmacen : System.Web.UI.Page
    {
        private TrabajadorDeAlmacenBO boTrabajadorDeAlmacen;
        private AlmacenBO boAlmacen;

        public ListarTrabajadoresAlmacen()
        {
            boTrabajadorDeAlmacen = new TrabajadorDeAlmacenBO();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Obtener la lista de vendedores
                BindingList<trabajadorDeAlmacen> trabajadores =
                    this.boTrabajadorDeAlmacen.listarTodos();

                // Asignar la lista de personas al GridView
                gvVendedores.DataSource = trabajadores;
                gvVendedores.DataBind(); // Actualiza el GridView
            }
        }

        protected void gvVendedores_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Editar" || e.CommandName == "Eliminar")
            {
                if (e.CommandName == "Editar")
                {
                    // Lógica para editar el vendedor con idVendedor

                    int idTrabajador = Convert.ToInt32(e.CommandArgument);

                    Session["idTrabajadorEditable"] = Convert.ToString(idTrabajador);
                    // Redirigir a la página de edición pasando el idVendedor como parámetro

                    Response.Redirect("EditarTrabajadorAlmacen.aspx");
                }
                else if (e.CommandName == "Eliminar")
                {
                    string[] argumentos = e.CommandArgument.ToString().Split(',');

                    // Convertir los parámetros según su tipo
                    int idTrabajador = int.Parse(argumentos[0]);
                    int idPersona = int.Parse(argumentos[1]);

                    this.boTrabajadorDeAlmacen.eliminar(idPersona);
                    // Recargar la lista de vendedores después de la eliminac
                    // Obtener la lista de vendedores
                    Response.Redirect("ListarTrabajadoresAlmacen.aspx");
                }
            }
        }
    }
}
