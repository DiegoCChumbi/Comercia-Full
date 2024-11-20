using System.Web.Optimization;
using System.Web.UI;

namespace ComerziaNewWA
{
    public class BundleConfig
    {
        // For more information on Bundling, visit http://go.microsoft.com/fwlink/?LinkID=303951
        public static void RegisterBundles(BundleCollection bundles)
        {
            bundles.Add(
                new ScriptBundle("~/Public/bundles/WebFormsJs").Include(
                    "~/Public/Scripts/WebForms/WebForms.js",
                    "~/Public/Scripts/WebForms/WebUIValidation.js",
                    "~/Public/Scripts/WebForms/MenuStandards.js",
                    "~/Public/Scripts/WebForms/Focus.js",
                    "~/Public/Scripts/WebForms/GridView.js",
                    "~/Public/Scripts/WebForms/DetailsView.js",
                    "~/Public/Scripts/WebForms/TreeView.js",
                    "~/Public/Scripts/WebForms/WebParts.js"
                )
            );

            // Order is very important for these files to work, they have explicit dependencies
            bundles.Add(
                new ScriptBundle("~/Public/bundles/MsAjaxJs").Include(
                    "~/Public/Scripts/WebForms/MsAjax/MicrosoftAjax.js",
                    "~/Public/Scripts/WebForms/MsAjax/MicrosoftAjaxApplicationServices.js",
                    "~/Public/Scripts/WebForms/MsAjax/MicrosoftAjaxTimer.js",
                    "~/Public/Scripts/WebForms/MsAjax/MicrosoftAjaxWebForms.js"
                )
            );

            // Use the Development version of Modernizr to develop with and learn from. Then, when you’re
            // ready for production, use the build tool at http://modernizr.com to pick only the tests you need
            bundles.Add(new ScriptBundle("~/Public/bundles/modernizr").Include("~/Public/Scripts/modernizr-*"));

            ScriptManager.ScriptResourceMapping.AddDefinition(
                "respond",
                new ScriptResourceDefinition
                {
                    Path = "~/Public/Scripts/respond.min.js",
                    DebugPath = "~/Public/Scripts/respond.js",
                }
            );
        }
    }
}
