using System;
using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaRelacionesComercialesBO
{
    public class ClienteBO : BaseBO
    {
        public BindingList<cliente> listarClientes()
        {
            cliente[] clientes = this.WsRelacionesComerciales.listarClientes();

            if (clientes == null)
            {
                return new BindingList<cliente>();
            }
            else
                return new BindingList<cliente>(clientes);
        }

        public BindingList<cliente> listarParaIndex()
        {
            cliente[] clientes = this.WsRelacionesComerciales.listarClientesParaIndex();

            if (clientes == null)
            {
                return new BindingList<cliente>();
            }
            else
                return new BindingList<cliente>(clientes);
        }

        public cliente obtenerPorId(string id)
        {
            return this.WsRelacionesComerciales.obtenerPorIdCliente(id);
        }

        public int modificar(
            int idEmpresa,
            string nombre,
            string direccion,
            string telefono,
            string email,
            string tipoIndustria,
            DateTime fechaRegistro,
            DateTime fechaUltimaCompra
        )
        {
            // Invocación del método modificarCliente en el servicio
            return this.WsRelacionesComerciales.modificarCliente(
                idEmpresa,
                nombre,
                direccion,
                telefono,
                email,
                tipoIndustria,
                fechaRegistro,
                fechaUltimaCompra
            );
        }
    }
}
