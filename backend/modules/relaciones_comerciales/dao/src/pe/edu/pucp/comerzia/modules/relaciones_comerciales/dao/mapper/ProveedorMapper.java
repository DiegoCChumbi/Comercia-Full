package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Proveedor;

public class ProveedorMapper extends EmpresaMapper<Proveedor> {

  public static class Columns extends EmpresaMapper.Columns {

    public static final Column<Date> fecha_afiliacion = Column.of(
      "fecha_afiliacion",
      Date.class
    );
    public static final Column<String> ruc = Column.of("RUC", String.class);
    public static final Column<String> razonSocial = Column.of(
      "razon_social",
      String.class
    );
    public static final Column<Double> calificacion = Column.of(
      "calificacion",
      Double.class
    );
    public static final Column<String> pais = Column.of(
      "pais",
      String.class
    );
  }

  @Override
  public Proveedor createEntity() {
    return new Proveedor();
  }

  @Override
  public Proveedor mapResultSetToEntity(ResultSet rs) throws SQLException {
    Proveedor proveedor = super.mapResultSetToEntity(rs);

    proveedor.setFecha_afiliacion(
      rs.getDate(Columns.fecha_afiliacion.getName())
    );
    proveedor.setRUC(rs.getString(Columns.ruc.getName()));
    proveedor.setRazonSocial(rs.getString(Columns.razonSocial.getName()));
    proveedor.setCalificacion(rs.getDouble(Columns.calificacion.getName()));
    proveedor.setPais(rs.getString(Columns.pais.getName()));

    return proveedor;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Proveedor entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.fecha_afiliacion, entity.getFecha_afiliacion());
    columns.put(Columns.ruc, entity.getRUC());
    columns.put(Columns.razonSocial, entity.getRazonSocial());
    columns.put(Columns.calificacion, entity.getCalificacion());
    columns.put(Columns.pais, entity.getPais());

    return columns;
  }

  @Override
  public Map<String, Object> getDiscriminatorColumns() {
    Map<String, Object> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("company_type", "provider_company");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, Object> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "provider_company".equals(discriminatorValues.get("company_type"));
  }
}
