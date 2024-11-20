<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="ListarVendedores.aspx.cs"
Inherits="ComerziaWA.ListarVendedores" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div
        class="form-container blue-container d-flex justify-content-between align-items-center"
    >
        <button
            type="button"
            onclick="window.location.href='Vendedores.aspx'"
            class="btn btn-secondary"
        >
            Regresar
        </button>
    </div>
    <h2 class="titulo-regular">Lista de Vendedores</h2>
    <div class="table-container">
        <asp:GridView
            ID="gvVendedores"
            runat="server"
            AutoGenerateColumns="False"
            CssClass="table table-hover table-responsive table-striped"
            OnRowCommand="gvVendedores_RowCommand"
            OnPageIndexChanging="gvVendedores_PageIndexChanging"
            AllowPaging="True"
            PageSize="10"
        >
            <Columns>
                <asp:BoundField DataField="Dni" HeaderText="Dni" />
                <asp:BoundField
                    DataField="Nombre"
                    HeaderText="Nombre Completo"
                />
                <asp:BoundField DataField="Telefono" HeaderText="Teléfono" />
                <asp:BoundField DataField="Correo" HeaderText="Correo" />
                <asp:TemplateField HeaderText="Acciones">
                    <ItemTemplate>
                        <!-- Ícono para Editar -->
                        <asp:LinkButton
                            ID="btnEditar"
                            runat="server"
                            CommandName="Editar"
                            CommandArgument='<%# Eval("id") %>'
                            CssClass="text-warning"
                        >
                            <i class="fa-solid fa-pencil"></i>
                        </asp:LinkButton>

                        <!-- Ícono para Eliminar -->
                        <asp:LinkButton
                            ID="btnEliminar"
                            runat="server"
                            CommandName="Eliminar"
                            CommandArgument='<%# Eval("id") + "," + Eval("id") %>'
                            CssClass="text-danger ms-3"
                        >
                            <i class="fa-solid fa-trash"></i>
                        </asp:LinkButton>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
</asp:Content>
