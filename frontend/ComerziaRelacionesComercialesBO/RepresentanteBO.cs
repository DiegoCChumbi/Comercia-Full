using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaRelacionesComercialesBO
{
    public class RepresentanteBO : BaseBO
    {
        public BindingList<representante> listarPorEmpresa(string id)
        {
            representante[] representantes =
                this.WsRelacionesComerciales.listarRepresentantesPorEmpresa(id);

            if (representantes == null)
            {
                return new BindingList<representante>();
            }
            else
                return new BindingList<representante>(representantes);
        }

        public BindingList<representante> listarPorNombre(string nom)
        {
            representante[] representantes =
                this.WsRelacionesComerciales.listarRepresentantesPorNombre(nom);

            if (representantes == null)
            {
                return new BindingList<representante>();
            }
            else
                return new BindingList<representante>(representantes);
        }

        public int eliminarPorId(string id)
        {
            return this.WsRelacionesComerciales.eliminarRepresentante(id);
        }

        public representante obtenerPorId(string id)
        {
            return this.WsRelacionesComerciales.obtenerPorIdRepresentante(id);
        }

        public int modificar(
            int idPersona,
            string dni,
            string nombreCompleto,
            string telefono,
            string correo,
            string direccion,
            int idEmpresa
        )
        {
            // Llama al método del servicio SOAP para modificar el representante
            return WsRelacionesComerciales.modificarRepresentante(
                idPersona,
                dni,
                nombreCompleto,
                telefono,
                correo,
                direccion,
                idEmpresa
            );
        }
    }
}
