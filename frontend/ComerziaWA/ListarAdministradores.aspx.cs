using System;
using System.Collections.Generic;
using System.ComponentModel;
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
    public partial class ListarAdministradores : System.Web.UI.Page
    {
        private AdministradorBO boAdministrador;
        private AlmacenBO boAlmacen;

        public ListarAdministradores()
        {
            boAdministrador = new AdministradorBO();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Obtener la lista de vendedores
                BindingList<administrador> administradores = this.boAdministrador.listarTodos();

                // Asignar la lista de personas al GridView
                gvVendedores.DataSource = administradores;
                gvVendedores.DataBind(); // Actualiza el GridView
            }
        }

        protected void gvVendedores_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Editar" || e.CommandName == "Eliminar")
            {
                if (e.CommandName == "Editar")
                {
                    int idAdministrador = Convert.ToInt32(e.CommandArgument);

                    Session["idAdministradorEditable"] = Convert.ToString(idAdministrador);
                    // Redirigir a la página de edición pasando el idVendedor como parámetro

                    Response.Redirect("EditarAdministrador.aspx");
                }
                else if (e.CommandName == "Eliminar")
                {
                    // Lógica para eliminar el vendedor con idVendedor
                    string[] argumentos = e.CommandArgument.ToString().Split(',');

                    // Convertir los parámetros según su tipo
                    int idAdministrador = int.Parse(argumentos[0]);
                    int idPersona = int.Parse(argumentos[1]);

                    this.boAdministrador.eliminar(idPersona);

                    // Recargar la lista de vendedores después de la eliminac

                    Response.Redirect("ListarAdministradores.aspx");
                }
            }
        }

        protected void gvVendedores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            // Establecer el índice de la página
            gvVendedores.PageIndex = e.NewPageIndex;

            // Obtener nuevamente la lista de administradores y vincularla al GridView
            BindingList<administrador> administradores = this.boAdministrador.listarTodos();
            gvVendedores.DataSource = administradores;
            gvVendedores.DataBind(); // Actualiza el GridView
        }
    }
}
