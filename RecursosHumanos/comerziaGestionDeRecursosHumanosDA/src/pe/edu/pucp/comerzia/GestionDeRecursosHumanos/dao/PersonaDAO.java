package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;

public interface PersonaDAO<T extends Persona> {
  public Integer insertar(T persona);

  public Integer insertar(
    T persona,
    Boolean usarTransaccion,
    Connection conexion
  );

  public Integer modificar(T persona);

  public Integer modificar(
    T persona,
    Boolean usarTransaccion,
    Connection conexion
  );

  public Integer eliminar(T persona);

  public Integer eliminar(
    T persona,
    Boolean usarTransaccion,
    Connection conexion
  );

  public ArrayList<T> listarTodos();

  public T obtenerPorId(Integer idPersona);

  public Boolean existePersona(T persona);
}
