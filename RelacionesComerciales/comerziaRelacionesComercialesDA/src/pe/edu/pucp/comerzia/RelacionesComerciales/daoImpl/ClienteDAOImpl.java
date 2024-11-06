package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ClienteDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.db.Tipo_Operacion;

public class ClienteDAOImpl extends EmpresaDAOImpl implements ClienteDAO {

  private Cliente cliente;

  @Override
  public Integer insertar(Cliente cliente) {
    this.cliente = cliente;
    this.retornarLlavePrimaria = true;

    // Set tipoEmpresa to 'CLIENTE' or 'AMBOS' as appropriate
    cliente.setTipoEmpresa("CLIENTE");

    // Insert into Empresa table with Cliente-specific fields
    Integer idEmpresa = super.insertar();

    this.retornarLlavePrimaria = false;
    return idEmpresa;
  }

  @Override
  protected String obtenerListaDeAtributosParaInsercion() {
    // Include Cliente-specific fields
    return (
      super.obtenerListaDeAtributosParaInsercion() +
      ", fechaRegistro, fechaUltimaCompra"
    );
  }

  @Override
  protected String incluirListaDeParametrosParaInsercion() {
    return super.incluirListaDeParametrosParaInsercion() + ", ?, ?";
  }

  @Override
  protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    super.incluirValorDeParametrosParaInsercion();
    this.incluirParametroDate(7, this.cliente.getFechaRegistro());
    this.incluirParametroDate(8, this.cliente.getFechaUltimaCompra());
  }

  @Override
  public Integer modificar(Cliente cliente) {
    this.cliente = cliente;
    return super.modificar();
  }

  @Override
  protected String obtenerListaDeValoresYAtributosParaModificacion() {
    // Include Cliente-specific fields
    return (
      super.obtenerListaDeValoresYAtributosParaModificacion() +
      ", fechaRegistro=?, fechaUltimaCompra=?"
    );
  }

  @Override
  protected void incluirValorDeParametrosParaModificacion()
    throws SQLException {
    super.incluirValorDeParametrosParaModificacion();
    this.incluirParametroDate(7, this.cliente.getFechaRegistro());
    this.incluirParametroDate(8, this.cliente.getFechaUltimaCompra());
    this.incluirParametroInt(9, this.cliente.getIdEmpresa()); // For WHERE clause
  }

  @Override
  protected String obtenerPredicadoParaLlavePrimaria() {
    String sql;
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
  public Integer eliminar(Cliente cliente) {
    this.cliente = cliente;
    return super.eliminar();
  }

  @Override
  protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    this.incluirParametroInt(1, this.cliente.getIdEmpresa());
  }

  @Override
  public ArrayList<Cliente> listarTodos() {
    return (ArrayList<Cliente>) super.listarTodos(null);
  }

  @Override
  protected String generarSQLParaListarTodos(Integer limite) {
    String sql =
      "select " +
      this.obtenerProyeccionParaSelect() +
      " from " +
      this.nombre_tabla +
      " where tipoEmpresa IN ('CLIENTE', 'AMBOS')";
    if (limite != null && limite > 0) {
      sql = sql.concat(" LIMIT ").concat(limite.toString());
    }
    return sql;
  }

  @Override
  protected String obtenerProyeccionParaSelect() {
    return (
      super.obtenerProyeccionParaSelect() + ", fechaRegistro, fechaUltimaCompra"
    );
  }

  @Override
  protected void agregarObjetoALaLista(List lista, ResultSet resultSet)
    throws SQLException {
    instanciarObjetoDelResultSet();
    lista.add(this.cliente);
  }

  @Override
  protected void instanciarObjetoDelResultSet() throws SQLException {
    this.cliente = new Cliente();
    this.cliente.setIdEmpresa(this.resultSet.getInt("idEmpresa"));
    this.cliente.setNombre(this.resultSet.getString("nombre"));
    this.cliente.setDireccion(this.resultSet.getString("direccion"));
    this.cliente.setTelefono(this.resultSet.getString("telefono"));
    this.cliente.setEmail(this.resultSet.getString("email"));
    this.cliente.setTipoIndustria(this.resultSet.getString("tipoIndustria"));
    this.cliente.setTipoEmpresa(this.resultSet.getString("tipoEmpresa"));
    this.cliente.setFechaRegistro(this.resultSet.getTimestamp("fechaRegistro"));
    this.cliente.setFechaUltimaCompra(
        this.resultSet.getTimestamp("fechaUltimaCompra")
      );
  }

  @Override
  public Cliente obtenerPorId(Integer idEmpresa) {
    this.cliente = new Cliente();
    this.cliente.setIdEmpresa(idEmpresa);
    super.obtenerPorId();
    return this.cliente;
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
  protected void incluirValorDeParametrosParaObtenerPorId()
    throws SQLException {
    this.incluirParametroInt(1, this.cliente.getIdEmpresa());
  }

  @Override
  protected void limpiarObjetoDelResultSet() {
    this.cliente = null;
  }
}
