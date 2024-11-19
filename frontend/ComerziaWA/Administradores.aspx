<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
    AutoEventWireup="true" CodeBehind="Administradores.aspx.cs"
    Inherits="ComerziaWA.Administradores" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <!-- Blue Section Below the Green Container -->
    <div
        class="form-container blue-container d-flex justify-content-between align-items-center">
        <button
            type="button"
            onclick="window.location.href='ListarAdministradores.aspx'"
            class="btn btn-primary">
            Ver Administradores Ingresados
        </button>
        <button
            type="button"
            onclick="window.location.href='GestionRecursosHumanos.aspx'"
            class="btn btn-secondary">
            Regresar
        </button>
    </div>

    <div class="form-container green-container">
        <h2>Crear Administrador</h2>

        <div class="form-columns">
            <!-- Primera columna -->
            <div class="form-column">
                <label for="txtDniAdministrador">DNI:</label>
                <asp:TextBox
                    ID="txtDniAdministrador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtNombreCompletoAdministrador">
                    Nombre Completo:</label>
                <asp:TextBox
                    ID="txtNombreCompletoAdministrador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtTelefonoAdministrador">Teléfono:</label>
                <asp:TextBox
                    ID="txtTelefonoAdministrador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtCorreoAdministrador">Correo:</label>
                <asp:TextBox
                    ID="txtCorreoAdministrador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtDireccionAdministrador">Dirección:</label>
                <asp:TextBox
                    ID="txtDireccionAdministrador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtSalarioAdministrador">Salario:</label>
                <asp:TextBox
                    ID="txtSalarioAdministrador"
                    runat="server"
                    CssClass="form-control" />
            </div>

            <!-- Segunda columna -->
            <div class="form-column">
                <label for="ddlEstadoAdministrador">Estado:</label>
                <asp:DropDownList
                    ID="ddlEstadoAdministrador"
                    runat="server"
                    CssClass="form-control">
                    <asp:ListItem Value="Activo">Activo</asp:ListItem>
                    <asp:ListItem Value="Inactivo">Inactivo</asp:ListItem>
                </asp:DropDownList>

                <label for="txtNombreUsuarioAdministrador">
                    Nombre de Usuario:</label>
                <asp:TextBox
                    ID="txtNombreUsuarioAdministrador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtContrasenhaAdministrador">Contraseña:</label>
                <asp:TextBox
                    ID="txtContrasenhaAdministrador"
                    runat="server"
                    TextMode="Password"
                    CssClass="form-control" />

                <label for="txtFechaContratacionAdministrador">
                    Fecha de Contratación:</label>
                <asp:TextBox
                    ID="txtFechaContratacionAdministrador"
                    runat="server"
                    CssClass="form-control"
                    TextMode="Date" />

                <label for="ddlElegirAlmacenAdministrador">Almacen:</label>
                <asp:DropDownList
                    ID="ddlElegirAlmacenAdministrador"
                    runat="server"
                    CssClass="form-control">
                </asp:DropDownList>
            </div>
        </div>

        <asp:Button
            ID="btnGuardarAdministrador"
            runat="server"
            Text="Guardar Administrador"
            CssClass="btnGuardar"
            OnClick="btnGuardarAdministrador_Click" />
    </div>

    <!-- Popup -->
    <div
        id="simplePopup"
        style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 300px; padding: 20px; background-color: white; border: 1px solid #ccc; z-index: 1000;">
        <h5>Mensaje</h5>
        <p id="popupMessage"></p>
        <button onclick="closePopup()">Cerrar</button>
    </div>

    <div
        id="popupOverlay"
        style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); z-index: 999;">
    </div>
    <script>
        function showPopup(message) {
            document.getElementById("popupMessage").innerText = message;
            document.getElementById("simplePopup").style.display = "block";
            document.getElementById("popupOverlay").style.display = "block";
        }

        function closePopup() {
            document.getElementById("simplePopup").style.display = "none";
            document.getElementById("popupOverlay").style.display = "none";
            location.reload();
        }
    </script>
</asp:Content>
