<%@ Page Title="" Language="C#" MasterPageFile="~/SoftPub.Master" AutoEventWireup="true" CodeBehind="gestionar_produccion.aspx.cs" Inherits="SoftPubWA.gestionar_produccion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Registro de productividad
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Scripts/SoftPub/gestionar_grupos_produccion.js"></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Registro de productividad</h2>
            </div>
            <div class="card-body">
                <div class="card border">
                    <div class="card-header bg-light">
                        <h5 class="card-title mb-0">Información del Grupo de Investigación</h5>
                    </div>
                    <div class="card-body">
                        <div class="mb-3 row">
                            <asp:Label ID="lblIdGrupo" runat="server" Text="Id del Grupo:" CssClass="col-sm-2 col-form-label" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtIdGrupo" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblNombreGrupo" runat="server" Text="Nombre del Grupo:" CssClass="col-sm-2 col-form-label" />
                            <div class="col-sm-4">
                                <asp:TextBox ID="txtNombreGrupo" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                            <asp:Button ID="btnBuscarGrupo" CssClass="btn btn-primary col-sm-2" runat="server" Text="Buscar Grupo" OnClick="btnBuscarGrupo_Click" />
                        </div>
                    </div>
                    <div class="card-header bg-light">
                        <h5 class="card-title mb-0">Información de la Línea de Investigación</h5>
                    </div>
                    <div class="card-body">
                        <div class="mb-3 row">
                            <asp:Label ID="lblIdLinea" runat="server" Text="Id de la línea:" CssClass="col-sm-2 col-form-label" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtIdLinea" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblNombreLinea" runat="server" Text="Nombre de la línea:" CssClass="col-sm-2 col-form-label" />
                            <div class="col-sm-4">
                                <asp:TextBox ID="txtNombreLinea" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                            <asp:Button ID="btnBuscarLinea" CssClass="btn btn-primary col-sm-2" runat="server" Text="Buscar Línea" OnClick="btnBuscarLinea_Click" />
                        </div>
                    </div>
                </div>
                <div class="card border">
                    <div class="card-header bg-light">
                        <h5 class="card-title mb-0">Detalle de la Publicación</h5>
                    </div>
                    <div class="card-body">
                        <div class="mb-3 row">
                            <asp:Label ID="lblIdPublicacion" runat="server" Text="Id Publicación:" CssClass="col-sm-2 col-form-label" />
                            <div class="col-sm-3">
                                <asp:TextBox ID="txtIdPublicacion" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                            <asp:Button ID="btnBuscarPublicacion" CssClass="btn btn-primary col-sm-2" runat="server" Text="Buscar Publicación" OnClick="btnBuscarPublicacion_Click"/>
                        </div>
                        <div class="mb-3 row">
                            <asp:Label ID="lblTituloPublicacion" runat="server" Text="Título:" CssClass="col-sm-2 col-form-label" />
                            <div class="col-sm-5">
                                <asp:TextBox ID="txtTituloPublicacion" runat="server" Enabled="false" CssClass="form-control" />
                            </div>
                            <div class="col-sm-3">
                                <asp:LinkButton ID="lbAgregarPublicacion" CssClass="btn btn-success" runat="server" Text="Agregar" OnClick="lbAgregarPublicacion_Click"/>
                            </div>
                        </div>                                                
                        <div class="row">
                            <asp:GridView ID="gvPublicaciones" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                                <Columns>
                                    <asp:BoundField HeaderText="Id Publicación" DataField="Publicacion.IdPublicacion" />
                                    <asp:BoundField HeaderText="Título" DataField="Publicacion.Titulo" />                                                                      
                                    <asp:TemplateField>
                                        <ItemTemplate>
                                            <asp:LinkButton  ID="lbEliminarPublicacion" runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" CommandArgument='<%# Eval("Publicacion.IdPublicacion") %>' OnClick="lbEliminarPublicacion_Click" />
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer clearfix">
                <asp:Button ID="btnRegresar" runat="server" Text="Regresar"
                    CssClass="float-start btn btn-secondary" OnClick="btnRegresar_Click" />
                <asp:Button ID="btnGuardar" runat="server" Text="Guardar"
                    CssClass="float-end btn btn-primary" OnClick="btnGuardar_Click" />
            </div>
        </div>
    </div>
    
    <asp:ScriptManager runat="server"></asp:ScriptManager>

    <div class="modal" id="form-modal-grupo">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Búsqueda de Grupos</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row align-items-center">
                                    <div class="col-auto">
                                        <asp:Label CssClass="form-label" runat="server" Text="Ingresar nombre:"></asp:Label>
                                    </div>
                                    <div class="col-sm-3">
                                        <asp:TextBox CssClass="form-control" ID="ModalGrupo_txtNombreGrupo" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="ModalGrupo_lbBuscarGrupo" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="ModalGrupo_lbBuscarGrupo_Click" />
                                    </div>
                                </div>
                            </div>
                            <div class="container row">
                                <asp:GridView ID="ModalGrupo_gvClientes" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalGrupo_gvClientes_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Id" DataField="IdGrupo" />
                                        <asp:BoundField HeaderText="Nombre" DataField="Nombre" />
                                        <asp:BoundField HeaderText="Sigla" DataField="Sigla" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <asp:LinkButton ID="ModalGrupo_lbSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalGrupo_lbSeleccionar_Click" CommandArgument='<%# Eval("IdGrupo") %>' />
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

    <div class="modal" id="form-modal-linea">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Selección de línea de investigación</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>                        
                            <div class="container row">
                                <asp:GridView ID="ModalLinea_gvLineas" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalLinea_gvLineas_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Id" DataField="IdLinea" />
                                        <asp:BoundField HeaderText="Línea" DataField="Nombre" />                                    
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <asp:LinkButton ID="ModalLinea_lbSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalLinea_lbSeleccionar_Click" CommandArgument='<%# Eval("IdLinea") %>' />
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

    <div class="modal" id="form-modal-publicacion">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Búsqueda de Publicaciones</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate>
                            <div class="container row pb-3 pt-3">
                                <div class="row align-items-center">
                                    <div class="col-auto">
                                        <asp:Label CssClass="form-label" runat="server" Text="Ingresar título:"></asp:Label>
                                    </div>
                                    <div class="col-sm-3">
                                        <asp:TextBox CssClass="form-control" ID="ModalPublicacion_txtTitulo" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="ModalPublicacion_lbBuscarPublicacion" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="ModalPublicacion_lbBuscarPublicacion_Click" />
                                    </div>
                                </div>
                            </div>
                            <div class="container row">
                                <asp:GridView ID="ModalPublicacion_gvPublicacion" runat="server" AllowPaging="true" PageSize="15" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalPublicacion_gvPublicacion_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Id" DataField="IdPublicacion" />
                                        <asp:BoundField HeaderText="Título" DataField="Titulo" />
                                        <asp:BoundField HeaderText="Tipo" DataField="Tipo" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <asp:LinkButton ID="ModalPublicacion_lbSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalPublicacion_lbSeleccionar_Click" CommandArgument='<%# Eval("IdPublicacion") %>' />
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
