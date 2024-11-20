using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaGestionComercialBO;
using ComerziaRecursosHumanosBO;
using ComerziaRelacionesComercialesBO;
using System;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ComerziaWA
{
    public partial class gestionar_documento : System.Web.UI.Page
    {
        private ProductoBO productoBO;
        private ProductoAlmacenadoBO productoAlmacenadoBO;
        private BindingList<producto> listaProductos;
        private BindingList<lineaDocumento> productosSeleccionados;
        private BindingList<int> idsLineasEliminar;
        private BindingList<lineaDocumento> productosSeleccionados_modificar;
        private DocumentoBO documentoBO;
        private VendedorBO vendedorBO;
        private TrabajadorDeAlmacenBO trabajadorDeAlmacenBO;
        private LineaDocumentoBO lineaDocumentoBO;
        private EmpresaBO empresaBO;
        private BindingList<vendedor> vendedoresCandidatos;
        private BindingList<trabajadorDeAlmacen> trabajadoresCandidatos;
        private BindingList<administrador> administradoresCandidatos;
        private BindingList<empresa> empresasCandidatas;
        private AdministradorBO administradorBO;
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
            this.empresaBO = new EmpresaBO();
            this.administradorBO = new AdministradorBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            this.idDocumento = (string)Session["idDocumento"];
            string accion = Request.QueryString["accion"];
            Debug.WriteLine("Cargando datos del documento con ID: " + this.idDocumento);

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
                        btnBuscarEmpresa.Enabled = false;
                        break;
                    case "insertar":
                        lbGuardar.Text = "Guardar";
                        break;
                }
            }
            if (Session["idsLineasEliminar"] == null)
                idsLineasEliminar = new BindingList<int>();
            else
                idsLineasEliminar = new BindingList<int>(
                    ((BindingList<int>)Session["idsLineasEliminar"]).ToList()
                );

            if (Session["productosSeleccionados_modificar"] == null)
                productosSeleccionados_modificar = new BindingList<lineaDocumento>();
            else
                productosSeleccionados_modificar = new BindingList<lineaDocumento>(
                    (
                        (BindingList<lineaDocumento>)Session["productosSeleccionados_modificar"]
                    ).ToList()
                );

            if (Session["productosSeleccionados"] == null)
                productosSeleccionados = new BindingList<lineaDocumento>();
            else
                productosSeleccionados = new BindingList<lineaDocumento>(
                    ((BindingList<lineaDocumento>)Session["productosSeleccionados"]).ToList()
                );

            if (Session["id"] == null)
                Session["id"] = 0;
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
            btnBuscarAdministrador.Enabled = false;
            btnAgregarProducto.Enabled = false;
            btnBuscarEmpresa.Enabled = false;
        }

        private void cargarDatosDeLaBD()
        {
            documento _documento = this.documentoBO.obtenerPorId(this.idDocumento);
            vendedor _vendedor = this.vendedorBO.obtenerPorId(_documento.idVendedor);
            Session["idVendedor"] = _documento.idVendedor;
            empresa _empresa = this.empresaBO.obtenerPorId(_documento.idEmpresa.ToString());
            Session["idEmpresa"] = _documento.idEmpresa;
            trabajadorDeAlmacen _trabajador = trabajadorDeAlmacenBO.obtenerPorId(
                _documento.idTrabajadorDeAlmacen
            );
            Session["idTrabajadorDeAlmacen"] = _documento.idTrabajadorDeAlmacen;
            administrador _administrador = this.administradorBO.obtenerPorId(
                _documento.idAdministrador
            );
            Session["idAdministrador"] = _documento.idAdministrador;
            txtEmpresa.Text = _empresa.nombre;
            if (_vendedor != null)
                txtVendedor.Text = _vendedor.nombre;
            if (_trabajador != null)
                txtTrabajador.Text = _trabajador.nombre;
            if (_administrador != null)
                txtAdministrador.Text = _administrador.nombre;
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
            Session["idsLineasEliminar"] = null;
            Session["productosSeleccionados_modificar"] = null;
            Session["id"] = 0;
            Response.Redirect("GestionComercial.aspx");
        }

        protected void lbGuardar_Click(object sender, EventArgs e)
        {
            string accion = Request.QueryString["accion"];
            Debug.WriteLine(accion);
            if (accion == "ver")
                Response.Redirect("gestionar_documento.aspx?accion=modificar");
            else
            {
                int idDocumentoInsertado;
                /*------ESTADO------*/
                estadoDocumentoEnum _estado = new estadoDocumentoEnum();
                if (rbSolicitud.Checked == true)
                    _estado = estadoDocumentoEnum.SOLICITUD;
                else if (rbCotizacion.Checked == true)
                    _estado = estadoDocumentoEnum.COTIZACION;
                else if (rbAnulado.Checked == true)
                    _estado = estadoDocumentoEnum.ANULADO;
                else
                    _estado = estadoDocumentoEnum.PAGADO;
                /*------TIPO------*/
                tipoDocumentoEnum _tipo = new tipoDocumentoEnum();
                if (rbCompra.Checked == true)
                    _tipo = tipoDocumentoEnum.COMPRA;
                else if (rbVenta.Checked == true)
                    _tipo = tipoDocumentoEnum.VENTA;
                else
                    _tipo = tipoDocumentoEnum.FACTURA;

                if (accion == "modificar")
                {
                    Debug.WriteLine(idDocumento);
                    Debug.WriteLine(Session["idEmpresa"].ToString());
                    Debug.WriteLine(Session["idVendedor"].ToString());
                    documentoBO.modificar(
                        int.Parse(idDocumento),
                        (int)Session["idEmpresa"],
                        _estado,
                        _tipo,
                        (int)Session["idVendedor"],
                        (int)Session["idAdministrador"],
                        (int)Session["idTrabajadorDeAlmacen"]
                    );

                    /*------ELIMINAR LINEA_DOCUMENTO------*/
                    foreach (int idLineaEliminar in idsLineasEliminar)
                    {
                        lineaDocumentoBO.eliminar(idLineaEliminar);
                    }
                    /*------INSERTAR LINEA_DOCUMENTO------*/
                    foreach (lineaDocumento linea in productosSeleccionados_modificar)
                    {
                        lineaDocumentoBO.insertar(
                            int.Parse(idDocumento),
                            linea.idProducto,
                            linea.cantidad,
                            linea.precioUnitario
                        );
                    }
                }
                else
                {
                    idDocumentoInsertado = documentoBO.insertar(
                        (int)Session["idEmpresa"],
                        _estado,
                        _tipo,
                        (int)Session["idVendedor"],
                        (int)Session["idAdministrador"],
                        (int)Session["idTrabajadorDeAlmacen"]
                    );
                    Debug.WriteLine(idDocumentoInsertado.ToString());
                    Debug.WriteLine(_estado.ToString());
                    /*------LINEA_DOCUMENTO------*/
                    foreach (lineaDocumento linea in productosSeleccionados)
                    {
                        lineaDocumentoBO.insertar(
                            idDocumentoInsertado,
                            linea.idProducto,
                            linea.cantidad,
                            linea.precioUnitario
                        );
                    }
                }
            }
            Session["productosSeleccionados"] = null;
            Session["idsLineasEliminar"] = null;
            Session["productosSeleccionados_modificar"] = null;
            Session["id"] = 0;
            Response.Redirect("GestionComercial.aspx");
        }

        /*------PRODUCTOS------*/

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
                    // Creo la linea del documento
                    int idProductoSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
                    lineaDocumento _lineaDocumento = new lineaDocumento();

                    _lineaDocumento.idProducto = idProductoSeleccionado;
                    _lineaDocumento.cantidad = cantidad;
                    _lineaDocumento.precioUnitario = precioUnitario;
                    if (this.idDocumento != null) //en caso este modificando, lo diferencio de los ya existentes colocandole un id negativo
                    {
                        int idModificar = (int)Session["id"];
                        idModificar--;
                        Session["id"] = idModificar;
                        _lineaDocumento.id = idModificar;
                        productosSeleccionados_modificar.Add(_lineaDocumento);
                        Session["productosSeleccionados_modificar"] =
                            productosSeleccionados_modificar;
                    }

                    // Limpia el TextBox y actualiza el GridView
                    txtCantidadProducto.Text = "";
                    txtPrecioProducto.Text = "";
                    productosSeleccionados.Add(_lineaDocumento);
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

        protected void BtnEliminarProducto_Click(object sender, EventArgs e)
        {
            // Obtener el índice de la fila seleccionada
            LinkButton btnEliminar = (LinkButton)sender;
            //int index = Convert.ToInt32(btnEliminar.CommandArgument);

            // Obtener los parámetros desde CommandArgument
            string[] argumentos = btnEliminar.CommandArgument.ToString().Split(',');

            // Convertir los parámetros según su tipo
            int index = int.Parse(argumentos[0]);
            int idEliminar = int.Parse(argumentos[1]);

            // Eliminar el elemento de la lista
            productosSeleccionados.RemoveAt(index);
            Session["productosSeleccionados"] = productosSeleccionados;
            if (this.idDocumento != null) // en caso este modificando
            {
                if (idEliminar > 0) // si es un producto que ya estaba registrado en la BD
                {
                    idsLineasEliminar.Add(idEliminar);
                    Session["idsLineasEliminar"] = idsLineasEliminar;
                }
                else
                {
                    int pos = buscarEnProductosSeleccionados_modificar(idEliminar);
                    productosSeleccionados_modificar.RemoveAt(pos);
                    Session["productosSeleccionados_modificar"] = productosSeleccionados_modificar;
                }
            }
            // Volver a enlazar los datos al GridView después de la eliminación
            dgvProductos.DataSource = productosSeleccionados;
            dgvProductos.DataBind();
        }

        int buscarEnProductosSeleccionados_modificar(int idEliminar)
        {
            for (int i = 0; i < productosSeleccionados_modificar.Count; i++)
            {
                lineaDocumento linea = productosSeleccionados_modificar.ElementAt(i);
                if (linea.id == idEliminar)
                {
                    return i;
                }
            }
            return -1;
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
                        btnEliminar.Visible = false;
                    else
                    {
                        btnEliminar.Visible = true;
                    }
                }
            }
        }

        /*------VENDEDOR------*/
        protected void btnBuscarVendedor_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormVendedorInsertar() };"; // Se enlaza con el modal.
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void ModalVendedor_lbBuscarVendedor_Click(object sender, EventArgs e)
        {
            string nombre = ModalVendedor_txtVendedorNombre.Text;
            this.vendedoresCandidatos = this.vendedorBO.listarPorNombre(nombre);
            ModalVendedor_gvVendedor.DataSource = this.vendedoresCandidatos;
            ModalVendedor_gvVendedor.DataBind();
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormVendedorInsertar();",
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
            string idVendedor = (((LinkButton)sender).CommandArgument).ToString();
            vendedor _vendedor = this.vendedorBO.obtenerPorId(int.Parse(idVendedor));
            Session["idVendedor"] = _vendedor.id;
            txtVendedor.Text = _vendedor.nombre;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('', '');", true);
        }

        /*------TRABAJADOR------*/
        protected void btnBuscarTrabajador_Click(object sender, EventArgs e)
        {
            string script =
                "window.onload = function() { showModalFormTrabajadorDeAlmacenInsertar() };"; // Se enlaza con el modal.
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void ModalTrabajador_lbBuscarTrabajador_Click(object sender, EventArgs e)
        {
            string nombre = ModalTrabajador_txtTrabajadorNombre.Text;
            this.trabajadoresCandidatos = this.trabajadorDeAlmacenBO.listarPorNombre(nombre);
            ModalTrabajador_gvTrabajador.DataSource = this.trabajadoresCandidatos;
            ModalTrabajador_gvTrabajador.DataBind();
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormTrabajadorDeAlmacenInsertar();",
                true
            ); // Para que se mantenga abierto
        }

        protected void ModalTrabajador_gvTrabajador_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalTrabajador_gvTrabajador.PageIndex = e.NewPageIndex;
            ModalTrabajador_gvTrabajador.DataBind();
        }

        protected void ModalTrabajador_lbTrabajadorSeleccionar_Click(object sender, EventArgs e)
        {
            string idTrabajador = (((LinkButton)sender).CommandArgument).ToString();
            trabajadorDeAlmacen _trabajador = this.trabajadorDeAlmacenBO.obtenerPorId(
                int.Parse(idTrabajador)
            );
            Session["idTrabajadorDeAlmacen"] = _trabajador.id;
            txtTrabajador.Text = _trabajador.nombre;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('', '');", true);
        }

        /*------EMPRESA------*/
        protected void btnBuscarEmpresa_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormEmpresaInsertar() };"; // Se enlaza con el modal.
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void ModalEmpresa_lbBuscarEmpresa_Click(object sender, EventArgs e)
        {
            string nombre = ModalEmpresa_txtEmpresaNombre.Text;
            this.empresasCandidatas = this.empresaBO.listarPorNombre(nombre);
            ModalEmpresa_gvEmpresa.DataSource = this.empresasCandidatas;
            ModalEmpresa_gvEmpresa.DataBind();
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormEmpresaInsertar();",
                true
            ); // Para que se mantenga abierto
        }

        protected void ModalEmpresa_gvEmpresa_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalEmpresa_gvEmpresa.PageIndex = e.NewPageIndex;
            ModalEmpresa_gvEmpresa.DataBind();
        }

        protected void ModalEmpresa_lbEmpresaSeleccionar_Click(object sender, EventArgs e)
        {
            string idEmpresa = (((LinkButton)sender).CommandArgument).ToString();
            empresa _empresa = this.empresaBO.obtenerPorId(idEmpresa);
            Session["idEmpresa"] = int.Parse(idEmpresa);
            txtEmpresa.Text = _empresa.nombre;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('', '');", true);
        }

        /*------ADMINISTRADOR------*/
        protected void btnBuscarAdministrador_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormAdministradorInsertar() };"; // Se enlaza con el modal.
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void ModalAdministrador_lbBuscarAdministrador_Click(object sender, EventArgs e)
        {
            string nombre = ModalAdministrador_txtAdministradorNombre.Text;
            this.administradoresCandidatos = this.administradorBO.listarPorNombre(nombre);
            ModalAdministrador_gvAdministrador.DataSource = this.administradoresCandidatos;
            ModalAdministrador_gvAdministrador.DataBind();
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormAdministradorInsertar();",
                true
            ); // Para que se mantenga abierto
        }

        protected void ModalAdministrador_gvAdministrador_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalAdministrador_gvAdministrador.PageIndex = e.NewPageIndex;
            ModalAdministrador_gvAdministrador.DataBind();
        }

        protected void ModalAdministrador_lbAdministradorSeleccionar_Click(
            object sender,
            EventArgs e
        )
        {
            string idAdministrador = (((LinkButton)sender).CommandArgument).ToString();
            administrador _administrador = this.administradorBO.obtenerPorId(
                int.Parse(idAdministrador)
            );
            Session["idAdministrador"] = _administrador.id;
            txtAdministrador.Text = _administrador.nombre;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('', '');", true);
        }
    }
}
