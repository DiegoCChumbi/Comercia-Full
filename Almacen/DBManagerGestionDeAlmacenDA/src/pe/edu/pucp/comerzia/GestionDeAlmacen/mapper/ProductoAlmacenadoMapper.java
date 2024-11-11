package pe.edu.pucp.comerzia.GestionDeAlmacen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;
import pe.edu.pucp.comerzia.db.utils.Column;

public class ProductoAlmacenadoMapper
  implements EntityMapper<ProductoAlmacenado> {

  public static class Columns {

    public static final Column<Integer> id = new Column<>("id", Integer.class);
    public static final Column<Integer> idAlmacen = new Column<>(
      "id_almacen",
      Integer.class
    );
    public static final Column<Integer> idProducto = new Column<>(
      "id_producto",
      Integer.class
    );
    public static final Column<Date> fechaAlmacenado = new Column<>(
      "fecha_almacenado",
      Date.class
    );
    public static final Column<Integer> stockActual = new Column<>(
      "stock_actual",
      Integer.class
    );
  }

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
  public Map<Column<?>, Object> mapEntityToColumns(ProductoAlmacenado entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.idAlmacen, entity.getIdAlmacen());
    columns.put(Columns.idProducto, entity.getIdProducto());
    columns.put(Columns.fechaAlmacenado, entity.getFechaAlmacenado());
    columns.put(Columns.stockActual, entity.getStockActual());

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
