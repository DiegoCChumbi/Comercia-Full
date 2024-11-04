using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


namespace SoftPubWA
{
    public partial class gestionar_produccion : System.Web.UI.Page
    {/*
        private GrupoBo grupoBO;
        private LineaBo lineaBO;
        private PublicacionBo publicacionBO;
        private ProductosLineaBo productosLineaBO;
        
        BindingList<Object> listaGrupos;
        BindingList<Object> listaLineas;
        BindingList<Object> listaPublicaciones;
        BindingList<ProductosLinea> listaProductos;

        public gestionar_produccion()
        {
            this.grupoBO = new GrupoBo();
            this.lineaBO = new LineaBo();
            this.publicacionBO = new PublicacionBo();
            this.productosLineaBO = new ProductosLineaBo();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["listaProductos"] == null)
                this.listaProductos = new BindingList<ProductosLinea>();           
            else
                this.listaProductos = (BindingList<ProductosLinea>)Session["listaProductos"];            
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("mantenimiento_grupo.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            this.productosLineaBO.insertarEnLote(this.listaProductos);
            Session["listaProductos"] = null;
            Response.Redirect("mantenimiento_grupo.aspx");
        }

        protected void btnBuscarGrupo_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormGrupo() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void btnBuscarLinea_Click(object sender, EventArgs e)
        {
            int? idGrupoSeleccionado = (int?)Session["idGrupoSeleccionado"];
            this.listaLineas = this.lineaBO.listarPorGrupo(idGrupoSeleccionado);
            ModalLinea_gvLineas.DataSource = this.listaLineas;
            ModalLinea_gvLineas.DataBind();

            string script = "window.onload = function() { showModalFormLinea() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void btnBuscarPublicacion_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormPublicacion() };";
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }
                
        protected void lbAgregarPublicacion_Click(object sender, EventArgs e)
        {
            int idGrupoSeleccionado = (int)Session["idGrupoSeleccionado"];
            int idLineaSeleccionado = (int)Session["idLineaSeleccionado"];
            int idPublicacionSeleccionada = (int)Session["idPublicacionSeleccionada"];

            Grupo grupo = new Grupo();
            grupo.IdGrupo = idGrupoSeleccionado;

            Linea linea = new Linea();
            linea.IdLinea = idLineaSeleccionado;
            linea.Grupo = grupo;

            Publicacion publicacion = new Publicacion();
            publicacion.IdPublicacion = idPublicacionSeleccionada;
            publicacion.Titulo = txtTituloPublicacion.Text;

            ProductosLinea productosLinea = new ProductosLinea();
            productosLinea.Publicacion = publicacion;
            productosLinea.Linea = linea;

            listaProductos.Add(productosLinea);
            Session["listaProductos"] = listaProductos;
            gvPublicaciones.DataSource = listaProductos;
            gvPublicaciones.DataBind();

            txtIdPublicacion.Text = "";
            txtTituloPublicacion.Text = "";
        }

        protected void ModalGrupo_lbBuscarGrupo_Click(object sender, EventArgs e)
        {
            string nombre = ModalGrupo_txtNombreGrupo.Text;
            this.listaGrupos = this.grupoBO.buscarGrupos(nombre);
            ModalGrupo_gvClientes.DataSource = this.listaGrupos;
            ModalGrupo_gvClientes.DataBind();
        }

        protected void ModalGrupo_gvClientes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            ModalGrupo_gvClientes.PageIndex = e.NewPageIndex;
            ModalGrupo_gvClientes.DataBind();
        }

        protected void ModalGrupo_lbSeleccionar_Click(object sender, EventArgs e)
        {
            int idGrupoSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
            Session["idGrupoSeleccionado"] = idGrupoSeleccionado;
            Grupo grupo = this.grupoBO.obtenerPorId(idGrupoSeleccionado);
            txtIdGrupo.Text = grupo.IdGrupo.ToString();
            txtNombreGrupo.Text = grupo.Nombre;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void ModalLinea_lbSeleccionar_Click(object sender, EventArgs e)
        {
            int idLineaSeleccionado = Int32.Parse(((LinkButton)sender).CommandArgument);
            int idGrupoSeleccionado = (int) Session["idGrupoSeleccionado"];
            Session["idLineaSeleccionado"] = idLineaSeleccionado;
            Linea linea = this.lineaBO.obtenerPorId(idGrupoSeleccionado, idLineaSeleccionado);
            txtIdLinea.Text = linea.IdLinea.ToString();
            txtNombreLinea.Text = linea.Nombre;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void ModalLinea_gvLineas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            ModalLinea_gvLineas.PageIndex = e.NewPageIndex;
            ModalLinea_gvLineas.DataBind();
        }      

        protected void ModalPublicacion_lbBuscarPublicacion_Click(object sender, EventArgs e)
        {
            string titulo = ModalPublicacion_txtTitulo.Text;
            this.listaPublicaciones = this.publicacionBO.buscarPublicacion(titulo);
            ModalPublicacion_gvPublicacion.DataSource = this.listaPublicaciones;
            ModalPublicacion_gvPublicacion.DataBind();
        }

        protected void ModalPublicacion_gvPublicacion_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            ModalPublicacion_gvPublicacion.PageIndex = e.NewPageIndex;
            ModalPublicacion_gvPublicacion.DataBind();
        }

        protected void ModalPublicacion_lbSeleccionar_Click(object sender, EventArgs e)
        {
            int idPublicacionSeleccionada = Int32.Parse(((LinkButton)sender).CommandArgument);
            Session["idPublicacionSeleccionada"] = idPublicacionSeleccionada;
            Publicacion publicacion = this.publicacionBO.obtenerPorId(idPublicacionSeleccionada);
            txtIdPublicacion.Text = publicacion.IdPublicacion.ToString();
            txtTituloPublicacion.Text = publicacion.Titulo;            
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void lbEliminarPublicacion_Click(object sender, EventArgs e)
        {
            int idPublicacion = Int32.Parse(((LinkButton)sender).CommandArgument);
            foreach(ProductosLinea productosLinea in this.listaProductos)
            {
                if (productosLinea.Publicacion.IdPublicacion == idPublicacion)
                {
                    this.listaProductos.Remove(productosLinea);
                    break;
                }
            }
            gvPublicaciones.DataSource = listaProductos;
            gvPublicaciones.DataBind();
        }*/
    }
}