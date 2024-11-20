<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="gestion_almacen.aspx.cs"
Inherits="ComerziaWA.gestion_almacen" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestion de Almacen
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <h2>Gestion de almacen</h2>
        <div class="container row">
            <asp:GridView
                ID="dgvAlmacenes"
                runat="server"
                AllowPaging="True"
                PageSize="10"
                OnPageIndexChanging="dgvAlmacenes_PageIndexChanging"
                AutoGenerateColumns="false"
                CssClass="table table-hover table-responsive table-striped"
                OnRowDataBound="dgvAlmacenes_RowEstado"
            >
                <Columns>
                    <asp:BoundField HeaderText="N°" DataField="id" />
                    <asp:BoundField HeaderText="Nombre" DataField="nombre" />
                    <asp:BoundField
                        HeaderText="Descripción"
                        DataField="descripcion"
                    />
                    <asp:BoundField HeaderText="Estado" DataField="estado" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton
                                runat="server"
                                Text="<i class='fa-solid fa-eye'></i>"
                                OnClick="btnVer_Click"
                                CommandArgument='<%# Eval("Id") %>'
                            />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>
