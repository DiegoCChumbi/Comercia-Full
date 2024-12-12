package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper.VisitaMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Visita;

public class VisitaDAO extends BaseDAO<Visita, Integer> {

  private static final VisitaDAO instance = new VisitaDAO();

  public static VisitaDAO getVisitaInstance() {
    return instance;
  }

  // Default
  protected VisitaDAO() {
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
