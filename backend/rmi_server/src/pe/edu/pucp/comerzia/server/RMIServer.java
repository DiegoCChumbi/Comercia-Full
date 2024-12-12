package pe.edu.pucp.comerzia.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.bo.AlmacenRemoteBO;
import pe.edu.pucp.comerzia.bo.ProductoRemoteBO;
import pe.edu.pucp.comerzia.core.db.DBManager;
import pe.edu.pucp.comerzia.interfaces.AlmacenRemote;
import pe.edu.pucp.comerzia.interfaces.ProductoRemote;

public class RMIServer {

  private static final String CONFIG_FILE = "rmi.properties";

  private static String ip;
  private static Integer port;

  public static void main(String[] args) {
    loadProperties();

    try {
      System.setProperty("java.rmi.server.hostname", ip);

      LocateRegistry.createRegistry(port);

      ProductoRemote productoRemoteBO = new ProductoRemoteBO(port);
      AlmacenRemote almacenRemoteBO = new AlmacenRemoteBO(port);

      // TODO: use map?
      String nombreServicio = getServiceName("productoRemoteBO");
      Naming.rebind(nombreServicio, productoRemoteBO);

      nombreServicio = getServiceName("almacenRemoteBO");
      Naming.rebind(nombreServicio, almacenRemoteBO);

      System.out.println("Servidor RMI registrado correctamente...");
    } catch (RemoteException | MalformedURLException ex) {
      Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void loadProperties() {
    Properties properties = new Properties();
    try (InputStream input = RMIServer.class.getResourceAsStream(CONFIG_FILE)) {
      if (input == null) {
        throw new FileNotFoundException(
          "Property file not found: " + CONFIG_FILE
        );
      }
      properties.load(input);

      // Load each property
      ip = properties.getProperty("ip");
      port = Integer.valueOf(properties.getProperty("port"));
    } catch (IOException ex) {
      Logger.getLogger(DBManager.class.getName()).log(
        Level.SEVERE,
        "Error loading properties file",
        ex
      );
    }
  }

  public static String getServiceName(String serviceName) {
    return "//" + ip + ":" + port + "/" + serviceName;
  }
}
