package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper.RepresentanteMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Representante;

public class RepresentanteDAO extends PersonaDAO<Representante> {

  public RepresentanteDAO() {
    super(Representante.class, new RepresentanteMapper());
  }
}
