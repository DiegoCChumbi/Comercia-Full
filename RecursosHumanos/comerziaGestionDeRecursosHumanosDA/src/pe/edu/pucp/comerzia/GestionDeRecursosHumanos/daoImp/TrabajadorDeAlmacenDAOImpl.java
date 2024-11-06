package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.TrabajadorDeAlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

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
  extends EmpleadoDAOImpl<TrabajadorDeAlmacen>
  implements TrabajadorDeAlmacenDAO<TrabajadorDeAlmacen> {

  private TrabajadorDeAlmacen trabajador;

  public TrabajadorDeAlmacenDAOImpl() {
    super();
    this.trabajador = null;
  }

  @Override
  public Integer insertar(TrabajadorDeAlmacen trabajador) {
    this.trabajador = trabajador;
    this.retornarLlavePrimaria = true;

    // Set tipoPersona to 'TRABAJADOR_DE_ALMACEN'
    this.trabajador.setTipoPersona("TRABAJADOR_DE_ALMACEN");

    // Insert into Persona table with administrador-specific fields
    Integer idPersona = super.insertar(); // This will handle Persona and administrador fields

    this.retornarLlavePrimaria = false;
    return idPersona;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return (
      super.obtenerListaDeAtributosParaInsercion() +
      "idAlmacen, licenciaMontacarga"
    );
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return super.incluirListaDeParametrosParaInsercion() + ", ?, ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    super.incluirValorDeParametrosParaInsercion();

    this.incluirParametroInt(13, this.trabajador.getIdAlmacen());
    this.incluirParametroBoolean(14, this.trabajador.isLicenciaMontacarga());
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return (
      super.obtenerListaDeValoresYAtributosParaModificacion() +
      "idAlmacen=?, licenciaMontacarga=?"
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

    this.incluirParametroInt(13, this.trabajador.getIdAlmacen());
    this.incluirParametroBoolean(14, this.trabajador.isLicenciaMontacarga());
    this.incluirParametroInt(15, this.trabajador.getIdPersona()); // WHERE clause
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.trabajador.getIdPersona());
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    return (
      super.obtenerProyeccionParaSelect() + "idAlmacen, licenciaMontacarga"
    );
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
    this.incluirParametroInt(1, this.trabajador.getIdPersona());
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

    this.trabajador.setTipoPersona(this.resultSet.getString("tipoPersona"));
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
  public Integer insertar(
    TrabajadorDeAlmacen trabajador,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.insertar(trabajador);
  }

  @Override
  public Integer modificar(
    TrabajadorDeAlmacen trabajador,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.modificar(trabajador);
  }

  @Override
  public Integer eliminar(
    TrabajadorDeAlmacen trabajador,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.eliminar(trabajador);
  }

  @Override
  public ArrayList<TrabajadorDeAlmacen> listarTodos() {
    return (ArrayList<TrabajadorDeAlmacen>) super.listarTodos(null);
  }

  @Override
  public TrabajadorDeAlmacen obtenerPorId(Integer idPersona) {
    this.trabajador = new TrabajadorDeAlmacen();
    this.trabajador.setIdPersona(idPersona);
    super.obtenerPorId();
    return this.trabajador;
  }

  @Override
  public Integer modificar(TrabajadorDeAlmacen trabajador) {
    this.trabajador = trabajador;
    return super.modificar();
  }

  @Override
  public Integer eliminar(TrabajadorDeAlmacen trabajador) {
    this.trabajador = trabajador;
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
  public Boolean existeTrabajadorDeAlmacen(
    TrabajadorDeAlmacen trabajador,
    Boolean abreConexion
  ) {
    this.trabajador = trabajador;
    Integer idPersona = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql =
        "SELECT idPersona FROM " +
        this.nombre_tabla +
        " WHERE idPersona=? AND tipoPersona IN ('TRABAJADOR_DE_ALMACEN', 'AMBOS') AND eliminado = FALSE";
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.trabajador.getIdPersona());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idPersona = this.resultSet.getInt("idPersona");
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
    return idPersona != null;
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE tipoPersona = 'TRABAJADOR_DE_ALMACEN' AND eliminado = FALSE";
    if (limite != null && limite > 0) {
      sql = sql.concat(" LIMIT ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  public Boolean existeTrabajadorDeAlmacen(TrabajadorDeAlmacen administrador) {
    Boolean abreConexion = true;
    return existeTrabajadorDeAlmacen(trabajador, abreConexion);
  }
}
