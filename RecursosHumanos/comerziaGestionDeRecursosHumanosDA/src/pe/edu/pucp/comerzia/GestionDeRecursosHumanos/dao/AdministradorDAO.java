/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

/**
 *
 * @author chumbi
 */
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;

public interface AdministradorDAO<T extends Administrador>
  extends EmpleadoDAO<Administrador> {
  public Boolean existeAdministrador(Administrador administrador);

  public Boolean existeAdministrador(
    Administrador administrador,
    Boolean abreConexion
  );
}
