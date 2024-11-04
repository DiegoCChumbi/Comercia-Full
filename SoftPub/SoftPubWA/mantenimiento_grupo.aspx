<%@ Page Title="" Language="C#" MasterPageFile="~/SoftPub.Master" AutoEventWireup="true" CodeBehind="mantenimiento_grupo.aspx.cs" Inherits="SoftPubWA.mantenimiento_grupo" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Mantenimiento de Grupos de Investigación
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
        <div class="container">
            <h2>Mantenimiento de Grupos de Investigación</h2>
            <div class="container row">
                <asp:GridView ID="dgvGrupo" runat="server" AllowPaging="true" PageSize="5" OnPageIndexChanging="dgvGrupo_PageIndexChanging" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                    <Columns>
                        <asp:BoundField HeaderText="Grupo de Investigación" DataField="Nombre"/>
                        <asp:BoundField HeaderText="Línea de Investigación" DataField="Lineas[0].Nombre"/>
                        <asp:BoundField HeaderText="# Productos" DataField="Lineas[0].Cantidad_productos"/>    
                    </Columns>
                </asp:GridView>
            </div>
            <div class="card-footer">
                <asp:Button ID="btnRegistroGrupo" CssClass="float-start btn btn-primary" runat="server" Text="Registro grupo" OnClick="btnRegistroGrupo_Click"/>
                <asp:Button ID="btnRegistroProduccion" CssClass="float-end btn btn-secondary" runat="server" Text="Registro producción" OnClick="btnRegistroProduccion_Click"/>
            </div>
        </div>
</asp:Content>
