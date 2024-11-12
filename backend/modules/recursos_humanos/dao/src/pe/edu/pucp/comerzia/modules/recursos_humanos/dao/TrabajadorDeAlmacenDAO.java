package pe.edu.pucp.comerzia.modules.recursos_humanos.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.TrabajadorDeAlmacenMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.TrabajadorDeAlmacen;

public class TrabajadorDeAlmacenDAO extends EmpleadoDAO<TrabajadorDeAlmacen> {

  public TrabajadorDeAlmacenDAO() {
    super(TrabajadorDeAlmacen.class, new TrabajadorDeAlmacenMapper());
  }
}
