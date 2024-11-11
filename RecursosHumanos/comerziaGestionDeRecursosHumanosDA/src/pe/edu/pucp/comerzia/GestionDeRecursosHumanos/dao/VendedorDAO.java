package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper.VendedorMapper;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

public class VendedorDAO extends EmpleadoDAO<Vendedor> {

  public VendedorDAO() {
    super(Vendedor.class, new VendedorMapper());
  }
}
