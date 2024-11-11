package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.db.utils.Column;

public class PersonaMapper<T extends Persona> implements EntityMapper<T> {

  public static class Columns {

    public static final Column<Integer> id = new Column<>("id", Integer.class);
    public static final Column<String> dni = new Column<>("dni", String.class);
    public static final Column<String> nombre = new Column<>(
      "nombre",
      String.class
    );
    public static final Column<String> telefono = new Column<>(
      "telefono",
      String.class
    );
    public static final Column<String> correo = new Column<>(
      "correo",
      String.class
    );
    public static final Column<String> direccion = new Column<>(
      "direccion",
      String.class
    );
    public static final Column<Boolean> eliminado = new Column<>(
      "eliminado",
      Boolean.class
    );
  }

  @SuppressWarnings("unchecked")
  public T createEntity() {
    return (T) new Persona();
  }

  @Override
  public T mapResultSetToEntity(ResultSet rs) throws SQLException {
    T persona = createEntity();

    persona.setId(rs.getInt("id"));
    persona.setDni(rs.getString("dni"));
    persona.setNombre(rs.getString("nombre"));
    persona.setTelefono(rs.getString("telefono"));
    persona.setCorreo(rs.getString("correo"));
    persona.setDireccion(rs.getString("direccion"));
    persona.setEliminado(rs.getBoolean("eliminado"));

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
    columns.put(Columns.eliminado, entity.getEliminado());

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
