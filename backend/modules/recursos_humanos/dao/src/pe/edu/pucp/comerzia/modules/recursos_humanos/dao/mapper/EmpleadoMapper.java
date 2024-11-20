package pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.core.dao.utils.PasswordUtils;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Empleado;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.EstadoEmpleadoEnum;

public class EmpleadoMapper<T extends Empleado> extends PersonaMapper<T> {

  public static class Columns extends PersonaMapper.Columns {

    public static final Column<EstadoEmpleadoEnum> estado = Column.of(
      "estado",
      EstadoEmpleadoEnum.class
    );
    public static final Column<String> nombreUsuario = Column.of(
      "nombre_usuario",
      String.class
    );
    public static final Column<String> contrasenha = Column.of(
      "contrasenha",
      String.class
    );
    public static final Column<Double> salario = Column.of(
      "salario",
      Double.class
    );
    public static final Column<String> fechaContratacion = Column.of(
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

    empleado.setEstado(
      EstadoEmpleadoEnum.valueOf(rs.getString(Columns.estado.getName()))
    );
    empleado.setNombreUsuario(rs.getString(Columns.nombreUsuario.getName()));
    empleado.setContrasenha(rs.getString(Columns.contrasenha.getName()));
    empleado.setSalario(rs.getDouble(Columns.salario.getName()));
    empleado.setFechaContratacion(
      rs.getDate(Columns.fechaContratacion.getName())
    );
    return empleado;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(T entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.estado, entity.getEstado());
    columns.put(Columns.nombreUsuario, entity.getNombreUsuario());
    try {
      columns.put(
        Columns.contrasenha,
        PasswordUtils.hashPassword(entity.getContrasenha())
      );
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
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
