package pe.edu.pucp.comerzia.GestionDeAlmacen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.db.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.db.utils.Column;

public class ProductoMapper implements EntityMapper<Producto> {

  public static class Columns {

    public static final Column<Integer> id = new Column<>("id", Integer.class);
    public static final Column<String> nombre = new Column<>(
      "nombre",
      String.class
    );
    public static final Column<Double> precio = new Column<>(
      "precio",
      Double.class
    );
    public static final Column<Integer> stockMinimo = new Column<>(
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

    producto.setId(rs.getInt("id"));
    producto.setNombre(rs.getString("nombre"));
    producto.setPrecio(rs.getDouble("precio"));
    producto.setStockMinimo(rs.getInt("stock_minimo"));

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
