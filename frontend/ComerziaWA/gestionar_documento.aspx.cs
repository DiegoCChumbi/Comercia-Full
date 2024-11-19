using System;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaGestionComercialBO;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class gestionar_documento : System.Web.UI.Page
    {
        private ProductoBO productoBO;
        private ProductoAlmacenadoBO productoAlmacenadoBO;
        private BindingList<producto> listaProductos;
        private BindingList<lineaDocumento> productosSeleccionados;
        private DocumentoBO documentoBO;
        private VendedorBO vendedorBO;
        private TrabajadorDeAlmacenBO trabajadorDeAlmacenBO;
        private LineaDocumentoBO lineaDocumentoBO;
        private VendedorBO daoVendedor;
        private BindingList<vendedor> vendedoresCandidatos;
        private bool esta_viendo = false;
        private string idDocumento;

        public gestionar_documento()
        {
            this.productoBO = new ProductoBO();
            this.productoAlmacenadoBO = new ProductoAlmacenadoBO();
            this.documentoBO = new DocumentoBO();
            this.vendedorBO = new VendedorBO();
            this.trabajadorDeAlmacenBO = new TrabajadorDeAlmacenBO();
            this.lineaDocumentoBO = new LineaDocumentoBO();
            this.daoVendedor = new VendedorBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            this.idDocumento = (string)Session["idDocumento"];
            string accion = Request.QueryString["accion"];
            if (!IsPostBack)
            {
                switch (accion)
                {
                    case "ver":
                        esta_viendo = true;
                        lbGuardar.Text = "Editar";
                        this.cargarDatosDeLaBD();
                        this.deshabilitarTodo();
                        break;
                    case "modificar":
                        lbGuardar.Text = "Guardar";
                        this.cargarDatosDeLaBD();
                        break;
                    case "insertar":
                        lbGuardar.Text = "Guardar";
                        break;
                }
            }
            if (Session["productosSeleccionados"] == null)
                productosSeleccionados = new BindingList<lineaDocumento>();
            else
            {
                productosSeleccionados = new BindingList<lineaDocumento>(
                    ((BindingList<lineaDocumento>)Session["productosSeleccionados"]).ToList()
                );
            }
        }

        private void deshabilitarTodo()
        {
            rbSolicitud.Disabled = true;
            rbCotizacion.Disabled = true;
            rbAnulado.Disabled = true;
            rbPagado.Disabled = true;
            rbCompra.Disabled = true;
            rbVenta.Disabled = true;
            rbFactura.Disabled = true;
            btnBuscarVendedor.Enabled = false;
            btnBuscarTrabajador.Enabled = false;
            btnAgregarProducto.Enabled = false;
        }

        private void cargarDatosDeLaBD()
        {
            documento _documento = this.documentoBO.obtenerPorId(this.idDocumento);
            vendedor _vendedor = vendedorBO.obtenerPorId(_documento.idVendedor);
            trabajadorDeAlmacen _trabajador = trabajadorDeAlmacenBO.obtenerPorId(
                _documento.idTrabajadorDeAlmacen
            );
            if (_vendedor != null)
                txtVendedor.Text = _vendedor.nombre;
            if (_trabajador != null)
                txtTrabajador.Text = _trabajador.nombre;
            rbAnulado.Disabled = false;
            rbPagado.Disabled = false;
            switch (_documento.estado.ToString())
            {
                case "SOLICITUD":
                    rbSolicitud.Checked = true;
                    break;
                case "COTIZACION":
                    rbCotizacion.Checked = true;
                    break;
                case "ANULADO":
                    rbAnulado.Checked = true;
                    break;
                case "PAGADO":
                    rbPagado.Checked = true;
                    break;
            }
            switch (_documento.tipo.ToString())
            {
                case "COMPRA":
                    rbCompra.Checked = true;
                    break;
                case "VENTA":
                    rbVenta.Checked = true;
                    break;
                case "FACTURA":
                    rbFactura.Checked = true;
                    break;
            }

            BindingList<lineaDocumento> productosSeleccionados_copia =
                new BindingList<lineaDocumento>();
            productosSeleccionados_copia = lineaDocumentoBO.listarPorDocumento(
                int.Parse(this.idDocumento)
            );
            Session["productosSeleccionados"] = productosSeleccionados_copia;
            dgvProductos.DataSource = productosSeleccionados_copia;
            dgvProductos.DataBind();
        }

        protected void lbRegresar_Click(object sender, EventArgs e)
        {
            Session["productosSeleccionados"] = null;
            Response.Redirect("GestionComercial.aspx");
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            Session["productosSeleccionados"] = null;
            string accion = Request.QueryString["accion"];
            switch (accion)
            {
                case "ver":
                    Response.Redirect("gestionar_documento.aspx?accion=modificar");
                    break;
                case "modificar":
                    //recuperar todo y modificar
                    break;
                case "insertar":
                    //recuperar todo e insertar
                    documento _documento = new documento();
                    if (rbSolicitud.Checked == true)
                        _documento.estado = (estadoDocumentoEnum)
                            Enum.Parse(typeof(estadoDocumentoEnum), "SOLICITUD");
                    else if (rbCotizacion.Checked == true)
                        _documento.estado = (estadoDocumentoEnum)
                            Enum.Parse(typeof(estadoDocumentoEnum), "COTIZACION");
                    else if (rbAnulado.Checked == true)
                        _documento.estado = (estadoDocumentoEnum)
                            Enum.Parse(typeof(estadoDocumentoEnum), "ANULADO");
                    else
                        _documento.estado = (estadoDocumentoEnum)
                            Enum.Parse(typeof(estadoDocumentoEnum), "PAGADO");

                    if (rbCompra.Checked == true)
                        _documento.estado = (estadoDocumentoEnum)
                            Enum.Parse(typeof(estadoDocumentoEnum), "COMPRA");
                    else if (rbVenta.Checked == true)
                        _documento.estado = (estadoDocumentoEnum)
                            Enum.Parse(typeof(estadoDocumentoEnum), "VENTA");
                    else
                        _documento.estado = (estadoDocumentoEnum)
                            Enum.Parse(typeof(estadoDocumentoEnum), "FACTURA");

                    //if (txtVendedor.Text != null) _documento.idVendedor = ;
                    //if (txtTrabajador.Text != null) _documento.idVendedor =  ;
                    break;
            }
            Response.Redirect("GestionComercial.aspx");
        }

        protected void btnBuscarVendedor_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormVendedorInsertar() };"; // Se enlaza con el modal.
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void ModalVendedor_lbBuscarVendedor_Click(object sender, EventArgs e)
        {
            string nombre = ModalVendedor_txtVendedorNombre.Text;
            //this.vendedoresCandidatos = this.daoVendedor.listarPorNombre(nombre);
            ModalVendedor_gvVendedor.DataSource = this.vendedoresCandidatos;
            ModalVendedor_gvVendedor.DataBind();
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormRepresentanteInsertar();",
                true
            ); // Para que se mantenga abierto
        }

        protected void ModalVendedor_gvVendedor_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalVendedor_gvVendedor.PageIndex = e.NewPageIndex;
            ModalVendedor_gvVendedor.DataBind();
        }

        protected void ModalVendedor_lbVendedorSeleccionar_Click(object sender, EventArgs e)
        {
            //string idPersona = (((LinkButton)sender).CommandArgument).ToString();
            //vendedor ven = this.daoVendedor.obtenerPorId(idPersona);
            //this.daoVendedor.modificar(Int32.Parse(idPersona), rep.dni, rep.nombreCompleto, rep.telefono,
            //    rep.correo, rep.direccion, Int32.Parse(this.idEmpresa));
            //ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('', '');", true);
        }

        protected void btnBuscarTrabajador_Click(object sender, EventArgs e) { }

        protected void BtnEliminarProducto_Click(object sender, EventArgs e)
        {
            // Obtener el índice de la fila seleccionada
            LinkButton btnEliminar = (LinkButton)sender;
            int index = Convert.ToInt32(btnEliminar.CommandArgument);

            // Eliminar el elemento de la lista
            productosSeleccionados.RemoveAt(index);
            Session["productosSeleccionados"] = productosSeleccionados;
            // Volver a enlazar los datos al GridView después de la eliminación
            dgvProductos.DataSource = productosSeleccionados;
            dgvProductos.DataBind();
        }

        protected void ModalProducto_gvProducto_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalProducto_gvProducto.PageIndex = e.NewPageIndex;
            ModalProducto_gvProducto.DataBind();
        }

        protected void dgvProductos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvProductos.PageIndex = e.NewPageIndex;
            dgvProductos.DataBind();
        }

        protected void dgvProductos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                LinkButton btnEliminar = (LinkButton)(e.Row.FindControl("btnEliminar"));
                if (btnEliminar != null)
                {
                    if (esta_viendo)
                        btnEliminar.Visible = true;
                    else
                    {
                        btnEliminar.Visible = true;
                    }
                }
            }
        }

        protected void ModalProducto_lbSeleccionar_Click(object sender, EventArgs e)
        {
            LinkButton btnSeleccionar = (LinkButton)sender;
            GridViewRow row = (GridViewRow)btnSeleccionar.NamingContainer;

            // Encuentra el TextBox dentro de la fila actual
            TextBox txtCantidadProducto = (TextBox)
                row.FindControl("ModalProducto_txtCantidadProducto");
            TextBox txtPrecioProducto = (TextBox)row.FindControl("ModalProducto_txtPrecioProducto");

            if (
                txtCantidadProducto != null
                && txtCantidadProducto.Text.Trim() != ""
                && txtPrecioProducto != null
                && txtPrecioProducto.Text.Trim() != ""
            )
            {
                // Ahora puedes acceder al valor del TextBox
                // Intenta convertir el texto a un entero
                // Prueba si es posible realizar la conversion
                if (
                    int.TryParse(txtCantidadProducto.Text, out int cantidad)
                    && Double.TryParse(txtPrecioProducto.Text, out Double precioUnitario)
                )
                {
                    int idProductoSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
                    lineaDocumento prod = new lineaDocumento();
                    if (this.idDocumento != null)
                        prod.idDocumento = int.Parse(this.idDocumento);
                    prod.idProducto = idProductoSeleccionado;
                    prod.cantidad = cantidad;
                    prod.precioUnitario = precioUnitario;

                    // Ahora se inserta en la base de datos
                    //this.productoAlmacenadoBO.insertar(Int32.Parse(idAlmacen), DateTime.Now, stock, prod.idProducto);

                    // Limpia el TextBox y actualiza el GridView
                    txtCantidadProducto.Text = "";
                    txtPrecioProducto.Text = "";
                    productosSeleccionados.Add(prod);
                    Session["productosSeleccionados"] = productosSeleccionados;
                    dgvProductos.DataSource = productosSeleccionados;
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
                        "alert('Por favor, ingrese un número válido en el campo de cantidad o precio.');",
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
            this.listaProductos = this.productoBO.buscarPorNombre(nombre);
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

        protected void BtnVerProducto_Click(object sender, EventArgs e)
        {
            int idProductoSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
            producto productoAMostrar = this.productoBO.obtenerPorId(idProductoSeleccionado);

            // Asignar los valores
            txtTituloProd.Text = productoAMostrar.nombre.ToString();
            txtPrecioProd.Text = productoAMostrar.precio.ToString();
            txtStockMinimoProd.Text = productoAMostrar.stockMinimo.ToString();

            // Mostrar el modal usando ScriptManager en lugar de ClientScript
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormDatosProducto();",
                true
            );
        }
    }
}
