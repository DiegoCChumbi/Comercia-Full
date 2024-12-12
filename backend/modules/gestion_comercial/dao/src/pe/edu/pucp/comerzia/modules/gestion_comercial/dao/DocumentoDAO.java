package pe.edu.pucp.comerzia.modules.gestion_comercial.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper.DocumentoMapper;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.Documento;

public class DocumentoDAO extends BaseDAO<Documento, Integer> {

  private static final DocumentoDAO instance = new DocumentoDAO();

  public static DocumentoDAO getDocumentoInstance() {
    return instance;
  }

  // Default
  protected DocumentoDAO() {
    super(Documento.class, new DocumentoMapper());
  }

  @Override
  protected String getTableName() {
    return "documento";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
