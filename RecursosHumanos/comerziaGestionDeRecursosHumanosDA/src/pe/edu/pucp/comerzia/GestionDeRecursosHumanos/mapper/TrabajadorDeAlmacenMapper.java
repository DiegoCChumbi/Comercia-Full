package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;
import pe.edu.pucp.comerzia.db.utils.Column;

public class TrabajadorDeAlmacenMapper
  extends EmpleadoMapper<TrabajadorDeAlmacen> {

  public static class Columns extends EmpleadoMapper.Columns {

    public static final Column<Integer> idAlmacen = new Column<>(
      "id_almacen",
      Integer.class
    );
    public static final Column<Boolean> licenciaMontacarga = new Column<>(
      "licencia_montacarga",
      Boolean.class
    );
  }

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
  public Map<Column<?>, Object> mapEntityToColumns(TrabajadorDeAlmacen entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.idAlmacen, entity.getIdAlmacen());
    columns.put(Columns.licenciaMontacarga, entity.getLicenciaMontacarga());

    return columns;
  }

  @Override
  public Map<String, Object> getDiscriminatorColumns() {
    Map<String, Object> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("employee_type", "warehouse_employee");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, Object> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "warehouse_employee".equals(
        discriminatorValues.get("employee_type")
      );
  }
}
