package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao;

import pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper.ProveedorMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Proveedor;

public class ProveedorDAO extends EmpresaDAO<Proveedor> {

  public ProveedorDAO() {
    super(Proveedor.class, new ProveedorMapper());
  }
}
