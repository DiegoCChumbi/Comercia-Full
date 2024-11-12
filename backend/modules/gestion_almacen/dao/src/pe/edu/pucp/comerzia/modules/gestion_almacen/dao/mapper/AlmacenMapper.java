package pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;

public class AlmacenMapper implements EntityMapper<Almacen> {

  public static class Columns {

    public static final Column<Integer> id = new Column<>("id", Integer.class);
    public static final Column<String> nombre = new Column<>(
      "nombre",
      String.class
    );
    public static final Column<String> estado = new Column<>(
      "estado",
      String.class
    );
    public static final Column<String> descripcion = new Column<>(
      "descripcion",
      String.class
    );
  }

  @Override
  public Almacen createEntity() {
    return new Almacen();
  }

  @Override
  public Almacen mapResultSetToEntity(ResultSet rs) throws SQLException {
    Almacen almacen = createEntity();

    almacen.setId(rs.getInt("id"));
    almacen.setNombre(rs.getString("nombre"));
    almacen.setEstado(rs.getString("estado"));
    almacen.setDescripcion(rs.getString("descripcion"));

    return almacen;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Almacen entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.nombre, entity.getNombre());
    columns.put(Columns.estado, entity.getEstado());
    columns.put(Columns.descripcion, entity.getDescripcion());

    return columns;
  }

  @Override
  public void setGeneratedId(Almacen entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(Almacen entity) {
    return entity.getId();
  }
}
