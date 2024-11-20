<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="GestionComercial.aspx.cs"
Inherits="ComerziaWA.GestionComercial" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestión Comercial
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <h2>Mantenimiento de Documentos</h2>
        <div class="container row">
            <asp:GridView
                ID="dgvDocumento"
                runat="server"
                AllowPaging="true"
                PageSize="5"
                OnPageIndexChanging="dgvDocumento_PageIndexChanging"
                AutoGenerateColumns="false"
                CssClass="table table-hover table-responsive table-striped"
            >
                <Columns>
                    <asp:BoundField
                        HeaderText="Id del documento"
                        DataField="Id"
                    />
                    <asp:BoundField
                        HeaderText="Empresa"
                        DataField="IdEmpresa"
                    />
                    <asp:BoundField HeaderText="Tipo" DataField="Tipo" />
                    <asp:BoundField HeaderText="Estado" DataField="Estado" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton
                                runat="server"
                                Text="<i class='fa-solid fa-eye'></i>"
                                CommandArgument='<%# Eval("Id") %>'
                                OnClick="lbVer_Click"
                            />
                            <asp:LinkButton
                                runat="server"
                                Text="<i class='fa-solid fa-trash ps-2'></i>"
                                CommandArgument='<%# Eval("Id") %>'
                                OnClick="lbEliminar_Click"
                            />
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
                    OnClick="btnInsertar_Click"
                />
            </div>
        </div>
    </div>
</asp:Content>
