package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper.EmpleadoMapper;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleadoEnum;

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
    return empleadoDAO
      .query()
      .whereAll(
        EmpleadoMapper.Columns.nombreUsuario.eq(cuenta),
        EmpleadoMapper.Columns.contrasenha.eq(contrasenha)
      )
      .count();
  }

  public String devolverNombreEmpleado(Integer idEmpleado) throws SQLException {
    Optional<Empleado> empleado = empleadoDAO
      .query()
      .where(EmpleadoMapper.Columns.id.eq(idEmpleado))
      .unique();
    return empleado.get().getNombreUsuario();
  }
}
