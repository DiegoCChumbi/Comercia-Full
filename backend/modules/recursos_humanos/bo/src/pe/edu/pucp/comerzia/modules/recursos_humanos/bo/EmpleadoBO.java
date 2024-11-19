package pe.edu.pucp.comerzia.modules.recursos_humanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.EmpleadoMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Empleado;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.EstadoEmpleadoEnum;

public class EmpleadoBO {

  private EmpleadoDAO<Empleado> empleadoDAO;

  public EmpleadoBO() {
    this.empleadoDAO = new EmpleadoDAO<>();
  }

  public Integer insertar(
    Integer id,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion
  ) throws SQLException {
    Empleado empleado = new Empleado();
    empleado.setId(id);
    empleado.setEstado(estado);
    empleado.setNombreUsuario(nombreUsuario);
    empleado.setContrasenha(contrasenha);
    empleado.setSalario(salario);
    empleado.setFechaContratacion(fechaContratacion);

    return empleadoDAO.insert(empleado);
  }

  public Integer modificar(
    Integer id,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion
  ) throws SQLException {
    Empleado empleado = new Empleado();
    empleado.setId(id);
    empleado.setEstado(estado);
    empleado.setNombreUsuario(nombreUsuario);
    empleado.setContrasenha(contrasenha);
    empleado.setSalario(salario);
    empleado.setFechaContratacion(fechaContratacion);

    return empleadoDAO.update(id, empleado);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return empleadoDAO.delete(id);
  }

  public ArrayList<Empleado> listarTodos() throws SQLException {
    return new ArrayList<>(empleadoDAO.findAll());
  }

  public Optional<Empleado> obtenerPorId(Integer id) throws SQLException {
    return empleadoDAO.findById(id);
  }

  public Integer verificarEmpleado(String cuenta, String contrasenha)
    throws SQLException {
    Optional<Empleado> empleado = empleadoDAO
      .query()
      .whereAll(
        EmpleadoMapper.Columns.nombreUsuario.eq(cuenta),
        EmpleadoMapper.Columns.contrasenha.eq(contrasenha)
      )
      .unique();
    return empleado.get().getId(); // Throws NoSuchElementException if not found
  }

  public String devolverNombreEmpleado(Integer idEmpleado) throws SQLException {
    Optional<Empleado> empleado = empleadoDAO
      .query()
      .where(EmpleadoMapper.Columns.id.eq(idEmpleado))
      .unique();
    return empleado.get().getNombreUsuario();
  }

  public ArrayList<Empleado> listarParaIndex() throws SQLException {
    return new ArrayList<>(empleadoDAO.query().limit(3).list());
  }

  public String devolverRol(Integer idEmpleado) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
