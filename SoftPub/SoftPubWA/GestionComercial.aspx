<%@ Page Title="" Language="C#" MasterPageFile="~/SoftPub.Master" AutoEventWireup="true" CodeBehind="GestionComercial.aspx.cs" Inherits="SoftPubWA.insertar_especialidad" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestión Comercial
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Creación de Documento</h2>
            </div>
            <div class="card-body">
                <div class="mb-3 row">
                    <asp:Label ID="lblIdEspecialidad" runat="server" Text="Id Documento: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-4">
                        <asp:TextBox ID="txtIdEspecialidad" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblEspecialidad" runat="server" Text="Nombre de Especialidad: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-4">
                        <asp:TextBox ID="txtEspecialidad" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblFacultad" runat="server" Text="Nombre de Facultad: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-4">
                        <asp:TextBox ID="txtFacultad" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                 <div class="mb-3 row">
                     <asp:Label ID="lblEstado" runat="server" Text="Estado: " CssClass="col-sm-2 col-form-label"></asp:Label>
                     <div class="col-sm-4">
                         <asp:DropDownList  ID="DropDownList1" runat="server" CssClass="form-control">
                             <asp:ListItem Selected="True" Value="SOLICITUD"> Solicitud </asp:ListItem>
                             <asp:ListItem Value="COTIZACION"> Cotización </asp:ListItem>        
                         </asp:DropDownList>                        
                     </div>
                 </div>
                <div class="mb-3 row">
                    <asp:Label ID="lblTipo" runat="server" Text="Tipo: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-4">
                        <asp:DropDownList  ID="ddlTipo" runat="server" CssClass="form-control">
                            <asp:ListItem Selected="True" Value="COMPRA"> Compra </asp:ListItem>
                            <asp:ListItem Value="FACTURA"> Factura </asp:ListItem>        
                        </asp:DropDownList>                        
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</asp:Content>
