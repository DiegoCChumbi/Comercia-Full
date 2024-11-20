using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
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
                // Cargar los empleados solo en la primera carga de la página
                CargarEmpleados();
            }
        }

        private void CargarEmpleados()
        {
            // Obtener la lista de empleados desde la lógica de negocio
            BindingList<empleado> empleados = this.boEmpleado.listarTodos();

            // Asignar la lista de empleados al GridView
            gvEmpleados.DataSource = empleados;
            gvEmpleados.DataBind();
        }

        protected void gvEmpleados_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            // Cambiar el índice de página
            gvEmpleados.PageIndex = e.NewPageIndex;

            // Recargar los datos del GridView para mostrar la página correspondiente
            CargarEmpleados();
        }
    }
}
