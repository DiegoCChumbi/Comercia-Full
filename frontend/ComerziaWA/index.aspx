<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="ComerziaWA.index" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <%--
    <h1>Bienvenido al programa llamado ComerziaDB</h1>
    <p>
        Este aplicativo Web se ha desarrollado como parte del curso de
        Programación 3
    </p>
    --%>

    <style>
        .card {
            background-color: #f3f4f6;
            border: none;
        }
    </style>

    <div class="container">
        <%--Gestion de relaciones comerciales--%> <% if (userRole == "admin" ||
        userRole == "vendedor") { %>
        <div class="row" style="margin-bottom: 20px">
            <h2 style="font-weight: bold">Gestion de Relaciones Comerciales</h2>
            <div class="col">
                <div
                    class="card"
                    style="
                        width: 38rem;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                    "
                >
                    <%--Informacion Clientes--%>
                    <div class="card-body">
                        <h5 class="card-title">Clientes</h5>
                        <asp:GridView
                            ID="gvIndexCliente"
                            runat="server"
                            AutoGenerateColumns="false"
                            CssClass="gvIndexCliente"
                            BorderColor="Transparent"
                        >
                            <Columns>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        <%# Eval("nombre") %>
                                        <!-- Nombre en la primera línea -->
                                        <br />
                                        <span
                                            style="
                                                color: gray;
                                                font-size: 0.9em;
                                            "
                                        >
                                            <%# Eval("email") %>
                                            <!-- Descripción en la segunda línea -->
                                        </span>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                    </div>
                    <%--Zona boton--%>
                    <div
                        class="card-footer d-flex justify-content-between align-items-center"
                        style="border-top: none; background: none"
                    >
                        <!-- Botón en la izquierda -->
                        <a
                            class="btn btn-secondary"
                            href="gestion_relaciones_comerciales.aspx"
                            role="button"
                            style="
                                background-color: transparent;
                                border: none;
                                color: #0b5ed7;
                            "
                        >
                            Ver Todos
                            <i
                                class="fa-solid fa-arrow-right"
                                style="color: #0b5ed7"
                            ></i>
                        </a>

                        <!-- Botón en la derecha -->
                        <a
                            class="btn btn-primary"
                            href="gestion_relaciones_comerciales.aspx"
                            role="button"
                        >
                            <i
                                class="fa-solid fa-plus"
                                style="color: #ffffff"
                            ></i>
                            Añadir
                        </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div
                    class="card"
                    style="
                        width: 38rem;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                    "
                >
                    <%--Informacion Proveedores--%>
                    <div class="card-body">
                        <h5 class="card-title">Proveedores</h5>
                        <asp:GridView
                            ID="gvIndexProveedor"
                            runat="server"
                            AutoGenerateColumns="false"
                            CssClass="gvIndexProveedor"
                            BorderColor="Transparent"
                        >
                            <Columns>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        <%# Eval("nombre") %>
                                        <!-- Nombre en la primera línea -->
                                        <br />
                                        <span
                                            style="
                                                color: gray;
                                                font-size: 0.9em;
                                            "
                                        >
                                            <%# Eval("email") %>
                                            <!-- Descripción en la segunda línea -->
                                        </span>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                    </div>
                    <%--Zona boton--%>
                    <div
                        class="card-footer d-flex justify-content-between align-items-center"
                        style="border-top: none; background: none"
                    >
                        <!-- Botón en la izquierda -->
                        <a
                            class="btn btn-secondary"
                            href="#"
                            role="button"
                            style="
                                background-color: transparent;
                                border: none;
                                color: #0b5ed7;
                            "
                        >
                            Ver Todos
                            <i
                                class="fa-solid fa-arrow-right"
                                style="color: #0b5ed7"
                            ></i>
                        </a>

                        <!-- Botón en la derecha -->
                        <a class="btn btn-primary" href="#" role="button">
                            <i
                                class="fa-solid fa-plus"
                                style="color: #ffffff"
                            ></i>
                            Añadir
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <% } %> <%--Gestion de almacen--%> <% if (userRole == "admin" ||
        userRole == "trabajador" || userRole == "vendedor") { %>
        <div class="row" style="margin-bottom: 20px">
            <h2 style="font-weight: bold">Gestión de Almacén</h2>
            <div class="col">
                <div
                    class="card"
                    style="
                        width: 38rem;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                    "
                >
                    <%--Informacion Producto--%>
                    <div class="card-body">
                        <h5 class="card-title">Productos</h5>
                        <asp:GridView
                            ID="gvIndexProducto"
                            runat="server"
                            AutoGenerateColumns="false"
                            CssClass="gvIndexProducto"
                            BorderColor="Transparent"
                        >
                            <Columns>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;-
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="nombre" />
                            </Columns>
                        </asp:GridView>
                    </div>
                    <%--Zona boton--%>
                    <div
                        class="card-footer d-flex justify-content-between align-items-center"
                        style="border-top: none; background: none"
                    >
                        <!-- Botón en la izquierda -->
                        <a
                            class="btn btn-secondary"
                            href="gestion_almacen.aspx"
                            role="button"
                            style="
                                background-color: transparent;
                                border: none;
                                color: #0b5ed7;
                            "
                        >
                            Ver Todos
                            <i
                                class="fa-solid fa-arrow-right"
                                style="color: #0b5ed7"
                            ></i>
                        </a>

                        <!-- Botón en la derecha -->
                        <a class="btn btn-primary" href="#" role="button">
                            <i
                                class="fa-solid fa-plus"
                                style="color: #ffffff"
                            ></i>
                            Añadir
                        </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div
                    class="card"
                    style="
                        width: 38rem;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                    "
                >
                    <%--Informacion Almacen--%>
                    <div class="card-body">
                        <h5 class="card-title">Almacenes</h5>
                        <asp:GridView
                            ID="gvIndexAlmacen"
                            runat="server"
                            AutoGenerateColumns="false"
                            CssClass="gvIndexAlmacen"
                            BorderColor="Transparent"
                        >
                            <Columns>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;-
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Nombre" />
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="descripcion" />
                            </Columns>
                        </asp:GridView>
                    </div>
                    <%--Zona boton--%>
                    <div
                        class="card-footer d-flex justify-content-between align-items-center"
                        style="border-top: none; background: none"
                    >
                        <!-- Botón en la izquierda -->
                        <a
                            class="btn btn-secondary"
                            href="#"
                            role="button"
                            style="
                                background-color: transparent;
                                border: none;
                                color: #0b5ed7;
                            "
                        >
                            Ver Todos
                            <i
                                class="fa-solid fa-arrow-right"
                                style="color: #0b5ed7"
                            ></i>
                        </a>

                        <!-- Botón en la derecha -->
                        <a class="btn btn-primary" href="#" role="button">
                            <i
                                class="fa-solid fa-plus"
                                style="color: #ffffff"
                            ></i>
                            Añadir
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <% } %> <%--Gestion de recursos humanos--%> <% if (userRole == "admin")
        { %>
        <div class="row" style="margin-bottom: 20px">
            <h2 style="font-weight: bold">Gestión de Recursos Humanos</h2>
            <div class="col">
                <div
                    class="card"
                    style="
                        width: 25rem;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                    "
                >
                    <%--Informacion Trabajador de Almacen--%>
                    <div class="card-body">
                        <h5 class="card-title">Trabajadores de Almacen</h5>
                        <asp:GridView
                            ID="gvIndexTrabajador"
                            runat="server"
                            AutoGenerateColumns="false"
                            CssClass="gvIndexTrabajador"
                            BorderColor="Transparent"
                        >
                            <Columns>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;-
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Nombre" />
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Dni" />
                            </Columns>
                        </asp:GridView>
                    </div>
                    <%--Zona boton--%>
                    <div
                        class="card-footer d-flex justify-content-between align-items-center"
                        style="border-top: none; background: none"
                    >
                        <!-- Botón en la izquierda -->
                        <a
                            class="btn btn-secondary"
                            href="ListarTrabajadoresAlmacen.aspx"
                            role="button"
                            style="
                                background-color: transparent;
                                border: none;
                                color: #0b5ed7;
                            "
                        >
                            Ver Todos
                            <i
                                class="fa-solid fa-arrow-right"
                                style="color: #0b5ed7"
                            ></i>
                        </a>

                        <!-- Botón en la derecha -->
                        <a
                            class="btn btn-primary"
                            href="TrabajadorDeAlmacen.aspx"
                            role="button"
                        >
                            <i
                                class="fa-solid fa-plus"
                                style="color: #ffffff"
                            ></i>
                            Añadir
                        </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div
                    class="card"
                    style="width: 25rem"
                    style="
                        width: 25rem;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                    "
                >
                    <%--Informacion Vendedores--%>
                    <div class="card-body">
                        <h5 class="card-title">Vendedores</h5>
                        <asp:GridView
                            ID="gvIndexVendedor"
                            runat="server"
                            AutoGenerateColumns="false"
                            CssClass="gvIndexVendedor"
                            BorderColor="Transparent"
                        >
                            <Columns>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;-
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Nombre" />
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Dni" />
                            </Columns>
                        </asp:GridView>
                    </div>
                    <%--Zona boton--%>
                    <div
                        class="card-footer d-flex justify-content-between align-items-center"
                        style="border-top: none; background: none"
                    >
                        <!-- Botón en la izquierda -->
                        <a
                            class="btn btn-secondary"
                            href="ListarVendedores.aspx"
                            role="button"
                            style="
                                background-color: transparent;
                                border: none;
                                color: #0b5ed7;
                            "
                        >
                            Ver Todos
                            <i
                                class="fa-solid fa-arrow-right"
                                style="color: #0b5ed7"
                            ></i>
                        </a>

                        <!-- Botón en la derecha -->
                        <a
                            class="btn btn-primary"
                            href="Vendedores.aspx"
                            role="button"
                        >
                            <i
                                class="fa-solid fa-plus"
                                style="color: #ffffff"
                            ></i>
                            Añadir
                        </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div
                    class="card"
                    style="width: 25rem"
                    style="
                        width: 25rem;
                        display: flex;
                        flex-direction: column;
                        justify-content: space-between;
                    "
                >
                    <%--Informacion Administradores--%>
                    <div class="card-body">
                        <h5 class="card-title">Administradores</h5>
                        <asp:GridView
                            ID="gvIndexAdministradores"
                            runat="server"
                            AutoGenerateColumns="false"
                            CssClass="gvIndexAdministradores"
                            BorderColor="Transparent"
                        >
                            <Columns>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;-
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Nombre" />
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <!-- Espacio en blanco -->
                                    </ItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Dni" />
                            </Columns>
                        </asp:GridView>
                    </div>
                    <%--Zona boton--%>
                    <div
                        class="card-footer d-flex justify-content-between align-items-center"
                        style="border-top: none; background: none"
                    >
                        <!-- Botón en la izquierda -->
                        <a
                            class="btn btn-secondary"
                            href="ListarAdministradores.aspx"
                            role="button"
                            style="
                                background-color: transparent;
                                border: none;
                                color: #0b5ed7;
                            "
                        >
                            Ver Todos
                            <i
                                class="fa-solid fa-arrow-right"
                                style="color: #0b5ed7"
                            ></i>
                        </a>

                        <!-- Botón en la derecha -->
                        <a
                            class="btn btn-primary"
                            href="Administradores.aspx"
                            role="button"
                        >
                            <i
                                class="fa-solid fa-plus"
                                style="color: #ffffff"
                            ></i>
                            Añadir
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</asp:Content>
