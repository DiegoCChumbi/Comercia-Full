using System;
using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaGestionAlmacenBO
{
    public class ProductoAlmacenadoBO : BaseBO
    {
        public BindingList<productoAlmacenado> listarPorAlmacen(string idAlmacen)
        {
            // Se deserializa y retorna un arreglo.
            // Almacen se obtiene del WSDL
            productoAlmacenado[] arreglo = this.WsGestionAlmacen.listarPorAlmacen(idAlmacen);
            if (arreglo == null)
            {
                return new BindingList<productoAlmacenado>();
            }
            else
                return new BindingList<productoAlmacenado>(arreglo);
        }

        public int insertar(
            int idAlmacen,
            DateTime fechaAlmacenado,
            int stockActual,
            int idProducto
        )
        {
            return this.WsGestionAlmacen.insertarProductoAlmacenado(
                idAlmacen,
                fechaAlmacenado,
                stockActual,
                idProducto
            );
        }

        public int eliminarPorId(int idProdAlm)
        {
            return this.WsGestionAlmacen.eliminarProductoAlmacenado(idProdAlm);
        }
    }
}
