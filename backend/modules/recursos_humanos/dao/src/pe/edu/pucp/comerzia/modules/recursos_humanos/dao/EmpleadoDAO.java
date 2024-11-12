package pe.edu.pucp.comerzia.modules.recursos_humanos.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.EmpleadoMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Empleado;

public class EmpleadoDAO<T extends Empleado> extends PersonaDAO<T> {

  public EmpleadoDAO(Class<T> entityClass, EntityMapper<T> entityMapper) {
    super(entityClass, entityMapper);
  }

  // Default
  @SuppressWarnings("unchecked")
  public EmpleadoDAO() {
    super(
      (Class<T>) Empleado.class,
      (EntityMapper<T>) new EmpleadoMapper<Empleado>()
    );
  }
}
