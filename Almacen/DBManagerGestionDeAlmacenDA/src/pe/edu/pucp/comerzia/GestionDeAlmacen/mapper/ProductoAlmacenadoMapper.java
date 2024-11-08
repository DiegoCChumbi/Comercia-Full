package pe.edu.pucp.comerzia.GestionDeAlmacen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;

public class ProductoAlmacenadoMapper
  implements EntityMapper<ProductoAlmacenado> {

  @Override
  public ProductoAlmacenado createEntity() {
    return new ProductoAlmacenado();
  }

  @Override
  public ProductoAlmacenado mapResultSetToEntity(ResultSet rs)
    throws SQLException {
    ProductoAlmacenado almacen = createEntity();

    almacen.setId(rs.getInt("id"));
    almacen.setIdAlmacen(rs.getInt("id_almacen"));
    almacen.setIdProducto(rs.getInt("id_producto"));
    almacen.setFechaAlmacenado(rs.getDate("fecha_almacenado"));
    almacen.setStockActual(rs.getInt("stock_actual"));

    return almacen;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(ProductoAlmacenado entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("id_almacen", entity.getIdAlmacen());
    columns.put("id_producto", entity.getIdProducto());
    columns.put("fecha_almacenado", entity.getFechaAlmacenado());
    columns.put("stock_actual", entity.getStockActual());

    return columns;
  }

  @Override
  public void setGeneratedId(ProductoAlmacenado entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(ProductoAlmacenado entity) {
    return entity.getId();
  }
}
