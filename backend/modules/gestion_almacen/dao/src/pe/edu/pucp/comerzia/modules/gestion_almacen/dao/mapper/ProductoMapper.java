package pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;

public class ProductoMapper implements EntityMapper<Producto> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<String> nombre = Column.of(
      "nombre",
      String.class
    );
    public static final Column<Double> precio = Column.of(
      "precio",
      Double.class
    );
    public static final Column<Integer> stockMinimo = Column.of(
      "stock_minimo",
      Integer.class
    );
  }

  @Override
  public Producto createEntity() {
    return new Producto();
  }

  @Override
  public Producto mapResultSetToEntity(ResultSet rs) throws SQLException {
    Producto producto = createEntity();

    producto.setId(rs.getInt(Columns.id.getName()));
    producto.setNombre(rs.getString(Columns.nombre.getName()));
    producto.setPrecio(rs.getDouble(Columns.precio.getName()));
    producto.setStockMinimo(rs.getInt(Columns.stockMinimo.getName()));

    return producto;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Producto entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.nombre, entity.getNombre());
    columns.put(Columns.precio, entity.getPrecio());
    columns.put(Columns.stockMinimo, entity.getStockMinimo());

    return columns;
  }

  @Override
  public void setGeneratedId(Producto entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(Producto entity) {
    return entity.getId();
  }
}
