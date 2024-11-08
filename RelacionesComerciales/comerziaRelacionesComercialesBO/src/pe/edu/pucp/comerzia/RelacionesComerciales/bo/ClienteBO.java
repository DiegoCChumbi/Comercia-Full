package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ClienteDAO;

public class ClienteBO {

  private ClienteDAO clienteDAO;

  public ClienteBO() {
    this.clienteDAO = new ClienteDAO();
  }

  public Integer insertar(
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fechaRegistro,
    Date fechaUltimaCompra
  ) throws SQLException {
    Cliente cliente = new Cliente();

    cliente.setNombre(nombre);
    cliente.setDireccion(direccion);
    cliente.setTelefono(telefono);
    cliente.setEmail(email);
    cliente.setTipoIndustria(tipoIndustria);
    cliente.setFechaRegistro(fechaRegistro);
    cliente.setFechaUltimaCompra(fechaUltimaCompra);

    return this.clienteDAO.insert(cliente);
  }

  public Integer modificar(
    Integer id,
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria,
    Date fechaRegistro,
    Date fechaUltimaCompra
  ) throws SQLException {
    Cliente cliente = new Cliente();

    cliente.setId(id);
    cliente.setNombre(nombre);
    cliente.setDireccion(direccion);
    cliente.setTelefono(telefono);
    cliente.setEmail(email);
    cliente.setTipoIndustria(tipoIndustria);
    cliente.setFechaRegistro(fechaRegistro);
    cliente.setFechaUltimaCompra(fechaUltimaCompra);

    return clienteDAO.update(cliente);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return this.clienteDAO.delete(id);
  }

  public ArrayList<Cliente> listarTodos() throws SQLException {
    return new ArrayList<>(this.clienteDAO.findAll());
  }

  public Optional<Cliente> obtenerPorId(Integer id) throws SQLException {
    return this.clienteDAO.findById(id);
  }
}
