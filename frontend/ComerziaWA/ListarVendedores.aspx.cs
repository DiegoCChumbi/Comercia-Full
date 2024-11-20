using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.ServiceModel;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class ListarVendedores : System.Web.UI.Page
    {
        private VendedorBO boVendedor;
        private AlmacenBO boAlmacen;

        public ListarVendedores()
        {
            boVendedor = new VendedorBO();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Obtener la lista de vendedores
                BindingList<vendedor> vendedores = this.boVendedor.listarTodos();

                // Asignar la lista de personas al GridView
                gvVendedores.DataSource = vendedores;
                gvVendedores.DataBind(); // Actualiza el GridView
            }
        }

        protected void gvVendedores_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Editar" || e.CommandName == "Eliminar")
            {
                if (e.CommandName == "Editar")
                {
                    // Obtener el valor de idVendedor desde CommandArgument
                    int idVendedor = Convert.ToInt32(e.CommandArgument);

                    // Guardar el idVendedor en la sesión
                    Session["idVendedorEditable"] = Convert.ToString(idVendedor);

                    // Redirigir a la página de edición pasando el idVendedor como parámetro

                    Response.Redirect("EditarVendedor.aspx");
                }
                else if (e.CommandName == "Eliminar")
                {
                    // Obtener los parámetros desde CommandArgument
                    string[] argumentos = e.CommandArgument.ToString().Split(',');

                    // Convertir los parámetros según su tipo
                    int idVendedor = int.Parse(argumentos[0]);
                    int idPersona = int.Parse(argumentos[1]);

                    // Lógica para eliminar el vendedor con idVendedor

                    this.boVendedor.eliminar(idVendedor, idPersona);
                    // Recargar la lista de vendedores después de la eliminac
                    // Obtener la lista de vendedores
                    Response.Redirect("ListarVendedores.aspx");
                }
            }
        }

        protected void gvVendedores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            // Establecer el índice de la página
            gvVendedores.PageIndex = e.NewPageIndex;

            // Obtener nuevamente la lista de vendedores y vincularla al GridView
            BindingList<vendedor> vendedores = this.boVendedor.listarTodos();
            gvVendedores.DataSource = vendedores;
            gvVendedores.DataBind(); // Actualiza el GridView
        }
    }
}
