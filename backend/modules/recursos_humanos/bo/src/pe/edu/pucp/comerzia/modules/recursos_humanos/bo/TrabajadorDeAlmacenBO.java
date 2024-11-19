package pe.edu.pucp.comerzia.modules.recursos_humanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.TrabajadorDeAlmacenDAO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.EstadoEmpleadoEnum;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.TrabajadorDeAlmacen;

public class TrabajadorDeAlmacenBO {

  private TrabajadorDeAlmacenDAO trabajadorDeAlmacenDAO;

  public TrabajadorDeAlmacenBO() {
    this.trabajadorDeAlmacenDAO = new TrabajadorDeAlmacenDAO();
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
    Integer idAlmacen,
    Boolean licenciaMontacarga
  ) throws SQLException {
    TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();

    trabajadorDeAlmacen.setDni(dni);
    trabajadorDeAlmacen.setNombre(nombreCompleto);
    trabajadorDeAlmacen.setTelefono(telefono);
    trabajadorDeAlmacen.setCorreo(correo);
    trabajadorDeAlmacen.setDireccion(direccion);

    trabajadorDeAlmacen.setEstado(estado);
    trabajadorDeAlmacen.setNombreUsuario(nombreUsuario);
    trabajadorDeAlmacen.setContrasenha(contrasenha);
    trabajadorDeAlmacen.setSalario(salario);
    trabajadorDeAlmacen.setFechaContratacion(fechaContratacion);

    trabajadorDeAlmacen.setIdAlmacen(idAlmacen);
    trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);

    return trabajadorDeAlmacenDAO.insert(trabajadorDeAlmacen);
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
    Integer idAlmacen,
    Boolean licenciaMontacarga
  ) throws SQLException {
    TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();

    trabajadorDeAlmacen.setId(id);
    trabajadorDeAlmacen.setDni(dni);
    trabajadorDeAlmacen.setNombre(nombreCompleto);
    trabajadorDeAlmacen.setTelefono(telefono);
    trabajadorDeAlmacen.setCorreo(correo);
    trabajadorDeAlmacen.setDireccion(direccion);

    trabajadorDeAlmacen.setEstado(estado);
    trabajadorDeAlmacen.setNombreUsuario(nombreUsuario);
    trabajadorDeAlmacen.setContrasenha(contrasenha);
    trabajadorDeAlmacen.setSalario(salario);
    trabajadorDeAlmacen.setFechaContratacion(fechaContratacion);

    trabajadorDeAlmacen.setIdAlmacen(idAlmacen);
    trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);

    return trabajadorDeAlmacenDAO.update(id, trabajadorDeAlmacen);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return trabajadorDeAlmacenDAO.delete(id);
  }

  public ArrayList<TrabajadorDeAlmacen> listarTodos() throws SQLException {
    return new ArrayList<>(trabajadorDeAlmacenDAO.findAll());
  }

  public Optional<TrabajadorDeAlmacen> obtenerPorId(Integer id)
    throws SQLException {
    return trabajadorDeAlmacenDAO.findById(id);
  }

  public ArrayList<TrabajadorDeAlmacen> listarParaIndex() throws SQLException {
    return new ArrayList<>(trabajadorDeAlmacenDAO.query().limit(3).list());
  }
}
