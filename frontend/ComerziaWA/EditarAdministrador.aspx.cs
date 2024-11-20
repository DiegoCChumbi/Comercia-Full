using ComerziaBO.ComerziaWS;
using ComerziaGestionAlmacenBO;
using ComerziaRecursosHumanosBO;
using OtrosValidation;
using System;
using System.ComponentModel;
using System.Text;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ComerziaWA
{
    public partial class EditarAdministrador : System.Web.UI.Page
    {
        private AdministradorBO boAdministrador;
        private administrador administradorEditable;
        private AlmacenBO boAlmacen;

        public EditarAdministrador()
        {
            boAdministrador = new AdministradorBO();
            administradorEditable = new administrador();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // método listarTodos para obtener la lista de almacenes
                BindingList<almacen> almacenes = this.boAlmacen.listarTodos();

                // Configuramos la fuente de datos del DropDownList
                ddlElegirAlmacenAdministrador.DataSource = almacenes;

                // Mostramos solo el nombre del almacen
                ddlElegirAlmacenAdministrador.DataValueField = "id";
                ddlElegirAlmacenAdministrador.DataTextField = "nombre"; // Nombre es la propiedad que contiene el nombre del administrador

                // Enlazamos los datos
                ddlElegirAlmacenAdministrador.DataBind();

                // Obtener el idAdministrador de la sesión
                if (
                    Session["idAdministradorEditable"] != null
                    && int.TryParse(
                        Session["idAdministradorEditable"].ToString(),
                        out int idAdministradorEditable
                    )
                )
                {
                    // Llamar al método para obtener los datos del vendedor
                    administradorEditable = this.boAdministrador.obtenerPorId(
                        idAdministradorEditable
                    );
                    // Guardar el objeto en ViewState para mantener su estado
                    ViewState["administradorEditable"] = administradorEditable;

                    if (administradorEditable == null)
                    {
                        System.Diagnostics.Debug.WriteLine("No hay idAdministrador en la sesión.");
                    }

                    // Asignar los valores del administrador a los controles del formulario


                    txtDniAdministrador.Text = administradorEditable.dni;
                    txtNombreCompletoAdministrador.Text = administradorEditable.nombre;
                    txtTelefonoAdministrador.Text = administradorEditable.telefono;
                    txtCorreoAdministrador.Text = administradorEditable.correo;
                    txtDireccionAdministrador.Text = administradorEditable.direccion;
                    txtSalarioAdministrador.Text = administradorEditable.salario.ToString("F2"); // Formato de dos decimales

                    if (estadoEmpleadoEnum.ACTIVO == administradorEditable.estado)
                    {
                        ddlEstadoAdministrador.SelectedValue = "Activo";
                    }
                    else
                    {
                        ddlEstadoAdministrador.SelectedValue = "Inactivo";
                    }

                    ListItem item = ddlElegirAlmacenAdministrador.Items.FindByValue(
                        administradorEditable.idAlmacen.ToString()
                    );
                    if (item != null)
                    {
                        // Selecciona el almacén que coincide con el idAlmacen
                        ddlElegirAlmacenAdministrador.ClearSelection();
                        item.Selected = true;
                    }
                }
            }
            else
            {
                // Recuperar administradorEditable desde ViewState en un postback
                administradorEditable = (administrador)ViewState["administradorEditable"];
            }
        }

        protected void btnGuardarVendedor_Click(object sender, EventArgs e)
        {
            // Parsear Salario y IngresosVentas como double
            double salarioL = 0;

            // Si todas las validaciones son correctas,  crear el administrador
            var nuevoAdministrador = new OtrosVali
            {
                Dni = txtDniAdministrador.Text.Trim(),
                NombreCompleto = txtNombreCompletoAdministrador.Text.Trim(),
                Telefono = txtTelefonoAdministrador.Text.Trim(),
                Correo = txtCorreoAdministrador.Text.Trim(),
                Direccion = txtDireccionAdministrador.Text.Trim(),
                NombreUsuario = administradorEditable.nombreUsuario,
                Contrasenha = administradorEditable.contrasenha,
                Salario = txtSalarioAdministrador.Text.Trim(),
                FechaContratacion = DateTime.MinValue.ToString("yyyy-MM-dd"),
            };

            // Aquí seguiría el proceso de validación con el `OtrosValidator`
            var validator = new OtrosValidator();
            var result = validator.Validate(nuevoAdministrador);

            if (!result.IsValid)
            {
                StringBuilder errores = new StringBuilder();
                foreach (var error in result.Errors)
                {
                    errores.AppendLine(error.ErrorMessage);
                }

                if (errores.Length > 0)
                {
                    string mensajeErrores = errores
                        .ToString()
                        .Replace("\n", "\\n")
                        .Replace("\r", "")
                        .Replace("'", "\\'");

                    ScriptManager.RegisterStartupScript(
                        this,
                        this.GetType(),
                        "showErrorPopup",
                        $"showPopup('{mensajeErrores}');",
                        true
                    );
                }

                return; // Detener el proceso
            }

            // Estado
            estadoEmpleadoEnum estado;
            if (ddlEstadoAdministrador.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }

            //Id Almacenado
            int idAlmacenSeleccionado = Int32.Parse(ddlElegirAlmacenAdministrador.SelectedValue);

            this.boAdministrador.modificar(
                administradorEditable.id,
                nuevoAdministrador.Dni,
                nuevoAdministrador.NombreCompleto,
                nuevoAdministrador.Telefono,
                nuevoAdministrador.Correo,
                nuevoAdministrador.Direccion,
                estado,
                administradorEditable.nombreUsuario,
                administradorEditable.contrasenha,
                salarioL,
                administradorEditable.fechaContratacion,
                idAlmacenSeleccionado
            );

            Session.Remove("idAdministradorEditable");
            Response.Redirect("ListarAdministradores.aspx");
        }
    }
}
