package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.db.utils.Column;

public class AdministradorMapper extends EmpleadoMapper<Administrador> {

  public static class Columns extends EmpleadoMapper.Columns {

    public static final Column<Integer> idAlmacen = new Column<>(
      "id_almacen",
      Integer.class
    );
  }

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
  public Map<Column<?>, Object> mapEntityToColumns(Administrador entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.idAlmacen, entity.getIdAlmacen());

    return columns;
  }

  @Override
  public Map<String, Object> getDiscriminatorColumns() {
    Map<String, Object> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("employee_type", "admin_employee");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, Object> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "admin_employee".equals(discriminatorValues.get("employee_type"));
  }
}
