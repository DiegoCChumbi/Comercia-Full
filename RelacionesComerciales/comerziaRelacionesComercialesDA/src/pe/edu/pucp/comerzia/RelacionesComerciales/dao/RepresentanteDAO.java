/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Representante;

public interface RepresentanteDAO extends PersonaDAO {
  public Integer insertar(Representante representante);

  public Integer modificar(Representante representante);

  public Integer eliminar(Representante representante);

  public ArrayList<Representante> listarTodos();

  public Representante obtenerPorId(Integer idRepresentante);

  public Boolean existeRepresentante(Representante representante);

  public Boolean existeRepresentante(
    Representante representante,
    Boolean abreConexion
  );
}
