<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="TrabajadorDeAlmacen.aspx.cs"
Inherits="ComerziaWA.TrabajadorDeAlmacen" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div
        class="form-container blue-container d-flex justify-content-between align-items-center"
    >
        <button
            type="button"
            onclick="window.location.href='GestionRecursosHumanos.aspx'"
            class="btn btn-secondary"
        >
            Regresar
        </button>
        <button
            type="button"
            onclick="window.location.href='ListarTrabajadoresAlmacen.aspx'"
            class="btn btn-primary"
        >
            Ver Trabajadores de Almacén
        </button>
    </div>

    <div class="form-container green-container">
        <h2>Crear Trabajador de Almacen</h2>

        <div class="form-columns">
            <!-- Primera columna -->
            <div class="form-column">
                <label for="txtDniTrabajador">DNI:</label>
                <asp:TextBox
                    ID="txtDniTrabajador"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtNombreCompletoTrabajador"
                    >Nombre Completo:</label
                >
                <asp:TextBox
                    ID="txtNombreCompletoTrabajador"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtTelefonoTrabajador">Teléfono:</label>
                <asp:TextBox
                    ID="txtTelefonoTrabajador"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtCorreoTrabajador">Correo:</label>
                <asp:TextBox
                    ID="txtCorreoTrabajador"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtDireccionTrabajador">Dirección:</label>
                <asp:TextBox
                    ID="txtDireccionTrabajador"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtSalarioTrabajador">Salario:</label>
                <asp:TextBox
                    ID="txtSalarioTrabajador"
                    runat="server"
                    CssClass="form-control"
                />
            </div>

            <!-- Segunda columna -->
            <div class="form-column">
                <label for="ddlEstadoTrabajador">Estado:</label>
                <asp:DropDownList
                    ID="ddlEstadoTrabajador"
                    runat="server"
                    CssClass="form-control"
                >
                    <asp:ListItem Value="Activo">Activo</asp:ListItem>
                    <asp:ListItem Value="Inactivo">Inactivo</asp:ListItem>
                </asp:DropDownList>

                <label for="txtNombreUsuarioTrabajador"
                    >Nombre de Usuario:</label
                >
                <asp:TextBox
                    ID="txtNombreUsuarioTrabajador"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtContrasenhaTrabajador">Contraseña:</label>
                <asp:TextBox
                    ID="txtContrasenhaTrabajador"
                    runat="server"
                    TextMode="Password"
                    CssClass="form-control"
                />

                <label for="txtFechaContratacionTrabajador"
                    >Fecha de Contratación:</label
                >
                <asp:TextBox
                    ID="txtFechaContratacionTrabajador"
                    runat="server"
                    CssClass="form-control"
                    TextMode="Date"
                />

                <label for="ddlElegirAlmacenTrabajador">Almacen:</label>
                <asp:DropDownList
                    ID="ddlElegirAlmacenTrabajador"
                    runat="server"
                    CssClass="form-control"
                >
                </asp:DropDownList>

                <label for="ddlPermisoTrabajador"
                    >¿Tiene permiso para conducir?:</label
                >
                <asp:DropDownList
                    ID="ddlPermisoTrabajador"
                    runat="server"
                    CssClass="form-control"
                >
                    <asp:ListItem Value="true">SI</asp:ListItem>
                    <asp:ListItem Value="false">NO</asp:ListItem>
                </asp:DropDownList>
            </div>
        </div>

        <asp:Button
            ID="btnGuardarAdministrador"
            runat="server"
            Text="Guardar"
            CssClass="btnGuardar"
            OnClick="btnGuardarAdministrador_Click"
        />
    </div>

    <!-- Popup -->
    <div
        id="simplePopup"
        style="
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            padding: 20px;
            background-color: white;
            border: 1px solid #ccc;
            z-index: 1000;
        "
    >
        <h5>Mensaje</h5>
        <p id="popupMessage"></p>
        <button onclick="closePopup()">Cerrar</button>
    </div>

    <div
        id="popupOverlay"
        style="
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 999;
        "
    ></div>
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
