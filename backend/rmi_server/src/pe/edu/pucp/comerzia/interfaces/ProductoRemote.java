package pe.edu.pucp.comerzia.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;

public interface ProductoRemote extends Remote {
  public ArrayList<Producto> listarTodos() throws RemoteException;

  public Integer insertar(Producto producto) throws RemoteException;
}
