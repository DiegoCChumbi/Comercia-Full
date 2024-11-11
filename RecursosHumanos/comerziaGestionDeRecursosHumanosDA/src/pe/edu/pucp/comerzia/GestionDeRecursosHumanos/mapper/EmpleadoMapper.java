package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleadoEnum;
import pe.edu.pucp.comerzia.db.utils.Column;

public class EmpleadoMapper<T extends Empleado> extends PersonaMapper<T> {

  public static class Columns extends PersonaMapper.Columns {

    public static final Column<EstadoEmpleadoEnum> estado = new Column<>(
      "estado",
      EstadoEmpleadoEnum.class
    );
    public static final Column<String> nombreUsuario = new Column<>(
      "nombre_usuario",
      String.class
    );
    public static final Column<String> contrasenha = new Column<>(
      "contrasenha",
      String.class
    );
    public static final Column<Double> salario = new Column<>(
      "salario",
      Double.class
    );
    public static final Column<String> fechaContratacion = new Column<>(
      "fecha_contratacion",
      String.class
    );
  }

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
  public Map<Column<?>, Object> mapEntityToColumns(T entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.estado, entity.getEstado());
    columns.put(Columns.nombreUsuario, entity.getNombreUsuario());
    columns.put(Columns.contrasenha, entity.getContrasenha());
    columns.put(Columns.salario, entity.getSalario());
    columns.put(Columns.fechaContratacion, entity.getFechaContratacion());

    return columns;
  }

  @Override
  public Map<String, Object> getDiscriminatorColumns() {
    Map<String, Object> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("person_type", "employee_person");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, Object> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "employee_person".equals(discriminatorValues.get("person_type"));
  }
}
