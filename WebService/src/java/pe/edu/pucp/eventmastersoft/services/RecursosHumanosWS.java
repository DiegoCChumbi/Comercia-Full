package pe.edu.pucp.eventmastersoft.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.EmpleadoBO;

@WebService(
  serviceName = "RecursosHumanosWS",
  targetNamespace = "http://services.eventmastersoft.pucp.edu.pe"
)
public class RecursosHumanosWS {

  /**
   * This is a sample web service operation
   */
  private EmpleadoBO boEmpleado;

  @WebMethod(operationName = "verEmpleado")
  public Integer verificarEmpleado(
    @WebParam(name = "cuenta") String cuenta,
    @WebParam(name = "contrasenha") String contrasenha
  ) {
    Integer idEmpleado = -1;
    try {
      boEmpleado = new EmpleadoBO();
      idEmpleado = boEmpleado.verificarEmpleado(cuenta, contrasenha);
    } catch (Exception ex) {
      System.out.println("Error en verificarEmpleado: " + ex.getMessage());
      System.out.println(ex.getMessage());
    }
    return idEmpleado;
  }

  @WebMethod(operationName = "devolverNombreEmpleado")
  public String devolverNombreEmpleado(
    @WebParam(name = "idEmpleado") Integer idEmpleado
  ) {
    String nombre = null;
    try {
      boEmpleado = new EmpleadoBO();
      nombre = boEmpleado.devolverNombreEmpleado(idEmpleado);
    } catch (Exception ex) {
      System.out.println("Error en devolverNombreEmpleado: " + ex.getMessage());
      System.out.println(ex.getMessage());
    }
    return nombre;
  }
}
