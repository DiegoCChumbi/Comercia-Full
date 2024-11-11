package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;
import pe.edu.pucp.comerzia.RelacionesComerciales.mapper.ClienteMapper;

public class ClienteDAO extends EmpresaDAO<Cliente> {

  public ClienteDAO() {
    super(Cliente.class, new ClienteMapper());
  }
}
