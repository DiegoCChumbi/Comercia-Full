using System;
using System.ComponentModel;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class GestionRecursosHumanos : System.Web.UI.Page
    {
        private EmpleadoBO boEmpleado;

        public GestionRecursosHumanos()
        {
            boEmpleado = new EmpleadoBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Obtener la lista de vendedores
                BindingList<empleado> empleados = this.boEmpleado.listarTodos();

                // Asignar la lista de personas al GridView
                gvEmpleados.DataSource = empleados;
                gvEmpleados.DataBind(); // Actualiza el GridView
            }
        }

        protected void gvEmpleados_RowCommand(object sender, GridViewCommandEventArgs e) { }
    }
}
