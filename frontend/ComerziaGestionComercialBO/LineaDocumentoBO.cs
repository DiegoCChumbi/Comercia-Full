using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaGestionComercialBO
{
    public class LineaDocumentoBO : BaseBO
    {
        public int insertar(int idDocumento, int idProducto, int cantidad, double precioUnitario)
        {
            return this.WsGestionComercialCliente.insertar_lineaDocumento(
                idDocumento,
                idProducto,
                cantidad,
                precioUnitario
            );
        }

        public int modificar(
            int id,
            int idDocumento,
            int idProducto,
            int cantidad,
            double precioUnitario
        )
        {
            return this.WsGestionComercialCliente.modificar_lineaDocumento(
                id,
                idDocumento,
                idProducto,
                cantidad,
                precioUnitario
            );
        }

        public int eliminar(int id)
        {
            return this.WsGestionComercialCliente.eliminar_lineaDocumento(id);
        }

        public lineaDocumento obtenerPorId(int idDocumento)
        {
            return this.WsGestionComercialCliente.obtenerLineaDocumentoPorId(idDocumento);
        }

        public BindingList<lineaDocumento> listarPorDocumento(int idDocumento)
        {
            lineaDocumento[] arreglo = this.WsGestionComercialCliente.listarLineasPorDocumento(
                idDocumento
            );
            if (arreglo == null)
            {
                return new BindingList<lineaDocumento>();
            }
            else
                return new BindingList<lineaDocumento>(arreglo);
        }

        //public Boolean existeLinea(int idDocumento, int idProducto, int cantidad, double precioUnitario)
        //{
        //    return this.WsGestionComercialCliente.ex(idDocumento, idProducto, cantidad, precioUnitario);
        //}
    }
}
