package pe.edu.pucp.comerzia.bo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.interfaces.ProductoRemote;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;
import pe.edu.pucp.comerzia.utils.RemoteExceptionWrapper;

public class ProductoRemoteBO
  extends UnicastRemoteObject
  implements ProductoRemote {

  private final ProductoBO productoBO;

  public ProductoRemoteBO(Integer puerto) throws RemoteException {
    super(puerto);
    this.productoBO = new ProductoBO();
  }

  @Override
  public ArrayList<Producto> listarTodos() throws RemoteException {
    return RemoteExceptionWrapper.wrap(() -> this.productoBO.listarTodos());
  }

  @Override
  public Integer insertar(Producto producto) throws RemoteException {
    return RemoteExceptionWrapper.wrap(() -> this.productoBO.insertar(producto)
    );
  }
}
