package pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.ProductoAlmacenado;

public class ProductoAlmacenadoMapper
  implements EntityMapper<ProductoAlmacenado> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<Integer> idAlmacen = Column.of(
      "id_almacen",
      Integer.class
    );
    public static final Column<Integer> idProducto = Column.of(
      "id_producto",
      Integer.class
    );
    public static final Column<Date> fechaAlmacenado = Column.of(
      "fecha_almacenado",
      Date.class
    );
    public static final Column<Integer> stockActual = Column.of(
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

    almacen.setId(rs.getInt(Columns.id.getName()));
    almacen.setIdAlmacen(rs.getInt(Columns.idAlmacen.getName()));
    almacen.setIdProducto(rs.getInt(Columns.idProducto.getName()));
    almacen.setFechaAlmacenado(rs.getDate(Columns.fechaAlmacenado.getName()));
    almacen.setStockActual(rs.getInt(Columns.stockActual.getName()));

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
