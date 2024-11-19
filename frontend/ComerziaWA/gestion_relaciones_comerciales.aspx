<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
    AutoEventWireup="true" CodeBehind="gestion_relaciones_comerciales.aspx.cs"
    Inherits="ComerziaWA.gestion_relaciones_comerciales" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="row">
            <!-- Gestión de Clientes -->
            <div class="col-lg-6 col-md-12">
                <div class="row mb-3">
                    <div class="col-md-auto">
                        <h4>Gestión de Clientes</h4>
                    </div>
                    <div class="col">
                        <asp:Button
                            ID="btnAgregarCliente"
                            CssClass="float-end btn btn-primary"
                            runat="server"
                            Text="Agregar Cliente" />
                    </div>
                </div>

                <div class="col-form-label">
                    <asp:GridView
                        ID="dgvClientes"
                        runat="server"
                        AllowPaging="True"
                        PageSize="15"
                        OnPageIndexChanging="dgvClientes_PageIndexChanging"
                        AutoGenerateColumns="false"
                        CssClass="table table-hover table-responsive table-striped">
                        <Columns>
                            <asp:BoundField
                                HeaderText="ID"
                                DataField="id" />
                            <asp:BoundField
                                HeaderText="Nombre"
                                DataField="nombre" />
                            <asp:BoundField
                                HeaderText="Correo"
                                DataField="email" />
                            <asp:BoundField
                                HeaderText="Teléfono"
                                DataField="telefono" />
                            <asp:BoundField
                                HeaderText="Dirección"
                                DataField="direccion" />
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton
                                        runat="server"
                                        Text="<i class='fa-solid fa-eye'></i>"
                                        OnClick="btnVerCliente_Click"
                                        CommandArgument='<%# Eval("id") %>' />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
            </div>

            <!-- Gestión de Proveedores -->
            <div class="col-lg-6 col-md-12">
                <div class="row mb-3">
                    <div class="col-md-auto">
                        <h4>Gestión de Proveedores</h4>
                    </div>
                    <div class="col">
                        <asp:Button
                            ID="btnAgregarProveedor"
                            CssClass="float-end btn btn-primary"
                            runat="server"
                            Text="Agregar Proveedor" />
                    </div>
                </div>

                <div class="col-form-label">
                    <asp:GridView
                        ID="dgvProveedores"
                        runat="server"
                        AllowPaging="True"
                        PageSize="15"
                        OnPageIndexChanging="dgvProveedores_PageIndexChanging"
                        AutoGenerateColumns="false"
                        CssClass="table table-hover table-responsive table-striped">
                        <Columns>
                            <asp:BoundField
                                HeaderText="ID"
                                DataField="id" />
                            <asp:BoundField
                                HeaderText="Nombre"
                                DataField="nombre" />
                            <asp:BoundField
                                HeaderText="Correo"
                                DataField="email" />
                            <asp:BoundField
                                HeaderText="Teléfono"
                                DataField="telefono" />
                            <asp:BoundField
                                HeaderText="Dirección"
                                DataField="direccion" />
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton
                                        runat="server"
                                        Text="<i class='fa-solid fa-eye'></i>"
                                        OnClick="btnVerProveedor_Click"
                                        CommandArgument='<%# Eval("id") %>' />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
            </div>
        </div>
    </div>

    <!-- Creamos dos paginas, una para editar proveedor, otra para editar cliente -->
</asp:Content>
