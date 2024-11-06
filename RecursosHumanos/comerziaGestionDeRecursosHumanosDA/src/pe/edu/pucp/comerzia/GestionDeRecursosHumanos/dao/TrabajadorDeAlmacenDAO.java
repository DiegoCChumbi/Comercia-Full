package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

public interface TrabajadorDeAlmacenDAO<T extends TrabajadorDeAlmacen>
  extends EmpleadoDAO<TrabajadorDeAlmacen> {
  public Boolean existeTrabajadorDeAlmacen(TrabajadorDeAlmacen trabajador);

  public Boolean existeTrabajadorDeAlmacen(
    TrabajadorDeAlmacen trbajador,
    Boolean abreConexion
  );
}
