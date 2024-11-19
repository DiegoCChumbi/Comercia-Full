﻿<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="ListarTrabajadoresAlmacen.aspx.cs"
Inherits="ComerziaWA.ListarTrabajadoresAlmacen" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <h2>Lista de Trabajadores de Almacen</h2>
    <asp:GridView
        ID="gvVendedores"
        runat="server"
        AutoGenerateColumns="False"
        CssClass="gvVendedores"
        OnRowCommand="gvVendedores_RowCommand"
    >
        <Columns>
            <asp:BoundField DataField="Dni" HeaderText="Dni" />
            <asp:BoundField DataField="Nombre" HeaderText="Nombre Completo" />
            <asp:BoundField DataField="Telefono" HeaderText="Teléfono" />
            <asp:BoundField DataField="Correo" HeaderText="Correo" />
            <asp:TemplateField HeaderText="Acciones">
                <ItemTemplate>
                    <asp:Button
                        ID="btnEditar"
                        runat="server"
                        Text="Editar"
                        CssClass="btn btn-warning btn-sm"
                        CommandName="Editar"
                        CommandArgument='<%# Eval("idEmpleado") %>'
                    />
                    <asp:Button
                        ID="btnEliminar"
                        runat="server"
                        Text="Eliminar"
                        CssClass="btn btn-danger btn-sm"
                        CommandName="Eliminar"
                        CommandArgument='<%# Eval("idEmpleado") + "," + Eval("idPersona") %>'
                    />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
</asp:Content>