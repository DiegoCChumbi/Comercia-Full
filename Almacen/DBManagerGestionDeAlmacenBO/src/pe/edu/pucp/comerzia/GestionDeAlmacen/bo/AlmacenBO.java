package pe.edu.pucp.comerzia.GestionDeAlmacen.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.AlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;

public class AlmacenBO {

  private AlmacenDAO almacenDAO;

  public AlmacenBO() {
    this.almacenDAO = new AlmacenDAO();
  }

  public Integer insertar(String nombre, String estado, String descripcion)
    throws SQLException {
    Almacen almacen = new Almacen();

    almacen.setNombre(nombre);
    almacen.setEstado(estado);
    almacen.setDescripcion(descripcion);

    return almacenDAO.insert(almacen);
  }

  public Integer modificar(
    Integer id,
    String nombre,
    String estado,
    String descripcion
  ) throws SQLException {
    Almacen almacen = new Almacen();

    almacen.setId(id);
    almacen.setNombre(nombre);
    almacen.setEstado(estado);
    almacen.setDescripcion(descripcion);

    return almacenDAO.update(almacen);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return almacenDAO.delete(id);
  }

  public ArrayList<Almacen> listarTodos() throws SQLException {
    return new ArrayList<>(almacenDAO.findAll());
  }

  public Optional<Almacen> obtenerPorId(Integer id) throws SQLException {
    return this.almacenDAO.findById(id);
  }
}
