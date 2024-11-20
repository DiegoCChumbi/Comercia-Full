package pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.LineaDocumento;

public class LineaDocumentoMapper implements EntityMapper<LineaDocumento> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<Integer> idDocumento = Column.of(
      "id_documento",
      Integer.class
    );
    public static final Column<Integer> idProducto = Column.of(
      "id_producto",
      Integer.class
    );
    public static final Column<Integer> cantidad = Column.of(
      "cantidad",
      Integer.class
    );
    public static final Column<Double> precioUnitario = Column.of(
      "precio_unitario",
      Double.class
    );
  }

  @Override
  public LineaDocumento createEntity() {
    return new LineaDocumento();
  }

  @Override
  public LineaDocumento mapResultSetToEntity(ResultSet rs) throws SQLException {
    LineaDocumento lineaDocumento = createEntity();

    lineaDocumento.setId(rs.getInt(Columns.id.getName()));
    lineaDocumento.setIdDocumento(rs.getInt(Columns.idDocumento.getName()));
    lineaDocumento.setIdProducto(rs.getInt(Columns.idProducto.getName()));
    lineaDocumento.setCantidad(rs.getInt(Columns.cantidad.getName()));
    lineaDocumento.setPrecioUnitario(
      rs.getDouble(Columns.precioUnitario.getName())
    );

    return lineaDocumento;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(LineaDocumento entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.idDocumento, entity.getIdDocumento());
    columns.put(Columns.idProducto, entity.getIdProducto());
    columns.put(Columns.cantidad, entity.getCantidad());
    columns.put(Columns.precioUnitario, entity.getPrecioUnitario());

    return columns;
  }

  @Override
  public void setGeneratedId(LineaDocumento entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(LineaDocumento entity) {
    return entity.getId();
  }
}
