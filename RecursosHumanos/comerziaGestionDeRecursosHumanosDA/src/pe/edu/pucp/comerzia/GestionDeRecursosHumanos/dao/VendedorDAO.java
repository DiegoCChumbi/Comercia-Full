package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

public interface VendedorDAO<T extends Vendedor> extends EmpleadoDAO<Vendedor> {
  public Boolean existeVendedor(Vendedor vendedor);

  public Boolean existeVendedor(Vendedor vendedor, Boolean abreConexion);
}
