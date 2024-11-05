package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.VendedorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class VendedorDAOImpl extends DAOImpl implements VendedorDAO {

  private Vendedor vendedor;

  public VendedorDAOImpl() {
    super("Vendedor");
    this.vendedor = null;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return "idEmpleado,ingresosVentas,porcentajeComision";
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return "?,?,?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    this.incluirParametroInt(1, this.vendedor.getIdEmpleado());
    this.incluirParametroDouble(2, this.vendedor.getIngresosVentas());
    this.incluirParametroDouble(3, this.vendedor.getPorcentajeComision());
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return "ingresosVentas=?,porcentajeComision=?";
  }

  @Override
  protected String obtenerPredicadoParaLlavePrimaria() {
    String sql = "";
    if (
      this.tipo_Operacion == tipo_Operacion.MODIFICAR ||
      this.tipo_Operacion == tipo_Operacion.ELIMINAR
    ) {
      sql = "idEmpleado=?";
    } else {
      sql = "em.idEmpleado=?";
    }
    return sql;
  }

  @Override
  protected void incluirValorDeParametrosParaModificacion()
    throws SQLException {
    this.incluirParametroDouble(1, this.vendedor.getIngresosVentas());
    this.incluirParametroDouble(2, this.vendedor.getPorcentajeComision());
    this.incluirParametroInt(3, this.vendedor.getIdEmpleado());
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.vendedor.getIdVendedor());
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    String sql =
      "per.idPersona, per.dni, per.nombreCompleto, per.telefono, per.correo, per.direccion, ";
    sql = sql.concat(
      "em.idEmpleado, em.estado, em.nombreUsuario, em.contrasenha, em.salario, em.fechaContratacion, "
    );
    sql = sql.concat("ve.ingresosVentas,ve.porcentajeComision");
    return sql;
  }

  @Override
  protected void agregarObjetoALaLista(List lista, ResultSet resultSet)
    throws SQLException {
    instanciarObjetoDelResultSet();
    lista.add(this.vendedor);
  }

  @Override
  protected void incluirValorDeParametrosParaObtenerPorId()
    throws SQLException {
    this.incluirParametroInt(1, this.vendedor.getIdEmpleado());
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    this.vendedor = new Vendedor();
    this.vendedor.setIdPersona(this.resultSet.getInt("idPersona"));
    this.vendedor.setDni(this.resultSet.getString("dni"));
    this.vendedor.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
    this.vendedor.setTelefono(this.resultSet.getString("telefono"));
    this.vendedor.setCorreo(this.resultSet.getString("correo"));
    this.vendedor.setDireccion((this.resultSet.getString("direccion")));

    this.vendedor.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
    this.vendedor.setIdPersona(this.resultSet.getInt("idPersona"));
    this.vendedor.setDni(this.resultSet.getString("dni"));
    this.vendedor.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
    this.vendedor.setTelefono(this.resultSet.getString("telefono"));
    this.vendedor.setCorreo(this.resultSet.getString("correo"));
    this.vendedor.setDireccion((this.resultSet.getString("direccion")));
    this.vendedor.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
    this.vendedor.setEstado(
        EstadoEmpleado.valueOf(this.resultSet.getString("estado"))
      );
    this.vendedor.setNombreUsuario(this.resultSet.getString("nombreUsuario"));
    this.vendedor.setSalario(this.resultSet.getDouble("salario"));
    this.vendedor.setFechaContratacion(
        this.resultSet.getDate("fechaContratacion")
      );

    //this.vendedor.setIdVendedor(this.resultSet.getInt("idVendedor"));
    this.vendedor.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
    this.vendedor.setIngresosVentas(this.resultSet.getDouble("ingresosVentas"));
    this.vendedor.setPorcentajeComision(
        this.resultSet.getDouble("porcentajeComision")
      );
  }

  @Override
  protected void limpiarObjetoDelResultSet() {
    this.vendedor = null;
  }

  @Override
  public Integer insertar(Vendedor vendedor) {
    this.vendedor = vendedor;
    //        Integer idPersona = null;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(this.vendedor.getIdPersona());
    //        persona.setDni(this.vendedor.getDni());
    //        persona.setNombreCompleto(this.vendedor.getNombreCompleto());
    //        persona.setTelefono(this.vendedor.getTelefono());
    //        persona.setCorreo(this.vendedor.getTelefono());
    //        persona.setDireccion(this.vendedor.getDireccion());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();
    //        Boolean existePersona = personaDAO.existePersona(persona);
    //        Boolean existeVendedor = false;

    Integer idEmpleado = null;
    Empleado empleado = new Empleado();
    empleado.setDni(this.vendedor.getDni());
    empleado.setNombreCompleto(this.vendedor.getNombreCompleto());
    empleado.setTelefono(this.vendedor.getTelefono());
    empleado.setCorreo(this.vendedor.getCorreo());
    empleado.setDireccion(this.vendedor.getDireccion());

    empleado.setEstado(this.vendedor.getEstado());
    empleado.setNombreUsuario(this.vendedor.getNombreUsuario());
    empleado.setContrasenha(this.vendedor.getContrasenha());
    empleado.setSalario(this.vendedor.getSalario());
    empleado.setFechaContratacion(this.vendedor.getFechaContratacion());

    EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
    Boolean existeEmpleado = empleadoDAO.existeEmpleado(empleado);
    Boolean existeVendedor = false;

    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      if (!existeEmpleado) {
        idEmpleado = empleadoDAO.insertar(
          empleado,
          this.usarTransaccion,
          this.conexion
        );
        this.vendedor.setIdEmpleado(idEmpleado);
      } else {
        idEmpleado = empleado.getIdPersona();
        this.vendedor.setIdPersona(idEmpleado);
        Boolean abreConexion = false;
        existeVendedor = this.existeVendedor(vendedor, abreConexion);
      }
      if (!existeVendedor) {
        super.insertar();
      }
      this.comitarTransaccion();
    } catch (Exception ex) {
      System.err.println("Error al intentar insertar - " + ex);
      try {
        this.rollbackTransaccion();
      } catch (SQLException ex1) {
        System.err.println("Error al intentar hacer rollback - " + ex1);
      }
    } finally {
      try {
        this.cerrarConexion();
      } catch (SQLException ex) {
        System.err.println("Error al intentar cerrar la conexion - " + ex);
      }
    }
    this.usarTransaccion = true;
    return idEmpleado;
  }

  @Override
  public Integer modificar(Vendedor vendedor) {
    Integer retorno = 0;
    //        this.vendedor = vendedor;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(vendedor.getIdPersona());
    //        persona.setCorreo(vendedor.getCorreo());
    //        persona.setDireccion(vendedor.getDireccion());
    //        persona.setDni(vendedor.getDni());
    //        persona.setNombreCompleto(vendedor.getNombreCompleto());
    //        persona.setTelefono(vendedor.getTelefono());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();

    this.vendedor = vendedor;

    Empleado empleado = new Empleado();
    empleado.setDni(this.vendedor.getDni());
    empleado.setNombreUsuario(this.vendedor.getNombreUsuario());
    empleado.setTelefono(this.vendedor.getTelefono());
    empleado.setCorreo(this.vendedor.getCorreo());
    empleado.setDireccion(this.vendedor.getDireccion());

    empleado.setEstado(this.vendedor.getEstado());
    empleado.setNombreUsuario(this.vendedor.getNombreUsuario());
    empleado.setContrasenha(this.vendedor.getContrasenha());
    empleado.setSalario(this.vendedor.getSalario());
    empleado.setFechaContratacion(this.vendedor.getFechaContratacion());

    EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();

    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      empleadoDAO.modificar(empleado, this.usarTransaccion, this.conexion);
      retorno = super.modificar();
      this.comitarTransaccion();
    } catch (SQLException ex) {
      System.err.println("Error al intentar modificar - " + ex);
      try {
        this.rollbackTransaccion();
      } catch (SQLException ex1) {
        System.err.println("Error al intentar hacer rollback - " + ex1);
      }
    } finally {
      try {
        this.cerrarConexion();
      } catch (SQLException ex) {
        System.err.println("Error al intentar cerrar la conexion - " + ex);
      }
    }
    this.usarTransaccion = true;
    return retorno;
  }

  @Override
  public Integer eliminar(Vendedor vendedor) {
    Integer retorno = 0;
    this.vendedor = vendedor;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(this.vendedor.getIdPersona());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();

    Empleado empleado = new Empleado();
    empleado.setIdEmpleado(this.vendedor.getIdEmpleado());
    EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();

    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      retorno = super.eliminar();
      empleadoDAO.eliminar(empleado, this.usarTransaccion, this.conexion);
      this.comitarTransaccion();
    } catch (SQLException ex) {
      System.err.println("Error al intentar eliminar - " + ex);
      try {
        this.rollbackTransaccion();
      } catch (SQLException ex1) {
        System.err.println("Error al intentar hacer rollback - " + ex1);
      }
    } finally {
      try {
        this.cerrarConexion();
      } catch (SQLException ex) {
        System.err.println("Error al intentar cerrar la conexion - " + ex);
      }
    }
    this.usarTransaccion = true;
    return retorno;
  }

  @Override
  public ArrayList<Vendedor> listarTodos() {
    return (ArrayList<Vendedor>) super.listarTodos(null);
  }

  @Override
  public Vendedor obtenerPorId(Integer idEmpleado) {
    this.vendedor = new Vendedor();
    this.vendedor.setIdEmpleado(idEmpleado);
    super.obtenerPorId();
    return this.vendedor;
  }

  @Override
  protected String generarSQLParaListarPorId() {
    String sql = "select ";
    sql = sql.concat(this.obtenerProyeccionParaSelect());
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" ve ");
    sql = sql.concat(
      "join Empleado em join Persona per on em.idEmpleado = ve.idEmpleado and em.idPersona = per.idPersona "
    );
    sql = sql.concat(" where ");
    sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
    return sql;
  }

  @Override
  public Boolean existeVendedor(Vendedor vendedor, Boolean abreConexion) {
    this.vendedor = vendedor;
    Integer idEmpleado = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql = "select idEmpleado from Empleado where ";
      sql = sql.concat("idEmpleado=? ");
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.vendedor.getIdEmpleado());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idEmpleado = this.resultSet.getInt("idEmpleado");
      }
    } catch (SQLException ex) {
      System.err.println("Error al consultar si existe vendedor - " + ex);
    } finally {
      try {
        if (abreConexion) {
          this.cerrarConexion();
        }
      } catch (SQLException ex) {
        System.err.println("Error al cerrar la conexión - " + ex);
      }
    }
    return idEmpleado != null;
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    String sql = "select ";
    sql = sql.concat(obtenerProyeccionParaSelect());
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" ve ");
    sql = sql.concat(
      "join Empleado em join Persona per on em.idEmpleado = ve.idEmpleado and em.idPersona = per.idPersona "
    );
    if (limite != null && limite > 0) {
      sql = sql.concat(" limit ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  public Boolean existeVendedor(Vendedor administrador) {
    Boolean abreConexion = true;
    return existeVendedor(administrador, abreConexion);
  }
}
