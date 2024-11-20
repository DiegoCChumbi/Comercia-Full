<%@ Page Title="" Language="C#" MasterPageFile="~/Comerzia.Master"
AutoEventWireup="true" CodeBehind="GestionRecursosHumanos.aspx.cs"
Inherits="ComerziaWA.GestionRecursosHumanos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="contenedor-principal">
        <!-- Contenedor para la creación de personal -->
        <div class="gestion-empleados-contenedor">
            <h1 class="gestion-empleados-titulo">Creación Personal</h1>
            <div class="gestion-empleados-botones">
                <a href="Vendedores.aspx" class="gestion-empleados-btn"
                    >Vendedores</a
                >
                <a href="Administradores.aspx" class="gestion-empleados-btn"
                    >Administradores</a
                >
                <a href="TrabajadorDeAlmacen.aspx" class="gestion-empleados-btn"
                    >Trabajadores de Almacén</a
                >
            </div>
        </div>

        <div class="contenedor-gridview">
            <h2>Lista de Empleados</h2>
            <asp:GridView
                ID="gvEmpleados"
                runat="server"
                AutoGenerateColumns="False"
                AllowPaging="True"
                PageSize="8"
                CssClass="table table-hover table-responsive table-striped"
                OnPageIndexChanging="gvEmpleados_PageIndexChanging"
            >
                <Columns>
                    <asp:BoundField DataField="Dni" HeaderText="DNI" />
                    <asp:BoundField
                        DataField="Nombre"
                        HeaderText="Nombre Completo"
                    />
                    <asp:BoundField
                        DataField="Telefono"
                        HeaderText="Teléfono"
                    />
                    <asp:BoundField DataField="Correo" HeaderText="Correo" />
                    <asp:BoundField
                        DataField="FechaContratacion"
                        HeaderText="Fecha de Contratación"
                        DataFormatString="{0:dd/MM/yyyy}"
                        SortExpression="FechaContratacion"
                    />
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>
