using System;
using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaGestionComercialBO
{
    public class DocumentoBO : BaseBO
    {
        public int insertar(
            int idEmpresa,
            estadoDocumentoEnum _estado,
            tipoDocumentoEnum _tipo,
            int idVendedor,
            int idAdministrador,
            int idTrabajadorDeAlmacen
        )
        {
            return this.WsGestionComercialCliente.insertar_documento(
                idEmpresa,
                _estado,
                _tipo,
                idVendedor,
                idAdministrador,
                idTrabajadorDeAlmacen
            );
        }

        public int modificar(
            int idDocumento,
            int idEmpresa,
            estadoDocumentoEnum _estado,
            tipoDocumentoEnum _tipo,
            int idVendedor,
            int idAdministrador,
            int idTrabajadorDeAlmacen
        )
        {
            return this.WsGestionComercialCliente.modificar_documento(
                idDocumento,
                idEmpresa,
                _estado,
                _tipo,
                idVendedor,
                idAdministrador,
                idTrabajadorDeAlmacen
            );
        }

        public int eliminar(int idDocumento)
        {
            return this.WsGestionComercialCliente.eliminar_documento(idDocumento);
        }

        public BindingList<documento> listarTodos()
        {
            documento[] arreglo = this.WsGestionComercialCliente.listarDocumentos();
            if (arreglo == null)
            {
                return new BindingList<documento>();
            }
            else
                return new BindingList<documento>(arreglo);
        }

        public documento obtenerPorId(string idDocumento)
        {
            return this.WsGestionComercialCliente.obtenerDocumentoPorId(idDocumento);
        }

        public Boolean existedocumento(
            int idEmpresa,
            estadoDocumentoEnum _estado,
            tipoDocumentoEnum _tipo,
            int idVendedor,
            int idAdministrador,
            int idTrabajadorDeAlmacen
        )
        {
            return this.WsGestionComercialCliente.existeDocumento(
                idEmpresa,
                _estado,
                _tipo,
                idVendedor,
                idAdministrador,
                idTrabajadorDeAlmacen
            );
        }

        public BindingList<documento> listarPorEmpresa(string idEmpresa)
        {
            documento[] arreglo = this.WsGestionComercialCliente.listarPorEmpresa(idEmpresa);
            if (arreglo == null)
            {
                return new BindingList<documento>();
            }
            else
                return new BindingList<documento>(arreglo);
        }
    }
}
