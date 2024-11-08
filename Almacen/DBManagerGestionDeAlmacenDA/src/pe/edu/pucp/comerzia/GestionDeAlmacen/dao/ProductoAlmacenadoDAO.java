package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import pe.edu.pucp.comerzia.GestionDeAlmacen.mapper.ProductoAlmacenadoMapper;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.db.BaseDAOImpl;
import pe.edu.pucp.comerzia.db.utils.Column;

public class ProductoAlmacenadoDAO
  extends BaseDAOImpl<ProductoAlmacenado, Integer> {

  public static final Column<Integer> id = new Column<>("id", Integer.class);
  public static final Column<Integer> idAlmacen = new Column<>(
    "id_almacen",
    Integer.class
  );
  public static final Column<Integer> idProducto = new Column<>(
    "id_producto",
    Integer.class
  );
  public static final Column<Integer> fechaAlmacenado = new Column<>(
    "fecha_almacenado",
    Integer.class
  );
  public static final Column<Integer> stockActual = new Column<>(
    "stock_actual",
    Integer.class
  );

  // Default
  public ProductoAlmacenadoDAO() {
    super(ProductoAlmacenado.class, new ProductoAlmacenadoMapper());
  }

  @Override
  protected String getTableName() {
    return "producto_almacenado";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
