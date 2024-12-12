package pe.edu.pucp.comerzia.modules.gestion_almacen.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper.ProductoAlmacenadoMapper;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.ProductoAlmacenado;

public class ProductoAlmacenadoDAO
  extends BaseDAO<ProductoAlmacenado, Integer> {

  private static final ProductoAlmacenadoDAO instance =
    new ProductoAlmacenadoDAO();

  public static ProductoAlmacenadoDAO getProductoAlmacenadoInstance() {
    return instance;
  }

  // Default
  protected ProductoAlmacenadoDAO() {
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
