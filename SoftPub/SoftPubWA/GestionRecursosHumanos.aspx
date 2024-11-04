<%@ Page Title="" Language="C#" MasterPageFile="~/SoftPub.Master" AutoEventWireup="true" CodeBehind="GestionRecursosHumanos.aspx.cs" Inherits="SoftPubWA.insertar_seccion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="card">
    <div class="card-header">
        <h2>Creación de Personal</h2>
    </div>
    <div class="card-body">
        <div class="mb-3 row">
            <asp:Label ID="lblIdSeccion" runat="server" Text="Id Empleado: " CssClass="col-sm-2 col-form-label"></asp:Label>
            <div class="col-sm-4">
                <asp:TextBox ID="txtIdSeccion" runat="server" CssClass="form-control"></asp:TextBox>
            </div>
        </div>
        <div class="mb-3 row">
            <asp:Label ID="lblSeccion" runat="server" Text="Nombre de Sección: " CssClass="col-sm-2 col-form-label"></asp:Label>
            <div class="col-sm-4">
                <asp:TextBox ID="txtSeccion" runat="server" CssClass="form-control"></asp:TextBox>
            </div>
        </div>
        <div class="mb-3 row">
            <asp:Label ID="lblDepartamento" runat="server" Text="Nombre del Departamento: " CssClass="col-sm-2 col-form-label"></asp:Label>
            <div class="col-sm-4">
                <asp:TextBox ID="txtDepartamento" runat="server" CssClass="form-control"></asp:TextBox>
            </div>
        </div>
        <div class="mb-3 row">
            <asp:Label ID="lblTipoPersona" runat="server" Text="Rol: " CssClass="col-sm-2 col-form-label"></asp:Label>
            <div class="col-sm-4">
                <asp:DropDownList  ID="DropDownList1" runat="server" CssClass="form-control">
                    <asp:ListItem Selected="True" Value="VENDEDOR"> Vendedor </asp:ListItem>
                    <asp:ListItem Value="ALMACENERO"> Almacenero </asp:ListItem>        
                </asp:DropDownList>                        
            </div>
        </div>

    </div>
  
   
    </div>
</asp:Content>
