package pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Persona;

public class PersonaMapper<T extends Persona> implements EntityMapper<T> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<String> dni = Column.of("dni", String.class);
    public static final Column<String> nombre = Column.of(
      "nombre",
      String.class
    );
    public static final Column<String> telefono = Column.of(
      "telefono",
      String.class
    );
    public static final Column<String> correo = Column.of(
      "correo",
      String.class
    );
    public static final Column<String> direccion = Column.of(
      "direccion",
      String.class
    );
  }

  @SuppressWarnings("unchecked")
  public T createEntity() {
    return (T) new Persona();
  }

  @Override
  public T mapResultSetToEntity(ResultSet rs) throws SQLException {
    T persona = createEntity();

    persona.setId(rs.getInt(Columns.id.getName()));
    persona.setDni(rs.getString(Columns.dni.getName()));
    persona.setNombre(rs.getString(Columns.nombre.getName()));
    persona.setTelefono(rs.getString(Columns.telefono.getName()));
    persona.setCorreo(rs.getString(Columns.correo.getName()));
    persona.setDireccion(rs.getString(Columns.direccion.getName()));

    return persona;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(T entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.dni, entity.getDni());
    columns.put(Columns.nombre, entity.getNombre());
    columns.put(Columns.telefono, entity.getTelefono());
    columns.put(Columns.correo, entity.getCorreo());
    columns.put(Columns.direccion, entity.getDireccion());

    return columns;
  }

  @Override
  public void setGeneratedId(T entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(T entity) {
    return entity.getId();
  }
}
