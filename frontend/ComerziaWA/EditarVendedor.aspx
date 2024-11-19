<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
    AutoEventWireup="true" CodeBehind="EditarVendedor.aspx.cs"
    Inherits="ComerziaWA.EditarVendedor" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="form-container green-container">
        <h2>Editar Vendedor</h2>

        <div class="form-columns">
            <!-- Primera columna -->
            <div class="form-column">
                <label for="txtDniVendedor">DNI:</label>
                <asp:TextBox
                    ID="txtDniVendedor"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtNombreCompletoVendedor">Nombre Completo:</label>
                <asp:TextBox
                    ID="txtNombreCompletoVendedor"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtTelefonoVendedor">Teléfono:</label>
                <asp:TextBox
                    ID="txtTelefonoVendedor"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtCorreoVendedor">Correo:</label>
                <asp:TextBox
                    ID="txtCorreoVendedor"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtDireccionVendedor">Dirección:</label>
                <asp:TextBox
                    ID="txtDireccionVendedor"
                    runat="server"
                    CssClass="form-control" />
            </div>

            <!-- Segunda columna -->
            <div class="form-column">
                <label for="ddlEstadoVendedor">Estado:</label>
                <asp:DropDownList
                    ID="ddlEstadoVendedor"
                    runat="server"
                    CssClass="form-control">
                    <asp:ListItem Value="Activo">Activo</asp:ListItem>
                    <asp:ListItem Value="Inactivo">Inactivo</asp:ListItem>
                </asp:DropDownList>

                <label for="txtSalarioVendedor">Salario:</label>
                <asp:TextBox
                    ID="txtSalarioVendedor"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtIngresosVentas">Ingresos Ventas:</label>
                <asp:TextBox
                    ID="txtIngresosVentas"
                    runat="server"
                    CssClass="form-control" />

                <label for="txtPorcentajeComision">Porcentaje Comisión:</label>
                <asp:TextBox
                    ID="txtPorcentajeComision"
                    runat="server"
                    CssClass="form-control" />
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
