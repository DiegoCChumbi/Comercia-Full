package pe.edu.pucp.comerzia.modules.recursos_humanos.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.AdministradorMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Administrador;

public class AdministradorDAO extends EmpleadoDAO<Administrador> {

  private static final AdministradorDAO instance = new AdministradorDAO();

  public static AdministradorDAO getAdministradorInstance() {
    return instance;
  }

  protected AdministradorDAO() {
    super(Administrador.class, new AdministradorMapper());
  }
}
