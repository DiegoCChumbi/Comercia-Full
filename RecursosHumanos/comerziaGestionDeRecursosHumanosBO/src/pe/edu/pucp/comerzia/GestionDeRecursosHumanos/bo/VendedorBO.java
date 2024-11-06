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
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.VendedorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.VendedorDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

/*

public class Vendedor {

    private Integer idVendedor;

    private Integer idEmpleado;
    // private Empleado empleado;

    private Double ingresosVentas;
    private Double porcentajeComision;
 */
public class VendedorBO {

  private VendedorDAO<Vendedor> vendedorDAO;

  public VendedorBO() {
    this.vendedorDAO = new VendedorDAOImpl();
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
    Double ingresosVentas,
    Double porcentajeComision
  ) {
    Vendedor vendedor = new Vendedor();

    vendedor.setDni(dni);
    vendedor.setNombreCompleto(nombreCompleto);
    vendedor.setTelefono(telefono);
    vendedor.setCorreo(correo);
    vendedor.setDireccion(direccion);

    vendedor.setEstado(estado);
    vendedor.setNombreUsuario(nombreUsuario);
    vendedor.setContrasenha(contrasenha);
    vendedor.setSalario(salario);
    vendedor.setFechaContratacion(fechaContratacion);

    vendedor.setIngresosVentas(ingresosVentas);
    vendedor.setPorcentajeComision(porcentajeComision);

    return vendedorDAO.insertar(vendedor);
  }

  public Integer modificar(
    Integer idPersona,
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
    Double ingresosVentas,
    Double porcentajeComision
  ) {
    Vendedor vendedor = new Vendedor();

    vendedor.setIdPersona(idPersona);
    vendedor.setDni(dni);
    vendedor.setNombreCompleto(nombreCompleto);
    vendedor.setTelefono(telefono);
    vendedor.setCorreo(correo);
    vendedor.setDireccion(direccion);

    vendedor.setEstado(estado);
    vendedor.setNombreUsuario(nombreUsuario);
    vendedor.setContrasenha(contrasenha);
    vendedor.setSalario(salario);
    vendedor.setFechaContratacion(fechaContratacion);

    vendedor.setIngresosVentas(ingresosVentas);
    vendedor.setPorcentajeComision(porcentajeComision);

    return vendedorDAO.modificar(vendedor);
  }

  public Integer eliminar(Integer idPersona) {
    Vendedor vendedor = new Vendedor();
    vendedor.setIdPersona(idPersona);
    return vendedorDAO.eliminar(vendedor);
  }

  public ArrayList<Vendedor> listarTodos() {
    return vendedorDAO.listarTodos();
  }

  public Vendedor obtenerPorId(Integer id) {
    return vendedorDAO.obtenerPorId(id);
  }
}
