package pe.edu.pucp.comerzia.modules.notificacion.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.modules.notificacion.dao.NotificacionDAO;
import pe.edu.pucp.comerzia.modules.notificacion.model.Notificacion;

public class NotificacionBO {

  private NotificacionDAO notificacionDAO;

  public NotificacionBO() {
    this.notificacionDAO = NotificacionDAO.getNotificacionInstance();
  }

  public ArrayList<Notificacion> listarTodos() throws SQLException {
    return new ArrayList<>(notificacionDAO.findAll());
  }
}
