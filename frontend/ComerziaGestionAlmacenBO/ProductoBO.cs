using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaGestionAlmacenBO
{
    public class ProductoBO : BaseBO
    {
        public int insertar(string nombre, double precio, int stockMinim)
        {
            return this.WsGestionAlmacen.insertar_producto(nombre, precio, stockMinim);
        }

        public int modificar(int idProducto, string nombre, double precio, int stockMinimo)
        {
            return this.WsGestionAlmacen.modificar_producto(
                idProducto,
                nombre,
                precio,
                stockMinimo
            );
        }

        public int eliminar(int idProducto)
        {
            return this.WsGestionAlmacen.eliminar_producto(idProducto);
        }

        public BindingList<producto> buscarPorNombre(string nombreProd)
        {
            // Se deserializa y retorna un arreglo.
            // Almacen se obtiene del WSDL
            producto[] arreglo = this.WsGestionAlmacen.buscarPorNombre(nombreProd);
            if (arreglo == null)
            {
                return new BindingList<producto>();
            }
            else
                return new BindingList<producto>(arreglo);
        }

        public BindingList<producto> listaParaIndex()
        {
            producto[] arreglo = this.WsGestionAlmacen.listarProductoParaIndex();
            if (arreglo == null)
            {
                return new BindingList<producto>();
            }
            else
                return new BindingList<producto>(arreglo);
        }

        public producto obtenerPorId(int idProductoSeleccionado)
        {
            return this.WsGestionAlmacen.buscarProductoPorId(idProductoSeleccionado);
        }
    }
}
