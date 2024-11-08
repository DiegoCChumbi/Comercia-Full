package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
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

    return empleadoDAO.update(empleado);
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
  // public Integer verificarEmpleado(String cuenta, String contrasenha) {
  // return empleadoDAO.query().where(
  //   empleadoDAO.getNombreUsuario().eq(cuenta).and(
  //     empleadoDAO.getContrasenha().eq(contrasenha)
  //   )
  // ).fetchCount();
  // }

  // public String devolverNombreEmpleado(Integer idEmpleado) {
  // return empleadoDAO.query().where(empleadoDAO.getId().eq(idEmpleado)).fetchOne().getNombreUsuario();
  // }
}
