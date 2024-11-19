using System;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaRelacionesComercialesBO;

namespace ComerziaWA
{
    public partial class mostrar_proveedor : System.Web.UI.Page
    {
        private string idEmpresa;
        private static bool esta_modificando = false;
        private proveedor prov;
        private ProveedorBO daoProveedor;
        private RepresentanteBO daoRepresentante;
        private DocumentoBO daoDocumento;
        private BindingList<representante> representantes;
        private BindingList<representante> representantesCandidatos;
        private BindingList<documento> documentos;

        public mostrar_proveedor()
        {
            this.daoProveedor = new ProveedorBO();
            this.daoRepresentante = new RepresentanteBO();
            this.daoDocumento = new DocumentoBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            this.cargarRepresentantes();
            this.cargarDocumentos();
            this.botonEditar();
            if (Session["Telefono"] != null)
                txtTelefono.Text = Session["Telefono"].ToString();
            if (Session["Direccion"] != null)
                txtDireccion.Text = Session["Direccion"].ToString();
            if (Session["Correo"] != null)
                txtEmail.Text = Session["Correo"].ToString();
        }

        protected void botonEditar()
        {
            if (!esta_modificando)
            {
                btnModificar.Text = "Editar";
                txtTelefono.Enabled = false;
                txtDireccion.Enabled = false;
                txtEmail.Enabled = false;
            }
            else
            { // Tendrá un diferente tipo de evento.
                btnModificar.Text = "Terminar";
                txtTelefono.Enabled = true;
                txtDireccion.Enabled = true;
                txtEmail.Enabled = true;
            }
        }

        protected void Page_Init(object sender, EventArgs e)
        {
            this.idEmpresa = (string)Session["idEmpresa"];
            this.cargarProveedor();
        }

        protected void cargarRepresentantes()
        {
            this.representantes = this.daoRepresentante.listarPorEmpresa(idEmpresa);
            dgvRepresentantes.DataSource = representantes;
            dgvRepresentantes.DataBind();
        }

        protected void cargarProveedor()
        {
            this.prov = this.daoProveedor.obtenerPorId(idEmpresa);
            txtTitulo.Text = prov.nombre.ToString();
            txtTelefono.Text = prov.telefono.ToString();
            txtDireccion.Text = prov.direccion.ToString();
            txtEmail.Text = prov.email.ToString();
        }

        protected void cargarDocumentos()
        {
            this.documentos = this.daoDocumento.listarPorEmpresa(idEmpresa);
            dgvDocumentos.DataSource = this.documentos;
            dgvDocumentos.DataBind();
        }

        protected void dgvRepresentantes_RowDataBound(object sender, GridViewRowEventArgs e)
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

        protected void btnAgregarRepresentante_Click(object sender, EventArgs e)
        {
            string script = "window.onload = function() { showModalFormRepresentanteInsertar() };"; // Se enlaza con el modal.
            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }

        protected void dgvRepresentantes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvRepresentantes.PageIndex = e.NewPageIndex;
            dgvRepresentantes.DataBind();
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            if (esta_modificando)
            {
                esta_modificando = false;
                Response.Redirect("mostrar_proveedor.aspx");
            }
            else
            {
                Response.Redirect("gestion_relaciones_comerciales.aspx");
            }
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            if (!esta_modificando)
            {
                esta_modificando = true;
            }
            else
            {
                string direccion = txtDireccion.Text;
                string email = txtEmail.Text;
                string telefono = txtTelefono.Text;

                this.daoProveedor.modificar(
                    this.prov.id,
                    this.prov.nombre,
                    direccion,
                    telefono,
                    email,
                    this.prov.tipoIndustria,
                    this.prov.fecha_afiliacion,
                    this.prov.RUC,
                    this.prov.razonSocial,
                    this.prov.calificacion,
                    this.prov.pais
                );
                esta_modificando = false;
            }

            Response.Redirect("mostrar_proveedor.aspx");
        }

        protected void btnEliminarRepresentante_Click(object sender, EventArgs e)
        {
            int idPersona = Int32.Parse(((LinkButton)sender).CommandArgument);
            if (this.daoRepresentante.eliminarPorId(idPersona.ToString()) != 0)
            {
                // Si no es 0, significa que hubo una actualización.
                this.cargarRepresentantes();
                // Esto para que se conserven los cambios de la edición.
                Session["Telefono"] = txtTelefono.Text;
                Session["Direccion"] = txtDireccion.Text;
                Session["Correo"] = txtEmail.Text;
            }
            Response.Redirect("mostrar_proveedor.aspx"); // Para que se actualice.
        }

        protected void btnVerRepresentante_Click(object sender, EventArgs e) { }

        protected void ModalRepresentante_lbBuscarRepresentante_Click(object sender, EventArgs e)
        {
            string nombre = ModalRepresentante_txtRepresentanteNombre.Text;
            this.representantesCandidatos = this.daoRepresentante.listarPorNombre(nombre);
            ModalRepresentante_gvRepresentante.DataSource = this.representantesCandidatos;
            ModalRepresentante_gvRepresentante.DataBind();
            ScriptManager.RegisterStartupScript(
                this,
                GetType(),
                "OpenModalScript",
                "showModalFormRepresentanteInsertar();",
                true
            );
        }

        protected void ModalRepresentante_gvRepresentante_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalRepresentante_gvRepresentante.PageIndex = e.NewPageIndex;
            ModalRepresentante_gvRepresentante.DataBind();
        }

        protected void ModalRepresentante_lbRepresentante_Click(object sender, EventArgs e)
        {
            string idPersona = (((LinkButton)sender).CommandArgument).ToString();
            representante rep = this.daoRepresentante.obtenerPorId(idPersona);
            this.daoRepresentante.modificar(
                Int32.Parse(idPersona),
                rep.dni,
                rep.nombre,
                rep.telefono,
                rep.correo,
                rep.direccion,
                Int32.Parse(this.idEmpresa)
            );
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('', '');", true);
        }

        protected void dgvDocumentos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvDocumentos.PageIndex = e.NewPageIndex;
            dgvDocumentos.DataBind();
        }
    }
}
