package pe.edu.pucp.comerzia.gestioncomercial.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;
import pe.edu.pucp.comerzia.db.utils.Column;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;

public class LineaDocumentoMapper implements EntityMapper<LineaDocumento> {

  public static class Columns {

    public static final Column<Integer> id = new Column<>("id", Integer.class);
    public static final Column<Integer> idDocumento = new Column<>(
      "id_documento",
      Integer.class
    );
    public static final Column<Integer> idProducto = new Column<>(
      "id_producto",
      Integer.class
    );
    public static final Column<Integer> cantidad = new Column<>(
      "cantidad",
      Integer.class
    );
    public static final Column<Double> precioUnitario = new Column<>(
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

    lineaDocumento.setId(rs.getInt("id"));
    lineaDocumento.setIdDocumento(rs.getInt("id_documento"));
    lineaDocumento.setIdProducto(rs.getInt("id_producto"));
    lineaDocumento.setCantidad(rs.getInt("cantidad"));
    lineaDocumento.setPrecioUnitario(rs.getDouble("precio_unitario"));

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
