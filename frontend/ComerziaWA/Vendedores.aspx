<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="Vendedores.aspx.cs"
Inherits="ComerziaWA.Vendedores" %>
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
            onclick="window.location.href='ListarVendedores.aspx'"
            class="btn btn-primary"
        >
            Ver Vendedores
        </button>
    </div>

    <div class="form-container green-container">
        <h2>Crear Vendedor</h2>

        <div class="form-columns">
            <!-- Primera columna -->
            <div class="form-column">
                <label for="txtDniVendedor">DNI:</label>
                <asp:TextBox
                    ID="txtDniVendedor"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtNombreCompletoVendedor">Nombre Completo:</label>
                <asp:TextBox
                    ID="txtNombreCompletoVendedor"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtTelefonoVendedor">Teléfono:</label>
                <asp:TextBox
                    ID="txtTelefonoVendedor"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtCorreoVendedor">Correo:</label>
                <asp:TextBox
                    ID="txtCorreoVendedor"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtDireccionVendedor">Dirección:</label>
                <asp:TextBox
                    ID="txtDireccionVendedor"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtSalarioVendedor">Salario:</label>
                <asp:TextBox
                    ID="txtSalarioVendedor"
                    runat="server"
                    CssClass="form-control"
                />
            </div>

            <!-- Segunda columna -->
            <div class="form-column">
                <label for="ddlEstadoVendedor">Estado:</label>
                <asp:DropDownList
                    ID="ddlEstadoVendedor"
                    runat="server"
                    CssClass="form-control"
                >
                    <asp:ListItem Value="Activo">Activo</asp:ListItem>
                    <asp:ListItem Value="Inactivo">Inactivo</asp:ListItem>
                </asp:DropDownList>

                <label for="txtNombreUsuarioVendedor">Nombre de Usuario:</label>
                <asp:TextBox
                    ID="txtNombreUsuarioVendedor"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtContrasenhaVendedor">Contraseña:</label>
                <asp:TextBox
                    ID="txtContrasenhaVendedor"
                    runat="server"
                    TextMode="Password"
                    CssClass="form-control"
                />

                <label for="txtFechaContratacionVendedor"
                    >Fecha de Contratación:</label
                >
                <asp:TextBox
                    ID="txtFechaContratacionVendedor"
                    runat="server"
                    CssClass="form-control"
                    TextMode="Date"
                />

                <label for="txtIngresosVentas">Ingresos Ventas:</label>
                <asp:TextBox
                    ID="txtIngresosVentas"
                    runat="server"
                    CssClass="form-control"
                />

                <label for="txtPorcentajeComision">Porcentaje Comisión:</label>
                <asp:TextBox
                    ID="txtPorcentajeComision"
                    runat="server"
                    CssClass="form-control"
                />
            </div>
        </div>

        <asp:Button
            ID="btnGuardarVendedor"
            runat="server"
            Text="Guardar"
            CssClass="btnGuardar"
            OnClick="btnGuardarVendedor_Click"
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
            // O eliminar location.reload(); si no deseas recargar la página
        }
    </script>
</asp:Content>
