package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ProveedorDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.db.Tipo_Operacion;

public class ProveedorDAOImpl extends EmpresaDAOImpl implements ProveedorDAO {

  private Proveedor proveedor;

  public ProveedorDAOImpl() {
    super();
    this.proveedor = null;
  }

  @Override
  public Integer insertar(Proveedor proveedor) {
    this.proveedor = proveedor;
    this.retornarLlavePrimaria = true;

    // Set tipoEmpresa to 'PROVEEDOR' or 'AMBOS' as appropriate
    this.proveedor.setTipoEmpresa("PROVEEDOR");

    // Insert into Empresa table with Proveedor-specific fields
    Integer idEmpresa = super.insertar();

    this.retornarLlavePrimaria = false;
    return idEmpresa;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    return (
      super.obtenerListaDeAtributosParaInsercion() +
      ", fecha_afiliacion, RUC, razonSocial, calificacion, pais"
    );
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return super.incluirListaDeParametrosParaInsercion() + ", ?, ?, ?, ?, ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    super.incluirValorDeParametrosParaInsercion();
    this.incluirParametroDate(7, this.proveedor.getFecha_afiliacion());
    this.incluirParametroString(8, this.proveedor.getRUC());
    this.incluirParametroString(9, this.proveedor.getRazonSocial());
    this.incluirParametroDouble(10, this.proveedor.getCalificacion());
    this.incluirParametroString(11, this.proveedor.getPais());
  }

  @Override
  public Integer modificar(Proveedor proveedor) {
    this.proveedor = proveedor;
    return super.modificar();
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    // Include Proveedor-specific fields
    return (
      super.obtenerListaDeValoresYAtributosParaModificacion() +
      ", fecha_afiliacion=?, RUC=?, razonSocial=?, calificacion=?, pais=?"
    );
  }

  @Override
  protected void incluirValorDeParametrosParaModificacion()
    throws SQLException {
    super.incluirValorDeParametrosParaModificacion();
    this.incluirParametroDate(7, this.proveedor.getFecha_afiliacion());
    this.incluirParametroString(8, this.proveedor.getRUC());
    this.incluirParametroString(9, this.proveedor.getRazonSocial());
    this.incluirParametroDouble(10, this.proveedor.getCalificacion());
    this.incluirParametroString(11, this.proveedor.getPais());
    this.incluirParametroInt(12, this.proveedor.getIdEmpresa()); // For WHERE clause
  }

  @Override
  protected String obtenerPredicadoParaLlavePrimaria() {
    String sql = "";
    if (
      this.tipo_Operacion == Tipo_Operacion.MODIFICAR ||
      this.tipo_Operacion == Tipo_Operacion.ELIMINAR
    ) {
      sql = "idEmpresa=?";
    } else {
      sql = "emp.idEmpresa=?";
    }
    return sql;
  }

  @Override
  public Integer eliminar(Proveedor proveedor) {
    this.proveedor = proveedor;
    return super.eliminar();
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.proveedor.getIdEmpresa());
  }

  @Override
  public ArrayList<Proveedor> listarTodos() {
    return (ArrayList<Proveedor>) super.listarTodos(null);
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE tipoEmpresa IN ('PROVEEDOR', 'AMBOS')";
    if (limite != null && limite > 0) {
      sql = sql.concat(" LIMIT ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    // Include Proveedor-specific fields
    return (
      super.obtenerProyeccionParaSelect() +
      ", fecha_afiliacion, RUC, razonSocial, calificacion, pais"
    );
  }

  @Override
  protected void agregarObjetoALaLista(List lista, ResultSet resultSet)
    throws SQLException {
    instanciarObjetoDelResultSet();
    lista.add(this.proveedor);
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    this.proveedor = new Proveedor();
    this.proveedor.setIdEmpresa(this.resultSet.getInt("idEmpresa"));
    this.proveedor.setNombre(this.resultSet.getString("nombre"));
    this.proveedor.setDireccion(this.resultSet.getString("direccion"));
    this.proveedor.setTelefono(this.resultSet.getString("telefono"));
    this.proveedor.setEmail(this.resultSet.getString("email"));
    this.proveedor.setTipoIndustria(this.resultSet.getString("tipoIndustria"));
    this.proveedor.setTipoEmpresa(this.resultSet.getString("tipoEmpresa")); // Set via constructor or setter
    this.proveedor.setFecha_afiliacion(
        this.resultSet.getTimestamp("fecha_afiliacion")
      );
    this.proveedor.setRUC(this.resultSet.getString("RUC"));
    this.proveedor.setRazonSocial(this.resultSet.getString("razonSocial"));
    this.proveedor.setCalificacion(this.resultSet.getDouble("calificacion"));
    this.proveedor.setPais(this.resultSet.getString("pais"));
  }

  @Override
  public Proveedor obtenerPorId(Integer idEmpresa) {
    this.proveedor = new Proveedor();
    this.proveedor.setIdEmpresa(idEmpresa);
    super.obtenerPorId();
    return this.proveedor;
  }

  @Override
  protected String generarSQLParaListarPorId() {
    String sql =
      "SELECT " +
      this.obtenerProyeccionParaSelect() +
      " FROM " +
      this.nombre_tabla +
      " WHERE idEmpresa=?";
    return sql;
  }

  @Override
  protected void incluirValorDeParametrosParaObtenerPorId()
    throws SQLException {
    this.incluirParametroInt(1, this.proveedor.getIdEmpresa());
  }

  @Override
  protected void limpiarObjetoDelResultSet() {
    this.proveedor = null;
  }

  @Override
  public Boolean existeProveedor(Proveedor proveedor) {
    Boolean abreConexion = true;
    return existeProveedor(proveedor, abreConexion);
  }

  @Override
  public Boolean existeProveedor(Proveedor proveedor, Boolean abreConexion) {
    this.proveedor = proveedor;
    Integer idEmpresa = null;
    try {
      if (abreConexion) {
        this.abrirConexion();
      }
      String sql =
        "SELECT idEmpresa FROM " +
        this.nombre_tabla +
        " WHERE idEmpresa=? AND tipoEmpresa IN ('PROVEEDOR', 'AMBOS') AND eliminado = FALSE";
      this.colocarSQLenStatement(sql);
      this.incluirParametroInt(1, this.proveedor.getIdEmpresa());
      this.ejecutarConsultaEnBD(sql);
      if (this.resultSet.next()) {
        idEmpresa = this.resultSet.getInt("idEmpresa");
      }
    } catch (SQLException ex) {
      System.err.println("Error al consultar si existe proveedor - " + ex);
    } finally {
      try {
        if (abreConexion) {
          this.cerrarConexion();
        }
      } catch (SQLException ex) {
        System.err.println("Error al cerrar la conexión - " + ex);
      }
    }
    return idEmpresa != null;
  }
}
