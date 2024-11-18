package pe.edu.pucp.comerzia.modules.gestion_almacen.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper.ProductoMapper;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;

public class ProductoDAO extends BaseDAO<Producto, Integer> {

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
