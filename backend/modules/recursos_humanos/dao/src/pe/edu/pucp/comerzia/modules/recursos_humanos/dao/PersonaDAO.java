package pe.edu.pucp.comerzia.modules.recursos_humanos.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.PersonaMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Persona;

public class PersonaDAO<T extends Persona> extends BaseDAO<T, Integer> {

  public PersonaDAO(Class<T> entityClass, EntityMapper<T> entityMapper) {
    super(entityClass, entityMapper);
  }

  // Default
  @SuppressWarnings("unchecked")
  public PersonaDAO() {
    super(
      (Class<T>) Persona.class,
      (EntityMapper<T>) new PersonaMapper<Persona>()
    );
  }

  @Override
  protected String getTableName() {
    return "persona";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
