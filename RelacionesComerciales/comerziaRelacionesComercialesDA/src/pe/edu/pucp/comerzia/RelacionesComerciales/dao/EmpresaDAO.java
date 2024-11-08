package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.mapper.EmpresaMapper;
import pe.edu.pucp.comerzia.db.BaseDAOImpl;

public class EmpresaDAO<T extends Empresa> extends BaseDAOImpl<T, Integer> {

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
