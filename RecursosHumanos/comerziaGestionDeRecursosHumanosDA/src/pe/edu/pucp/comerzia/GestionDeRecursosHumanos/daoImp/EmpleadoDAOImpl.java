package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;

public class EmpleadoDAOImpl<T extends Empleado>
  extends PersonaDAOImpl<T>
  implements EmpleadoDAO<T> {

  private Empleado empleado;

  public EmpleadoDAOImpl() {
    super();
    this.empleado = null;
  }

  @Override
  public Integer insertar(Empleado empleado) {
    this.empleado = empleado;
    this.retornarLlavePrimaria = true;

    // Set tipoPersona to 'EMPLEADO'
    this.empleado.setTipoPersona("EMPLEADO");

    // Insert into Persona table with Empleado-specific fields
    Integer idPersona = super.insertar(); // This will handle Persona and Empleado fields

    this.retornarLlavePrimaria = false;
    return idPersona;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    // Include Empleado-specific fields along with Persona fields
    return (
      super.obtenerListaDeAtributosParaInsercion() +
      "estado, nombreUsuario, contrasenha, salario, fechaContratacion"
    );
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    // Add placeholders for Empleado-specific fields
    return super.incluirListaDeParametrosParaInsercion() + ", ?, ?, ?, ?, ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    // Insert Persona fields first
    super.incluirValorDeParametrosParaInsercion();

    this.incluirParametroString(8, this.empleado.getEstado().toString());
    this.incluirParametroString(9, this.empleado.getNombreUsuario());
    this.incluirParametroString(10, this.empleado.getContrasenha());
    this.incluirParametroDouble(11, this.empleado.getSalario());
    this.incluirParametroDate(12, this.empleado.getFechaContratacion());
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    // Include Empleado-specific fields in the SET clause
    return (
      super.obtenerListaDeValoresYAtributosParaModificacion() +
      "estado=?, nombreUsuario=?, contrasenha=?, salario=?, fechaContratacion=?"
    );
  }

  @Override
  protected void incluirValorDeParametrosParaModificacion()
    throws SQLException {
    // Update Persona fields first
    super.incluirValorDeParametrosParaModificacion();

    // Update Empleado-specific fields
    this.incluirParametroString(8, this.empleado.getEstado().toString());
    this.incluirParametroString(9, this.empleado.getNombreUsuario());
    this.incluirParametroString(10, this.empleado.getContrasenha());
    this.incluirParametroDouble(11, this.empleado.getSalario());
    this.incluirParametroDate(12, this.empleado.getFechaContratacion());
    this.incluirParametroInt(13, this.empleado.getIdPersona()); // WHERE clause
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.empleado.getIdPersona());
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    // Include Empleado-specific fields in SELECT
    return (
      super.obtenerProyeccionParaSelect() +
      "estado, nombreUsuario, contrasenha, salario, fechaContratacion"
    );
  }

  @Override
  protected void agregarObjetoALaLista(List lista, ResultSet resultSet)
    throws SQLException {
    instanciarObjetoDelResultSet();
    lista.add(this.empleado);
  }

  @Override
  protected void incluirValorDeParametrosParaObtenerPorId()
    throws SQLException {
    this.incluirParametroInt(1, this.empleado.getIdPersona());
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    // Instantiate Empleado object and populate fields from ResultSet
    this.empleado = new Empleado();
    this.empleado.setIdPersona(this.resultSet.getInt("idPersona"));
    this.empleado.setDni(this.resultSet.getString("dni"));
    this.empleado.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
    this.empleado.setTelefono(this.resultSet.getString("telefono"));
    this.empleado.setCorreo(this.resultSet.getString("correo"));
    this.empleado.setDireccion(this.resultSet.getString("direccion"));

    // Empleado-specific fields
    this.empleado.setTipoPersona(this.resultSet.getString("tipoPersona"));
    this.empleado.setEstado(
        EstadoEmpleado.valueOf(this.resultSet.getString("estado"))
      );
    this.empleado.setNombreUsuario(this.resultSet.getString("nombreUsuario"));
    this.empleado.setContrasenha(this.resultSet.getString("contrasenha"));
    this.empleado.setSalario(this.resultSet.getDouble("salario"));
    this.empleado.setFechaContratacion(
        this.resultSet.getDate("fechaContratacion")
      );
  }

  @Override
  protected void limpiarObjetoDelResultSet() {
    this.empleado = null;
  }

  @Override
  public ArrayList<T> listarTodos() {
    return (ArrayList<T>) super.listarTodos(null);
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    // Select only Empleado records where tipoPersona = 'EMPLEADO' and not eliminado
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE tipoPersona = 'EMPLEADO' AND eliminado = FALSE";
    if (limite != null && limite > 0) {
      sql = sql.concat(" LIMIT ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  public T obtenerPorId(Integer idPersona) {
    this.empleado = new Empleado();
    this.empleado.setIdPersona(idPersona);
    super.obtenerPorId();
    return (T) this.empleado;
  }

  @Override
  public Integer modificar(Empleado empleado) {
    this.empleado = empleado;
    return super.modificar();
  }

  @Override
  public Integer eliminar(Empleado empleado) {
    this.empleado = empleado;
    return super.eliminar();
  }

  @Override
  public Boolean existeEmpleado(T empleado, Boolean abreConexion) {
    this.empleado = empleado;
    Integer idPersona = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql =
        "SELECT idPersona FROM " +
        this.nombre_tabla +
        " WHERE idPersona=? AND tipoPersona IN ('EMPLEADO', 'AMBOS') AND eliminado = FALSE";
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.empleado.getIdPersona());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idPersona = this.resultSet.getInt("idPersona");
      }
    } catch (SQLException ex) {
      System.err.println("Error al consultar si existe empleado - " + ex);
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
  public Boolean existeEmpleado(T empleado) {
    Boolean abreConexion = true;
    return existeEmpleado(empleado, abreConexion);
  }

  // Verification Methods

  @Override
  public Integer verificarEmpleado(
    String cuenta,
    String contrasenha,
    Boolean abreConexion
  ) {
    Integer idPersona = -1;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      if (this.conexion == null) {
        throw new RuntimeException(
          "La conexión a la base de datos no se pudo establecer."
        );
      }
      String sql =
        "SELECT idPersona FROM Persona WHERE nombreUsuario = ? AND contrasenha = ? AND tipoPersona = 'EMPLEADO' AND eliminado = FALSE";
      this.colocarSQLenStatement(sql);

      // Establecer los parámetros de cuenta y contraseña en el statement
      this.incluirParametroString(1, cuenta);
      this.incluirParametroString(2, contrasenha);
      this.ejecutarConsultaEnBD(sql);

      // Verificar si hay un resultado en el ResultSet
      if (this.resultSet.next()) {
        idPersona = this.resultSet.getInt("idPersona");
        System.out.println("Empleado verificado con ID: " + idPersona);
      }
    } catch (SQLException ex) {
      System.err.println("Error al verificar empleado - " + ex);
    } finally {
      try {
        if (abreConexion) {
          this.cerrarConexion();
        }
      } catch (SQLException ex) {
        System.err.println("Error al cerrar la conexión - " + ex);
      }
    }
    return idPersona;
  }

  @Override
  public Integer verificarEmpleado(String cuenta, String contrasenha) {
    Boolean abreConexion = true;
    return verificarEmpleado(cuenta, contrasenha, abreConexion);
  }

  @Override
  public String devolverNombreEmpleado(
    Integer idEmpleado,
    Boolean abreConexion
  ) {
    String nombreCompleto = null;

    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      if (this.conexion == null) {
        throw new RuntimeException(
          "La conexión a la base de datos no se pudo establecer."
        );
      }
      // Retrieve nombreCompleto for the given Empleado ID
      String sql =
        "SELECT nombreCompleto FROM Persona WHERE idPersona = ? AND tipoPersona = 'EMPLEADO' AND eliminado = FALSE";
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, idEmpleado);
      this.ejecutarConsultaEnBD(sql);

      if (this.resultSet.next()) {
        nombreCompleto = this.resultSet.getString("nombreCompleto");
      }
    } catch (SQLException ex) {
      System.err.println("Error al consultar el nombre del empleado - " + ex);
    } finally {
      try {
        if (abreConexion) {
          this.cerrarConexion();
        }
      } catch (SQLException ex) {
        System.err.println("Error al cerrar la conexión - " + ex);
      }
    }
    return nombreCompleto;
  }

  @Override
  public String devolverNombreEmpleado(Integer idEmpleado) {
    Boolean abreConexion = true;
    return devolverNombreEmpleado(idEmpleado, abreConexion);
  }

  @Override
  public Integer insertar(
    Empleado empleado,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.insertar(empleado);
  }

  @Override
  public Integer modificar(
    Empleado empleado,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.modificar(empleado);
  }

  @Override
  public Integer eliminar(
    Empleado empleado,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.eliminar(empleado);
  }
}
