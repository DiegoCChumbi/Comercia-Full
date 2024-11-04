<%@ Page Title="" Language="C#" MasterPageFile="~/SoftPub.Master" AutoEventWireup="true" CodeBehind="mantenimiento_especialidad.aspx.cs" Inherits="SoftPubWA.mantenimiento_especialidad" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Mantenimiento de Especialidad
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <h2>Mantenimiento de Especialidad</h2>
        <div class="container row">
            <asp:GridView ID="dgvEspecialidad" runat="server" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                <Columns>
                    <asp:BoundField HeaderText="Id de la Especialidad" DataField="IdEspecialidad"/>
                    <asp:BoundField HeaderText="Nombre de la Especialidad" DataField="Nombre_especialidad"/>
                    <asp:BoundField HeaderText="Facultad" DataField="Facultad"/>
                    <asp:BoundField HeaderText="Nivel" DataField="Nivel"/>
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>"  CommandArgument='<%# Eval("IdEspecialidad") %>' OnClick="lbModificar_Click" />
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" CommandArgument='<%# Eval("IdEspecialidad") %>' OnClick="lbEliminar_Click"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <div class="container row">
            <div class="text-end">
                <asp:Button ID="btnInsertar" CssClass="float-start btn btn-primary" runat="server" Text="Insertar" OnClick="btnInsertar_Click" />
            </div>
        </div>
    </div>
</asp:Content>
