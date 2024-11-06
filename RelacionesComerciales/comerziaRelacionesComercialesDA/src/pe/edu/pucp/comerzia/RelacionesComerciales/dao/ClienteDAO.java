package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;

public interface ClienteDAO extends EmpresaDAO {
  public Integer insertar(Cliente cliente);

  public Integer modificar(Cliente cliente);

  public Integer eliminar(Cliente cliente);

  public ArrayList<Cliente> listarTodos();

  public Cliente obtenerPorId(Integer idCliente);
}
