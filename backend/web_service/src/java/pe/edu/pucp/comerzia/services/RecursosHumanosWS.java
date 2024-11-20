package pe.edu.pucp.comerzia.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.recursos_humanos.bo.AdministradorBO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.bo.EmpleadoBO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.bo.PersonaBO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.bo.TrabajadorDeAlmacenBO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.bo.VendedorBO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Administrador;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Empleado;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.EstadoEmpleadoEnum;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Persona;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.TrabajadorDeAlmacen;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Vendedor;

@WebService(
  serviceName = "RecursosHumanosWS",
  targetNamespace = "http://services.comerzia.pucp.edu.pe"
)
public class RecursosHumanosWS {

  private final EmpleadoBO boEmpleado;
  private final AdministradorBO boAdministrador;
  private final TrabajadorDeAlmacenBO boTrabajadorDeAlmacen;
  private final VendedorBO boVendedor;
  private final PersonaBO boPersona;

  public RecursosHumanosWS() {
    boEmpleado = new EmpleadoBO();
    boAdministrador = new AdministradorBO();
    boTrabajadorDeAlmacen = new TrabajadorDeAlmacenBO();
    boVendedor = new VendedorBO();
    boPersona = new PersonaBO();
  }

  @WebMethod(operationName = "insertar_administrador")
  public Integer insertar_administrador(
    @WebParam(name = "dni") String dni,
    @WebParam(name = "nombreCompleto") String nombreCompleto,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "correo") String correo,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "estado") EstadoEmpleadoEnum estado,
    @WebParam(name = "nombreUsuario") String nombreUsuario,
    @WebParam(name = "contrasenha") String contrasenha,
    @WebParam(name = "salario") Double salario,
    @WebParam(name = "fechaContratacion") Date fechaContratacion,
    @WebParam(name = "idAlmacen") Integer idAlmacen
  ) {
    try {
      return boAdministrador.insertar(
        dni,
        nombreCompleto,
        telefono,
        correo,
        direccion,
        estado,
        nombreUsuario,
        contrasenha,
        salario,
        fechaContratacion,
        idAlmacen
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificar_administrador")
  public Integer modificar_administrador(
    @WebParam(name = "idPersona") Integer idPersona,
    @WebParam(name = "dni") String dni,
    @WebParam(name = "nombreCompleto") String nombreCompleto,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "correo") String correo,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "estado") EstadoEmpleadoEnum estado,
    @WebParam(name = "nombreUsuario") String nombreUsuario,
    @WebParam(name = "contrasenha") String contrasenha,
    @WebParam(name = "salario") Double salario,
    @WebParam(name = "fechaContratacion") Date fechaContratacion,
    @WebParam(name = "idAlmacen") Integer idAlmacen
  ) {
    try {
      return boAdministrador.modificar(
        idPersona,
        dni,
        nombreCompleto,
        telefono,
        correo,
        direccion,
        estado,
        nombreUsuario,
        contrasenha,
        salario,
        fechaContratacion,
        idAlmacen
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "eliminar_administrador")
  public Integer eliminar_administrador(
    @WebParam(name = "idPersona") Integer idPersona
  ) {
    try {
      return boAdministrador.eliminar(idPersona);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "listarTodos_administrador")
  public ArrayList<Administrador> listarTodos_administrador() {
    try {
      return boAdministrador.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "buscarPorNombre_administrador")
  public ArrayList<Administrador> buscarAdministradores(
    @WebParam(name = "nombreAdministrador") String nombre
  ) {
    try {
      return boAdministrador.buscarAdministradores(nombre);
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerPorId_administrador")
  public Administrador obtenerPorId_administrador(
    @WebParam(name = "id") Integer id
  ) {
    try {
      Optional<Administrador> administrador = boAdministrador.obtenerPorId(id);
      return administrador.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "insertar_trabajadorDeAlmacen")
  public Integer insertar_trabajadorDeAlmacen(
    @WebParam(name = "dni") String dni,
    @WebParam(name = "nombreCompleto") String nombreCompleto,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "correo") String correo,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "estado") EstadoEmpleadoEnum estado,
    @WebParam(name = "nombreUsuario") String nombreUsuario,
    @WebParam(name = "contrasenha") String contrasenha,
    @WebParam(name = "salario") Double salario,
    @WebParam(name = "fechaContratacion") Date fechaContratacion,
    @WebParam(name = "idAlmacen") Integer idAlmacen,
    @WebParam(name = "licenciaMontacarga") Boolean licenciaMontacarga
  ) {
    try {
      return boTrabajadorDeAlmacen.insertar(
        dni,
        nombreCompleto,
        telefono,
        correo,
        direccion,
        estado,
        nombreUsuario,
        contrasenha,
        salario,
        fechaContratacion,
        idAlmacen,
        licenciaMontacarga
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificar_trabajadorDeAlmacen")
  public Integer modificar_trabajadorDeAlmacen(
    @WebParam(name = "idPersona") Integer idPersona,
    @WebParam(name = "dni") String dni,
    @WebParam(name = "nombreCompleto") String nombreCompleto,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "correo") String correo,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "estado") EstadoEmpleadoEnum estado,
    @WebParam(name = "nombreUsuario") String nombreUsuario,
    @WebParam(name = "contrasenha") String contrasenha,
    @WebParam(name = "salario") Double salario,
    @WebParam(name = "fechaContratacion") Date fechaContratacion,
    @WebParam(name = "idAlmacen") Integer idAlmacen,
    @WebParam(name = "licenciaMontacarga") Boolean licenciaMontacarga
  ) {
    try {
      return boTrabajadorDeAlmacen.modificar(
        idPersona,
        dni,
        nombreCompleto,
        telefono,
        correo,
        direccion,
        estado,
        nombreUsuario,
        contrasenha,
        salario,
        fechaContratacion,
        idAlmacen,
        licenciaMontacarga
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "eliminar_trabajadorDeAlmacen")
  public Integer eliminar_trabajadorDeAlmacen(
    @WebParam(name = "idPersona") Integer idPersona
  ) {
    try {
      return boTrabajadorDeAlmacen.eliminar(idPersona);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "listarTodos_trabajadorDeAlmacen")
  public ArrayList<TrabajadorDeAlmacen> listarTodos_trabajadorDeAlmacen() {
    try {
      return boTrabajadorDeAlmacen.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "buscarPorNombre_trabajadorDeAlmacen")
  public ArrayList<TrabajadorDeAlmacen> buscarTrabajadoresDeAlmacen(
    @WebParam(name = "nombreTrabajadorDeAlmacen") String nombre
  ) {
    try {
      return boTrabajadorDeAlmacen.buscarTrabajadoresDeAlmacen(nombre);
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerPorId_trabajadorDeAlmacen")
  public TrabajadorDeAlmacen obtenerPorId_trabajadorDeAlmacen(
    @WebParam(name = "id") Integer id
  ) {
    try {
      Optional<TrabajadorDeAlmacen> trabajadorDeAlmacen =
        boTrabajadorDeAlmacen.obtenerPorId(id);
      return trabajadorDeAlmacen.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "insertar_vendedor")
  public Integer insertar_vendedor(
    @WebParam(name = "dni") String dni,
    @WebParam(name = "nombreCompleto") String nombreCompleto,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "correo") String correo,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "estado") EstadoEmpleadoEnum estado,
    @WebParam(name = "nombreUsuario") String nombreUsuario,
    @WebParam(name = "contrasenha") String contrasenha,
    @WebParam(name = "salario") Double salario,
    @WebParam(name = "fechaContratacion") Date fechaContratacion,
    @WebParam(name = "ingresosVentas") Double ingresosVentas,
    @WebParam(name = "porcentajeComision") Double porcentajeComision
  ) {
    try {
      return boVendedor.insertar(
        dni,
        nombreCompleto,
        telefono,
        correo,
        direccion,
        estado,
        nombreUsuario,
        contrasenha,
        salario,
        fechaContratacion,
        ingresosVentas,
        porcentajeComision
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificar_vendedor")
  public Integer modificar_vendedor(
    @WebParam(name = "idPersona") Integer idPersona,
    @WebParam(name = "dni") String dni,
    @WebParam(name = "nombreCompleto") String nombreCompleto,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "correo") String correo,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "estado") EstadoEmpleadoEnum estado,
    @WebParam(name = "nombreUsuario") String nombreUsuario,
    @WebParam(name = "contrasenha") String contrasenha,
    @WebParam(name = "salario") Double salario,
    @WebParam(name = "fechaContratacion") Date fechaContratacion,
    @WebParam(name = "ingresosVentas") Double ingresosVentas,
    @WebParam(name = "porcentajeComision") Double porcentajeComision
  ) {
    try {
      return boVendedor.modificar(
        idPersona,
        dni,
        nombreCompleto,
        telefono,
        correo,
        direccion,
        estado,
        nombreUsuario,
        contrasenha,
        salario,
        fechaContratacion,
        ingresosVentas,
        porcentajeComision
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "eliminar_vendedor")
  public Integer eliminar_vendedor(
    @WebParam(name = "idVendedor") Integer idVendedor,
    @WebParam(name = "idPersona") Integer idPersona
  ) {
    try {
      return boVendedor.eliminar(idPersona);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "listarTodos_vendedor")
  public ArrayList<Vendedor> listarTodos_vendedor() {
    try {
      return boVendedor.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "buscarPorNombre_vendedor")
  public ArrayList<Vendedor> buscarVendedores(
    @WebParam(name = "nombreVendedor") String nombre
  ) {
    try {
      return boVendedor.buscarVendedores(nombre);
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerPorId_vendedor")
  public Vendedor obtenerPorId_vendedor(@WebParam(name = "id") Integer id) {
    try {
      Optional<Vendedor> vendedor = boVendedor.obtenerPorId(id);
      return vendedor.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "verEmpleado")
  public Integer verificarEmpleado(
    @WebParam(name = "cuenta") String cuenta,
    @WebParam(name = "contrasenha") String contrasenha
  ) {
    try {
      return boEmpleado.verificarEmpleado(cuenta, contrasenha);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "devolverNombreEmpleado")
  public String devolverNombreEmpleado(
    @WebParam(name = "idEmpleado") Integer idEmpleado
  ) {
    try {
      return boEmpleado.devolverNombreEmpleado(idEmpleado);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "devolverRol")
  public String devolverRol(@WebParam(name = "idEmpleado") Integer idEmpleado) {
    try {
      return boEmpleado.devolverRol(idEmpleado);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "listarTodos_empleado")
  public ArrayList<Empleado> listarTodos_empleado() {
    try {
      return boEmpleado.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerPorId_Empleado")
  public Persona obtenerPorId_Persona(@WebParam(name = "id") Integer id) {
    try {
      Optional<Empleado> empleado = boEmpleado.obtenerPorId(id);
      return empleado.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "listarParaIndex_administrador")
  public ArrayList<Administrador> listarParaIndex_administrador() {
    try {
      return boAdministrador.listarParaIndex();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarParaIndex_trabajadorDeAlmacen")
  public ArrayList<TrabajadorDeAlmacen> listarParaIndex_trabajadorDeAlmacen() {
    try {
      return boTrabajadorDeAlmacen.listarParaIndex();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarParaIndex_vendedor")
  public ArrayList<Vendedor> listarParaIndex_vendedor() {
    try {
      return boVendedor.listarParaIndex();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}
