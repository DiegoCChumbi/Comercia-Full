<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="login.aspx.cs" Inherits="SoftPubWA.login" %>


<!DOCTYPE html>
<html>
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Iniciar Sesión - Comerzia</title>
    
    <!-- Estilos y scripts necesarios -->
    <link href="Content/bootstrap.css" rel="stylesheet" />
    <link href="Content/site.css" rel="stylesheet" />
    <script src="Scripts/jquery-3.7.1.js"></script>
    <script src="Scripts/bootstrap.js"></script>

    <style>
        /* Estilos específicos para la página de inicio de sesión */
        .login-container {
            max-width: 800px; /* Ajustar el tamaño del formulario */
            padding: 4rem; /* Aumentar el padding para mayor espacio */
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #ffffff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .logo-img {
            width: 200px; /* Aumentar el tamaño del logo */
        }
    </style>
</head>
<body class="bg-light">
    <form runat="server">
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="text-center login-container">
                <img src="Images/Comerzia.jpg" alt="Comerzia Logo" class="mb-4 logo-img" />
                <div>
                    <div class="form-group">
                        <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control mb-3" placeholder="Usuario"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <asp:TextBox ID="txtContrasenha" runat="server" CssClass="form-control mb-3" TextMode="Password" placeholder="Contraseña"></asp:TextBox>
                    </div>
                    <asp:Button ID="btnIniciarSesion" runat="server" Text="Iniciar Sesión" CssClass="btn btn-primary btn-block" OnClick="btnIniciarSesion_Click" />
                    <asp:Label ID="lblError" runat="server" CssClass="text-danger mt-3" />
                </div>
            </div>
        </div>
    </form>
</body>
</html>
