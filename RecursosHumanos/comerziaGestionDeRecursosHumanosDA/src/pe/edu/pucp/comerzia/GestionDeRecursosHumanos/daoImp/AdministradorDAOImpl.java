package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.AdministradorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.DAOImpl;

/*
public class Administrador {

    private Integer idAdministrador;

    private Integer idAlmacen;
 */
public class AdministradorDAOImpl extends DAOImpl implements AdministradorDAO {

  private Administrador administrador;

  public AdministradorDAOImpl() {
    super("Administrador");
    this.administrador = null;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return "idEmpleado,idAlmacen";
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return "?,?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    this.incluirParametroInt(1, this.administrador.getIdEmpleado());
    this.incluirParametroInt(2, this.administrador.getIdAlmacen());
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return "idAlmacen=?";
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
    this.incluirParametroInt(1, this.administrador.getIdAlmacen());
    this.incluirParametroInt(2, this.administrador.getIdEmpleado());
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.administrador.getIdEmpleado());
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    String sql =
      "per.idPersona, per.dni, per.nombreCompleto, per.telefono, per.correo, per.direccion, ";
    sql = sql.concat(
      "em.idEmpleado, em.estado, em.nombreUsuario, em.contrasenha, em.salario, em.fechaContratacion ,"
    );
    sql = sql.concat("ad.idAlmacen");
    return sql;
  }

  @Override
  protected void agregarObjetoALaLista(List lista, ResultSet resultSet)
    throws SQLException {
    instanciarObjetoDelResultSet();
    lista.add(this.administrador);
  }

  @Override
  protected void incluirValorDeParametrosParaObtenerPorId()
    throws SQLException {
    this.incluirParametroInt(1, this.administrador.getIdEmpleado());
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    this.administrador = new Administrador();
    //        this.administrador.setIdPersona(this.resultSet.getInt("pe.idPersona"));
    this.administrador.setDni(this.resultSet.getString("dni"));
    this.administrador.setNombreCompleto(
        this.resultSet.getString("nombreCompleto")
      );
    this.administrador.setTelefono(this.resultSet.getString("telefono"));
    this.administrador.setCorreo(this.resultSet.getString("correo"));
    this.administrador.setDireccion((this.resultSet.getString("direccion")));

    this.administrador.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
    //this.administrador.setIdPersona(this.resultSet.getInt("idPersona"));
    this.administrador.setEstado(
        EstadoEmpleado.valueOf(this.resultSet.getString("estado"))
      );
    this.administrador.setNombreUsuario(
        this.resultSet.getString("nombreUsuario")
      );
    this.administrador.setContrasenha(this.resultSet.getString("contrasenha"));
    this.administrador.setSalario(this.resultSet.getDouble("salario"));
    this.administrador.setFechaContratacion(
        this.resultSet.getDate("fechaContratacion")
      );

    this.administrador.setIdAlmacen(this.resultSet.getInt("idAlmacen"));
  }

  @Override
  protected void limpiarObjetoDelResultSet() {
    this.administrador = null;
  }

  @Override
  public Integer insertar(Administrador administrador) {
    this.administrador = administrador;
    //        Integer idPersona = null;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(this.administrador.getIdPersona());
    //        persona.setDni(this.administrador.getDni());
    //        persona.setNombreCompleto(this.administrador.getNombreCompleto());
    //        persona.setTelefono(this.administrador.getTelefono());
    //        persona.setCorreo(this.administrador.getTelefono());
    //        persona.setDireccion(this.administrador.getDireccion());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();
    //        Boolean existePersona = personaDAO.existePersona(persona);
    //        Boolean existeAdministrador = false;

    Integer idEmpleado = null;
    Empleado empleado = new Empleado();
    empleado.setDni(this.administrador.getDni());
    empleado.setNombreCompleto(this.administrador.getNombreCompleto());
    empleado.setTelefono(this.administrador.getTelefono());
    empleado.setCorreo(this.administrador.getCorreo());
    empleado.setDireccion(this.administrador.getDireccion());

    empleado.setEstado(this.administrador.getEstado());
    empleado.setNombreUsuario(this.administrador.getNombreUsuario());
    empleado.setContrasenha(this.administrador.getContrasenha());
    empleado.setSalario(this.administrador.getSalario());
    empleado.setFechaContratacion(this.administrador.getFechaContratacion());

    EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
    Boolean existeEmpleado = empleadoDAO.existeEmpleado(empleado);
    Boolean existeAdministrador = false;

    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      if (!existeEmpleado) {
        idEmpleado = empleadoDAO.insertar(
          empleado,
          this.usarTransaccion,
          this.conexion
        );
        this.administrador.setIdEmpleado(idEmpleado);
      } else {
        idEmpleado = empleado.getIdEmpleado();
        this.administrador.setIdEmpleado(idEmpleado);
        Boolean abreConexion = false;
        existeAdministrador = this.existeAdministrador(
            administrador,
            abreConexion
          );
      }
      if (!existeAdministrador) {
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
  public Integer modificar(Administrador administrador) {
    Integer retorno = 0;
    //        this.administrador = administrador;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(administrador.getIdPersona());
    //        persona.setCorreo(administrador.getCorreo());
    //        persona.setDireccion(administrador.getDireccion());
    //        persona.setDni(administrador.getDni());
    //        persona.setNombreCompleto(administrador.getNombreCompleto());
    //        persona.setTelefono(administrador.getTelefono());
    //
    //        PersonaDAO personaDAO = new PersonaDAOImpl();

    this.administrador = administrador;

    Empleado empleado = new Empleado();
    empleado.setDni(this.administrador.getDni());
    empleado.setNombreUsuario(this.administrador.getNombreUsuario());
    empleado.setTelefono(this.administrador.getTelefono());
    empleado.setCorreo(this.administrador.getCorreo());
    empleado.setDireccion(this.administrador.getDireccion());

    empleado.setEstado(this.administrador.getEstado());
    empleado.setNombreUsuario(this.administrador.getNombreUsuario());
    empleado.setContrasenha(this.administrador.getContrasenha());
    empleado.setSalario(this.administrador.getSalario());
    empleado.setFechaContratacion(this.administrador.getFechaContratacion());

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
  public Integer eliminar(Administrador administrador) {
    Integer retorno = 0;
    this.administrador = administrador;
    //        Persona persona = new Persona();
    //        persona.setIdPersona(this.administrador.getIdPersona());
    //        PersonaDAO personaDAO = new PersonaDAOImpl();
    Empleado empleado = new Empleado();
    empleado.setIdEmpleado(this.administrador.getIdEmpleado());
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
  public ArrayList<Administrador> listarTodos() {
    return (ArrayList<Administrador>) super.listarTodos(null);
  }

  @Override
  public Administrador obtenerPorId(Integer idEmpleado) {
    this.administrador = new Administrador();
    this.administrador.setIdEmpleado(idEmpleado);
    super.obtenerPorId();
    return this.administrador;
  }

  @Override
  protected String generarSQLParaListarPorId() {
    String sql = "select ";
    sql = sql.concat(this.obtenerProyeccionParaSelect());
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" ad ");
    sql = sql.concat(
      "join Empleado em join Persona per on em.idEmpleado = ad.idEmpleado and em.idPersona = per.idPersona "
    );
    sql = sql.concat(" where ");
    sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
    return sql;
  }

  @Override
  public Boolean existeAdministrador(
    Administrador administrador,
    Boolean abreConexion
  ) {
    this.administrador = administrador;
    Integer idEmpleado = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql = "select idEmpleado from Empleado where ";
      sql = sql.concat("idEmpleado=? ");
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.administrador.getIdEmpleado());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idEmpleado = this.resultSet.getInt("idEmpleado");
      }
    } catch (SQLException ex) {
      System.err.println("Error al consultar si existe administrador - " + ex);
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
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" ad ");
    sql = sql.concat(
      "join Empleado em join Persona per on em.idEmpleado = ad.idEmpleado and em.idPersona = per.idPersona "
    );
    if (limite != null && limite > 0) {
      sql = sql.concat(" limit ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  public Boolean existeAdministrador(Administrador administrador) {
    Boolean abreConexion = true;
    return existeAdministrador(administrador, abreConexion);
  }
}
