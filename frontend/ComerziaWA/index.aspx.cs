using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;
using ComerziaRelacionesComercialesBO;
using System;
using System.ComponentModel;

namespace ComerziaWA
{
    public partial class index : System.Web.UI.Page
    {
        private AdministradorBO boAdministrador;
        private VendedorBO boVendedor;
        private TrabajadorDeAlmacenBO boTrabajadorDeAlmacen;

        private ProductoBO boProducto;
        private AlmacenBO boAlmacen;

        private ClienteBO boClientes;
        private ProveedorBO boProveedor;

        public String userRole;

        public index()
        {
            boAdministrador = new AdministradorBO();
            boVendedor = new VendedorBO();
            boTrabajadorDeAlmacen = new TrabajadorDeAlmacenBO();

            boProducto = new ProductoBO();
            boAlmacen = new AlmacenBO();

            boClientes = new ClienteBO();
            boProveedor = new ProveedorBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            //userRole = (String)Session["rol"];
            userRole = "admin";

            if (userRole == "admin" || userRole == "vendedor")
            {
                BindingList<cliente> clientes = this.boClientes.listarParaIndex();
                BindingList<proveedor> proveedores = this.boProveedor.listarParaIndex();

                gvIndexCliente.DataSource = clientes;
                gvIndexCliente.DataBind();

                gvIndexProveedor.DataSource = proveedores;
                gvIndexProveedor.DataBind();
            }

            if (userRole == "admin" || userRole == "vendedor" || userRole == "trabajador")
            {
                BindingList<producto> productos = this.boProducto.listaParaIndex();
                BindingList<almacen> almacenes = this.boAlmacen.listarParaIndex();

                gvIndexProducto.DataSource = productos;
                gvIndexProducto.DataBind();
                gvIndexAlmacen.DataSource = almacenes;
                gvIndexAlmacen.DataBind();
            }
            if (userRole == "admin")
            {
                BindingList<administrador> administradores = this.boAdministrador.listarParaIndex();
                BindingList<vendedor> vendedores = this.boVendedor.listarParaIndex();
                BindingList<trabajadorDeAlmacen> trabajadores =
                    this.boTrabajadorDeAlmacen.listarParaIndex();

                gvIndexAdministradores.DataSource = administradores;
                gvIndexAdministradores.DataBind();

                gvIndexVendedor.DataSource = vendedores;
                gvIndexVendedor.DataBind();

                gvIndexTrabajador.DataSource = trabajadores;
                gvIndexTrabajador.DataBind();
            }
        }
    }
}
