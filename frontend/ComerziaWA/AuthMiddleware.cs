using System;
using System.Web;

namespace ComerziaWA
{
    public class AuthMiddleware
    {
        public static void ApplyAuthLogic(HttpContext _context)
        {
            string requestedPath = _context.Request.RawUrl.ToLower();

            bool isApiAuthRoute = IsApiAuthRoute(requestedPath);
            bool isPublicRoute = IsPublicRoute(requestedPath);
            bool isAuthenticationRoute = IsAuthenticationRoute(requestedPath);
            bool userIsAuthenticated = _context.Session != null && _context.Session["User"] != null;

            if (isApiAuthRoute)
            {
                return;
            }
            else if (isAuthenticationRoute)
            {
                if (userIsAuthenticated)
                {
                    _context.Response.Redirect(AuthConfig.DefaultAuthenticatedRedirect);
                }
            }
            else if (!isPublicRoute)
            {
                if (!userIsAuthenticated)
                {
                    _context.Response.Redirect("/login");
                }
            }
        }

        private static bool IsApiAuthRoute(string path) =>
            path.StartsWith(AuthConfig.ApiAuthPrefix);

        private static bool IsPublicRoute(string path) =>
            Array.Exists(
                AuthConfig.PublicRoutes,
                route => path.Equals(route, StringComparison.OrdinalIgnoreCase)
            );

        private static bool IsAuthenticationRoute(string path) =>
            Array.Exists(
                AuthConfig.AuthenticationRoutes,
                route => path.Equals(route, StringComparison.OrdinalIgnoreCase)
            );
    }
}
