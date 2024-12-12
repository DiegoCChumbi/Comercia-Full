package pe.edu.pucp.comerzia.modules.recursos_humanos.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.VendedorMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Vendedor;

public class VendedorDAO extends EmpleadoDAO<Vendedor> {

  private static final VendedorDAO instance = new VendedorDAO();

  public static VendedorDAO getVendedorInstance() {
    return instance;
  }

  protected VendedorDAO() {
    super(Vendedor.class, new VendedorMapper());
  }
}
