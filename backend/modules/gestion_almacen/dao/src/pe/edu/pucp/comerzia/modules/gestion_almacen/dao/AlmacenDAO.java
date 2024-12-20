package pe.edu.pucp.comerzia.modules.gestion_almacen.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper.AlmacenMapper;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;

public class AlmacenDAO extends BaseDAO<Almacen, Integer> {

  // Default
  public AlmacenDAO() {
    super(Almacen.class, new AlmacenMapper());
  }

  @Override
  protected String getTableName() {
    return "almacen";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
