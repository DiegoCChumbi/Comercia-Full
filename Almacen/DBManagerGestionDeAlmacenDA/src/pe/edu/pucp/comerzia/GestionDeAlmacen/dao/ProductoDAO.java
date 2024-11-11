package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import pe.edu.pucp.comerzia.GestionDeAlmacen.mapper.ProductoMapper;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.db.BaseDAO;
import pe.edu.pucp.comerzia.db.utils.Column;

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
