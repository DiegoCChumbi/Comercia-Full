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
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.AdministradorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.AdministradorDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;

/*
public class Administrador {

    private Integer idAdministrador;
    private static int idCorrelativo = 1;

    private Integer idAlmacen;
 */
public class AdministradorBO {

  private AdministradorDAO administradorDAO;

  public AdministradorBO() {
    this.administradorDAO = new AdministradorDAOImpl();
  }

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
    Integer idAlmacen
  ) {
    Administrador administrador = new Administrador();

    administrador.setDni(dni);
    administrador.setNombreCompleto(nombreCompleto);
    administrador.setTelefono(telefono);
    administrador.setCorreo(correo);
    administrador.setDireccion(direccion);

    administrador.setEstado(estado);
    administrador.setNombreUsuario(nombreUsuario);
    administrador.setContrasenha(contrasenha);
    administrador.setSalario(salario);
    administrador.setFechaContratacion(fechaContratacion);

    administrador.setIdAlmacen(idAlmacen);

    return administradorDAO.insertar(administrador);
  }

  //    public Integer insertar(Integer idAdministrador, Integer idAlmacen) {
  //        Administrador administrador = new Administrador();
  //        administrador.setIdAdministrador(idAdministrador);
  //        administrador.setIdAlmacen(idAlmacen);
  //        return administradorDAO.insertar(administrador);
  //    }

  // public Integer modificar(Integer idAdministrador, String dni, String nombreCompleto, String telefono,
  //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
  //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
  //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa, EstadoEmpleado estadoE) {
  //     Administrador administrador = new Administrador();
  //     administrador.setIdAdministrador(idAdministrador);
  //     administrador.setDni(dni);
  //     administrador.setNombreCompleto(nombreCompleto);
  //     administrador.setTelefono(telefono);
  //     administrador.setCorreo(correo);
  //     administrador.setDireccion(direccion);
  //     administrador.setIngresosVentas(ingresosVentas);
  //     administrador.setPorcentajeComision(porcentajeComision);
  //     administrador.setLicenciaMontacarga(licenciaMontacarga);
  //     administrador.setAlmacenAlmacenero(almacenAlmacenero);
  //     administrador.setExperiencia(experiencia);
  //     administrador.setAlmacenAdministrador(almacenAdministrador);
  //     administrador.setEmpresa(empresa);
  //     administrador.setTipoP(tipoP);
  //     administrador.setEstadoE(estadoE);
  //     return administradorDAO.modificar(administrador);
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
    Integer idAlmacen
  ) {
    Administrador administrador = new Administrador();
    administrador.setIdEmpleado(idEmpleado);
    administrador.setDni(dni);
    administrador.setNombreCompleto(nombreCompleto);
    administrador.setTelefono(telefono);
    administrador.setCorreo(correo);
    administrador.setDireccion(direccion);

    administrador.setEstado(estado);
    administrador.setNombreUsuario(nombreUsuario);
    administrador.setContrasenha(contrasenha);
    administrador.setSalario(salario);
    administrador.setFechaContratacion(fechaContratacion);

    administrador.setIdAlmacen(idAlmacen);
    return administradorDAO.modificar(administrador);
  }

  //--
  public Integer eliminar(Integer idEmpleado) {
    Administrador administrador = new Administrador();
    administrador.setIdEmpleado(idEmpleado);
    return administradorDAO.eliminar(administrador);
  }

  public ArrayList<Administrador> listarTodos() {
    return administradorDAO.listarTodos();
  }

  public Administrador obtenerPorId(Integer id) {
    return administradorDAO.obtenerPorId(id);
  }
}
