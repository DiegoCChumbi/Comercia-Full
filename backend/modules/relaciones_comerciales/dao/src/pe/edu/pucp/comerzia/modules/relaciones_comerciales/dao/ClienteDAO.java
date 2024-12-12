package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao;

import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper.ClienteMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Cliente;

public class ClienteDAO extends EmpresaDAO<Cliente> {

  private static final ClienteDAO instance = new ClienteDAO();

  public static ClienteDAO getClienteInstance() {
    return instance;
  }

  protected ClienteDAO() {
    super(Cliente.class, new ClienteMapper());
  }
}
