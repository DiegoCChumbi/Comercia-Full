<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master" AutoEventWireup="true" CodeBehind="gestionar_documento.aspx.cs" Inherits="ComerziaWA.gestionar_documento" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestionar Documentos
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <script src="Public/Scripts/ComerziaScripts/gestionar-producto.js"></script>
    <script src="Public/Scripts/ComerziaScripts/gestionar-documento.js"></script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="card">
        <div class="card-header">
            <h2>Creación de documentos</h2>
        </div>
        <div class="card-body">
            <div class="mb-3 row">
                <div class="col-1">
                    <asp:Label ID="lblEstado" runat="server" Text="Estado:" CssClass="col-form-label fw-bold"></asp:Label>
                </div>
                <div class="col-1">
                    <div class="form-check form-check-inline">
                        <input id="rbSolicitud" class="form-check-input" type="radio" runat="server" name="estado" />
                        <label id="lblSolicitud" class="form-check-label" for="cphContenido_rbSolicitud">Solicitud</label>
                    </div>
                </div>
                <div class="col-1">
                    <div class="form-check form-check-inline">
                        <input id="rbCotizacion" class="form-check-input" type="radio" runat="server" name="estado" />
                        <label id="lblCotizacion" class="form-check-label" for="cphContenido_rbCotizacion">Cotizacion</label>
                    </div>
                </div>
                <div class="col-1">
                    <div class="form-check form-check-inline">
                        <input id="rbAnulado" class="form-check-input" type="radio" runat="server" name="estado" disabled />
                        <label id="lblAnulado" class="form-check-label" for="cphContenido_rbAnulado">Anulado</label>
                    </div>
                </div>
                <div class="col-1">
                    <div class="form-check form-check-inline">
                        <input id="rbPagado" class="form-check-input" type="radio" runat="server" name="estado" disabled />
                        <label id="lblPagado" class="form-check-label" for="cphContenido_rbPagado">Pagado</label>
                    </div>
                </div>
            </div>

            <div class="mb-3 row">
                <div class="col-1">
                    <asp:Label ID="lblTipo" runat="server" Text="Tipo:" CssClass="col-form-label fw-bold"></asp:Label>
                </div>
                <div class="col-1">
                    <div class="form-check form-check-inline">
                        <input id="rbCompra" class="form-check-input" type="radio" runat="server" name="tipo" />
                        <label id="lblCompra" class="form-check-label" for="cphContenido_rbCompra">Compra</label>
                    </div>
                </div>
                <div class="col-1">
                    <div class="form-check form-check-inline">
                        <input id="rbVenta" class="form-check-input" type="radio" runat="server" name="tipo" />
                        <label id="lblVenta" class="form-check-label" for="cphContenido_rbVenta">Venta</label>
                    </div>
                </div>
                <div class="col-1">
                    <div class="form-check form-check-inline">
                        <input id="rbFactura" class="form-check-input" type="radio" runat="server" name="tipo" />
                        <label id="lblFactura" class="form-check-label" for="cphContenido_rbFactura">Factura</label>
                    </div>
                </div>
            </div>

            <div class="mb-3 row">
                <asp:Label ID="lblVendedor" runat="server" Text="Vendedor:" CssClass="col-sm-1 col-form-label fw-bold" />
                <div class="col-sm-3">
                    <asp:TextBox ID="txtVendedor" runat="server" Enabled="false" CssClass="form-control" />
                </div>
                <div class="col">
                    <asp:Button ID="btnBuscarVendedor" CssClass="btn btn-primary col-sm-auto" runat="server" Text="Buscar Vendedor" OnClick="btnBuscarVendedor_Click" />
                </div>
            </div>

            <div class="mb-3 row">
                <asp:Label ID="lblTrabajador" runat="server" Text="Trabajador:" CssClass="col-sm-1 col-form-label fw-bold" />
                <div class="col-sm-3">
                    <asp:TextBox ID="txtTrabajador" runat="server" Enabled="false" CssClass="form-control" />
                </div>
                <div class="col">
                    <asp:Button ID="btnBuscarTrabajador" CssClass="btn btn-primary col-sm-auto" runat="server" Text="Buscar Trabajador" OnClick="btnBuscarTrabajador_Click" />
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
                            Text="Agregar producto" OnClick="btnAgregarProducto_Click" />
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
                            <asp:BoundField HeaderText="Producto" DataField="id" />
                            <asp:BoundField HeaderText="Cantidad" DataField="cantidad" />
                            <asp:BoundField HeaderText="Precio" DataField="precioUnitario" />
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-eye'></i>" OnClick="BtnVerProducto_Click" CommandArgument='<%# Eval("id") %>' />
                                    <asp:LinkButton ID="btnEliminar" runat="server" Text="<i class='fa-solid fa-trash'></i>" OnClick="BtnEliminarProducto_Click" CommandArgument='<%# Container.DataItemIndex %>' />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>

                </div>
            </div>

        </div>

        <div class="card-footer clearfix">
            <asp:LinkButton ID="lbRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="lbRegresar_Click" />
            <asp:LinkButton ID="lbGuardar" runat="server" Text="<i class='fa-regular fa-floppy-disk'></i> Guardar" CssClass="float-end btn btn-primary" OnClick="lbGuardar_Click" />
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
                                        <asp:BoundField HeaderText="Producto" DataField="nombre" />
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
                                                        <asp:LinkButton ID="ModalProducto_lbSeleccionar" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalProducto_lbSeleccionar_Click" CommandArgument='<%# Eval("id") %>' />
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
                                    <asp:TextBox ID="txtTituloProd" runat="server" Enabled="false" CssClass="fs-1" BorderStyle="None" />
                                </div>
                            </div>
                            <div class="container row">
                                <div class="container p-3 my-0 bg-light rounded">
                                    <div class="mb-3 row">
                                        <asp:Label ID="lblPrecioProd" runat="server" Text="Precio:" CssClass="col-sm-3 col-form-label" AssociatedControlID="txtPrecioProd" />
                                        <div class="col-sm-4">
                                            <asp:TextBox ID="txtPrecioProd" runat="server" Enabled="false" CssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="mb-3 row">
                                        <asp:Label ID="lblStockMinimoProd" runat="server" Text="Stock Minimo:" CssClass="col-sm-3 col-form-label" AssociatedControlID="txtStockMinimoProd" />
                                        <div class="col-sm-4">
                                            <asp:TextBox ID="txtStockMinimoProd" runat="server" Enabled="false" CssClass="form-control" />
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
                                        <asp:BoundField HeaderText="Nombre completo:" DataField="Nombre" />
                                        <asp:BoundField HeaderText="Telefono:" DataField="telefono" />
                                        <asp:BoundField HeaderText="DNI:" DataField="dni" />
                                        <asp:TemplateField>
                                            <ItemTemplate>
                                                <div class="row align-items-center">
                                                    <div class="col-auto">
                                                        <asp:LinkButton ID="ModalVendedor_lbVendedor" class="btn btn-success" runat="server" Text="<i class='fa-solid fa-check ps-2'></i> Seleccionar" OnClick="ModalVendedor_lbVendedorSeleccionar_Click" CommandArgument='<%# Eval("idPersona") %>' />
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
