package pe.edu.pucp.comerzia.RelacionesComerciales.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;

public class ClienteMapper extends EmpresaMapper<Cliente> {

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
  public Map<String, Object> mapEntityToColumns(Cliente entity) {
    Map<String, Object> columns = super.mapEntityToColumns(entity);

    columns.put("fecha_registro", entity.getFechaRegistro());
    columns.put("fecha_ultima_compra", entity.getFechaUltimaCompra());

    return columns;
  }

  @Override
  public Map<String, String> getDiscriminatorColumns() {
    Map<String, String> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("company_type", "client_company");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, String> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "client_company".equals(discriminatorValues.get("company_type"));
  }
}
