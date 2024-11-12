package pe.edu.pucp.comerzia.modules.gestion_almacen.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper.ProductoMapper;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;

public class ProductoDAO extends BaseDAO<Producto, Integer> {

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

  // Default
  public ProductoDAO() {
    super(Producto.class, new ProductoMapper());
  }

  @Override
  protected String getTableName() {
    return "producto";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
