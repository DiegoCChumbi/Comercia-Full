package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper.EmpresaMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Empresa;

public class EmpresaDAO<T extends Empresa> extends BaseDAO<T, Integer> {

  public EmpresaDAO(Class<T> entityClass, EntityMapper<T> entityMapper) {
    super(entityClass, entityMapper);
  }

  // Default
  @SuppressWarnings("unchecked")
  public EmpresaDAO() {
    super(
      (Class<T>) Empresa.class,
      (EntityMapper<T>) new EmpresaMapper<Empresa>()
    );
  }

  @Override
  protected String getTableName() {
    return "empresa";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
