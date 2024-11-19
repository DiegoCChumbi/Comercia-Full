using System;
using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaRecursosHumanosBO
{
    public class VendedorBO : BaseBO
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
            double ingresosVentas,
            double porcentajeComision
        )
        {
            return this.WsRecursosHumanos.insertar_vendedor(
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
                ingresosVentas,
                porcentajeComision
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
            double ingresosVentas,
            double porcentajeComision
        )
        {
            return this.WsRecursosHumanos.modificar_vendedor(
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
                ingresosVentas,
                porcentajeComision
            );
        }

        public int eliminar(int idTrabajador, int idPersona)
        {
            return this.WsRecursosHumanos.eliminar_vendedor(idTrabajador, idPersona);
        }

        public BindingList<vendedor> listarTodos()
        {
            vendedor[] arreglo = this.WsRecursosHumanos.listarTodos_vendedor();

            if (arreglo == null)
            {
                return new BindingList<vendedor>();
            }

            return new BindingList<vendedor>(arreglo);
        }

        public vendedor obtenerPorId(int id)
        {
            return this.WsRecursosHumanos.obtenerPorId_vendedor(id);
        }

        public BindingList<vendedor> listarParaIndex()
        {
            vendedor[] arreglo = this.WsRecursosHumanos.listarParaIndex_vendedor();

            if (arreglo == null)
            {
                return new BindingList<vendedor>();
            }

            return new BindingList<vendedor>(arreglo);
        }
    }
}
