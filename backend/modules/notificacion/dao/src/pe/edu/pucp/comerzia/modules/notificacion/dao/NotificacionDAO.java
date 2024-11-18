package pe.edu.pucp.comerzia.modules.notificacion.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.notificacion.dao.mapper.NotificacionMapper;
import pe.edu.pucp.comerzia.modules.notificacion.model.Notificacion;

public class NotificacionDAO extends BaseDAO<Notificacion, Integer> {

  // Default
  public NotificacionDAO() {
    super(Notificacion.class, new NotificacionMapper());
  }

  @Override
  protected String getTableName() {
    return "notificacion";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
