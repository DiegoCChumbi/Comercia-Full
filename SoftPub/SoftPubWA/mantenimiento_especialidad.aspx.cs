using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


namespace SoftPubWA
{
    public partial class mantenimiento_especialidad : System.Web.UI.Page
    {/*
        private EspecialidadBo especialidadBo;
        private BindingList<Object> listaDeTodos;

        public mantenimiento_especialidad()
        {
            this.especialidadBo = new EspecialidadBo();
            this.listaDeTodos = this.especialidadBo.listarTodos();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            dgvEspecialidad.DataSource = listaDeTodos;
            dgvEspecialidad.DataBind();
        }

        protected void btnInsertar_Click(object sender, EventArgs e)
        {
            Response.Redirect("gestionar_especialidad.aspx");
        }

        protected void lbModificar_Click(object sender, EventArgs e)
        {
            int idEspecialidad = Int32.Parse(((LinkButton)sender).CommandArgument);
            Session["idEspecialidad"] = idEspecialidad;
            Response.Redirect("gestionar_especialidad.aspx?accion=modificar");
        }

        protected void lbEliminar_Click(object sender, EventArgs e)
        {
            int idEspecialidad = Int32.Parse(((LinkButton)sender).CommandArgument);
            this.especialidadBo.eliminar(idEspecialidad);
            Response.Redirect("mantenimiento_especialidad.aspx");
        }*/
    }
}