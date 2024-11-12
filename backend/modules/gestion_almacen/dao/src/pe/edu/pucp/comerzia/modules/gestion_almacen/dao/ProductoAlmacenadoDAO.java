package pe.edu.pucp.comerzia.modules.gestion_almacen.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper.ProductoAlmacenadoMapper;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.ProductoAlmacenado;

public class ProductoAlmacenadoDAO
  extends BaseDAO<ProductoAlmacenado, Integer> {

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
