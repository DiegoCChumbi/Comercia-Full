/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo;

/**
 *
 * @author chumbi
 */
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.TrabajadorDeAlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.TrabajadorDeAlmacenDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

/*
public class TrabajadorDeAlmacen {

    private Integer idTrabajadorDeAlmacen;

    private Integer idEmpleado;
    // private Empleado empleado;
    private Integer idAlmacen;
    // private Almacen almacenDeTrabajo;

    private boolean licenciaMontacarga;
 */
public class TrabajadorDeAlmacenBO {

  private TrabajadorDeAlmacenDAO trabajadorDeAlmacenDAO;

  public TrabajadorDeAlmacenBO() {
    this.trabajadorDeAlmacenDAO = new TrabajadorDeAlmacenDAOImpl();
  }

  // public Integer insertar(Integer idEmpleado, Integer almacenAlmacenero, Integer experiencia,
  //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa,
  //         TipoTrabajadorDeAlmacen tipoP, EstadoEmpleado estadoE) {
  //     TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();
  //     trabajadorDeAlmacen.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);
  //     trabajadorDeAlmacen.setDni(dni);
  //     trabajadorDeAlmacen.setNombreCompleto(nombreCompleto);
  //     trabajadorDeAlmacen.setTelefono(telefono);
  //     trabajadorDeAlmacen.setCorreo(correo);
  //     trabajadorDeAlmacen.setDireccion(direccion);
  //     trabajadorDeAlmacen.setIngresosVentas(ingresosVentas);
  //     trabajadorDeAlmacen.setPorcentajeComision(porcentajeComision);
  //     trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);
  //     trabajadorDeAlmacen.setAlmacenAlmacenero(almacenAlmacenero);
  //     trabajadorDeAlmacen.setExperiencia(experiencia);
  //     trabajadorDeAlmacen.setAlmacenAdministrador(almacenAdministrador);
  //     trabajadorDeAlmacen.setEmpresa(empresa);
  //     trabajadorDeAlmacen.setTipoP(tipoP);
  //     trabajadorDeAlmacen.setEstadoE(estadoE);
  //     return trabajadorDeAlmacenDAO.insertar(trabajadorDeAlmacen);
  // }
  public Integer insertar(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleado estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion,
    Integer idAlmacen,
    Boolean licenciaMontacarga
  ) {
    TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();

    trabajadorDeAlmacen.setDni(dni);
    trabajadorDeAlmacen.setNombreCompleto(nombreCompleto);
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

    return trabajadorDeAlmacenDAO.insertar(trabajadorDeAlmacen);
  }

  // public Integer modificar(Integer idTrabajadorDeAlmacen, String dni, String nombreCompleto, String telefono,
  //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
  //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
  //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa, EstadoEmpleado estadoE) {
  //     TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();
  //     trabajadorDeAlmacen.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);
  //     trabajadorDeAlmacen.setDni(dni);
  //     trabajadorDeAlmacen.setNombreCompleto(nombreCompleto);
  //     trabajadorDeAlmacen.setTelefono(telefono);
  //     trabajadorDeAlmacen.setCorreo(correo);
  //     trabajadorDeAlmacen.setDireccion(direccion);
  //     trabajadorDeAlmacen.setIngresosVentas(ingresosVentas);
  //     trabajadorDeAlmacen.setPorcentajeComision(porcentajeComision);
  //     trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);
  //     trabajadorDeAlmacen.setAlmacenAlmacenero(almacenAlmacenero);
  //     trabajadorDeAlmacen.setExperiencia(experiencia);
  //     trabajadorDeAlmacen.setAlmacenAdministrador(almacenAdministrador);
  //     trabajadorDeAlmacen.setEmpresa(empresa);
  //     trabajadorDeAlmacen.setTipoP(tipoP);
  //     trabajadorDeAlmacen.setEstadoE(estadoE);
  //     return trabajadorDeAlmacenDAO.modificar(trabajadorDeAlmacen);
  // }
  public Integer modificar(
    Integer idEmpleado,
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleado estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion,
    Integer idAlmacen,
    Boolean licenciaMontacarga
  ) {
    TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();

    trabajadorDeAlmacen.setIdEmpleado(idEmpleado);
    trabajadorDeAlmacen.setDni(dni);
    trabajadorDeAlmacen.setNombreCompleto(nombreCompleto);
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

    return trabajadorDeAlmacenDAO.modificar(trabajadorDeAlmacen);
  }

  public Integer eliminar(Integer idTrabajador) {
    TrabajadorDeAlmacen trabajador = new TrabajadorDeAlmacen();
    trabajador.setIdTrabajadorDeAlmacen(idTrabajador);
    return trabajadorDeAlmacenDAO.eliminar(trabajador);
  }

  public ArrayList<TrabajadorDeAlmacen> listarTodos() {
    return trabajadorDeAlmacenDAO.listarTodos();
  }

  public TrabajadorDeAlmacen obtenerPorId(Integer id) {
    return trabajadorDeAlmacenDAO.obtenerPorId(id);
  }
}
