using System;
using System.ComponentModel;
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
                    int idPersona = int.Parse(argumentos[1]);

                    this.boAdministrador.eliminar(idPersona);

                    // Recargar la lista de vendedores después de la eliminac

                    Response.Redirect("ListarAdministradores.aspx");
                }
            }
        }
    }
}
