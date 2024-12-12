package pe.edu.pucp.comerzia.modules.gestion_almacen.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.ProductoAlmacenadoDAO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper.ProductoAlmacenadoMapper;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.ProductoAlmacenado;

public class ProductoAlmacenadoBO {

  private ProductoAlmacenadoDAO productoAlmacenadoDAO;

  public ProductoAlmacenadoBO() {
    this.productoAlmacenadoDAO =
      ProductoAlmacenadoDAO.getProductoAlmacenadoInstance();
  }

  public Integer insertar(
    Integer idAlmacen,
    Date fechaAlmacenado,
    Integer stockActual,
    Integer idProducto
  ) throws SQLException {
    ProductoAlmacenado productoAlmacenado = new ProductoAlmacenado();

    productoAlmacenado.setIdAlmacen(idAlmacen);
    productoAlmacenado.setFechaAlmacenado(fechaAlmacenado);
    productoAlmacenado.setStockActual(stockActual);
    productoAlmacenado.setIdProducto(idProducto);

    return productoAlmacenadoDAO.insert(productoAlmacenado);
  }

  public Integer modificar(
    Integer id,
    Integer idAlmacen,
    Date fechaAlmacenado,
    Integer stockActual,
    Integer idProducto
  ) throws SQLException {
    ProductoAlmacenado productoAlmacenado = new ProductoAlmacenado();

    productoAlmacenado.setId(id);
    productoAlmacenado.setIdAlmacen(idAlmacen);
    productoAlmacenado.setFechaAlmacenado(fechaAlmacenado);
    productoAlmacenado.setStockActual(stockActual);
    productoAlmacenado.setIdProducto(idProducto);

    return productoAlmacenadoDAO.update(id, productoAlmacenado);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return productoAlmacenadoDAO.delete(id);
  }

  public ArrayList<ProductoAlmacenado> listarTodos() throws SQLException {
    return new ArrayList<>(productoAlmacenadoDAO.findAll());
  }

  public Optional<ProductoAlmacenado> obtenerPorId(Integer id)
    throws SQLException {
    return this.productoAlmacenadoDAO.findById(id);
  }

  public ArrayList<ProductoAlmacenado> listarPorAlmacen(Integer idAlmacen)
    throws SQLException {
    return new ArrayList<>(
      this.productoAlmacenadoDAO.query()
        .where(ProductoAlmacenadoMapper.Columns.idAlmacen.eq(idAlmacen))
        .list()
    );
  }
}
