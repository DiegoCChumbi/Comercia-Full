package pe.edu.pucp.comerzia.RelacionesComerciales.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;

public class ProveedorMapper extends EmpresaMapper<Proveedor> {

  @Override
  public Proveedor createEntity() {
    return new Proveedor();
  }

  @Override
  public Proveedor mapResultSetToEntity(ResultSet rs) throws SQLException {
    Proveedor proveedor = super.mapResultSetToEntity(rs);

    proveedor.setFecha_afiliacion(rs.getDate("fecha_afiliacion"));
    proveedor.setRUC(rs.getString("RUC"));
    proveedor.setRazonSocial(rs.getString("razon_social"));
    proveedor.setCalificacion(rs.getDouble("calificacion"));
    proveedor.setPais(rs.getString("pais"));

    return proveedor;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(Proveedor entity) {
    Map<String, Object> columns = super.mapEntityToColumns(entity);

    columns.put("fecha_afiliacion", entity.getFecha_afiliacion());
    columns.put("ruc", entity.getRUC());
    columns.put("razon_social", entity.getRazonSocial());
    columns.put("calificacion", entity.getCalificacion());
    columns.put("pais", entity.getPais());

    return columns;
  }

  @Override
  public Map<String, String> getDiscriminatorColumns() {
    Map<String, String> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("company_type", "provider_company");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, String> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "provider_company".equals(discriminatorValues.get("company_type"));
  }
}
