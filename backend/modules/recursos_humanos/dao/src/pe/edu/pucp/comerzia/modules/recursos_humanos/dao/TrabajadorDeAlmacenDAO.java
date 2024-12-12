package pe.edu.pucp.comerzia.modules.recursos_humanos.dao;

import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.TrabajadorDeAlmacenMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.TrabajadorDeAlmacen;

public class TrabajadorDeAlmacenDAO extends EmpleadoDAO<TrabajadorDeAlmacen> {

  private static final TrabajadorDeAlmacenDAO instance =
    new TrabajadorDeAlmacenDAO();

  public static TrabajadorDeAlmacenDAO getTrabajadorDeAlmacenInstance() {
    return instance;
  }

  protected TrabajadorDeAlmacenDAO() {
    super(TrabajadorDeAlmacen.class, new TrabajadorDeAlmacenMapper());
  }
}
