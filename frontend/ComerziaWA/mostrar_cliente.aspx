<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="mostrar_cliente.aspx.cs"
Inherits="ComerziaWA.mostrar_cliente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <script
        src="Public/Scripts/ComerziaScripts/gestionar-producto.js"
    ></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <!-- Colocaremos aca el nombre del cliente -->
        <div class="card">
            <div class="card-header">
                <div class="col-sm-4">
                    <asp:TextBox
                        ID="txtTitulo"
                        runat="server"
                        Enabled="false"
                        CssClass="fs-1"
                        BorderStyle="None"
                    />
                </div>
            </div>
            <div class="card-body">
                <!-- Datos del cliente -->
                <div class="container">
                    <div class="mb-3 row">
                        <!-- Para modificar el bottom margin -->
                        <asp:Label
                            ID="lblTelefono"
                            runat="server"
                            Text="Telefono"
                            CssClass="col-sm-3 col-form-label"
                            AssociatedControlID="txtTelefono"
                        />
                        <div class="col-sm-4">
                            <asp:TextBox
                                ID="txtTelefono"
                                runat="server"
                                Enabled="false"
                                CssClass="form-control"
                            />
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <!-- Para modificar el bottom margin -->
                        <asp:Label
                            ID="LblDireccion"
                            runat="server"
                            Text="Direccion"
                            CssClass="col-sm-3 col-form-label"
                            AssociatedControlID="txtDireccion"
                        />
                        <div class="col-sm-4">
                            <asp:TextBox
                                ID="txtDireccion"
                                runat="server"
                                Enabled="false"
                                CssClass="form-control"
                            />
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <!-- Para modificar el bottom margin -->
                        <asp:Label
                            ID="LblEmail"
                            runat="server"
                            Text="Correo"
                            CssClass="col-sm-3 col-form-label"
                            AssociatedControlID="txtEmail"
                        />
                        <div class="col-sm-4">
                            <asp:TextBox
                                ID="txtEmail"
                                runat="server"
                                Enabled="false"
                                CssClass="form-control"
                            />
                        </div>
                    </div>
                </div>

                <!-- Apartado de representantes -->

                <div class="row">
                    <!-- Gestión de Representantes -->
                    <div class="col-lg-6 col-md-12 p-3 bg-light rounded-3 px-3">
                        <div class="row mb-3">
                            <div class="col-md-auto">
                                <h4>Gestión Representantes</h4>
                            </div>
                            <div class="col">
                                <asp:Button
                                    ID="btnAgregarRepresentante"
                                    CssClass="float-end btn btn-primary"
                                    runat="server"
                                    Text="Agregar Representante"
                                    OnClick="btnAgregarRepresentante_Click"
                                />
                            </div>
                        </div>

                        <div class="col-form-label">
                            <asp:GridView
                                ID="dgvRepresentantes"
                                runat="server"
                                AllowPaging="True"
                                PageSize="15"
                                OnPageIndexChanging="dgvRepresentantes_PageIndexChanging1"
                                AutoGenerateColumns="false"
                                CssClass="table table-hover table-responsive table-striped"
                            >
                                <Columns>
                                    <asp:BoundField
                                        HeaderText="ID"
                                        DataField="id"
                                    />
                                    <asp:BoundField
                                        HeaderText="Nombre"
                                        DataField="Nombre"
                                    />
                                    <asp:BoundField
                                        HeaderText="Correo"
                                        DataField="correo"
                                    />
                                    <asp:BoundField
                                        HeaderText="Teléfono"
                                        DataField="telefono"
                                    />
                                    <asp:TemplateField>
                                        <ItemTemplate>
                                            <asp:LinkButton
                                                runat="server"
                                                Text="<i class='fa-solid fa-eye'></i>"
                                                OnClick="btnVerRepresentante_Click"
                                                CommandArgument='<%# Eval("id") %>'
                                            />
                                            <asp:LinkButton
                                                ID="btnEliminar"
                                                runat="server"
                                                Text="<i class='fa-solid fa-trash'></i>"
                                                Visible="False"
                                                OnClick="btnEliminarRepresentante_Click"
                                                CommandArgument='<%# Eval("id") %>'
                                            />
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>
                    </div>

                    <!-- Gestión de Documentos -->

                    <div class="col-lg-6 col-md-12 p-3 bg-light rounded-3 px-3">
                        <div class="row mb-3">
                            <div class="col-md-auto">
                                <h4>Gestión Documentos</h4>
                            </div>
                        </div>

                        <div class="col-form-label">
                            <asp:GridView
                                ID="dgvDocumentos"
                                runat="server"
                                AllowPaging="True"
                                PageSize="15"
                                OnPageIndexChanging="dgvDocumentos_PageIndexChanging"
                                AutoGenerateColumns="false"
                                CssClass="table table-hover table-responsive table-striped"
                            >
                                <Columns>
                                    <asp:BoundField
                                        HeaderText="ID"
                                        DataField="idDocumento"
                                    />
                                    <asp:BoundField
                                        HeaderText="tipo"
                                        DataField="tipo"
                                    />
                                    <asp:TemplateField>
                                        <ItemTemplate></ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <asp:Button
                    ID="btnRegresar"
                    CssClass="float-start btn btn-primary"
                    runat="server"
                    Text="Regresar"
                    OnClick="btnRegresar_Click"
                />
                <asp:Button
                    ID="btnModificar"
                    CssClass="float-end btn btn-primary"
                    runat="server"
                    Text="Editar"
                    OnClick="btnModificar_Click"
                />
            </div>
        </div>
    </div>

    <asp:ScriptManager runat="server"></asp:ScriptManager>

    <div class="modal" id="form-modal-insertar-representante">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Selección de Representante</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Cerrar"
                    ></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row align-items-center">
                                    <div class="col-auto">
                                        <asp:Label
                                            CssClass="form-label"
                                            runat="server"
                                            Text="Ingresar nombre:"
                                        ></asp:Label>
                                    </div>
                                    <div class="col-sm-3">
                                        <asp:TextBox
                                            CssClass="form-control"
                                            ID="ModalRepresentante_txtRepresentanteNombre"
                                            runat="server"
                                        ></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton
                                            ID="ModalRepresentante_lbBuscarRepresentante"
                                            runat="server"
                                            CssClass="btn btn-info"
                                            Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar"
                                            OnClick="ModalRepresentante_lbBuscarRepresentante_Click"
                                        />
                                    </div>
                                </div>
                            </div>

                            <div class="container row">
                                <asp:GridView
                                    ID="ModalRepresentante_gvRepresentante"
                                    runat="server"
                                    AllowPaging="true"
                                    PageSize="5"
                                    AutoGenerateColumns="false"
                                    CssClass="table table-hover table-responsive table-striped"
                                    OnPageIndexChanging="ModalRepresentante_gvRepresentante_PageIndexChanging"
                                >
                                    <Columns>
                                        <asp:BoundField
                                            HeaderText="Nombre completo:"
                                            DataField="Nombre"
                                        />
                                        <asp:BoundField
                                            HeaderText="Telefono:"
                                            DataField="telefono"
                                        />
                                        <asp:BoundField
                                            HeaderText="DNI:"
                                            DataField="dni"
                                        />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div
                                                    class="row align-items-center"
                                                >
                                                    <div class="col-auto">
                                                        <asp:LinkButton
                                                            ID="ModalRepresentante_lbRepresentante"
                                                            class="btn btn-success"
                                                            runat="server"
                                                            Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar"
                                                            OnClick="ModalRepresentante_lbRepresentanteSeleccionar_Click"
                                                            CommandArgument='<%# Eval("id") %>'
                                                        />
                                                    </div>
                                                </div>
                                            </ItemTemplate>
                                        </asp:TemplateField>
                                    </Columns>
                                </asp:GridView>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
