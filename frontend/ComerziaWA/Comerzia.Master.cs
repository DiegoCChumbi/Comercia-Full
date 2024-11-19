using System;
using System.ComponentModel;
using ComerziaBO.ComerziaWS;
using ComerziaNotificacionBO;
using ComerziaRecursosHumanosBO;

namespace ComerziaWA
{
    public partial class Comerzia : System.Web.UI.MasterPage
    {
        private EmpleadoBO boEmpleado;
        private NotificacionBO boNotificacion;

        public int cantNotificaciones;

        public Comerzia()
        {
            boEmpleado = new EmpleadoBO();
            boNotificacion = new NotificacionBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["idEmpleado"] != null)
            {
                int idEmpleado = (int)Session["idEmpleado"];
                string nombreEmpleado = boEmpleado.devolverNombreEmpleado(idEmpleado);

                // Asignar el nombre al elemento PersonaName
                Persona.InnerText = nombreEmpleado;
            }
            BindingList<notificacion> notificaciones;

            notificaciones = this.boNotificacion.listarTodos();
            cantNotificaciones = notificaciones.Count;

            gvNotificaciones.DataSource = notificaciones;
            gvNotificaciones.DataBind();
        }
    }
}
