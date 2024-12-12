package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper.EmpresaMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Empresa;

public class EmpresaDAO<T extends Empresa> extends BaseDAO<T, Integer> {

  private static final EmpresaDAO<Empresa> instance = new EmpresaDAO<>();

  public static EmpresaDAO<Empresa> getEmpresaInstance() {
    return instance;
  }

  protected EmpresaDAO(Class<T> entityClass, EntityMapper<T> entityMapper) {
    super(entityClass, entityMapper);
  }

  // Default
  @SuppressWarnings("unchecked")
  protected EmpresaDAO() {
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
