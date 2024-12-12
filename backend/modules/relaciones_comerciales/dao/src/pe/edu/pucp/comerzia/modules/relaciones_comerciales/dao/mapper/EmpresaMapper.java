package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Empresa;

public class EmpresaMapper<T extends Empresa> implements EntityMapper<T> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<String> nombre = Column.of(
      "nombre",
      String.class
    );
    public static final Column<String> direccion = Column.of(
      "direccion",
      String.class
    );
    public static final Column<String> telefono = Column.of(
      "telefono",
      String.class
    );
    public static final Column<String> email = Column.of("email", String.class);
    public static final Column<String> tipoIndustria = Column.of(
      "tipo_industria",
      String.class
    );
  }

  @SuppressWarnings("unchecked")
  public T createEntity() {
    return (T) new Empresa();
  }

  @Override
  public T mapResultSetToEntity(ResultSet rs) throws SQLException {
    T empresa = createEntity();

    empresa.setId(rs.getInt(Columns.id.getName()));
    empresa.setNombre(rs.getString(Columns.nombre.getName()));
    empresa.setDireccion(rs.getString(Columns.direccion.getName()));
    empresa.setTelefono(rs.getString(Columns.telefono.getName()));
    empresa.setEmail(rs.getString(Columns.email.getName()));
    empresa.setTipoIndustria(rs.getString(Columns.tipoIndustria.getName()));

    return empresa;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(T entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.nombre, entity.getNombre());
    columns.put(Columns.direccion, entity.getDireccion());
    columns.put(Columns.telefono, entity.getTelefono());
    columns.put(Columns.email, entity.getEmail());
    columns.put(Columns.tipoIndustria, entity.getTipoIndustria());

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
