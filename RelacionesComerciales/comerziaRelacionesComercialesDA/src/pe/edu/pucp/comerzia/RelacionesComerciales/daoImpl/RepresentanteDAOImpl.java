package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.PersonaDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Representante;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.RepresentanteDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.db.Tipo_Operacion;

public class RepresentanteDAOImpl extends DAOImpl implements RepresentanteDAO {

  private Representante representante;

  public RepresentanteDAOImpl() {
    super("Representante");
    this.representante = null;
  }

  @Override
  public Integer insertar(Representante representante) {
    this.representante = representante;
    Integer idPersona = null;
    Persona persona = new Persona();
    persona.setDni(this.representante.getDni());
    persona.setNombreCompleto(this.representante.getNombreCompleto());
    persona.setDireccion(this.representante.getDireccion());
    persona.setTelefono(this.representante.getTelefono());
    persona.setCorreo(this.representante.getCorreo());

    PersonaDAO personaDAO = new PersonaDAOImpl();
    Boolean existePersona = personaDAO.existePersona(persona);
    Boolean existeRepresentante = false;

    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      if (!existePersona) {
        idPersona = personaDAO.insertar(
          persona,
          this.usarTransaccion,
          this.conexion
        );
        this.representante.setIdPersona(idPersona);
      } else {
        idPersona = persona.getIdPersona();
        this.representante.setIdPersona(idPersona);
        Boolean abreConexion = false;
        existeRepresentante = this.existeRepresentante(
            this.representante,
            abreConexion
          );
      }
      if (!existeRepresentante) {
        super.insertar();
      }
      this.comitarTransaccion();
    } catch (SQLException ex) {
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
    return idPersona;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return "idPersona, idEmpresa";
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return "?, ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    this.incluirParametroInt(1, this.representante.getIdPersona());
    this.incluirParametroInt(2, this.representante.getIdEmpresa());
  }

  @Override
  public Integer modificar(Representante representante) {
    Integer retorno = 0;
    this.representante = representante;
    Persona persona = new Persona();
    persona.setIdPersona(this.representante.getIdPersona());
    persona.setDni(this.representante.getDni());
    persona.setNombreCompleto(this.representante.getNombreCompleto());
    persona.setDireccion(this.representante.getDireccion());
    persona.setTelefono(this.representante.getTelefono());
    persona.setCorreo(this.representante.getCorreo());

    PersonaDAO personaDAO = new PersonaDAOImpl();

    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      personaDAO.modificar(persona, this.usarTransaccion, this.conexion);
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
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return "idEmpresa=?";
  }

  @Override
  protected void incluirValorDeParametrosParaModificacion()
    throws SQLException {
    this.incluirParametroInt(2, this.representante.getIdPersona());
    this.incluirParametroInt(1, this.representante.getIdEmpresa());
  }

  @Override
  protected String obtenerPredicadoParaLlavePrimaria() {
    String sql;
    if (
      this.tipo_Operacion == Tipo_Operacion.MODIFICAR ||
      this.tipo_Operacion == Tipo_Operacion.ELIMINAR
    ) {
      sql = "idPersona=?";
    } else {
      sql = "per.idPersona=?";
    }
    return sql;
  }

  @Override
  public Integer eliminar(Representante representante) {
    Integer retorno = 0;
    this.representante = representante;
    Persona persona = new Persona();
    persona.setIdPersona(this.representante.getIdPersona());

    PersonaDAO personaDAO = new PersonaDAOImpl();
    this.usarTransaccion = false;
    try {
      this.iniciarTransaccion();
      retorno = super.eliminar();
      personaDAO.eliminar(persona, this.usarTransaccion, this.conexion);
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
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.representante.getIdPersona());
  }

  @Override
  public ArrayList<Representante> listarTodos() {
    return (ArrayList<Representante>) super.listarTodos(null);
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    String sql = "select ";
    sql = sql.concat(obtenerProyeccionParaSelect());
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" rep ");
    sql = sql.concat("join persona per on per.idPersona = rep.idPersona ");
    if (limite != null && limite > 0) {
      sql = sql.concat(" limit ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    String sql =
      "per.idPersona, per.nombreCompleto, per.direccion, per.telefono, ";
    sql = sql.concat("per.correo, per.dni, ");
    sql = sql.concat("rep.idEmpresa");
    return sql;
  }

  @Override
  protected void agregarObjetoALaLista(List lista, ResultSet resultSet)
    throws SQLException {
    instanciarObjetoDelResultSet();
    lista.add(this.representante);
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    this.representante = new Representante();
    this.representante.setIdPersona(this.resultSet.getInt("idPersona"));
    this.representante.setNombreCompleto(
        this.resultSet.getString("nombreCompleto")
      );
    this.representante.setDireccion(this.resultSet.getString("direccion"));
    this.representante.setTelefono(this.resultSet.getString("telefono"));
    this.representante.setCorreo(this.resultSet.getString("correo"));
    this.representante.setDni(this.resultSet.getString("dni"));
    this.representante.setIdEmpresa(this.resultSet.getInt("idEmpresa"));
  }

  @Override
  public Representante obtenerPorId(Integer idPersona) {
    this.representante = new Representante();
    this.representante.setIdPersona(idPersona);
    super.obtenerPorId();
    return this.representante;
  }

  @Override
  protected String generarSQLParaListarPorId() {
    String sql = "select ";
    sql = sql.concat(this.obtenerProyeccionParaSelect());
    sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" rep ");
    sql = sql.concat("join persona per on per.idPersona = rep.idPersona ");
    sql = sql.concat(" where ");
    sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
    return sql;
  }

  @Override
  protected void incluirValorDeParametrosParaObtenerPorId()
    throws SQLException {
    this.incluirParametroInt(1, this.representante.getIdPersona());
  }

  @Override
  protected void limpiarObjetoDelResultSet() {
    this.representante = null;
  }

  @Override
  public Boolean existeRepresentante(Representante representante) {
    Boolean abreConexion = true;
    return existeRepresentante(representante, abreConexion);
  }

  @Override
  public Boolean existeRepresentante(
    Representante representante,
    Boolean abreConexion
  ) {
    this.representante = representante;
    Integer idPersona = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql = "select idPersona from representante where ";
      sql = sql.concat("idPersona=? ");
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.representante.getIdPersona());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idPersona = this.resultSet.getInt("idPersona");
      }
    } catch (SQLException ex) {
      System.err.println("Error al consultar si existe representante - " + ex);
    } finally {
      try {
        if (abreConexion) {
          this.cerrarConexion();
        }
      } catch (SQLException ex) {
        System.err.println("Error al cerrar la conexión - " + ex);
      }
    }
    return idPersona != null;
  }
}
