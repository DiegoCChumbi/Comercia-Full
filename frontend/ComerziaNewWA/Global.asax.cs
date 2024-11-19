using System;
using System.IO;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Optimization;
using System.Web.Routing;
using ComerziaWA;

namespace ComerziaNewWA
{
    public class Global : HttpApplication
    {
        void Application_Start(object sender, EventArgs e)
        {
            RegisterRoutes(RouteTable.Routes, Server.MapPath("~/App"));
            BundleConfig.RegisterBundles(BundleTable.Bundles);
        }

        private void RegisterRoutes(RouteCollection routes, string appPhysicalPath)
        {
            routes.RouteExistingFiles = false;

            // Register root route if Page.aspx exists in ~/App
            string rootPagePath = Path.Combine(appPhysicalPath, "Page.aspx");
            if (File.Exists(rootPagePath))
            {
                routes.Add("RootRoute", new Route("", new RouteHandler("~/App/Page.aspx")));
            }

            // Dynamically register routes based on folder structure
            RegisterRoutesRecursive(routes, appPhysicalPath, "", "");
        }

        private void RegisterRoutesRecursive(
            RouteCollection routes,
            string currentPhysicalPath,
            string currentRouteUrl,
            string currentVirtualPath
        )
        {
            foreach (string directory in Directory.GetDirectories(currentPhysicalPath))
            {
                string folderName = Path.GetFileName(directory);
                bool isParameter = false;
                string paramName = null;

                if (folderName.StartsWith("[") && folderName.EndsWith("]"))
                {
                    isParameter = true;
                    paramName = folderName.Substring(1, folderName.Length - 2);
                }

                string routeSegment = isParameter ? "{" + paramName + "}" : folderName;
                string newRouteUrl = (currentRouteUrl + "/" + routeSegment).Trim('/');
                string newVirtualPath = (currentVirtualPath + "/" + folderName).Trim('/');

                // Check if Page.aspx exists in this directory
                string pagePath = Path.Combine(directory, "Page.aspx");
                if (File.Exists(pagePath))
                {
                    // Build the route
                    string routeUrl = newRouteUrl;
                    string virtualPath =
                        "~/" + Path.Combine("App", newVirtualPath, "Page.aspx").Replace("\\", "/");
                    routes.Add(routeUrl, new Route(routeUrl, new RouteHandler(virtualPath)));
                }

                // Recurse into subdirectories
                RegisterRoutesRecursive(routes, directory, newRouteUrl, newVirtualPath);
            }
        }

        protected void Session_Start(object sender, EventArgs e) { }

        protected void Application_BeginRequest(object sender, EventArgs e)
        {
            HttpContext context = HttpContext.Current;
            string requestedPath = context.Request.RawUrl.ToLower();

            if (IsStaticFile(requestedPath))
            {
                return;
            }
        }

        private static bool IsStaticFile(string path)
        {
            Match match = new Regex(@"\/((?!.+\.[\w]+$|public).*)").Match(path);
            return !match.Success || match.Index != 0 || match.Length != path.Length;
        }

        protected void Application_AuthenticateRequest(object sender, EventArgs e)
        {
            var _context = HttpContext.Current;
            var context = new HttpContextWrapper(HttpContext.Current);
            RouteData routeData = RouteTable.Routes.GetRouteData(context);

            if (routeData == null)
            {
                return;
            }

            // Activar cuando se tenga la lógica de autenticación
            // AuthMiddleware.ApplyAuthLogic(_context);
        }

        protected void Application_Error(object sender, EventArgs e) { }

        protected void Session_End(object sender, EventArgs e) { }

        protected void Application_End(object sender, EventArgs e) { }
    }
}
