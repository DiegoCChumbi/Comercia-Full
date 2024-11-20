using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaRelacionesComercialesBO;

namespace ComerziaWA
{
    public partial class gestion_relaciones_comerciales : System.Web.UI.Page
    {
        private ClienteBO cliente;
        private ProveedorBO proveedor;
        private BindingList<cliente> clientes;
        private BindingList<proveedor> proveedores;

        public gestion_relaciones_comerciales()
        {
            this.cliente = new ClienteBO();
            this.proveedor = new ProveedorBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            this.cargarClientes();
            this.cargarProveedores();
        }

        protected void cargarClientes()
        {
            clientes = this.cliente.listarClientes();
            dgvClientes.DataSource = clientes;
            dgvClientes.DataBind();
        }

        protected void cargarProveedores()
        {
            proveedores = this.proveedor.listarProveedores();
            dgvProveedores.DataSource = proveedores;
            dgvProveedores.DataBind();
        }

        protected void dgvClientes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvClientes.PageIndex = e.NewPageIndex;
            dgvClientes.DataBind();
        }

        protected void dgvProveedores_PageIndexChanging(object sender, GridViewPageEventArgs e) { }

        protected void btnVerCliente_Click(object sender, EventArgs e)
        {
            string idCliente = ((LinkButton)sender).CommandArgument;
            this.guardarEmpresa(idCliente);
            Response.Redirect("mostrar_cliente.aspx");
        }

        protected void guardarEmpresa(string id)
        {
            Session["idEmpresa"] = id;
        }

        protected void btnVerProveedor_Click(object sender, EventArgs e)
        {
            string idProveedor = ((LinkButton)sender).CommandArgument;
            this.guardarEmpresa(idProveedor);
            Response.Redirect("mostrar_proveedor.aspx");
        }
    }
}
