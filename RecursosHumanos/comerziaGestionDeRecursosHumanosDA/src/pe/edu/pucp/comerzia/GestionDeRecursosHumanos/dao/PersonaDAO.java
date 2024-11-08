package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper.PersonaMapper;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.BaseDAOImpl;

public class PersonaDAO<T extends Persona> extends BaseDAOImpl<T, Integer> {

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
