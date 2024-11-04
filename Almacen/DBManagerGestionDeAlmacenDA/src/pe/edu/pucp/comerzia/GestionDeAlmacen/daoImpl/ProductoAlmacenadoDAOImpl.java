package pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.ProductoAlmacenadoDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class ProductoAlmacenadoDAOImpl extends DAOImpl implements ProductoAlmacenadoDAO {

    private ProductoAlmacenado productoAlmacenado;
    private String sql_filtro;

    public ProductoAlmacenadoDAOImpl() {
        super("ProductoAlmacenado");
        this.productoAlmacenado = null;
        this.sql_filtro = null;
    }

    @Override
    public Boolean existeProductoAlmacenado(ProductoAlmacenado productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
        Integer idProductoAlmacenado = null;
        try {
            this.abrirConexion();
            String sql = "select idProductoAlmacenado from ProductoAlmacenado where ";
            sql = sql.concat("idAlmacen=? ");
            sql = sql.concat("and fechaAlmacenado=? ");
            sql = sql.concat("and stockActual=? ");
            sql = sql.concat("and idProducto=?");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.productoAlmacenado.getIdAlmacen());
            this.incluirParametroDate(2, this.productoAlmacenado.getFechaAlmacenado());
            this.incluirParametroInt(3, this.productoAlmacenado.getStockActual());
            this.incluirParametroInt(4, this.productoAlmacenado.getIdAlmacen());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idProductoAlmacenado = this.resultSet.getInt("idProductoAlmacenado");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar si existe persona - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        this.productoAlmacenado.setIdProductoAlmacenado(idProductoAlmacenado);
        return idProductoAlmacenado != null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idAlmacen,fechaAlmacenado,stockActual,idProducto";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.productoAlmacenado.getIdAlmacen());
        this.incluirParametroDate(2, this.productoAlmacenado.getFechaAlmacenado());
        this.incluirParametroInt(3, this.productoAlmacenado.getStockActual());
        this.incluirParametroInt(4, this.productoAlmacenado.getIdProducto());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "idAlmacen=?,fechaAlmacenado=?,stockActual=?,idProducto=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idProductoAlmacenado=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(5, this.productoAlmacenado.getIdProductoAlmacenado());
        this.incluirParametroInt(1, this.productoAlmacenado.getIdAlmacen());
        this.incluirParametroDate(2, this.productoAlmacenado.getFechaAlmacenado());
        this.incluirParametroInt(3, this.productoAlmacenado.getStockActual());
        this.incluirParametroInt(4, this.productoAlmacenado.getIdProducto());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.productoAlmacenado.getIdProductoAlmacenado());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idProductoAlmacenado,idAlmacen,fechaAlmacenado,stockActual,idProducto";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.productoAlmacenado);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.productoAlmacenado.getIdProductoAlmacenado());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.productoAlmacenado = new ProductoAlmacenado();
        this.productoAlmacenado.setIdProductoAlmacenado(this.resultSet.getInt("idProductoAlmacenado"));
        this.productoAlmacenado.setIdAlmacen(this.resultSet.getInt("idAlmacen"));
        this.productoAlmacenado.setFechaAlmacenado(this.resultSet.getTimestamp("fechaAlmacenado"));
        this.productoAlmacenado.setStockActual(this.resultSet.getInt("stockActual"));
        this.productoAlmacenado.setIdProducto(this.resultSet.getInt("idProducto"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.productoAlmacenado=null;
    }

    @Override
    public Integer insertar(ProductoAlmacenado productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(ProductoAlmacenado productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
        return super.modificar();
    }

    @Override
    public Integer eliminar(ProductoAlmacenado productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
        return super.eliminar();
    }

    @Override
    public ArrayList<ProductoAlmacenado> listarTodos() {
        return (ArrayList<ProductoAlmacenado>) super.listarTodos(null);
    }

    @Override
    public ProductoAlmacenado obtenerPorId(Integer idProductoAlmacenado) {
        this.productoAlmacenado = new ProductoAlmacenado();
        this.productoAlmacenado.setIdProductoAlmacenado(idProductoAlmacenado);
        this.obtenerPorId();
        return this.productoAlmacenado;
    }

    @Override
    public Integer insertar(ProductoAlmacenado productoAlmacenado, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(productoAlmacenado);
    }

    @Override
    public Integer modificar(ProductoAlmacenado productoAlmacenado, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.modificar(productoAlmacenado);
    }

    @Override
    public Integer eliminar(ProductoAlmacenado productoAlmacenado, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.eliminar(productoAlmacenado);
    }

    @Override
    public ArrayList<ProductoAlmacenado> listarPorAlmacen(Integer idAlmacen) {
        this.productoAlmacenado = new ProductoAlmacenado();
        this.productoAlmacenado.setIdAlmacen(idAlmacen);

        this.sql_filtro = " where idAlmacen=? ";
        ArrayList<ProductoAlmacenado> lista = this.listarTodos();
        this.sql_filtro = null;
        return lista;
    }
    
    @Override
    protected String obtenerPredicadoParaListado() {
        if (this.sql_filtro != null)
            return this.sql_filtro;
        return "";
    }
    
    @Override
    protected void incluirValorDeParametrosParaListado() throws SQLException
    {
        if (this.sql_filtro != null)
            this.incluirParametroInt(1, this.productoAlmacenado.getIdAlmacen());
    }
}
