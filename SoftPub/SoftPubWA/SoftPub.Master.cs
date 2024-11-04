using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftPubWA.ServicioWeb;

namespace SoftPubWA
{
    public partial class SoftPub : System.Web.UI.MasterPage
    {
        private RecursosHumanosWSClient daoEvento = new RecursosHumanosWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["idEmpleado"] != null)
            {
                int idEmpleado = (int)Session["idEmpleado"];
                string nombreEmpleado = daoEvento.devolverNombreEmpleado(idEmpleado);

                // Asignar el nombre al elemento PersonaName
                Persona.InnerText = nombreEmpleado;
            }
        }



    }
}