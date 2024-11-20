package pe.edu.pucp.comerzia.modules.notificacion.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.notificacion.model.Notificacion;

public class NotificacionMapper implements EntityMapper<Notificacion> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<Integer> idProducto = Column.of(
      "id_producto",
      Integer.class
    );
    public static final Column<Integer> idAlmacen = Column.of(
      "id_almacen",
      Integer.class
    );
    public static final Column<String> mensaje = Column.of(
      "mensaje",
      String.class
    );
  }

  @Override
  public Notificacion createEntity() {
    return new Notificacion();
  }

  @Override
  public Notificacion mapResultSetToEntity(ResultSet rs) throws SQLException {
    Notificacion notificacion = createEntity();

    notificacion.setId(rs.getInt(Columns.id.getName()));
    notificacion.setIdProducto(rs.getInt(Columns.idProducto.getName()));
    notificacion.setIdAlmacen(rs.getInt(Columns.idAlmacen.getName()));
    notificacion.setMensaje(rs.getString(Columns.mensaje.getName()));

    return notificacion;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Notificacion entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.idProducto, entity.getIdProducto());
    columns.put(Columns.idAlmacen, entity.getIdAlmacen());
    columns.put(Columns.mensaje, entity.getMensaje());

    return columns;
  }

  @Override
  public void setGeneratedId(Notificacion entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(Notificacion entity) {
    return entity.getId();
  }
}
