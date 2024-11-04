/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;

/**
 *
 * @author chumbi
 */
public interface EmpleadoDAO {
    public Integer insertar(Empleado empleado);
    
    public Integer insertar(Empleado empleado,Boolean usarTransaccion, Connection conexion);
    
    public Integer modificar(Empleado empleado);
    
    public Integer modificar(Empleado empleado,Boolean usarTransaccion, Connection conexion);
    
    public Integer eliminar(Empleado empleado);
    
    public Integer eliminar(Empleado empleado,Boolean usarTransaccion, Connection conexion);
    
    public ArrayList<Empleado> listarTodos();
    
    public Empleado obtenerPorId(Integer idEmpleado);
    
    public Boolean existeEmpleado(Empleado empleado);
    
    public Boolean existeEmpleado(Empleado empleado, Boolean abreConexion);
    
    public Integer verificarEmpleado(String cuenta, String contrasenha, Boolean abreConexion);
     
    public Integer verificarEmpleado(String cuenta, String contrasenha);
    
    public String devolverNombreEmpleado(Integer idEmpleado,Boolean abreConexion);
    
    public String devolverNombreEmpleado(Integer idEmpleado);
}
