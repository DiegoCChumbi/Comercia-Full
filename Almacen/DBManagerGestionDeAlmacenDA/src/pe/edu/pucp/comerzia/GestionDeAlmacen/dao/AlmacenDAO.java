package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import pe.edu.pucp.comerzia.GestionDeAlmacen.mapper.AlmacenMapper;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.db.BaseDAO;

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
