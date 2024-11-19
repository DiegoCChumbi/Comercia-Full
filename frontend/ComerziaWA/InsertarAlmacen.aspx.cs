using System;
using System.Web.UI;
using ComerziaGestionAlmacenBO;

namespace ComerziaWA
{
    public partial class InsertarAlmacen : System.Web.UI.Page
    {
        private AlmacenBO almacenBO;
        private ProductoBO productoBO;

        public InsertarAlmacen()
        {
            almacenBO = new AlmacenBO();
            productoBO = new ProductoBO();
        }

        protected void Page_Load(object sender, EventArgs e) { }

        protected void btnGuardarAlmacen_Click(object sender, EventArgs e)
        {
            // Obtener los valores de los controles de almacén
            string nombreAlmacen = txtNombre.Text.Trim(); // txtNombre para el nombre del almacén
            string estadoAlmacen = txtEstado.Text.Trim(); // txtEstado para el estado del almacén
            string descripcionAlmacen = txtDescripcion.Text.Trim(); // txtDescripcion para la descripción del almacén

            // Llamar al método insertar y obtener el resultado
            int resultadoAlmacen = this.almacenBO.insertar(
                nombreAlmacen,
                estadoAlmacen,
                descripcionAlmacen
            );

            // Verificar el resultado para mostrar un mensaje al usuario
            if (resultadoAlmacen > 0)
            {
                // Registro exitoso

                txtNombre.Text = string.Empty;
                txtEstado.Text = string.Empty;
                txtDescripcion.Text = string.Empty;
                // Mostrar mensaje de éxito
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showSuccessPopup",
                    "showPopup('Almacén guardado exitosamente.');",
                    true
                );
            }
            else
            {
                // Error en el registro

                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showSuccessPopup",
                    "showPopup('Error al registrar el almacén.');",
                    true
                );
            }
        }

        protected void btnGuardarProducto_Click(object sender, EventArgs e)
        {
            // Obtener los valores de los controles
            string nombreProducto = txtNombreProducto.Text.Trim();
            double precioProducto;
            int stockMinimoProducto;

            // Validar y convertir el precio y el stock mínimo
            if (
                double.TryParse(txtPrecioProducto.Text.Trim(), out precioProducto)
                && int.TryParse(txtStockMinimoProducto.Text.Trim(), out stockMinimoProducto)
            )
            {
                // Llamar al método insertar y obtener el resultado
                int resultadoProducto = this.productoBO.insertar(
                    nombreProducto,
                    precioProducto,
                    stockMinimoProducto
                );

                // Verificar el resultado
                if (resultadoProducto > 0)
                {
                    txtNombreProducto.Text = string.Empty;
                    txtPrecioProducto.Text = string.Empty;
                    txtStockMinimoProducto.Text = string.Empty;
                    // Mostrar mensaje de éxito
                    ScriptManager.RegisterStartupScript(
                        this,
                        this.GetType(),
                        "showSuccessPopup",
                        "showPopup('Producto guardado exitosamente.');",
                        true
                    );
                }
                else
                {
                    // Mostrar mensaje de error
                    ScriptManager.RegisterStartupScript(
                        this,
                        this.GetType(),
                        "showErrorPopup",
                        "showPopup('Error al guardar el producto.');",
                        true
                    );
                }
            }
            else
            {
                // Mensaje de error si los datos no son válidos

                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "showInvalidDataPopup",
                    "showPopup('Por favor, ingrese datos válidos.');",
                    true
                );
            }
        }
    }
}
