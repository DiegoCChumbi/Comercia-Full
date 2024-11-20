<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master" AutoEventWireup="true" CodeBehind="gestionar_documento.aspx.cs" Inherits="ComerziaWA.gestionar_documento" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestionar Documentos
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Public/Scripts/ComerziaScripts/gestionar-producto.js"></script>
    <script src="Public/Scripts/ComerziaScripts/gestionar-documento.js"></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Creación de documentos</h2>
            </div>
            <div class="card-body">
                <div class="container p-3 my-0 bg-light rounded">
                    <div class="mb-3 row">
                        <asp:Label ID="lblEmpresa" runat="server" Text="Empresa:" CssClass="col-sm-3 col-form-label" />
                        <div class="col-sm-3">
                            <asp:TextBox ID="txtEmpresa" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                        <div class="col">
                            <asp:Button ID="btnBuscarEmpresa" CssClass="btn btn-primary" runat="server" Text="Buscar Empresa" OnClick="btnBuscarEmpresa_Click" />
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <asp:Label ID="lblEstado" runat="server" Text="Estado:" CssClass="col-sm-3 col-form-label"></asp:Label>
                        <div class="col-1">
                            <div class="form-check form-check-inline">
                                <input id="rbSolicitud" class="form-check-input" type="radio" runat="server" name="estado"/>
                                <label id="lblSolicitud" class="form-check-label" for="cphContenido_rbSolicitud">Solicitud</label>
                            </div>
                        </div>
                        <div class="col-1">
                            <div class="form-check form-check-inline">
                                <input id="rbCotizacion" class="form-check-input" type="radio" runat="server" name="estado"/>
                                <label id="lblCotizacion" class="form-check-label" for="cphContenido_rbCotizacion">Cotizacion</label>
                            </div>
                        </div>
                        <div class="col-1">
                            <div class="form-check form-check-inline">
                                <input id="rbAnulado" class="form-check-input" type="radio" runat="server" name="estado" disabled/>
                                <label id="lblAnulado" class="form-check-label" for="cphContenido_rbAnulado">Anulado</label>
                            </div>
                        </div>
                        <div class="col-1">
                            <div class="form-check form-check-inline">
                                <input id="rbPagado" class="form-check-input" type="radio" runat="server" name="estado" disabled/>
                                <label id="lblPagado" class="form-check-label" for="cphContenido_rbPagado">Pagado</label>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <asp:Label ID="lblTipo" runat="server" Text="Tipo:" CssClass="col-sm-3 col-form-label"></asp:Label>
                        <div class="col-1">
                            <div class="form-check form-check-inline">
                                <input id="rbCompra" class="form-check-input" type="radio" runat="server" name="tipo"/>
                                <label id="lblCompra" class="form-check-label" for="cphContenido_rbCompra">Compra</label>
                            </div>
                        </div>
                        <div class="col-1">
                            <div class="form-check form-check-inline">
                                <input id="rbVenta" class="form-check-input" type="radio" runat="server" name="tipo"/>
                                <label id="lblVenta" class="form-check-label" for="cphContenido_rbVenta">Venta</label>
                            </div>
                        </div>
                        <div class="col-1">
                           <div class="form-check form-check-inline">
                               <input id="rbFactura" class="form-check-input" type="radio" runat="server" name="tipo"/>
                               <label id="lblFactura" class="form-check-label" for="cphContenido_rbFactura">Factura</label>
                           </div>
                       </div>
                    </div>

                    <div class="mb-3 row">
                        <asp:Label ID="lblVendedor" runat="server" Text="Vendedor:" CssClass="col-sm-3 col-form-label" />
                        <div class="col-sm-3">
                            <asp:TextBox ID="txtVendedor" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                        <div class="col">
                            <asp:Button ID="btnBuscarVendedor" CssClass="btn btn-primary" runat="server" Text="Buscar Vendedor" OnClick="btnBuscarVendedor_Click" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <asp:Label ID="lblTrabajador" runat="server" Text="Trabajador:" CssClass="col-sm-3 col-form-label" />
                        <div class="col-sm-3">
                            <asp:TextBox ID="txtTrabajador" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                        <div class="col">
                            <asp:Button ID="btnBuscarTrabajador" CssClass="btn btn-primary" runat="server" Text="Buscar Trabajador" OnClick="btnBuscarTrabajador_Click" />
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <asp:Label ID="lblAdministrador" runat="server" Text="Administrador:" CssClass="col-sm-3 col-form-label" />
                        <div class="col-sm-3">
                            <asp:TextBox ID="txtAdministrador" runat="server" Enabled="false" CssClass="form-control" />
                        </div>
                        <div class="col">
                        <asp:Button ID="btnBuscarAdministrador" CssClass="btn btn-primary" runat="server" Text="Buscar Administrador" OnClick="btnBuscarAdministrador_Click" />
                        </div>
                    </div>
                </div>

                <!-- Apartado de productos -->
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
                    <!-- El OnRowDataBound servirá para que se realice una acción cuando se carga la información por cada fila -->
                    <div class="container row mt-3 m-2">
                        <asp:GridView ID="dgvProductos" runat="server"
                            AllowPaging="True"
                            PageSize="5"
                            OnPageIndexChanging="dgvProductos_PageIndexChanging"
                            OnRowDataBound="dgvProductos_RowDataBound"
                            AutoGenerateColumns="false"
                            CssClass="table table-hover table-responsive table-striped">
                            <Columns>
                                <asp:BoundField HeaderText="Producto" DataField="idProducto" />
                                <asp:BoundField HeaderText="Cantidad" DataField="cantidad" />
                                <asp:BoundField HeaderText="Precio" DataField="precioUnitario" />
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-eye'></i>" OnClick="BtnVerProducto_Click" CommandArgument='<%# Eval("idProducto") %>' />
                                        <asp:LinkButton ID="btnEliminar" runat="server" Text="<i class='fa-solid fa-trash'></i>" OnClick="BtnEliminarProducto_Click" CommandArgument='<%# Container.DataItemIndex + "," + Eval("id")%>' />
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>

                    </div>
                </div>
            </div>

            <div class="card-footer clearfix">
                <asp:LinkButton ID="lbRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="lbRegresar_Click"/>
                <asp:LinkButton ID="lbGuardar" runat="server" Text="<i class='fa-regular fa-floppy-disk'></i> Guardar" CssClass="float-end btn btn-primary" OnClick="lbGuardar_Click"/>
            </div>
        </div>
    </div>

    <asp:ScriptManager runat="server"></asp:ScriptManager>
    <!-- Implementacion de modals -->
    <!-- Modal que muestra ventana para agregar producto -->
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
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div class="row align-items-center">
                                                     <div class="col-auto">
                                                         <asp:Label CssClass="form-label" runat="server" Text="Cantidad:"></asp:Label>
                                                     </div>
                                                     <div class="col-sm-3">
                                                         <asp:TextBox CssClass="form-control" ID="ModalProducto_txtCantidadProducto" runat="server"></asp:TextBox>
                                                     </div>
                                                     <div class="col-auto">
                                                         <asp:Label CssClass="form-label" runat="server" Text="Precio:"></asp:Label>
                                                     </div>
                                                     <div class="col-sm-3">
                                                         <asp:TextBox CssClass="form-control" ID="ModalProducto_txtPrecioProducto" runat="server"></asp:TextBox>
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


    <!-- Modal que muestra ventana para mostrar información del producto -->
    <div class="modal" id="form-modal-datosproducto">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Información del Producto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel runat="server">
                        <ContentTemplate> 
                            <div class="container row pb-3 pt-3">
                                <div class="row align-items-center">
                                    <asp:TextBox ID="txtTituloProd" runat="server" Enabled="false" CssClass="fs-1" BorderStyle="None"/>
                                </div>
                            </div>
                            <div class="container row">
                               <div class="container p-3 my-0 bg-light rounded">
                                    <div class="mb-3 row"> 
                                    <asp:Label ID="lblPrecioProd" runat="server" Text="Precio:" CssClass="col-sm-3 col-form-label" AssociatedControlID="txtPrecioProd"/>
                                    <div class="col-sm-4">
                                        <asp:TextBox ID="txtPrecioProd" runat="server" Enabled="false" CssClass="form-control"/>
                                    </div>
                                    </div>
                                    <div class="mb-3 row">
                                        <asp:Label ID="lblStockMinimoProd" runat="server" Text="Stock Minimo:" CssClass="col-sm-3 col-form-label" AssociatedControlID="txtStockMinimoProd"/>
                                        <div class="col-sm-4">
                                            <asp:TextBox ID="txtStockMinimoProd" runat="server" Enabled="false" CssClass="form-control" />
                                        </div>
                                    </div>
                              </div>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <button type="button" class="float-end btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal que muestra ventana para agregar un vendedor -->
    <div class="modal" id="form-modal-insertar-vendedor">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Selección de Vendedor</h5>
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
                                        <asp:TextBox CssClass="form-control" ID="ModalVendedor_txtVendedorNombre" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="ModalVendedor_lbBuscarVendedor" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="ModalVendedor_lbBuscarVendedor_Click" />
                                    </div>
                                </div>
                            </div>

                            <div class="container row">
                                <asp:GridView ID="ModalVendedor_gvVendedor" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalVendedor_gvVendedor_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Nombre completo:" DataField="nombreCompleto" />
                                        <asp:BoundField HeaderText="Telefono:" DataField="telefono" />
                                        <asp:BoundField HeaderText="DNI:" DataField="dni" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div class="row align-items-center">
                                                     <div class="col-auto">
                                                          <asp:LinkButton ID="ModalVendedor_lbVendedorSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalVendedor_lbVendedorSeleccionar_Click" CommandArgument='<%# Eval("idVendedor") %>' />
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

    <!-- Modal que muestra ventana para agregar un trabajador de almacen -->
    <div class="modal" id="form-modal-insertar-trabajadorDeAlmacen">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Selección de Trabajadores de almacen</h5>
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
                                        <asp:TextBox CssClass="form-control" ID="ModalTrabajador_txtTrabajadorNombre" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="ModalTrabajador_lbBuscarTrabajador" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="ModalTrabajador_lbBuscarTrabajador_Click" />
                                    </div>
                                </div>
                            </div>

                            <div class="container row">
                                <asp:GridView ID="ModalTrabajador_gvTrabajador" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalTrabajador_gvTrabajador_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Nombre completo:" DataField="nombreCompleto" />
                                        <asp:BoundField HeaderText="Telefono:" DataField="telefono" />
                                        <asp:BoundField HeaderText="DNI:" DataField="dni" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div class="row align-items-center">
                                                     <div class="col-auto">
                                                          <asp:LinkButton ID="ModalTrabajador_lbTrabajadorSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalTrabajador_lbTrabajadorSeleccionar_Click" CommandArgument='<%# Eval("idTrabajadorDeAlmacen") %>' />
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

    <!-- Modal que muestra ventana para agregar una empresa -->
    <div class="modal" id="form-modal-insertar-empresa">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Selección de la empresa</h5>
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
                                        <asp:TextBox CssClass="form-control" ID="ModalEmpresa_txtEmpresaNombre" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="ModalEmpresa_lbBuscarEmpresa" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="ModalEmpresa_lbBuscarEmpresa_Click" />
                                    </div>
                                </div>
                            </div>

                            <div class="container row">
                                <asp:GridView ID="ModalEmpresa_gvEmpresa" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalEmpresa_gvEmpresa_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Nombre:" DataField="nombre" />
                                        <asp:BoundField HeaderText="Tipo de industria:" DataField="tipoIndustria" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div class="row align-items-center">
                                                     <div class="col-auto">
                                                          <asp:LinkButton ID="ModalEmpresa_lbEmpresaSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalEmpresa_lbEmpresaSeleccionar_Click" CommandArgument='<%# Eval("idEmpresa") %>' />
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

    <!-- Modal que muestra ventana para agregar un trabajador de almacen -->
    <div class="modal" id="form-modal-insertar-administrador">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Selección de Administradores</h5>
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
                                        <asp:TextBox CssClass="form-control" ID="ModalAdministrador_txtAdministradorNombre" runat="server"></asp:TextBox>
                                    </div>
                                    <div class="col-sm-2">
                                        <asp:LinkButton ID="ModalAdministrador_lbBuscarAdministrador" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar" OnClick="ModalAdministrador_lbBuscarAdministrador_Click" />
                                    </div>
                                </div>
                            </div>

                            <div class="container row">
                                <asp:GridView ID="ModalAdministrador_gvAdministrador" runat="server" AllowPaging="true" PageSize="5" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="ModalAdministrador_gvAdministrador_PageIndexChanging">
                                    <Columns>
                                        <asp:BoundField HeaderText="Nombre completo:" DataField="nombreCompleto" />
                                        <asp:BoundField HeaderText="Telefono:" DataField="telefono" />
                                        <asp:BoundField HeaderText="DNI:" DataField="dni" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div class="row align-items-center">
                                                     <div class="col-auto">
                                                          <asp:LinkButton ID="ModalAdministrador_lbAdministradorSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalAdministrador_lbAdministradorSeleccionar_Click" CommandArgument='<%# Eval("idAdministrador") %>' />
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
