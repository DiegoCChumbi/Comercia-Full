using System;
using System.ComponentModel;
using ComerziaBO;
using ComerziaBO.ComerziaWS;

namespace ComerziaRecursosHumanosBO
{
    public class EmpleadoBO : BaseBO
    {
        public String devolverNombreEmpleado(int idEmpleado)
        {
            String nombre = this.WsRecursosHumanos.devolverNombreEmpleado(idEmpleado);

            return nombre;
        }

        public int verEmpleado(string cuenta, string contrasenha)
        {
            int idEmpleado = this.WsRecursosHumanos.verEmpleado(cuenta, contrasenha);

            return idEmpleado;
        }

        public BindingList<empleado> listarTodos()
        {
            empleado[] arreglo = this.WsRecursosHumanos.listarTodos_empleado();

            if (arreglo == null)
            {
                return new BindingList<empleado>();
            }

            return new BindingList<empleado>(arreglo);
        }

        public String devolverRol(int idEmpleado)
        {
            String rol = this.WsRecursosHumanos.devolverRol(idEmpleado);

            return rol;
        }
    }
}
