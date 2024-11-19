using System;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;

namespace ComerziaWA
{
    public partial class mostrar_almacen : System.Web.UI.Page
    {
        private string idAlmacen;
        private static bool esta_modificando = false; // Falta implementar
        private AlmacenBO almacenBO;
        private ProductoAlmacenadoBO productoBO;
        private ProductoBO prodBO;
        private almacen almacen;
        private BindingList<productoAlmacenado> productos; // Falta implementar.
        private BindingList<producto> listaProductos;
        private producto productoAMostrar;

        public mostrar_almacen()
        {
            this.almacenBO = new AlmacenBO();
            this.productoBO = new ProductoAlmacenadoBO();
            this.prodBO = new ProductoBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            this.cargarProductos();
            this.botonEditar();
            if (Session["DescripcionAlmacen"] != null)
                txtDescripcionAlmacen.Text = Session["DescripcionAlmacen"].ToString();
            if (Session["EstadoAlmacen"] != null)
                txtEstadoAlmacen.Text = Session["EstadoAlmacen"].ToString();
        }

        protected void botonEditar()
        {
            if (!esta_modificando)
            {
                btnModificar.Text = "Editar";
                txtEstadoAlmacen.Enabled = false;
                txtDescripcionAlmacen.Enabled = false;
            }
            else
            { // Tendrá un diferente tipo de evento.
                btnModificar.Text = "Terminar";
                txtEstadoAlmacen.Enabled = true;
                txtDescripcionAlmacen.Enabled = true;
            }
        }

        protected void Page_Init(object sender, EventArgs e)
        {
            this.idAlmacen = (string)Session["idAlmacen"];
            this.cargarAlmacen();
        }

        protected void cargarAlmacen()
        {
            this.almacen = this.almacenBO.obtenerPorId(idAlmacen);
            txtTitulo.Text = almacen.nombre.ToString();
            txtEstadoAlmacen.Text = almacen.estado.ToString();
            txtDescripcionAlmacen.Text = almacen.descripcion.ToString();
            if (txtEstadoAlmacen.Text.ToLower() == "operativo")
            {
                txtEstadoAlmacen.CssClass += "badge rounded-pill bg-primary";
            }
            else if (txtEstadoAlmacen.Text.ToLower() == "inoperativo")
            {
                txtEstadoAlmacen.CssClass += "badge rounded-pill bg-danger";
            }
        }

        protected void cargarProductos()
        {
            productos = this.productoBO.listarPorAlmacen(this.idAlmacen);
            dgvProductos.DataSource = productos;
            dgvProductos.DataBind();
        }

        protected void dgvProductos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvProductos.PageIndex = e.NewPageIndex;
            dgvProductos.DataBind();
        }

        protected void BtnEliminarProducto_Click(object sender, EventArgs e)
        {
            int idProductoAlmacenadoSeleccionado = Int32.Parse(
                ((LinkButton)sender).CommandArgument
            );
            if (this.productoBO.eliminarPorId(idProductoAlmacenadoSeleccionado) != 0)
            {
                // Si no es 0, significa que hubo una actualización.
                productos = this.productoBO.listarPorAlmacen(this.idAlmacen);
                dgvProductos.DataSource = productos;
                dgvProductos.DataBind();
                // Esto para que se conserven los cambios de la edición.
                Session["DescripcionAlmacen"] = txtDescripcionAlmacen.Text;
                Session["EstadoAlmacen"] = txtEstadoAlmacen.Text;
            }
            Response.Redirect("mostrar_almacen.aspx"); // Para que se actualice.
        }

        protected void ModalProducto_lbSeleccionar_Click(object sender, EventArgs e)
        {
            LinkButton btnSeleccionar = (LinkButton)sender;
            GridViewRow row = (GridViewRow)btnSeleccionar.NamingContainer;

            // Encuentra el TextBox dentro de la fila actual
            TextBox txtStockProducto = (TextBox)row.FindControl("ModalProducto_txtStockProducto");

            if (txtStockProducto != null && txtStockProducto.Text.Trim() != "")
            {
                // Ahora puedes acceder al valor del TextBox
                // Intenta convertir el texto a un entero
                // Prueba si es posible realizar la conversion
                if (int.TryParse(txtStockProducto.Text, out int stock))
                {
                    int idProductoSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
                    producto prod = this.prodBO.obtenerPorId(idProductoSeleccionado);

                    // Ahora se inserta en la base de datos
                    this.productoBO.insertar(Int32.Parse(idAlmacen), DateTime.Now, stock, prod.id);

                    // Limpia el TextBox y actualiza el GridView
                    txtStockProducto.Text = "";
                    dgvProductos.DataSource = productos;
                    dgvProductos.DataBind();

                    // Realiza un postback
                    ScriptManager.RegisterStartupScript(
                        this,
                        GetType(),
                        "",
                        "__doPostBack('', '');",
                        true
                    );
                }
                else
                {
                    // Si el valor no es un número válido, muestra un mensaje de error
                    ScriptManager.RegisterStartupScript(
                        this,
                        GetType(),
                        "InvalidInputAlert",
                        "alert('Por favor, ingrese un número válido en el campo de stock.');",
                        true
                    );
                }
            }
            // Caso contrario, se carga de nuevo el modal.
            else
                ScriptManager.RegisterStartupScript(
                    this,
                    GetType(),
                    "InvalidInputAlert",
                    "alert('Por favor, ingrese un numero.');",
                    true
                );
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
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormProducto();",
                true
            ); // Para que se mantenga abierto
        }

        protected void ModalProducto_gvProducto_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalProducto_gvProducto.PageIndex = e.NewPageIndex;
            ModalProducto_gvProducto.DataBind();
        }

        protected void BtnVerProducto_Click(object sender, EventArgs e)
        {
            int idProductoSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
            this.productoAMostrar = this.prodBO.obtenerPorId(idProductoSeleccionado);

            // Asignar los valores
            txtTituloProd.Text = this.productoAMostrar.nombre.ToString();
            txtPrecioProd.Text = this.productoAMostrar.precio.ToString();
            txtStockMinimoProd.Text = this.productoAMostrar.stockMinimo.ToString();

            // Mostrar el modal usando ScriptManager en lugar de ClientScript
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormDatosProducto();",
                true
            );
        }

        protected void dgvProductos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                LinkButton btnEliminar = (LinkButton)(e.Row.FindControl("btnEliminar"));
                if (btnEliminar != null)
                {
                    if (esta_modificando)
                        btnEliminar.Visible = true;
                    else
                    {
                        btnEliminar.Visible = false;
                    }
                }
            }
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            if (!esta_modificando)
            {
                esta_modificando = true;
            }
            else
            { // Tendrá un diferente tipo de evento.
                string estadoAlmacen = txtEstadoAlmacen.Text;
                string descripcionAlmacen = txtDescripcionAlmacen.Text;
                this.almacenBO.modificar(
                    this.almacen.id,
                    this.almacen.nombre.ToString(),
                    estadoAlmacen,
                    descripcionAlmacen
                );
                esta_modificando = false;
            }
            // No se hará refresco hasta tener todos los por eliminar.
            Response.Redirect("mostrar_almacen.aspx"); // Se hace un refrezco de la página
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            if (esta_modificando)
            {
                esta_modificando = false;
                Response.Redirect("mostrar_almacen.aspx");
            }
            else
            {
                Response.Redirect("gestion_almacen.aspx");
            }
        }
    }
}
