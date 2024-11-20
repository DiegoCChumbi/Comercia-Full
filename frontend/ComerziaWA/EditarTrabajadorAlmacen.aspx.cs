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
    public partial class EditarTrabajadorAlmacen : System.Web.UI.Page
    {
        private TrabajadorDeAlmacenBO boTrabajador;
        private trabajadorDeAlmacen trabajadorEditable;
        private AlmacenBO boAlmacen;

        public EditarTrabajadorAlmacen()
        {
            boTrabajador = new TrabajadorDeAlmacenBO();
            trabajadorEditable = new trabajadorDeAlmacen();
            boAlmacen = new AlmacenBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // método listarTodos para obtener la lista de almacenes
                BindingList<almacen> almacenes = this.boAlmacen.listarTodos();

                // Configuramos la fuente de datos del DropDownList
                ddlElegirAlmacenTrabajador.DataSource = almacenes;

                // Mostramos solo el nombre del almacen
                ddlElegirAlmacenTrabajador.DataValueField = "id";
                ddlElegirAlmacenTrabajador.DataTextField = "nombre"; // Nombre es la propiedad que contiene el nombre del administrador

                // Enlazamos los datos
                ddlElegirAlmacenTrabajador.DataBind();

                // Obtener el idVendedor de la sesión
                if (
                    Session["idTrabajadorEditable"] != null
                    && int.TryParse(
                        Session["idTrabajadorEditable"].ToString(),
                        out int idTrabajadorEditable
                    )
                )
                {
                    // Llamar al método para obtener los datos del vendedor
                    trabajadorEditable = this.boTrabajador.obtenerPorId(idTrabajadorEditable);
                    // Guardar el objeto en ViewState para mantener su estado
                    ViewState["trabajadorEditable"] = trabajadorEditable;

                    if (trabajadorEditable == null)
                    {
                        System.Diagnostics.Debug.WriteLine("No hay idTrabajador en la sesión.");
                    }

                    // Asignar los valores del vendedor a los controles del formulario


                    txtDniTrabajador.Text = trabajadorEditable.dni;
                    txtNombreCompletoTrabajador.Text = trabajadorEditable.nombre;
                    txtTelefonoTrabajador.Text = trabajadorEditable.telefono;
                    txtCorreoTrabajador.Text = trabajadorEditable.correo;
                    txtDireccionTrabajador.Text = trabajadorEditable.direccion;
                    txtSalarioTrabajador.Text = trabajadorEditable.salario.ToString("F2"); // Formato de dos decimales

                    if (estadoEmpleadoEnum.ACTIVO == trabajadorEditable.estado)
                    {
                        ddlEstadoTrabajador.SelectedValue = "Activo";
                    }
                    else
                    {
                        ddlEstadoTrabajador.SelectedValue = "Inactivo";
                    }

                    if (trabajadorEditable.licenciaMontacarga)
                    {
                        ddlPermisoTrabajador.SelectedValue = "true";
                    }
                    else
                    {
                        ddlPermisoTrabajador.SelectedValue = "false";
                    }

                    ListItem item = ddlElegirAlmacenTrabajador.Items.FindByValue(
                        trabajadorEditable.idAlmacen.ToString()
                    );
                    if (item != null)
                    {
                        // Selecciona el almacén que coincide con el idAlmacen
                        ddlElegirAlmacenTrabajador.ClearSelection();
                        item.Selected = true;
                    }
                }
            }
            else
            {
                // Recuperar trabajadorEditable desde ViewState en un postback
                trabajadorEditable = (trabajadorDeAlmacen)ViewState["trabajadorEditable"];
            }
        }

        protected void btnGuardarVendedor_Click(object sender, EventArgs e)
        {
            // Parsear Salario y IngresosVentas como double
            double salarioL = 0;

            // Si todas las validaciones son correctas,  crear el administrador
            var nuevoTrabajador = new OtrosVali
            {
                Dni = txtDniTrabajador.Text.Trim(),
                NombreCompleto = txtNombreCompletoTrabajador.Text.Trim(),
                Telefono = txtTelefonoTrabajador.Text.Trim(),
                Correo = txtCorreoTrabajador.Text.Trim(),
                Direccion = txtDireccionTrabajador.Text.Trim(),
                NombreUsuario = trabajadorEditable.nombreUsuario,
                Contrasenha = trabajadorEditable.contrasenha,
                Salario = txtSalarioTrabajador.Text.Trim(),
                FechaContratacion = DateTime.MinValue.ToString("yyyy-MM-dd"),
            };

            // Aquí seguiría el proceso de validación con el `OtrosValidator`
            var validator = new OtrosValidator();
            var result = validator.Validate(nuevoTrabajador);

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
            if (ddlEstadoTrabajador.SelectedValue == "Activo")
            {
                estado = estadoEmpleadoEnum.ACTIVO;
            }
            else
            {
                estado = estadoEmpleadoEnum.INACTIVO;
            }

            //Id Almacenado
            int idAlmacenSeleccionado = Int32.Parse(ddlElegirAlmacenTrabajador.SelectedValue);
            //Tiene licencia
            bool tieneLicenciaMontacarga = ddlPermisoTrabajador.SelectedValue == "true";

            this.boTrabajador.modificar(
                trabajadorEditable.id,
                nuevoTrabajador.Dni,
                nuevoTrabajador.NombreCompleto,
                nuevoTrabajador.Telefono,
                nuevoTrabajador.Correo,
                nuevoTrabajador.Direccion,
                estado,
                trabajadorEditable.nombreUsuario,
                trabajadorEditable.contrasenha,
                salarioL,
                trabajadorEditable.fechaContratacion,
                idAlmacenSeleccionado,
                tieneLicenciaMontacarga
            );

            Session.Remove("idTrabajadorEditable");
            Response.Redirect("ListarTrabajadoresAlmacen.aspx");
        }
    }
}
