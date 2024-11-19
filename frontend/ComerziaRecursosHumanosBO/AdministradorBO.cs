using System;
using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaRecursosHumanosBO
{
    public class AdministradorBO : BaseBO
    {
        public int insertar(
            string dni,
            string nombreCompleto,
            string telefono,
            string correo,
            string direccion,
            estadoEmpleadoEnum estado,
            string nombreUsuario,
            string contrasenha,
            double salario,
            DateTime fechaContratacion,
            int idAlmacen
        )
        {
            return this.WsRecursosHumanos.insertar_administrador(
                dni,
                nombreCompleto,
                telefono,
                correo,
                direccion,
                estado,
                nombreUsuario,
                contrasenha,
                salario,
                fechaContratacion,
                idAlmacen
            );
        }

        public int modificar(
            int idPersona,
            string dni,
            string nombreCompleto,
            string telefono,
            string correo,
            string direccion,
            estadoEmpleadoEnum estado,
            string nombreUsuario,
            string contrasenha,
            double salario,
            DateTime fechaContratacion,
            int idAlmacen
        )
        {
            return this.WsRecursosHumanos.modificar_administrador(
                idPersona,
                dni,
                nombreCompleto,
                telefono,
                correo,
                direccion,
                estado,
                nombreUsuario,
                contrasenha,
                salario,
                fechaContratacion,
                idAlmacen
            );
        }

        public int eliminar(int idPersona)
        {
            return this.WsRecursosHumanos.eliminar_administrador(idPersona);
        }

        public BindingList<administrador> listarTodos()
        {
            administrador[] arreglo = this.WsRecursosHumanos.listarTodos_administrador();
            if (arreglo == null)
            {
                return new BindingList<administrador>(); // Devuelve una lista vacía en caso de null
            }

            return new BindingList<administrador>(arreglo);
        }

        public administrador obtenerPorId(int idAdministrador)
        {
            return this.WsRecursosHumanos.obtenerPorId_administrador(idAdministrador);
        }

        public BindingList<administrador> listarParaIndex()
        {
            administrador[] arreglo = this.WsRecursosHumanos.listarParaIndex_administrador();
            if (arreglo == null)
            {
                return new BindingList<administrador>(); // Devuelve una lista vacía en caso de null
            }

            return new BindingList<administrador>(arreglo);
        }
    }
}
