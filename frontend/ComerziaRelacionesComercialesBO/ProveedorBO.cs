using System;
using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaRelacionesComercialesBO
{
    public class ProveedorBO : BaseBO
    {
        public BindingList<proveedor> listarProveedores()
        {
            proveedor[] proveedores = this.WsRelacionesComerciales.listarProveedores();

            if (proveedores == null)
            {
                return new BindingList<proveedor>();
            }
            else
                return new BindingList<proveedor>(proveedores);
        }

        public BindingList<proveedor> listarParaIndex()
        {
            proveedor[] proveedores = this.WsRelacionesComerciales.listarProveedoresParaIndex();

            if (proveedores == null)
            {
                return new BindingList<proveedor>();
            }
            else
                return new BindingList<proveedor>(proveedores);
        }

        public proveedor obtenerPorId(string id)
        {
            return this.WsRelacionesComerciales.obtenerPorIdProveedor(id);
        }

        public int modificar(
            int idEmpresa,
            string nombre,
            string direccion,
            string telefono,
            string email,
            string tipoIndustria,
            DateTime fechaAfiliacion,
            string ruc,
            string razonSocial,
            double calificacion,
            string pais
        )
        {
            // Llama al método de servicio web pasando cada parámetro necesario
            return this.WsRelacionesComerciales.modificarProveedor(
                idEmpresa,
                nombre,
                direccion,
                telefono,
                email,
                tipoIndustria,
                fechaAfiliacion,
                ruc,
                razonSocial,
                calificacion,
                pais
            );
        }
    }
}
