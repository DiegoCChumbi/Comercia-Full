using SoftComerziaBO;
using SoftComerziaBO.ComerziaWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftComerziaGestionAlmacenBO
{
    public class ProductoAlmacenadoBO : BaseBO
    {
        public BindingList<productoAlmacenado> listarPorAlmacen(string idAlmacen)
        {
            // Se deserializa y retorna un arreglo.
            // Almacen se obtiene del WSDL
            productoAlmacenado[] arreglo = this.WsCliente.listarPorAlmacen(idAlmacen);
            if (arreglo == null)
            {
                return new BindingList<productoAlmacenado>();
            }
            else return new BindingList<productoAlmacenado>(arreglo);
        }

        public int insertar(int idAlmacen, DateTime fechaAlmacenado, int stockActual, int idProducto)
        {
            return this.WsCliente.insertarProductoAlmacenado(idAlmacen, fechaAlmacenado, stockActual, idProducto);
        }
    }
}
