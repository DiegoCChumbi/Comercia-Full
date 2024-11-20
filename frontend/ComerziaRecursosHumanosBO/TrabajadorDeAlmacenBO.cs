using ComerziaBO;
using ComerziaBO.ComerziaWS;
using System;
using System.ComponentModel;

namespace ComerziaRecursosHumanosBO
{
    public class TrabajadorDeAlmacenBO : BaseBO
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
            int idAlmacen,
            bool licenciaMontacarga
        )
        {
            return this.WsRecursosHumanos.insertar_trabajadorDeAlmacen(
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
                idAlmacen,
                licenciaMontacarga
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
            int idAlmacen,
            bool licenciaMontacarga
        )
        {
            return this.WsRecursosHumanos.modificar_trabajadorDeAlmacen(
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
                idAlmacen,
                licenciaMontacarga
            );
        }

        public int eliminar(int idPersona)
        {
            return this.WsRecursosHumanos.eliminar_trabajadorDeAlmacen(idPersona);
        }

        public BindingList<trabajadorDeAlmacen> listarTodos()
        {
            trabajadorDeAlmacen[] arreglo =
                this.WsRecursosHumanos.listarTodos_trabajadorDeAlmacen();

            if (arreglo == null)
            {
                return new BindingList<trabajadorDeAlmacen>();
            }

            return new BindingList<trabajadorDeAlmacen>(arreglo);
        }

        public trabajadorDeAlmacen obtenerPorId(int id)
        {
            return this.WsRecursosHumanos.obtenerPorId_trabajadorDeAlmacen(id);
        }

        public BindingList<trabajadorDeAlmacen> listarParaIndex()
        {
            trabajadorDeAlmacen[] arreglo =
                this.WsRecursosHumanos.listarParaIndex_trabajadorDeAlmacen();

            if (arreglo == null)
            {
                return new BindingList<trabajadorDeAlmacen>();
            }

            return new BindingList<trabajadorDeAlmacen>(arreglo);
        }

        public BindingList<trabajadorDeAlmacen> listarPorNombre(String nombre)
        {
            trabajadorDeAlmacen[] arreglo = this.WsRecursosHumanos.buscarPorNombre_trabajadorDeAlmacen(nombre);

            if (arreglo == null)
            {
                return new BindingList<trabajadorDeAlmacen>();
            }

            return new BindingList<trabajadorDeAlmacen>(arreglo);
        }
    }
}
