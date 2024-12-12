package pe.edu.pucp.comerzia.bo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.interfaces.AlmacenRemote;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.AlmacenBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;
import pe.edu.pucp.comerzia.utils.RemoteExceptionWrapper;

public class AlmacenRemoteBO
  extends UnicastRemoteObject
  implements AlmacenRemote {

  private final AlmacenBO almacenBO;

  public AlmacenRemoteBO(Integer puerto) throws RemoteException {
    super(puerto);
    this.almacenBO = new AlmacenBO();
  }

  @Override
  public ArrayList<Almacen> listarTodos() throws RemoteException {
    return RemoteExceptionWrapper.wrap(() -> this.almacenBO.listarTodos());
  }

  @Override
  public Integer insertarAlmacen(Almacen almacen) throws RemoteException {
    return RemoteExceptionWrapper.wrap(() -> this.almacenBO.insertar(almacen));
  }
}
