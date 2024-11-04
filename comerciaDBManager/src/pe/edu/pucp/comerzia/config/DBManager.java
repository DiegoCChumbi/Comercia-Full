package pe.edu.pucp.comerzia.config;
//
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//import java.util.Map;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.sql.CallableStatement;
//import java.sql.Types;
//import java.net.URL;
//import java.net.URLDecoder;
//
//public final class DBManager {
//    
//    private static DBManager dbManager;
//    private String url;
//    private String usuario;
//    private String password;
//    private Connection con;
//    private ResultSet rs;
//    private final String nombreArchivo = "datosConexion.txt";
//    
//    private DBManager(){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            leerArchivoYCrearCadena();
//         
//        }catch(ClassNotFoundException ex){
//            System.out.println("Error registrando el driver: " + ex.getMessage());
//        }
//    }
//    
//    public static DBManager getInstance(){
//        if(dbManager == null)
//            createInstance();
//        return dbManager;
//    }
//    
//    private static void createInstance(){
//        dbManager = new DBManager();
//    }
//    
//    public Connection getConnection(){
//        try{
//            con = DriverManager.getConnection(url, usuario, password);
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }
//        return con;
//    }
//    
//    public void leerArchivoYCrearCadena() {
//        Map<String, String> config = new HashMap<>();
//        String rutaArchivo = "";
//        try{
//            URL resourceUrl = DBManager.class.getResource("/pe/edu/pucp/eventmastersoft/config/");
//            String decodedPath = URLDecoder.decode(resourceUrl.getPath(), "UTF-8");
//            rutaArchivo = decodedPath + nombreArchivo;
//        }catch(UnsupportedEncodingException ex){
//            System.out.println(ex.getMessage());
//        }
//        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                String[] partes = linea.split("=");
//                if (partes.length == 2) {
//                    config.put(partes[0].trim(), partes[1].trim());
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error leyendo archivo de conexion: " + e.getMessage());
//        }
//
//        usuario = config.get("user");
//        password = config.get("password");
//        url = "jdbc:mysql://" + config.get("hostname") + ":" + config.get("port") + "/" + config.get("database") + "?useSSL=false&allowPublicKeyRetrieval=true";
//        System.out.println("Conectando a la base de datos con los siguientes parámetros:");
//        System.out.println("URL: " + url);
//        System.out.println("Usuario: " + usuario);
//
//    }
//    
//    public void cerrarConexion() {
//        if(rs != null){
//            try{
//                rs.close();
//            }catch(SQLException ex){
//                System.out.println("Error al cerrar el lector:" + ex.getMessage());
//            }
//        }
//        if (con != null) {
//            try {
//                con.close();  
//            } catch (SQLException ex) {
//                System.out.println("Error al cerrar la conexión:" + ex.getMessage());
//            }
//        }
//    }
//    
//    public int ejecutarProcedimiento(String nombreProcedimiento, Map<String, Object> parametrosEntrada, Map<String, Object> parametrosSalida) {
//        int resultado = 0;
//        try{
//            CallableStatement cst = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, parametrosSalida);
//            if(parametrosEntrada != null)
//                registrarParametrosEntrada(cst, parametrosEntrada);
//            if(parametrosSalida != null)
//                registrarParametrosSalida(cst, parametrosSalida);
//        
//            resultado = cst.executeUpdate();
//        
//            if(parametrosSalida != null)
//                obtenerValoresSalida(cst, parametrosSalida);
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }finally{
//            cerrarConexion();
//        }
//        return resultado;
//    }
//    
//    private void registrarParametrosEntrada(CallableStatement cs, Map<String, Object> parametros) throws SQLException {
//        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            switch (value) {
//                case Integer entero -> cs.setInt(key, entero);
//                case String cadena -> cs.setString(key, cadena);
//                case Double decimal -> cs.setDouble(key, decimal);
//                case Boolean booleano -> cs.setBoolean(key, booleano);
//                case java.util.Date fecha -> cs.setDate(key, new java.sql.Date(fecha.getTime()));
//                case byte[] bytes -> cs.setBytes(key, bytes);
//                default -> {
//			cs.setString(key, value.toString());
//                }
//                // Agregar más tipos según sea necesario
//            }
//        }
//    }
//    
//    private void registrarParametrosSalida(CallableStatement cst, Map<String, Object> params) throws SQLException {
//        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            String nombre = entry.getKey();
//            int sqlType = (int) entry.getValue();
//            cst.registerOutParameter(nombre, sqlType);
//        }
//    }
//    
//    public CallableStatement formarLlamadaProcedimiento(String nombreProcedimiento, Map<String, Object> parametrosEntrada, Map<String, Object> parametrosSalida) throws SQLException{
//        con = getConnection();
//        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
//        int cantParametrosEntrada = 0;
//        int cantParametrosSalida = 0;
//        if(parametrosEntrada!=null) cantParametrosEntrada = parametrosEntrada.size();
//        if(parametrosSalida!=null) cantParametrosSalida = parametrosSalida.size();
//        int numParams =  cantParametrosEntrada + cantParametrosSalida;
//        for (int i = 0; i < numParams; i++) {
//            call.append("?");
//            if (i < numParams - 1) {
//                call.append(",");
//            }
//        }
//        call.append(")}");
//        return con.prepareCall(call.toString());
//    }
//    
//    public ResultSet ejecutarProcedimientoLectura(String nombreProcedimiento, Map<String, Object> parametrosEntrada){
//        try{
//            CallableStatement cs = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, null);
//            if(parametrosEntrada!=null) 
//                registrarParametrosEntrada(cs,parametrosEntrada);
//            rs = cs.executeQuery();
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }
//        return rs;
//    }
//    
//    private void obtenerValoresSalida(CallableStatement cst, Map<String, Object> parametrosSalida) throws SQLException {
//        for (Map.Entry<String, Object> entry : parametrosSalida.entrySet()) {
//            String nombre = entry.getKey();
//            int sqlType = (int) entry.getValue();
//            Object value = null;
//            switch (sqlType) {
//                case Types.INTEGER -> value = cst.getInt(nombre);
//                case Types.VARCHAR -> value = cst.getString(nombre);
//                case Types.DOUBLE -> value = cst.getDouble(nombre);
//                case Types.BOOLEAN -> value = cst.getBoolean(nombre);
//                case Types.DATE -> value = cst.getDate(nombre);
//                // Agregar más tipos según sea necesario
//            }
//            parametrosSalida.put(nombre, value);
//        }
//    }
//}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static final String ARCHIVO_CONFIGURACION = "jdbc.properties";
    
    private Connection conexion;
    private String driver;
    private String tipo_de_driver;
    private String base_de_datos;
    private String nombre_de_host;
    private String puerto;
    private String usuario;
    private String contraseña;
    private static DBManager dbManager = null;
    
    private DBManager(){}; //constructor privado para que no se pueda instanciar
    
    public static DBManager getInstance(){
        if (DBManager.dbManager == null)
            createInstance();
        return DBManager.dbManager;
    }
    
    private static void createInstance(){
        if (DBManager.dbManager == null)
            DBManager.dbManager = new DBManager();
    }
    
    public Connection getConnection(){
        leer_archivo_propiedades();
        try {
            Class.forName(this.driver);
            this.conexion = DriverManager.getConnection(getURL(), this.usuario, this.contraseña);
        } catch (ClassNotFoundException | SQLException ex) {        
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conexion;
    }
    
    private String getURL(){
        String url = this.tipo_de_driver.concat("://");
        url = url.concat(this.nombre_de_host);
        url = url.concat(":");
        url = url.concat(this.puerto);
        url = url.concat("/");
        url = url.concat(this.base_de_datos);
        return url;
    }
    
    private void leer_archivo_propiedades(){
        Properties properties = new Properties();
        String nmArchivoConf = "resources/"+ARCHIVO_CONFIGURACION;
        try {
            properties.load(new FileInputStream(new File(nmArchivoConf)));
            this.driver = properties.getProperty("driver");
            this.tipo_de_driver = properties.getProperty("tipo_de_driver");
            this.base_de_datos = properties.getProperty("base_de_datos");
            this.nombre_de_host = properties.getProperty("nombre_de_host");
            this.puerto = properties.getProperty("puerto");
            this.usuario = properties.getProperty("usuario");
            this.contraseña = properties.getProperty("contrasenha");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
}