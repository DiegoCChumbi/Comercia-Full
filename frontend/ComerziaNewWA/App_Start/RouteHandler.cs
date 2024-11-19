using System.Collections.Generic;
using System.Web;
using System.Web.Compilation;
using System.Web.Routing;

namespace ComerziaWA
{
    public class RouteHandler : IRouteHandler
    {
        private readonly string _virtualPath;

        public RouteHandler(string virtualPath)
        {
            _virtualPath = virtualPath;
        }

        public IHttpHandler GetHttpHandler(RequestContext requestContext)
        {
            // Extract parameters from route data
            RouteValueDictionary routeData = requestContext.RouteData.Values;
            Dictionary<string, string> parameters = new Dictionary<string, string>();

            foreach (string key in routeData.Keys)
            {
                parameters[key] = routeData[key].ToString();
            }

            // Store parameters in HttpContext.Items for use in Page
            HttpContext.Current.Items["RouteParameters"] = parameters;

            return BuildManager.CreateInstanceFromVirtualPath(
                    _virtualPath,
                    typeof(System.Web.UI.Page)
                ) as IHttpHandler;
        }
    }
}
