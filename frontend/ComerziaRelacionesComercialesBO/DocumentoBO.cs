using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaRelacionesComercialesBO
{
    public class DocumentoBO : BaseBO
    {
        public BindingList<documento> listarPorEmpresa(string id)
        {
            documento[] documentos = this.WsRelacionesComerciales.listarDocumentosPorEmpresa(id);

            if (documentos == null)
            {
                return new BindingList<documento>();
            }
            else
                return new BindingList<documento>(documentos);
        }
    }
}
