package pe.edu.pucp.comerzia.modules.relaciones_comerciales.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.ProveedorDAO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Proveedor;

public class ProveedorBO {

  private ProveedorDAO proveedorDAO;

  public ProveedorBO() {
    this.proveedorDAO = ProveedorDAO.getProveedorInstance();
  }

  public Integer insertar(
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fechaAfiliacion,
    String RUC,
    String razonSocial,
    Double calificacion,
    String pais
  ) throws SQLException {
    Proveedor proveedor = new Proveedor();

    proveedor.setNombre(nombre);
    proveedor.setDireccion(direccion);
    proveedor.setTelefono(telefono);
    proveedor.setEmail(email);
    proveedor.setTipoIndustria(tipoIndustria);
    proveedor.setFechaAfiliacion(fechaAfiliacion);
    proveedor.setRUC(RUC);
    proveedor.setRazonSocial(razonSocial);
    proveedor.setCalificacion(calificacion);
    proveedor.setPais(pais);

    return this.proveedorDAO.insert(proveedor);
  }

  public Integer modificar(
    Integer id,
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fechaAfiliacion,
    String RUC,
    String razonSocial,
    Double calificacion,
    String pais
  ) throws SQLException {
    Proveedor proveedor = new Proveedor();

    proveedor.setId(id);
    proveedor.setNombre(nombre);
    proveedor.setDireccion(direccion);
    proveedor.setTelefono(telefono);
    proveedor.setEmail(email);
    proveedor.setTipoIndustria(tipoIndustria);
    proveedor.setFechaAfiliacion(fechaAfiliacion);
    proveedor.setRUC(RUC);
    proveedor.setRazonSocial(razonSocial);
    proveedor.setCalificacion(calificacion);
    proveedor.setPais(pais);

    return this.proveedorDAO.update(id, proveedor);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return this.proveedorDAO.delete(id);
  }

  public Optional<Proveedor> obtenerPorId(Integer id) throws SQLException {
    return this.proveedorDAO.findById(id);
  }

  public ArrayList<Proveedor> listarTodos() throws SQLException {
    return new ArrayList<>(this.proveedorDAO.findAll());
  }

  public ArrayList<Proveedor> listarParaIndex() throws SQLException {
    return new ArrayList<>(this.proveedorDAO.query().limit(3).list());
  }
}
