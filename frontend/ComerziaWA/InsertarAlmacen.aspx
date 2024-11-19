<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
    AutoEventWireup="true" CodeBehind="InsertarAlmacen.aspx.cs"
    Inherits="ComerziaWA.InsertarAlmacen" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="form-wrapper">
        <div class="form-container">
            <h2>Crear Almacenes</h2>
            <label for="txtNombre">Nombre del Almacen:</label>
            <asp:TextBox
                ID="txtNombre"
                runat="server"
                CssClass="form-control" />

            <label for="txtEstado">Estado:</label>
            <asp:TextBox
                ID="txtEstado"
                runat="server"
                CssClass="form-control" />

            <label for="txtDescripcion">Descripción:</label>
            <asp:TextBox
                ID="txtDescripcion"
                runat="server"
                TextMode="MultiLine"
                Rows="4"
                CssClass="form-control" />

            <asp:Button
                ID="btnGuardarAlmacen"
                runat="server"
                Text="Guardar Almacen"
                CssClass="btnGuardar"
                OnClick="btnGuardarAlmacen_Click" />
        </div>

        <div class="form-container">
            <h2>Crear Productos</h2>
            <label for="txtNombreProducto">Nombre del Producto:</label>
            <asp:TextBox
                ID="txtNombreProducto"
                runat="server"
                CssClass="form-control" />

            <label for="txtPrecioProducto">Precio:</label>
            <asp:TextBox
                ID="txtPrecioProducto"
                runat="server"
                CssClass="form-control" />

            <label for="txtStockMinimoProducto">Stock Mínimo:</label>
            <asp:TextBox
                ID="txtStockMinimoProducto"
                runat="server"
                CssClass="form-control" />

            <asp:Button
                ID="btnGuardarProducto"
                runat="server"
                Text="Guardar Producto"
                CssClass="btnGuardar"
                OnClick="btnGuardarProducto_Click" />
        </div>
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
