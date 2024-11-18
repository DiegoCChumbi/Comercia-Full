package pe.edu.pucp.comerzia.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.modules.notificacion.bo.NotificacionBO;
import pe.edu.pucp.comerzia.modules.notificacion.model.Notificacion;

@WebService(
  serviceName = "NotificacionesWS",
  targetNamespace = "http://services.comerziasoft.pucp.edu.pe"
)
public class NotificacionesWS {

  private final NotificacionBO boNotificacion;

  public NotificacionesWS() {
    boNotificacion = new NotificacionBO();
  }

  @WebMethod(operationName = "listarNotificaciones")
  public ArrayList<Notificacion> listarNotificaciones() {
    try {
      return boNotificacion.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}
