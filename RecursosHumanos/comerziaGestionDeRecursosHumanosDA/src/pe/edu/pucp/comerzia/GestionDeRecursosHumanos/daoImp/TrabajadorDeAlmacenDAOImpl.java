package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.TrabajadorDeAlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;
import pe.edu.pucp.comerzia.db.DAOImpl;

/*
public class TrabajadorDeAlmacen {

    private Integer idTrabajadorDeAlmacen;

    private Integer idEmpleado;
    // private Empleado empleado;
    private Integer idAlmacen;
    // private Almacen almacenDeTrabajo;

    private boolean licenciaMontacarga;
 */
public class TrabajadorDeAlmacenDAOImpl
  extends DAOImpl
  implements TrabajadorDeAlmacenDAO {

  private TrabajadorDeAlmacen trabajador;

  public TrabajadorDeAlmacenDAOImpl() {
    super("TrabajadorDeAlmacen");
    this.trabajador = null;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return "idEmpleado,idAlmacen,licenciaMontacarga";
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return "?,?,?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    this.incluirParametroInt(1, this.trabajador.getIdEmpleado());
    this.incluirParametroInt(2, this.trabajador.getIdAlmacen());
    this.incluirParametroBoolean(3, this.trabajador.isLicenciaMontacarga());
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return "idAlmacen=?,licenciaMontacarga=?";
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
    this.incluirParametroInt(1, this.trabajador.getIdAlmacen());
    this.incluirParametroBoolean(1, this.trabajador.isLicenciaMontacarga());
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.trabajador.getIdPersona());
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    String sql =
      "per.idPersona, per.dni, per.nombreCompleto, per.telefono, per.correo, per.direccion, ";
    sql = sql.concat(
      "em.idEmpleado, em.estado, em.nombreUsuario, em.contrasenha, em.salario, em.fechaContratacion, "
    );
    sql = sql.concat("tra.idEmpleado, tra.idAlmacen, tra.licenciaMontacarga");
    return sql;
  }

  @Override
  protected void agregarObjetoALaLista(List lista, ResultSet resultSet)
    throws SQLException {
    instanciarObjetoDelResultSet();
    lista.add(this.trabajador);
  }

  @Override
  protected void incluirValorDeParametrosParaObtenerPorId()
    throws SQLException {
    this.incluirParametroInt(1, this.trabajador.getIdEmpleado());
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    this.trabajador = new TrabajadorDeAlmacen();
    //this.trabajador.setIdPersona(this.resultSet.getInt("idPersona"));
    this.trabajador.setDni(this.resultSet.getString("dni"));
    this.trabajador.setNombreCompleto(
        this.resultSet.getString("nombreCompleto")
      );
    this.trabajador.setTelefono(this.resultSet.getString("telefono"));
    this.trabajador.setCorreo(this.resultSet.getString("correo"));
    this.trabajador.setDireccion((this.resultSet.getString("direccion")));

    this.trabajador.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
    this.trabajador.setEstado(
        EstadoEmpleado.valueOf(this.resultSet.getString("estado"))
      );
    this.trabajador.setNombreUsuario(this.resultSet.getString("nombreUsuario"));
    this.trabajador.setContrasenha(this.resultSet.getString("contrasenha"));
    this.trabajador.setSalario(this.resultSet.getDouble("salario"));
    this.trabajador.setFechaContratacion(
        this.resultSet.getDate("fechaContratacion")
      );

    this.trabajador.setIdAlmacen(this.resultSet.getInt("idAlmacen"));
    this.trabajador.setLicenciaMontacarga(
        this.resultSet.getBoolean("licenciaMontacarga")
      );
  }

  @Override
  protected void limpiarObjetoDelResultSet() {
    this.trabajador = null;
  }

  @Override
  public Integer insertar(TrabajadorDeAlmacen trabajador) {
    this.trabajador = trabajador;
    //        Integer idPersona = null;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(this.trabajador.getIdPersona());
    //        persona.setDni(this.trabajador.getDni());
    //        persona.setNombreCompleto(this.trabajador.getNombreCompleto());
    //        persona.setTelefono(this.trabajador.getTelefono());
    //        persona.setCorreo(this.trabajador.getTelefono());
    //        persona.setDireccion(this.trabajador.getDireccion());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();
    //        Boolean existePersona = personaDAO.existePersona(persona);
    //        Boolean existeTrabajador = false;

    Integer idEmpleado = null;
    Empleado empleado = new Empleado();
    empleado.setDni(this.trabajador.getDni());
    empleado.setNombreCompleto(this.trabajador.getNombreCompleto());
    empleado.setNombreUsuario(this.trabajador.getNombreUsuario());
    empleado.setTelefono(this.trabajador.getTelefono());
    empleado.setCorreo(this.trabajador.getCorreo());
    empleado.setDireccion(this.trabajador.getDireccion());

    empleado.setEstado(this.trabajador.getEstado());
    empleado.setNombreUsuario(this.trabajador.getNombreUsuario());
    empleado.setContrasenha(this.trabajador.getContrasenha());
    empleado.setSalario(this.trabajador.getSalario());
    empleado.setFechaContratacion(this.trabajador.getFechaContratacion());

    EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
    Boolean existeEmpleado = empleadoDAO.existeEmpleado(empleado);
    Boolean existeTrabajador = false;

    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      if (!existeEmpleado) {
        idEmpleado = empleadoDAO.insertar(
          empleado,
          this.usarTransaccion,
          this.conexion
        );
        this.trabajador.setIdEmpleado(idEmpleado);
      } else {
        idEmpleado = empleado.getIdEmpleado();
        this.trabajador.setIdEmpleado(idEmpleado);
        Boolean abreConexion = false;
        existeTrabajador = this.existeTrabajadorDeAlmacen(
            trabajador,
            abreConexion
          );
      }
      if (!existeTrabajador) {
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
  public Integer modificar(TrabajadorDeAlmacen trabajador) {
    Integer retorno = 0;
    //        this.trabajador = trabajador;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(trabajador.getIdPersona());
    //        persona.setCorreo(trabajador.getCorreo());
    //        persona.setDireccion(trabajador.getDireccion());
    //        persona.setDni(trabajador.getDni());
    //        persona.setNombreCompleto(trabajador.getNombreCompleto());
    //        persona.setTelefono(trabajador.getTelefono());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();

    this.trabajador = trabajador;

    Empleado empleado = new Empleado();
    empleado.setDni(this.trabajador.getDni());
    empleado.setNombreUsuario(this.trabajador.getNombreUsuario());
    empleado.setTelefono(this.trabajador.getTelefono());
    empleado.setCorreo(this.trabajador.getCorreo());
    empleado.setDireccion(this.trabajador.getDireccion());

    empleado.setEstado(this.trabajador.getEstado());
    empleado.setNombreUsuario(this.trabajador.getNombreUsuario());
    empleado.setContrasenha(this.trabajador.getContrasenha());
    empleado.setSalario(this.trabajador.getSalario());
    empleado.setFechaContratacion(this.trabajador.getFechaContratacion());

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
  public Integer eliminar(TrabajadorDeAlmacen trabajador) {
    Integer retorno = 0;
    this.trabajador = trabajador;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(this.trabajador.getIdPersona());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();

    Empleado empleado = new Empleado();
    empleado.setIdEmpleado(this.trabajador.getIdEmpleado());
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
  public ArrayList<TrabajadorDeAlmacen> listarTodos() {
    return (ArrayList<TrabajadorDeAlmacen>) super.listarTodos(null);
  }

  @Override
  public TrabajadorDeAlmacen obtenerPorId(Integer idEmpleado) {
    this.trabajador = new TrabajadorDeAlmacen();
    this.trabajador.setIdEmpleado(idEmpleado);
    super.obtenerPorId();
    return this.trabajador;
  }

  @Override
  protected String generarSQLParaListarPorId() {
    String sql = "select ";
    sql = sql.concat(this.obtenerProyeccionParaSelect());
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" tra ");
    sql = sql.concat(
      "join Empleado em join Persona per on em.idEmpleado = tra.idEmpleado and em.idPersona = per.idPersona "
    );
    sql = sql.concat(" where ");
    sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
    return sql;
  }

  @Override
  public Boolean existeTrabajadorDeAlmacen(
    TrabajadorDeAlmacen trabajador,
    Boolean abreConexion
  ) {
    this.trabajador = trabajador;
    Integer idEmpleado = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql = "select idEmpleado from Empleado where ";
      sql = sql.concat("idEmpleado=? ");
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.trabajador.getIdEmpleado());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idEmpleado = this.resultSet.getInt("idEmpleado");
      }
    } catch (SQLException ex) {
      System.err.println("Error al consultar si existe trabajador - " + ex);
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
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" tra ");
    sql = sql.concat(
      "join Empleado em join Persona per on em.idEmpleado = tra.idEmpleado and em.idPersona = per.idPersona "
    );
    if (limite != null && limite > 0) {
      sql = sql.concat(" limit ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  public Boolean existeTrabajadorDeAlmacen(TrabajadorDeAlmacen administrador) {
    Boolean abreConexion = true;
    return existeTrabajadorDeAlmacen(trabajador, abreConexion);
  }
}
