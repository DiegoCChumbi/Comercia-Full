package pe.edu.pucp.comerzia.GestionDeAlmacen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;

public class ProductoMapper implements EntityMapper<Producto> {

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
  public Map<String, Object> mapEntityToColumns(Producto entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("nombre", entity.getNombre());
    columns.put("precio", entity.getPrecio());
    columns.put("stock_minimo", entity.getStockMinimo());

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
