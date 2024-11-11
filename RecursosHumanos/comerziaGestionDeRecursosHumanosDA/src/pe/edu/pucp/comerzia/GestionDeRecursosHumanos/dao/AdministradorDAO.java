package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper.AdministradorMapper;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;

public class AdministradorDAO extends EmpleadoDAO<Administrador> {

  public AdministradorDAO() {
    super(Administrador.class, new AdministradorMapper());
  }
}
