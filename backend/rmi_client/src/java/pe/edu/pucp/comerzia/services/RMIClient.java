package pe.edu.pucp.comerzia.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.interfaces.AlmacenRemote;
import pe.edu.pucp.comerzia.interfaces.ProductoRemote;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.server.RMIServer;

@WebService(serviceName = "RMIClient")
public class RMIClient {

  private ProductoRemote productoRemoteBO;
  private AlmacenRemote almacenRemoteBO;

  public RMIClient() {
    RMIServer.loadProperties();
    try {
      String nombreServicio = RMIServer.getServiceName(
        // "ingredienteBO"
        "productoRemoteBO"
      );
      this.productoRemoteBO = (ProductoRemote) Naming.lookup(nombreServicio);

      nombreServicio = RMIServer.getServiceName("almacenRemoteBO");
      this.almacenRemoteBO = (AlmacenRemote) Naming.lookup(nombreServicio);
    } catch (NotBoundException | MalformedURLException | RemoteException ex) {
      Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @WebMethod(operationName = "hello")
  public String hello(@WebParam(name = "name") String txt) {
    return "Hello " + txt + " !";
  }

  ////////////////////////////////////////////////////////////////////////////
  // PRODUCTO
  ////////////////////////////////////////////////////////////////////////////

  @WebMethod(operationName = "producto_insertar")
  public Integer producto_insertar(
    @WebParam(name = "producto") Producto producto
  ) {
    Integer retorno = null;
    try {
      retorno = this.productoRemoteBO.insertar(producto);
    } catch (RemoteException ex) {
      Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
  }

  ////////////////////////////////////////////////////////////////////////////
  // ALMACEN
  ////////////////////////////////////////////////////////////////////////////

  @WebMethod(operationName = "almacen_listarTodos")
  public ArrayList<Almacen> almacen_listarTodos() {
    ArrayList<Almacen> retorno = null;
    try {
      retorno = this.almacenRemoteBO.listarTodos();
    } catch (RemoteException ex) {
      Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
  }
}
