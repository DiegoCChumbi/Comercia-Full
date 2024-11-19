package pe.edu.pucp.comerzia.core.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

  private static final String CONFIG_FILE = "/jdbc.properties";

  private Connection connection;
  private String driver;
  private String dbType;
  private String dbName;
  private String host;
  private String port;
  private String username;
  private String password;
  private static DBManager dbManager = null;

  private DBManager() {
    try {
      loadProperties();
      Class.forName(driver);
    } catch (ClassNotFoundException ex) {
      System.out.println("Error registrando el driver: " + ex.getMessage());
    }
  }

  public static DBManager getInstance() {
    if (dbManager == null) {
      synchronized (DBManager.class) {
        if (dbManager == null) {
          dbManager = new DBManager();
        }
      }
    }
    return dbManager;
  }

  public Connection getConnection() {
    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(getURL(), username, password);
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(DBManager.class.getName()).log(
        Level.SEVERE,
        "Error establishing database connection",
        ex
      );
    }
    return connection;
  }

  private String getURL() {
    return (
      dbType +
      "://" +
      host +
      ":" +
      port +
      "/" +
      dbName +
      "?useSSL=false&allowPublicKeyRetrieval=true"
    );
  }

  private void loadProperties() {
    Properties properties = new Properties();
    try (InputStream input = getClass().getResourceAsStream(CONFIG_FILE)) {
      if (input == null) {
        throw new FileNotFoundException(
          "Property file not found: " + CONFIG_FILE
        );
      }
      properties.load(input);

      // Load each property
      driver = properties.getProperty("driver");
      dbType = properties.getProperty("db_type");
      dbName = properties.getProperty("database_name");
      host = properties.getProperty("host");
      port = properties.getProperty("port");
      username = properties.getProperty("username");
      password = properties.getProperty("password");
    } catch (IOException ex) {
      Logger.getLogger(DBManager.class.getName()).log(
        Level.SEVERE,
        "Error loading properties file",
        ex
      );
    }
  }

  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DBManager.class.getName()).log(
          Level.SEVERE,
          "Error closing database connection",
          ex
        );
      }
    }
  }
}
