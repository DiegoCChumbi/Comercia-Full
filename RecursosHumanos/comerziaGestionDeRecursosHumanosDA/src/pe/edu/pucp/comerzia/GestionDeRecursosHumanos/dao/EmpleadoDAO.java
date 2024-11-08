package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper.EmpleadoMapper;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.db.utils.Column;

public class EmpleadoDAO<T extends Empleado> extends PersonaDAO<T> {

  public static class Columns {

    public static final Column<Integer> id = new Column<>("id", Integer.class);
  }

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
