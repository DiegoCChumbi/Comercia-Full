package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.VendedorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class VendedorDAOImpl
  extends EmpleadoDAOImpl<Vendedor>
  implements VendedorDAO<Vendedor> {

  private Vendedor vendedor;

  public VendedorDAOImpl() {
    super();
    this.vendedor = null;
  }

  @Override
  public Integer insertar(Vendedor vendedor) {
    this.vendedor = vendedor;
    this.retornarLlavePrimaria = true;

    // Set tipoPersona to 'VENDEDOR'
    this.vendedor.setTipoPersona("VENDEDOR");

    // Insert into Persona table with administrador-specific fields
    Integer idPersona = super.insertar(); // This will handle Persona and administrador fields

    this.retornarLlavePrimaria = false;
    return idPersona;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return (
      super.obtenerListaDeAtributosParaInsercion() +
      "ingresosVentas,porcentajeComision"
    );
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return super.incluirListaDeParametrosParaInsercion() + ", ?, ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    super.incluirValorDeParametrosParaInsercion();

    this.incluirParametroDouble(13, this.vendedor.getIngresosVentas());
    this.incluirParametroDouble(14, this.vendedor.getPorcentajeComision());
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    return (
      super.obtenerListaDeValoresYAtributosParaModificacion() +
      "ingresosVentas=?, porcentajeComision=?"
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

    this.incluirParametroDouble(13, this.vendedor.getIngresosVentas());
    this.incluirParametroDouble(14, this.vendedor.getPorcentajeComision());
    this.incluirParametroInt(15, this.vendedor.getIdPersona()); // WHERE clause
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.vendedor.getIdPersona());
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    return (
      super.obtenerProyeccionParaSelect() + "ingresosVentas, porcentajeComision"
    );
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
    this.incluirParametroInt(1, this.vendedor.getIdPersona());
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

    this.vendedor.setTipoPersona(this.resultSet.getString("tipoPersona"));
    this.vendedor.setEstado(
        EstadoEmpleado.valueOf(this.resultSet.getString("estado"))
      );
    this.vendedor.setNombreUsuario(this.resultSet.getString("nombreUsuario"));
    this.vendedor.setSalario(this.resultSet.getDouble("salario"));
    this.vendedor.setFechaContratacion(
        this.resultSet.getDate("fechaContratacion")
      );

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
  public Integer insertar(
    Vendedor vendedor,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.insertar(vendedor);
  }

  @Override
  public Integer modificar(
    Vendedor vendedor,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.modificar(vendedor);
  }

  @Override
  public Integer eliminar(
    Vendedor vendedor,
    Boolean usarTransaccion,
    Connection conexion
  ) {
    this.usarTransaccion = usarTransaccion;
    this.conexion = conexion;
    return this.eliminar(vendedor);
  }

  @Override
  public ArrayList<Vendedor> listarTodos() {
    return (ArrayList<Vendedor>) super.listarTodos(null);
  }

  @Override
  public Vendedor obtenerPorId(Integer idPersona) {
    this.vendedor = new Vendedor();
    this.vendedor.setIdPersona(idPersona);
    super.obtenerPorId();
    return this.vendedor;
  }

  @Override
  public Integer modificar(Vendedor vendedor) {
    this.vendedor = vendedor;
    return super.modificar();
  }

  @Override
  public Integer eliminar(Vendedor vendedor) {
    this.vendedor = vendedor;
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
  public Boolean existeVendedor(Vendedor vendedor, Boolean abreConexion) {
    this.vendedor = vendedor;
    Integer idPersona = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql =
        "SELECT idPersona FROM " +
        this.nombre_tabla +
        " WHERE idPersona=? AND tipoPersona IN ('VENDEDOR', 'AMBOS') AND eliminado = FALSE";
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.vendedor.getIdPersona());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idPersona = this.resultSet.getInt("idPersona");
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
    return idPersona != null;
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE tipoPersona = 'VENDEDOR' AND eliminado = FALSE";
    if (limite != null && limite > 0) {
      sql = sql.concat(" LIMIT ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  public Boolean existeVendedor(Vendedor administrador) {
    Boolean abreConexion = true;
    return existeVendedor(administrador, abreConexion);
  }
}
