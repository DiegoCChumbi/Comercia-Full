using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;

namespace ComerziaWA
{
    public partial class gestion_almacen : System.Web.UI.Page
    {
        private AlmacenBO almacen;
        private BindingList<almacen> almacenes;

        public gestion_almacen()
        {
            almacen = new AlmacenBO();
            almacenes = this.almacen.listarTodos();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            dgvAlmacenes.DataSource = almacenes;
            dgvAlmacenes.DataBind();
        }

        protected void btnVer_Click(object sender, EventArgs e)
        {
            string idAlmacen = ((LinkButton)sender).CommandArgument;
            Session["idAlmacen"] = idAlmacen; // Guardamos el id
            Response.Redirect("mostrar_almacen.aspx");
        }

        protected void dgvAlmacenes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvAlmacenes.PageIndex = e.NewPageIndex;
            dgvAlmacenes.DataBind();
        }

        protected void dgvAlmacenes_Sorting(object sender, GridViewSortEventArgs e)
        {
            // Falta implementar.
            // Se debe de crear un método en el backend
        }

        protected void dgvAlmacenes_RowEstado(object sender, GridViewRowEventArgs e)
        {
            // Condicional necesario, para indicar que solo se aplique alos DataRow y no al Header, Footer o Pager.
            // DataControlRowType es un ENUM.
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                Label lblEstado = new Label();
                string estado = e.Row.Cells[3].Text; // De toda la fila, el "estado" se encuentra en el 3.
                // Obtenemos el contenido de la celda
                if (estado.ToLower() == "operativo")
                {
                    lblEstado.CssClass = "badge rounded-pill bg-primary";
                }
                else if (estado.ToLower() == "inoperativo")
                {
                    lblEstado.CssClass = "badge rounded-pill bg-danger";
                }
                lblEstado.Text = e.Row.Cells[3].Text.Trim(); // Limpiamos los espacios adicionales
                e.Row.Cells[3].Controls.Clear(); // Limpiar el contenido de la celda
                e.Row.Cells[3].Controls.Add(lblEstado); // Agregar el Label con el estilo
            }
        }
    }
}
