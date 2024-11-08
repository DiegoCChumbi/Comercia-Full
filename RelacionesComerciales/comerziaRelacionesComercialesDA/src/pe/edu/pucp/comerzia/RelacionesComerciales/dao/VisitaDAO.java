package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;
import pe.edu.pucp.comerzia.RelacionesComerciales.mapper.VisitaMapper;
import pe.edu.pucp.comerzia.db.BaseDAOImpl;

public class VisitaDAO extends BaseDAOImpl<Visita, Integer> {

  // Default
  public VisitaDAO() {
    super(Visita.class, new VisitaMapper());
  }

  @Override
  protected String getTableName() {
    return "visita";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
