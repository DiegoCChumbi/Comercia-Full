using System;
using System.ComponentModel;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionComercialBO;

namespace ComerziaWA
{
    public partial class GestionComercial : System.Web.UI.Page
    {
        private DocumentoBO documentoBo;
        private BindingList<documento> listaDeTodos;

        public GestionComercial()
        {
            this.documentoBo = new DocumentoBO();
            this.listaDeTodos = this.documentoBo.listarTodos();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            dgvDocumento.DataSource = listaDeTodos;
            dgvDocumento.DataBind();
        }

        protected void btnInsertar_Click(object sender, EventArgs e)
        {
            Response.Redirect("gestionar_documento.aspx?accion=insertar");
        }

        protected void lbVer_Click(object sender, EventArgs e)
        {
            string idDocumento = ((LinkButton)sender).CommandArgument;
            Session["idDocumento"] = idDocumento;
            Response.Redirect("gestionar_documento.aspx?accion=ver");
        }

        protected void lbEliminar_Click(object sender, EventArgs e)
        {
            string idDocumento = ((LinkButton)sender).CommandArgument;
            this.documentoBo.eliminar(int.Parse(idDocumento));
            Response.Redirect("GestionComercial.aspx");
        }

        protected void dgvDocumento_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvDocumento.PageIndex = e.NewPageIndex;
            dgvDocumento.DataBind();
        }
    }
}
