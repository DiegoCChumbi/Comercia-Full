package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

public class VendedorMapper extends EmpleadoMapper<Vendedor> {

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
  public Map<String, Object> mapEntityToColumns(Vendedor entity) {
    Map<String, Object> columns = super.mapEntityToColumns(entity);

    columns.put("ingresos_ventas", entity.getIngresosVentas());
    columns.put("porcentaje_comision", entity.getPorcentajeComision());

    return columns;
  }

  @Override
  public Map<String, String> getDiscriminatorColumns() {
    Map<String, String> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("employee_type", "sales_employee");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, String> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "sales_employee".equals(discriminatorValues.get("employee_type"));
  }
}