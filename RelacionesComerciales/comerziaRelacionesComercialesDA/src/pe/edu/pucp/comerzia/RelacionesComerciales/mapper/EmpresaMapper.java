package pe.edu.pucp.comerzia.RelacionesComerciales.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;

public class EmpresaMapper<T extends Empresa> implements EntityMapper<T> {

  @SuppressWarnings("unchecked")
  public T createEntity() {
    return (T) new Empresa();
  }

  @Override
  public T mapResultSetToEntity(ResultSet rs) throws SQLException {
    T empresa = createEntity();

    empresa.setId(rs.getInt("id"));
    empresa.setNombre(rs.getString("nombre"));
    empresa.setDireccion(rs.getString("direccion"));
    empresa.setTelefono(rs.getString("telefono"));
    empresa.setEmail(rs.getString("correo"));
    empresa.setTipoIndustria(rs.getString("tipoIndustria"));

    return empresa;
  }

  protected void mapAdditionalFields(ResultSet rs, T persona)
    throws SQLException {
    // Default implementation does nothing; subclasses can override
  }

  @Override
  public Map<String, Object> mapEntityToColumns(T entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("nombre", entity.getNombre());
    columns.put("direccion", entity.getDireccion());
    columns.put("telefono", entity.getTelefono());
    columns.put("correo", entity.getEmail());
    columns.put("tipo_industria", entity.getTipoIndustria());

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
