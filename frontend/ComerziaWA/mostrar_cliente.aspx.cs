using System;
using System.ComponentModel;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaRelacionesComercialesBO;

namespace ComerziaWA
{
    public partial class mostrar_cliente : System.Web.UI.Page
    {
        private string idEmpresa;
        private static bool esta_modificando = false;
        private cliente cli;
        private ClienteBO daoCliente;
        private RepresentanteBO daoRepresentante;
        private DocumentoBO daoDocumento;
        private BindingList<representante> representantes;
        private BindingList<representante> representantesCandidatos;
        private BindingList<documento> documentos;

        public mostrar_cliente()
        {
            this.daoCliente = new ClienteBO();
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

        protected void Page_Init(object sender, EventArgs e)
        {
            this.idEmpresa = (string)Session["idEmpresa"];
            this.cargarCliente();
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

        protected void cargarRepresentantes()
        {
            this.representantes = this.daoRepresentante.listarPorEmpresa(idEmpresa);
            dgvRepresentantes.DataSource = representantes;
            dgvRepresentantes.DataBind();
        }

        // Falta implementar
        protected void cargarDocumentos()
        {
            this.documentos = this.daoDocumento.listarPorEmpresa(idEmpresa);
            dgvDocumentos.DataSource = this.documentos;
            dgvDocumentos.DataBind();
        }

        protected void cargarCliente()
        {
            this.cli = this.daoCliente.obtenerPorId(idEmpresa);
            txtTitulo.Text = cli.nombre.ToString();
            txtTelefono.Text = cli.telefono.ToString();
            txtDireccion.Text = cli.direccion.ToString();
            txtEmail.Text = cli.email.ToString();
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
                Response.Redirect("mostrar_cliente.aspx");
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
            { // Tendrá un diferente tipo de evento.
                string direccion = txtDireccion.Text;
                string email = txtEmail.Text;
                string telefono = txtTelefono.Text;
                // Posible error con la fecha
                this.daoCliente.modificar(
                    this.cli.id,
                    this.cli.nombre,
                    direccion,
                    telefono,
                    email,
                    this.cli.tipoIndustria,
                    this.cli.fechaRegistro,
                    this.cli.fechaUltimaCompra
                );
                esta_modificando = false;
            }
            // No se hará refresco hasta tener todos los por eliminar.
            Response.Redirect("mostrar_cliente.aspx"); // Se hace un refrezco de la página
        }

        protected void dgvRepresentantes_PageIndexChanging1(object sender, GridViewPageEventArgs e)
        {
            dgvRepresentantes.PageIndex = e.NewPageIndex;
            dgvRepresentantes.DataBind();
        }

        protected void dgvDocumentos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvDocumentos.PageIndex = e.NewPageIndex;
            dgvDocumentos.DataBind();
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
            Response.Redirect("mostrar_cliente.aspx"); // Para que se actualice.
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
            ); // Para que se mantenga abierto
        }

        protected void ModalRepresentante_gvRepresentante_PageIndexChanging(
            object sender,
            GridViewPageEventArgs e
        )
        {
            ModalRepresentante_gvRepresentante.PageIndex = e.NewPageIndex;
            ModalRepresentante_gvRepresentante.DataBind();
        }

        protected void ModalRepresentante_lbRepresentanteSeleccionar_Click(
            object sender,
            EventArgs e
        )
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
    }
}
