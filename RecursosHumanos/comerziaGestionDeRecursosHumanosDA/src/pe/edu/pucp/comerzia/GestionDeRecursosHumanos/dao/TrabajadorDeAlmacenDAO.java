package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper.TrabajadorDeAlmacenMapper;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

public class TrabajadorDeAlmacenDAO extends EmpleadoDAO<TrabajadorDeAlmacen> {

  public TrabajadorDeAlmacenDAO() {
    super(TrabajadorDeAlmacen.class, new TrabajadorDeAlmacenMapper());
  }
}
