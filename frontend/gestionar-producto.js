function showModalFormProducto() {
	console.log("Ejecutandose correctamente");
	var modalFormProducto = new bootstrap.Modal(document.getElementById('form-modal-producto'));
	modalFormProducto.toggle();
}

function showModalFormDatosProducto() {
	console.log("Ejecutandose correctamente");
	var modalFormDatosProducto = new bootstrap.Modal(document.getElementById('form-modal-datosproducto'));
	modalFormDatosProducto.toggle();
}
