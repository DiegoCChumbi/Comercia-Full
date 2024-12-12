package pe.edu.pucp.comerzia.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;

public interface AlmacenRemote extends Remote {
  public ArrayList<Almacen> listarTodos() throws RemoteException;

  public Integer insertarAlmacen(Almacen almacen) throws RemoteException;
}
