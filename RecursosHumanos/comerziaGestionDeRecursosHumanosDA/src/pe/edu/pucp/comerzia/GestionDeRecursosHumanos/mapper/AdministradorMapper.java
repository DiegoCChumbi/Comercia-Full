package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;

public class AdministradorMapper extends EmpleadoMapper<Administrador> {

  @Override
  public Administrador createEntity() {
    return new Administrador();
  }

  @Override
  public Administrador mapResultSetToEntity(ResultSet rs) throws SQLException {
    Administrador administrador = super.mapResultSetToEntity(rs);

    administrador.setIdAlmacen(rs.getInt("id_almacen"));

    return administrador;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(Administrador entity) {
    Map<String, Object> columns = super.mapEntityToColumns(entity);

    columns.put("id_almacen", entity.getIdAlmacen());

    return columns;
  }

  @Override
  public Map<String, String> getDiscriminatorColumns() {
    Map<String, String> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("employee_type", "admin_employee");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, String> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "admin_employee".equals(discriminatorValues.get("employee_type"));
  }
}
