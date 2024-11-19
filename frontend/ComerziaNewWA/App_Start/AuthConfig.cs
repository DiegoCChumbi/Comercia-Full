namespace ComerziaNewWA.App_Start
{
    public class AuthConfig
    {
        public static readonly string[] PublicRoutes = { "/ventas/user-50/productos" };

        // TODO: Refactor The Name, these are not required routes, but routes that are used for authentication
        public static readonly string[] AuthenticationRoutes =
        {
            "/login",
            "/register",
            "/forgot-password",
            "/reset-password"
        };

        public static readonly string ApiAuthPrefix = "/api/auth";
        public static readonly string DefaultAuthenticatedRedirect = "/";
    }
}
