package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;

public class PersonaMapper<T extends Persona> implements EntityMapper<T> {

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

  protected void mapAdditionalFields(ResultSet rs, T persona)
    throws SQLException {
    // Default implementation does nothing; subclasses can override
  }

  @Override
  public Map<String, Object> mapEntityToColumns(T entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("dni", entity.getDni());
    columns.put("nombre", entity.getNombre());
    columns.put("telefono", entity.getTelefono());
    columns.put("correo", entity.getCorreo());
    columns.put("direccion", entity.getDireccion());
    columns.put("eliminado", entity.getEliminado());

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
