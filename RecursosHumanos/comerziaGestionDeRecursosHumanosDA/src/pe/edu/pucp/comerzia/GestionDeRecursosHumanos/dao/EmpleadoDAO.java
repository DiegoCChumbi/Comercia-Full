/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

/**
 *
 * @author chumbi
 */
public interface EmpleadoDAO<T extends Empleado> extends PersonaDAO<T> {
  public Boolean existeEmpleado(T empleado);

  public Boolean existeEmpleado(T empleado, Boolean abreConexion);

  public Integer verificarEmpleado(
    String cuenta,
    String contrasenha,
    Boolean abreConexion
  );

  public Integer verificarEmpleado(String cuenta, String contrasenha);

  public String devolverNombreEmpleado(
    Integer idEmpleado,
    Boolean abreConexion
  );

  public String devolverNombreEmpleado(Integer idEmpleado);
}
