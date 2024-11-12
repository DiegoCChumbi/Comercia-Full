package pe.edu.pucp.comerzia.modules.gestion_comercial.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper.LineaDocumentoMapper;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.LineaDocumento;

public class LineaDocumentoDAO extends BaseDAO<LineaDocumento, Integer> {

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

  // Default
  public LineaDocumentoDAO() {
    super(LineaDocumento.class, new LineaDocumentoMapper());
  }

  @Override
  protected String getTableName() {
    return "linea_documento";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
