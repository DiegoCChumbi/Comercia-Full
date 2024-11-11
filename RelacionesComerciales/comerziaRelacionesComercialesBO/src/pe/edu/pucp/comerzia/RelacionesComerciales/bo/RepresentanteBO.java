package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Representante;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.RepresentanteDAO;

public class RepresentanteBO {

  private RepresentanteDAO representanteDAO;

  public RepresentanteBO() {
    this.representanteDAO = new RepresentanteDAO();
  }

  public Integer insertar(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    Integer idEmpresa
  ) throws SQLException {
    Representante representante = new Representante();

    representante.setDni(dni);
    representante.setNombre(nombreCompleto);
    representante.setTelefono(telefono);
    representante.setCorreo(correo);
    representante.setDireccion(direccion);
    representante.setIdEmpresa(idEmpresa);

    return this.representanteDAO.insert(representante);
  }

  public Integer modificar(
    Integer id,
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    Integer idEmpresa
  ) throws SQLException {
    Representante representante = new Representante();

    representante.setId(id);
    representante.setDni(dni);
    representante.setNombre(nombreCompleto);
    representante.setTelefono(telefono);
    representante.setCorreo(correo);
    representante.setDireccion(direccion);
    representante.setIdEmpresa(idEmpresa);

    return representanteDAO.update(id, representante);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return this.representanteDAO.delete(id);
  }

  public Optional<Representante> obtenerPorId(Integer id) throws SQLException {
    return this.representanteDAO.findById(id);
  }

  public ArrayList<Representante> listarTodos() throws SQLException {
    return new ArrayList<>(this.representanteDAO.findAll());
  }
}
