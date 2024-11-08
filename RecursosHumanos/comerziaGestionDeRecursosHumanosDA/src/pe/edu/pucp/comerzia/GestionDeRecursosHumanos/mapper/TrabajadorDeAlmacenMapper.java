package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

public class TrabajadorDeAlmacenMapper
  extends EmpleadoMapper<TrabajadorDeAlmacen> {

  @Override
  public TrabajadorDeAlmacen createEntity() {
    return new TrabajadorDeAlmacen();
  }

  @Override
  public TrabajadorDeAlmacen mapResultSetToEntity(ResultSet rs)
    throws SQLException {
    TrabajadorDeAlmacen trabajadorDeAlmacen = super.mapResultSetToEntity(rs);

    trabajadorDeAlmacen.setIdAlmacen(rs.getInt("id_almacen"));
    trabajadorDeAlmacen.setLicenciaMontacarga(
      rs.getBoolean("licencia_montacarga")
    );

    return trabajadorDeAlmacen;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(TrabajadorDeAlmacen entity) {
    Map<String, Object> columns = super.mapEntityToColumns(entity);

    columns.put("id_almacen", entity.getIdAlmacen());
    columns.put("licencia_montacarga", entity.isLicenciaMontacarga());

    return columns;
  }

  @Override
  public Map<String, String> getDiscriminatorColumns() {
    Map<String, String> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("employee_type", "warehouse_employee");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, String> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "warehouse_employee".equals(
        discriminatorValues.get("employee_type")
      );
  }
}
