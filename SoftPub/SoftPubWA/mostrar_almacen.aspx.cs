using SoftComerziaBO.ComerziaWeb;
using SoftComerziaGestionAlmacenBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftPubWA
{
    public partial class mostrar_almacen : System.Web.UI.Page
    {
        private string idAlmacen;
        private bool esta_modificando; // Falta implementar
        private AlmacenBO almacenBO;
        private ProductoAlmacenadoBO productoBO;
        private ProductoBO prodBO;
        private almacen almacen;
        private BindingList<productoAlmacenado> productos; // Falta implementar.
        private BindingList<producto> listaProductos;
        public mostrar_almacen()
        {
            this.almacenBO = new AlmacenBO();
            this.productoBO = new ProductoAlmacenadoBO();
            this.prodBO = new ProductoBO();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            this.cargarProductos();
            dgvProductos.DataSource = productos;
            dgvProductos.DataBind();
        }

        protected void Page_Init(object sender, EventArgs e)
        {
            this.idAlmacen = (string)Session["idAlmacen"];
            this.cargarAlmacen();
        }

        protected void cargarAlmacen()
        {

            almacen almacen = this.almacenBO.obtenerPorId(idAlmacen); // Falta implementar
            txtTitulo.Text = almacen.nombre.ToString();
            txtEstadoAlmacen.Text = almacen.estado.ToString();
            txtDescripcionAlmacen.Text = almacen.descripcion.ToString();
            if (txtEstadoAlmacen.Text.ToLower() == "operativo")
            {
                txtEstadoAlmacen.CssClass += "badge rounded-pill bg-success";
            }
            else if (txtEstadoAlmacen.Text.ToLower() == "inoperativo")
            {
                txtEstadoAlmacen.CssClass += "badge rounded-pill bg-danger";
            }

        }

        protected void cargarProductos()
        {
            productos = this.productoBO.listarPorAlmacen(this.idAlmacen);
        }

        protected void dgvProductos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvProductos.PageIndex = e.NewPageIndex;
            dgvProductos.DataBind();
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {

        }

        protected void ModalProducto_lbSeleccionar_Click(object sender, EventArgs e)
        {
            LinkButton btnSeleccionar = (LinkButton)sender;
            GridViewRow row = (GridViewRow)btnSeleccionar.NamingContainer;

            // Encuentra el TextBox dentro de la fila actual
            TextBox txtStockProducto = (TextBox)row.FindControl("ModalProducto_txtStockProducto");

            if (txtStockProducto != null)
            {
                // Ahora puedes acceder al valor del TextBox
                string stock = txtStockProducto.Text;


                int idProductoSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
                producto prod = this.prodBO.obtenerPorId(idProductoSeleccionado);
                // Ahora se inserta en la base de datos.
                this.productoBO.insertar(Int32.Parse(idAlmacen), DateTime.Now, Int32.Parse(txtStockProducto.Text), prod.idProducto);
                txtStockProducto.Text = "";
                dgvProductos.DataSource = productos;
                dgvProductos.DataBind();
                ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('', '');", true);
            }

            ScriptManager.RegisterStartupScript(this, GetType(), "OpenModalScript", "showModalFormProducto();", true); // Caso contrario, se mantiene abierto.
        }

        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormProducto() };"; // Se enlaza con el modal.
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
            
        }

        protected void ModalProducto_lbBuscarProducto_Click(object sender, EventArgs e)
        {
            string nombre = ModalProducto_txtNombreProducto.Text;
            this.listaProductos = this.prodBO.buscarPorNombre(nombre);
            ModalProducto_gvProducto.DataSource = this.listaProductos;
            ModalProducto_gvProducto.DataBind();
            ScriptManager.RegisterStartupScript(this, GetType(), "OpenModalScript", "showModalFormProducto();", true); // Para que se mantenga abierto
        }

        protected void ModalProducto_gvProducto_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            ModalProducto_gvProducto.PageIndex = e.NewPageIndex;
            ModalProducto_gvProducto.DataBind();
        }
    }
}