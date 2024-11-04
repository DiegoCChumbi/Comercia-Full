using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace SoftPubWA
{
    public partial class insertar_especialidad : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
          
        }
        /*
        private EspecialidadBo especialidadBo;
        private int idEspecialidad;
        private bool esta_modificando;

        public insertar_especialidad()
        {
            this.especialidadBo = new EspecialidadBo();
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Page_Init(object sender, EventArgs e)
        {
            this.idEspecialidad = (int)Session["idEspecialidad"];
            string accion = Request.QueryString["accion"];
            if (accion != null && accion == "modificar")
                esta_modificando = true;
            else
                esta_modificando = false;
            if (esta_modificando)
                this.cargarDatosDeLaBD();
        }
        private void cargarDatosDeLaBD()
        {
            //Especialidad especialidad = this.especialidadBo.obtenerPorId(this.idEspecialidad);
           // txtIdEspecialidad.Text = especialidad.IdEspecialidad.ToString();
            //txtIdEspecialidad.Enabled = false;
            //txtEspecialidad.Text = especialidad.Nombre_especialidad;
            //txtFacultad.Text = especialidad.Facultad;
            //ddlNivel.SelectedValue = especialidad.Nivel.ToString();
        }
        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            int idEspecialidad = Int32.Parse(txtIdEspecialidad.Text); 
            string nombre_especialidad = txtEspecialidad.Text; 
            string faculdad = txtFacultad.Text; 
            string descripcion_nivel = ddlNivel.Text;
            if (esta_modificando)
                this.especialidadBo.modificar(idEspecialidad, nombre_especialidad, faculdad, descripcion_nivel);
            else
                this.especialidadBo.insertar(idEspecialidad, nombre_especialidad, faculdad, descripcion_nivel);
            
            Response.Redirect("mantenimiento_especialidad.aspx");
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("mantenimiento_especialidad.aspx");
        }*/
    }
}