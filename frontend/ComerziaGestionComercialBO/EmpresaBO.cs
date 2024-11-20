using ComerziaBO;
using ComerziaBO.ComerziaWS;
using System;
using System.ComponentModel;

namespace ComerziaGestionComercialBO
{
    public class EmpresaBO : BaseBO
    {
        public BindingList<empresa> listarPorNombre(String nombre)
        {
            empresa[] empresas = this.WsRelacionesComerciales.buscarPorNombre_empresa(nombre);

            if (empresas == null)
            {
                return new BindingList<empresa>();
            }
            else return new BindingList<empresa>(empresas);
        }

        public empresa obtenerPorId(String id)
        {
            return this.WsRelacionesComerciales.obtenerEmpresaPorId(id);
        }
    }
}
