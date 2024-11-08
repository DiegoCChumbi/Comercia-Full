package pe.edu.pucp.comerzia.GestionDeAlmacen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;

public class AlmacenMapper implements EntityMapper<Almacen> {

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
  public Map<String, Object> mapEntityToColumns(Almacen entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("nombre", entity.getNombre());
    columns.put("estado", entity.getEstado());
    columns.put("descripcion", entity.getDescripcion());

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
