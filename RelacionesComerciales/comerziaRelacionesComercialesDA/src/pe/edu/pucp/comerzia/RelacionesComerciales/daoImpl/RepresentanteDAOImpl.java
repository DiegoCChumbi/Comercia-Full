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

public class RepresentanteDAOImpl
  extends PersonaDAOImpl
  implements RepresentanteDAO {

  private Representante representante;

  public RepresentanteDAOImpl() {
    super();
    this.representante = null;
  }

  @Override
  public Integer insertar(Representante representante) {
    this.representante = representante;
    this.retornarLlavePrimaria = true;

    // Set tipoPersona to 'REPRESENTANTE' to indicate the subtype
    this.representante.setTipoPersona("REPRESENTANTE");

    // Insert into Persona table
    Integer idPersona = super.insertar(this.representante);

    this.retornarLlavePrimaria = false;
    return idPersona;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    // Include Representante-specific field: idEmpresa
    return super.obtenerListaDeAtributosParaInsercion() + ", idEmpresa";
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return super.incluirListaDeParametrosParaInsercion() + ", ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    super.incluirValorDeParametrosParaInsercion();
    this.incluirParametroInt(8, this.representante.getIdEmpresa());
  }

  @Override
  public Integer modificar(Representante representante) {
    this.representante = representante;
    return super.modificar();
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return (
      super.obtenerListaDeValoresYAtributosParaModificacion() + ", idEmpresa=?"
    );
  }

  @Override
  protected void incluirValorDeParametrosParaModificacion()
    throws SQLException {
    super.incluirValorDeParametrosParaModificacion();
    this.incluirParametroInt(8, this.representante.getIdEmpresa());
    this.incluirParametroInt(9, this.representante.getIdPersona()); // WHERE clause
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
    this.representante = representante;
    return super.eliminar();
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
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE tipoPersona IN ('REPRESENTANTE', 'AMBOS') AND eliminado = FALSE";
    if (limite != null && limite > 0) {
      sql += " LIMIT " + limite;
    }
    return sql;
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    // Include Representante-specific field: idEmpresa
    return super.obtenerProyeccionParaSelect() + ", idEmpresa";
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
    this.representante.setDni(this.resultSet.getString("dni"));
    this.representante.setNombreCompleto(
        this.resultSet.getString("nombreCompleto")
      );
    this.representante.setTelefono(this.resultSet.getString("telefono"));
    this.representante.setCorreo(this.resultSet.getString("correo"));
    this.representante.setDireccion(this.resultSet.getString("direccion"));
    this.representante.setTipoPersona(this.resultSet.getString("tipoPersona"));
    this.representante.setEliminado(this.resultSet.getBoolean("eliminado"));
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
      String sql =
        "SELECT idPersona FROM " +
        this.nombre_tabla +
        " WHERE idPersona=? AND tipoPersona IN ('REPRESENTANTE', 'AMBOS') AND eliminado = FALSE";
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
