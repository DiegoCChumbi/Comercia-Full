using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ComerziaWA
{
    public partial class logout : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // Limpia la sesión
            Session.Clear(); // Elimina todos los valores de la sesión
            Session.Abandon(); // Termina la sesión actual

            // Redirige al usuario a la página de inicio de sesión
            Response.Redirect("login.aspx");
        }
    }
}
