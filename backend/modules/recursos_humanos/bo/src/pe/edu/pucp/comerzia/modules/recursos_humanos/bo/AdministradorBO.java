package pe.edu.pucp.comerzia.modules.recursos_humanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.AdministradorDAO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.AdministradorMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Administrador;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.EstadoEmpleadoEnum;

public class AdministradorBO {

  private AdministradorDAO administradorDAO;

  public AdministradorBO() {
    this.administradorDAO = AdministradorDAO.getAdministradorInstance();
  }

  public Integer insertar(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion,
    Integer idAlmacen
  ) throws SQLException {
    Administrador administrador = new Administrador();

    administrador.setDni(dni);
    administrador.setNombre(nombreCompleto);
    administrador.setTelefono(telefono);
    administrador.setCorreo(correo);
    administrador.setDireccion(direccion);

    administrador.setEstado(estado);
    administrador.setNombreUsuario(nombreUsuario);
    administrador.setContrasenha(contrasenha);
    administrador.setSalario(salario);
    administrador.setFechaContratacion(fechaContratacion);

    administrador.setIdAlmacen(idAlmacen);

    return administradorDAO.insert(administrador);
  }

  public Integer modificar(
    Integer id,
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion,
    Integer idAlmacen
  ) throws SQLException {
    Administrador administrador = new Administrador();
    administrador.setId(id);
    administrador.setDni(dni);
    administrador.setNombre(nombreCompleto);
    administrador.setTelefono(telefono);
    administrador.setCorreo(correo);
    administrador.setDireccion(direccion);

    administrador.setEstado(estado);
    administrador.setNombreUsuario(nombreUsuario);
    administrador.setContrasenha(contrasenha);
    administrador.setSalario(salario);
    administrador.setFechaContratacion(fechaContratacion);

    administrador.setIdAlmacen(idAlmacen);
    return administradorDAO.update(id, administrador);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return administradorDAO.delete(id);
  }

  public ArrayList<Administrador> listarTodos() throws SQLException {
    return new ArrayList<>(administradorDAO.findAll());
  }

  public Optional<Administrador> obtenerPorId(Integer id) throws SQLException {
    return administradorDAO.findById(id);
  }

  public ArrayList<Administrador> listarParaIndex() throws SQLException {
    return new ArrayList<>(administradorDAO.query().limit(3).list());
  }

  public ArrayList<Administrador> buscarAdministradores(
    String nombreAdministrador
  ) throws SQLException {
    return new ArrayList<>(
      administradorDAO
        .query()
        .where(
          AdministradorMapper.Columns.nombre.like(
            "%" + nombreAdministrador + "%"
          )
        )
        .list()
    );
  }
}
