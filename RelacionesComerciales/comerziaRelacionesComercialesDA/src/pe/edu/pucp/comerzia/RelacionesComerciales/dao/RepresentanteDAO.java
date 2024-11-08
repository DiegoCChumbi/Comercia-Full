package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Representante;
import pe.edu.pucp.comerzia.RelacionesComerciales.mapper.RepresentanteMapper;

public class RepresentanteDAO extends PersonaDAO<Representante> {

  public RepresentanteDAO() {
    super(Representante.class, new RepresentanteMapper());
  }
}
