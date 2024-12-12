package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper.RepresentanteMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Representante;

public class RepresentanteDAO extends PersonaDAO<Representante> {

  private static final RepresentanteDAO instance = new RepresentanteDAO();

  public static RepresentanteDAO getRepresentanteInstance() {
    return instance;
  }

  protected RepresentanteDAO() {
    super(Representante.class, new RepresentanteMapper());
  }
}
