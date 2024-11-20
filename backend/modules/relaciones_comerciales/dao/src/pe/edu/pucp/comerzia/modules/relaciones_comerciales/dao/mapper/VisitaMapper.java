package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Visita;

public class VisitaMapper implements EntityMapper<Visita> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<Date> fecha = Column.of("fecha", Date.class);
    public static final Column<Double> duracion = Column.of(
      "duracion",
      Double.class
    );
    public static final Column<Integer> idCliente = Column.of(
      "id_cliente",
      Integer.class
    );
    public static final Column<Integer> idVendedor = Column.of(
      "id_vendedor",
      Integer.class
    );
  }

  @Override
  public Visita createEntity() {
    return new Visita();
  }

  @Override
  public Visita mapResultSetToEntity(ResultSet rs) throws SQLException {
    Visita visita = createEntity();

    visita.setId(rs.getInt(Columns.id.getName()));
    visita.setFecha(rs.getDate(Columns.fecha.getName()));
    visita.setDuracion(rs.getDouble(Columns.duracion.getName()));
    visita.setIdCliente(rs.getInt(Columns.idCliente.getName()));
    visita.setIdVendedor(rs.getInt(Columns.idVendedor.getName()));

    return visita;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Visita entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.fecha, entity.getFecha());
    columns.put(Columns.duracion, entity.getDuracion());
    columns.put(Columns.idCliente, entity.getIdCliente());
    columns.put(Columns.idVendedor, entity.getIdVendedor());

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
