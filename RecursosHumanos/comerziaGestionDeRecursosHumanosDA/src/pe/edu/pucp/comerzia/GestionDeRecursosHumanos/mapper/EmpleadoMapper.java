package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleadoEnum;

public class EmpleadoMapper<T extends Empleado> extends PersonaMapper<T> {

  @SuppressWarnings("unchecked")
  @Override
  public T createEntity() {
    return (T) new Empleado();
  }

  @Override
  public void setGeneratedId(Empleado entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(Empleado entity) {
    return entity.getId();
  }

  @Override
  public T mapResultSetToEntity(ResultSet rs) throws SQLException {
    T empleado = super.mapResultSetToEntity(rs);

    empleado.setEstado(EstadoEmpleadoEnum.valueOf(rs.getString("estado")));
    empleado.setNombreUsuario(rs.getString("nombre_usuario"));
    empleado.setContrasenha(rs.getString("contrasenha"));
    empleado.setSalario(rs.getDouble("salario"));
    empleado.setFechaContratacion(rs.getDate("fecha_contratacion"));
    return empleado;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(T entity) {
    Map<String, Object> columns = super.mapEntityToColumns(entity);

    columns.put("estado", entity.getEstado().toString());
    columns.put("nombre_usuario", entity.getNombreUsuario());
    columns.put("contrasenha", entity.getContrasenha());
    columns.put("salario", entity.getSalario());
    columns.put("fecha_contratacion", entity.getFechaContratacion());

    return columns;
  }

  @Override
  public Map<String, String> getDiscriminatorColumns() {
    Map<String, String> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("person_type", "employee_person");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, String> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "employee_person".equals(discriminatorValues.get("person_type"));
  }
}
