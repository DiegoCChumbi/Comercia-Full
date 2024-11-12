package pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Vendedor;

public class VendedorMapper extends EmpleadoMapper<Vendedor> {

  public static class Columns extends EmpleadoMapper.Columns {

    public static final Column<Double> ingresosVentas = new Column<>(
      "ingresos_ventas",
      Double.class
    );
    public static final Column<Double> porcentajeComision = new Column<>(
      "porcentaje_comision",
      Double.class
    );
  }

  @Override
  public Vendedor createEntity() {
    return new Vendedor();
  }

  @Override
  public Vendedor mapResultSetToEntity(ResultSet rs) throws SQLException {
    Vendedor vendedor = super.mapResultSetToEntity(rs);

    vendedor.setIngresosVentas(rs.getDouble("ingresos_ventas"));
    vendedor.setPorcentajeComision(rs.getDouble("porcentaje_comision"));

    return vendedor;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Vendedor entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.ingresosVentas, entity.getIngresosVentas());
    columns.put(Columns.porcentajeComision, entity.getPorcentajeComision());

    return columns;
  }

  @Override
  public Map<String, Object> getDiscriminatorColumns() {
    Map<String, Object> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("employee_type", "sales_employee");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, Object> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "sales_employee".equals(discriminatorValues.get("employee_type"));
  }
}
