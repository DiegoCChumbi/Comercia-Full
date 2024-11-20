package pe.edu.pucp.comerzia.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.core.model.BaseEntity;

public abstract class BaseEntityMapper<T extends BaseEntity>
  implements EntityMapper<T> {

  public static class Columns {

    public static final Column<Timestamp> createdAt = Column.of(
      "created_at",
      Timestamp.class
    );
    public static final Column<Timestamp> updatedAt = Column.of(
      "updated_at",
      Timestamp.class
    );
    public static final Column<Timestamp> deletedAt = Column.of(
      "deleted_at",
      Timestamp.class
    );
  }

  @Override
  public T mapResultSetToEntity(ResultSet rs) throws SQLException {
    T entity = createEntity();
    entity.setCreatedAt(rs.getTimestamp(Columns.createdAt.getName()));
    entity.setUpdatedAt(rs.getTimestamp(Columns.updatedAt.getName()));
    entity.setDeletedAt(rs.getTimestamp(Columns.deletedAt.getName()));
    return entity;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(T entity) {
    Map<Column<?>, Object> columns = new HashMap<>();
    columns.put(Columns.createdAt, entity.getCreatedAt());
    columns.put(Columns.updatedAt, entity.getUpdatedAt());
    columns.put(Columns.deletedAt, entity.getDeletedAt());
    return columns;
  }
}
