package pe.edu.pucp.comerzia.modules.gestion_almacen.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;

public class AlmacenMapper implements EntityMapper<Almacen> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<String> nombre = Column.of(
      "nombre",
      String.class
    );
    public static final Column<String> estado = Column.of(
      "estado",
      String.class
    );
    public static final Column<String> descripcion = Column.of(
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

    almacen.setId(rs.getInt(Columns.id.getName()));
    almacen.setNombre(rs.getString(Columns.nombre.getName()));
    almacen.setEstado(rs.getString(Columns.estado.getName()));
    almacen.setDescripcion(rs.getString(Columns.descripcion.getName()));

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
