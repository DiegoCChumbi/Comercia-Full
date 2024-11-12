package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Cliente;

public class ClienteMapper extends EmpresaMapper<Cliente> {

  public static class Columns extends EmpresaMapper.Columns {

    public static final Column<Integer> fechaRegistro = new Column<>(
      "fecha_registro",
      Integer.class
    );
    public static final Column<Integer> fechaUltimaCompra = new Column<>(
      "fecha_ultima_compra",
      Integer.class
    );
  }

  @Override
  public Cliente createEntity() {
    return new Cliente();
  }

  @Override
  public Cliente mapResultSetToEntity(ResultSet rs) throws SQLException {
    Cliente cliente = super.mapResultSetToEntity(rs);

    cliente.setFechaRegistro(rs.getDate("fecha_registro"));
    cliente.setFechaUltimaCompra(rs.getDate("fecha_ultima_compra"));
    return cliente;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Cliente entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.fechaRegistro, entity.getFechaRegistro());
    columns.put(Columns.fechaUltimaCompra, entity.getFechaUltimaCompra());

    return columns;
  }

  @Override
  public Map<String, Object> getDiscriminatorColumns() {
    Map<String, Object> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("company_type", "client_company");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, Object> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "client_company".equals(discriminatorValues.get("company_type"));
  }
}
