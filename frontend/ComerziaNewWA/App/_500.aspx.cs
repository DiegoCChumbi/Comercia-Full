using System;
using System.Web;

namespace ComerziaNewWA.App
{
    public partial class _500 : System.Web.UI.Page
    {
        protected string ExceptionDetails = string.Empty;
        protected string InnerExceptionTitle = "Error Details";

        protected void Page_Load(object sender, EventArgs e)
        {
            Exception exception = HttpContext.Current.Items["Exception"] as Exception;

            if (exception != null)
            {
                // Display detailed error information during development
#if DEBUG
                ExceptionDetails = Server.HtmlEncode(exception.ToString());
                InnerExceptionTitle = string.IsNullOrEmpty(exception.InnerException?.Message)
                    ? "Error Details"
                    : $"Inner Exception: {Server.HtmlEncode(exception.InnerException.Message)}";
                ErrorDetailsPanel.Visible = true;
#else
                // In production, do not display exception details
                ErrorDetailsPanel.Visible = false;
#endif
            }
            else
            {
                // No exception details are available
                ErrorDetailsPanel.Visible = false;
            }
        }
    }
}
