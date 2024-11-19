using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaGestionAlmacenBO
{
    public class AlmacenBO : BaseBO
    {
        public int insertar(string nombre, string estado, string descripcion)
        {
            return this.WsGestionAlmacen.insertar_almacen(nombre, estado, descripcion);
        }

        public int modificar(int idAlmacen, string nombre, string estado, string descripcion)
        {
            return this.WsGestionAlmacen.modificar_almacen(idAlmacen, nombre, estado, descripcion);
        }

        public int eliminar(int idAlmacen)
        {
            return this.WsGestionAlmacen.eliminar_almacen(idAlmacen);
        }

        public BindingList<almacen> listarTodos()
        {
            // Se deserializa y retorna un arreglo.
            // Almacen se obtiene del WSDL
            almacen[] arreglo = this.WsGestionAlmacen.listarAlmacenes();
            return new BindingList<almacen>(arreglo);
        }

        public BindingList<almacen> listarParaIndex()
        {
            // Se deserializa y retorna un arreglo.
            // Almacen se obtiene del WSDL
            almacen[] arreglo = this.WsGestionAlmacen.listarAlmacenesParaIndex();
            return new BindingList<almacen>(arreglo);
        }

        public almacen obtenerPorId(string idAlmacen)
        {
            return this.WsGestionAlmacen.obtenerPorId(idAlmacen);
        }
    }
}
