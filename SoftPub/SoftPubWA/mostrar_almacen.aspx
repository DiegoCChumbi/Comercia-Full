<%@ Page Title="" Language="C#" MasterPageFile="~/SoftPub.Master" AutoEventWireup="true" CodeBehind="mostrar_almacen.aspx.cs" Inherits="SoftPubWA.mostrar_almacen" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="/Scripts/ComerziaScripts/gestionar-producto.js"></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <!-- Colocaremos aca el nombre del almacen -->
        <!-- El container servira para que el contenido de la pagina se centro y sea responsivo -->
        <div class="card">
            <div class="card-header">
                <div class="col-sm-4">
                    <asp:TextBox ID="txtTitulo" runat="server" Enabled="false" CssClass="fs-1" BorderStyle="None"/>
                </div>
            </div>
            <div class="card-body">
                <!-- Datos del almacen -->
                    <div class="container p-3 my-0 bg-light rounded">
                        <div class="mb-3 row"> <!-- Para modificar el bottom margin -->
                        <asp:Label ID="lblEstado" runat="server" Text="Estado" CssClass="col-sm-3 col-form-label" AssociatedControlID="txtEstadoAlmacen"/>
                        <div class="col-sm-4">
                            <asp:TextBox ID="txtEstadoAlmacen" ForeColor="White" runat="server" Enabled="false" CssClass="form-control" Style="text-align: center"/>
                        </div>
                    </div>
                    <div class="mb-3 row"> <!-- Para modificar el bottom margin -->
                        <asp:Label ID="LblDescripcion" runat="server" Text="Descripcion" CssClass="col-sm-3 col-form-label" AssociatedControlID="txtDescripcionAlmacen"/>
                        <div class="col-sm-4">
                            <asp:TextBox ID="txtDescripcionAlmacen" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                    </div>
                </div>
                <!-- Lugar para colocar formulario -->
                <!-- En este caso, no usaremos formularios -->
                <!-- Trabajaremos como si fuera un HTML normal, con la diferencia en que usaremos Label -->
                <!-- Los enabled es para indicar que no se pueden modificar -->
                <!-- mb indica el margin bottom -->
            
                <!-- Gestion de productos del almacen -->
                <!-- Falta implementar lo del boton de visualizar -->
                <div class="container p-3 my-4 bg-light rounded">
                    <div class="row bottom-9">
                        <div class="col-md-auto">
                            <h4>Gestion de Productos</h4>
                        </div>
                        <div class="col align-content-sm-end">
                            <asp:Button ID="btnAgregarProducto" CssClass="float-end btn btn-primary align-content-lg-end" runat="server"
    Text="Agregar producto" OnClick="btnAgregarProducto_Click"/>
                        </div>
                    </div>
                
                    <div class="container row mt-3 m-2">
                        <asp:GridView ID="dgvProductos" runat="server"
                            AllowPaging="True"
                            PageSize="5"
                            OnPageIndexChanging="dgvProductos_PageIndexChanging"
                            AutoGenerateColumns="false"
                            CssClass="table table-hover table-responsive table-striped">
                            <Columns>
                                <asp:BoundField HeaderText="N°" DataField="idProducto" />
                                <asp:BoundField HeaderText="Cantidad" DataField="stockActual" />
                                <asp:BoundField HeaderText="Fecha" DataField="fechaAlmacenado" />
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-eye'></i>"/>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>

                    </div>
                </div>
            </div>
            <div class="card-footer">
                <asp:Button ID="btnModificar" CssClass="float-end btn btn-primary" runat="server"
                    Text="Editar" OnClick="btnModificar_Click"/>
            </div>
        </div>        
    </div>

    <asp:ScriptManager runat="server"></asp:ScriptManager>
    <!-- Implementacion de modals -->
    <div class="modal" id="form-modal-producto">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Selección de Producto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
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
                                        <asp:TextBox CssClass="form-control" ID="ModalProducto_txtNombreProducto" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="ModalProducto_lbBuscarProducto" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="ModalProducto_lbBuscarProducto_Click" />
                                    </div>
                                </div>
                            </div>

                            <div class="container row">
                                <asp:GridView ID="ModalProducto_gvProducto" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalProducto_gvProducto_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Producto" DataField="nombreProducto" />
                                        <asp:BoundField HeaderText="Precio" DataField="precio" />                                    
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div class="row align-items-center">
                                                     <div class="col-auto">
                                                         <asp:Label CssClass="form-label" runat="server" Text="Stock:"></asp:Label>
                                                     </div>
                                                     <div class="col-sm-3">
                                                          <asp:TextBox CssClass="form-control" ID="ModalProducto_txtStockProducto" runat="server"></asp:TextBox>
                                                     </div>
                                                     <div class="col-auto">
                                                          <asp:LinkButton ID="ModalProducto_lbSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalProducto_lbSeleccionar_Click" CommandArgument='<%# Eval("idProducto") %>' />
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
