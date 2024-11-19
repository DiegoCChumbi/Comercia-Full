<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
    AutoEventWireup="true" CodeBehind="EditarAdministrador.aspx.cs"
    Inherits="ComerziaWA.EditarAdministrador" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="form-container green-container">
        <h2>Editar Administrador</h2>

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

                <label for="txtSalarioAdministrador">Salario:</label>
                <asp:TextBox
                    ID="txtSalarioAdministrador"
                    runat="server"
                    CssClass="form-control" />

                <label for="ddlElegirAlmacenAdministrador">Almacen:</label>
                <asp:DropDownList
                    ID="ddlElegirAlmacenAdministrador"
                    runat="server"
                    CssClass="form-control">
                </asp:DropDownList>
            </div>
        </div>

        <asp:Button
            ID="btnGuardarVendedor"
            runat="server"
            Text="Guardar Vendedor"
            CssClass="btnGuardar"
            OnClick="btnGuardarVendedor_Click" />
    </div>
</asp:Content>
