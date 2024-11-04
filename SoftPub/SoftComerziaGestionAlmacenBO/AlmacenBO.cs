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
    public class AlmacenBO : BaseBO
    {
        public BindingList<almacen> listarTodos()
        {
            // Se deserializa y retorna un arreglo.
            // Almacen se obtiene del WSDL
            almacen[] arreglo = this.WsCliente.listarAlmacenes();
            return new BindingList<almacen>(arreglo);
        }

        public almacen obtenerPorId(string idAlmacen)
        {
            return this.WsCliente.obtenerPorId(idAlmacen);
        }
    }
}
