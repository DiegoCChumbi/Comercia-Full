<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
    AutoEventWireup="true" CodeBehind="mantenimiento_seccion.aspx.cs"
    Inherits="ComerziaWA.mantenimiento_seccion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Mantenimiento de Sección
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <h2>Mantenimiento de Secciones</h2>
        <div class="container row">
            <asp:GridView
                ID="dgvSeccion"
                runat="server"
                AllowPaging="true"
                PageSize="5"
                OnPageIndexChanging="dgvSeccion_PageIndexChanging"
                AutoGenerateColumns="false"
                CssClass="table table-hover table-responsive table-striped">
                <Columns>
                    <asp:BoundField
                        HeaderText="Id de la Sección"
                        DataField="IdSeccion" />
                    <asp:BoundField
                        HeaderText="Nombre de la Sección"
                        DataField="Nombre_Seccion" />
                    <asp:BoundField
                        HeaderText="Departamento"
                        DataField="Departamento" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton
                                runat="server"
                                Text="<i class='fa-solid fa-edit ps-2'></i>"
                                CommandArgument='<%# Eval("IdSeccion") %>'
                                OnClick="lbModificar_Click" />
                            <asp:LinkButton
                                runat="server"
                                Text="<i class='fa-solid fa-trash ps-2'></i>"
                                CommandArgument='<%# Eval("IdSeccion") %>'
                                OnClick="lbEliminar_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <div class="container row">
            <div class="text-end">
                <asp:Button
                    ID="btnInsertar"
                    CssClass="float-start btn btn-primary"
                    runat="server"
                    Text="Insertar"
                    OnClick="btnInsertar_Click" />
            </div>
        </div>
    </div>
</asp:Content>
