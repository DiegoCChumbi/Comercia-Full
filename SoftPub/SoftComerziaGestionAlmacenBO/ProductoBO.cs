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
    public class ProductoBO : BaseBO
    {
        public BindingList<producto> buscarPorNombre(string nombreProd)
        {
            // Se deserializa y retorna un arreglo.
            // Almacen se obtiene del WSDL
            producto[] arreglo = this.WsCliente.buscarPorNombre(nombreProd);
            if (arreglo == null)
            {
                return new BindingList<producto>();
            }
            else return new BindingList<producto>(arreglo);
        }

        public producto obtenerPorId(int idProductoSeleccionado)
        {
            return this.WsCliente.buscarProductoPorId(idProductoSeleccionado);
        }
    }
}
