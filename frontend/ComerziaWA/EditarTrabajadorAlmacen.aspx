<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
    AutoEventWireup="true" CodeBehind="EditarTrabajadorAlmacen.aspx.cs"
    Inherits="ComerziaWA.EditarTrabajadorAlmacen" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="form-container green-container">
        <h2>Editar Trabajador de Almacén</h2>

        <div class="form-columns">
            <!-- Primera columna -->
            <div class="form-column">
                <label for="txtDniTrabajador">DNI:</label>
                <asp:TextBox
                    ID="txtDniTrabajador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtNombreCompletoTrabajador">
                    Nombre Completo:</label>
                <asp:TextBox
                    ID="txtNombreCompletoTrabajador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtTelefonoTrabajador">Teléfono:</label>
                <asp:TextBox
                    ID="txtTelefonoTrabajador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtCorreoTrabajador">Correo:</label>
                <asp:TextBox
                    ID="txtCorreoTrabajador"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtDireccionTrabajador">Dirección:</label>
                <asp:TextBox
                    ID="txtDireccionTrabajador"
                    runat="server"
                    CssClass="form-control" />
            </div>

            <!-- Segunda columna -->
            <div class="form-column">
                <label for="ddlEstadoTrabajador">Estado:</label>
                <asp:DropDownList
                    ID="ddlEstadoTrabajador"
                    runat="server"
                    CssClass="form-control">
                    <asp:ListItem Value="Activo">Activo</asp:ListItem>
                    <asp:ListItem Value="Inactivo">Inactivo</asp:ListItem>
                </asp:DropDownList>

                <label for="txtSalarioTrabajador">Salario:</label>
                <asp:TextBox
                    ID="txtSalarioTrabajador"
                    runat="server"
                    CssClass="form-control" />

                <label for="ddlElegirAlmacenTrabajador">Almacen:</label>
                <asp:DropDownList
                    ID="ddlElegirAlmacenTrabajador"
                    runat="server"
                    CssClass="form-control">
                </asp:DropDownList>

                <label for="ddlPermisoTrabajador">Tiene permiso:</label>
                <asp:DropDownList
                    ID="ddlPermisoTrabajador"
                    runat="server"
                    CssClass="form-control">
                    <asp:ListItem Value="true">SI</asp:ListItem>
                    <asp:ListItem Value="false">NO</asp:ListItem>
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
