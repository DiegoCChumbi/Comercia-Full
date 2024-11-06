package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.AdministradorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;

/*
public class Administrador {

    private Integer idAdministrador;

    private Integer idAlmacen;
 */
public class AdministradorDAOImpl
  extends EmpleadoDAOImpl<Administrador>
  implements AdministradorDAO<Administrador> {

  private Administrador administrador;

  public AdministradorDAOImpl() {
    super();
    this.administrador = null;
  }

  @Override
  public Integer insertar(Administrador administrador) {
    this.administrador = administrador;
    this.retornarLlavePrimaria = true;

    // Set tipoPersona to 'ADMINISTRADOR'
    this.administrador.setTipoPersona("ADMINISTRADOR");

    // Insert into Persona table with administrador-specific fields
    Integer idPersona = super.insertar(); // This will handle Persona and administrador fields

    this.retornarLlavePrimaria = false;
    return idPersona;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return (super.obtenerListaDeAtributosParaInsercion() + "idAlmacen");
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return super.incluirListaDeParametrosParaInsercion() + ", ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    super.incluirValorDeParametrosParaInsercion();

    this.incluirParametroInt(13, this.administrador.getIdAlmacen());
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return (
      super.obtenerListaDeValoresYAtributosParaModificacion() + "idAlmacen=?"
    );
  }

  @Override
  protected String obtenerPredicadoParaLlavePrimaria() {
    String sql = "";
    if (
      this.tipo_Operacion == tipo_Operacion.MODIFICAR ||
      this.tipo_Operacion == tipo_Operacion.ELIMINAR
    ) {
      sql = "idPersona=?";
    } else {
      sql = "em.idPersona=?";
    }
    return sql;
  }

  @Override
  protected void incluirValorDeParametrosParaModificacion()
    throws SQLException {
    super.incluirValorDeParametrosParaModificacion();

    this.incluirParametroInt(13, this.administrador.getIdAlmacen());
    this.incluirParametroInt(14, this.administrador.getIdPersona()); // WHERE clause
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.administrador.getIdPersona());
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    return (super.obtenerProyeccionParaSelect() + "idAlmacen");
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
    this.incluirParametroInt(1, this.administrador.getIdPersona());
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    this.administrador = new Administrador();
    this.administrador.setIdPersona(this.resultSet.getInt("idPersona"));
    this.administrador.setDni(this.resultSet.getString("dni"));
    this.administrador.setNombreCompleto(
        this.resultSet.getString("nombreCompleto")
      );
    this.administrador.setTelefono(this.resultSet.getString("telefono"));
    this.administrador.setCorreo(this.resultSet.getString("correo"));
    this.administrador.setDireccion(this.resultSet.getString("direccion"));

    // Empleado-specific fields
    this.administrador.setTipoPersona(this.resultSet.getString("tipoPersona"));
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
  public Integer insertar(
    Administrador administrador,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.insertar(administrador);
  }

  @Override
  public Integer modificar(
    Administrador administrador,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.modificar(administrador);
  }

  @Override
  public Integer eliminar(
    Administrador administrador,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.eliminar(administrador);
  }

  @Override
  public ArrayList<Administrador> listarTodos() {
    return (ArrayList<Administrador>) super.listarTodos(null);
  }

  @Override
  public Administrador obtenerPorId(Integer idPersona) {
    this.administrador = new Administrador();
    this.administrador.setIdPersona(idPersona);
    super.obtenerPorId();
    return this.administrador;
  }

  @Override
  public Integer modificar(Administrador administrador) {
    this.administrador = administrador;
    return super.modificar();
  }

  @Override
  public Integer eliminar(Administrador administrador) {
    this.administrador = administrador;
    return super.eliminar();
  }

  @Override
  protected String generarSQLParaListarPorId() {
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE " +
      this.obtenerPredicadoParaLlavePrimaria();
    return sql;
  }

  @Override
  public Boolean existeAdministrador(
    Administrador administrador,
    Boolean abreConexion
  ) {
    this.administrador = administrador;
    Integer idPersona = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql =
        "SELECT idPersona FROM " +
        this.nombre_tabla +
        " WHERE idPersona=? AND tipoPersona IN ('ADMINISTRADOR')";
      sql = sql.concat("idPersona=? ");
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.administrador.getIdPersona());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idPersona = this.resultSet.getInt("idPersona");
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
    return idPersona != null;
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE tipoPersona = 'ADMINISTRADOR' AND eliminado = FALSE";
    if (limite != null && limite > 0) {
      sql = sql.concat(" LIMIT ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  public Boolean existeAdministrador(Administrador administrador) {
    Boolean abreConexion = true;
    return existeAdministrador(administrador, abreConexion);
  }
}
