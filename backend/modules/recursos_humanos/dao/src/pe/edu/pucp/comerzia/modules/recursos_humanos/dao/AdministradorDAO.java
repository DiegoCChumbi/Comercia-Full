package pe.edu.pucp.comerzia.modules.recursos_humanos.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.AdministradorMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Administrador;

public class AdministradorDAO extends EmpleadoDAO<Administrador> {

  public AdministradorDAO() {
    super(Administrador.class, new AdministradorMapper());
  }
}
