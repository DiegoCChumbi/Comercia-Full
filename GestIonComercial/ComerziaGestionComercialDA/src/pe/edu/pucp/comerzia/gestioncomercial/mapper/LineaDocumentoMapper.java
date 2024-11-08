package pe.edu.pucp.comerzia.gestioncomercial.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;

public class LineaDocumentoMapper implements EntityMapper<LineaDocumento> {

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
  public Map<String, Object> mapEntityToColumns(LineaDocumento entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("id_documento", entity.getIdDocumento());
    columns.put("id_producto", entity.getIdProducto());
    columns.put("cantidad", entity.getCantidad());
    columns.put("precio_unitario", entity.getPrecioUnitario());

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
