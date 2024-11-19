using System;
using System.Web.UI;

namespace ComerziaNewWA.Account
{
    public partial class Register : Page
    {
        protected void CreateUser_Click(object sender, EventArgs e)
        {
            ErrorMessage.Text = "My custom error message";
        }
    }
}
