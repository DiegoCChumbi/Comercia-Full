package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;
import pe.edu.pucp.comerzia.RelacionesComerciales.mapper.ProveedorMapper;

public class ProveedorDAO extends EmpresaDAO<Proveedor> {

  public ProveedorDAO() {
    super(Proveedor.class, new ProveedorMapper());
  }
}
