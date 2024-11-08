package pe.edu.pucp.comerzia.RelacionesComerciales.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;

public class VisitaMapper implements EntityMapper<Visita> {

  @Override
  public Visita createEntity() {
    return new Visita();
  }

  @Override
  public Visita mapResultSetToEntity(ResultSet rs) throws SQLException {
    Visita visita = createEntity();

    visita.setId(rs.getInt("id"));
    visita.setFecha(rs.getDate("fecha"));
    visita.setDuracion(rs.getDouble("duracion"));
    visita.setIdCliente(rs.getInt("id_cliente"));
    visita.setIdVendedor(rs.getInt("id_vendedor"));

    return visita;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(Visita entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("fecha", entity.getFecha());
    columns.put("duracion", entity.getDuracion());
    columns.put("id_cliente", entity.getIdCliente());
    columns.put("id_vendedor", entity.getIdVendedor());

    return columns;
  }

  @Override
  public void setGeneratedId(Visita entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(Visita entity) {
    return entity.getId();
  }
}
