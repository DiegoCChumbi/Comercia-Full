package pe.edu.pucp.comerzia.modules.gestion_comercial.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper.LineaDocumentoMapper;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.LineaDocumento;

public class LineaDocumentoDAO extends BaseDAO<LineaDocumento, Integer> {

  // Default
  public LineaDocumentoDAO() {
    super(LineaDocumento.class, new LineaDocumentoMapper());
  }

  @Override
  protected String getTableName() {
    return "linea_documento";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
