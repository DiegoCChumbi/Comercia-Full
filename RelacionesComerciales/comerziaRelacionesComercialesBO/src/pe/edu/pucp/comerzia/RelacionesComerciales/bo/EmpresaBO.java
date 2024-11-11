package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;

public class EmpresaBO {

  private EmpresaDAO<Empresa> empresaDAO;

  public EmpresaBO() {
    this.empresaDAO = new EmpresaDAO<>();
  }

  public Integer insertar(
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria
  ) throws SQLException {
    Empresa empresa = new Empresa();

    empresa.setNombre(nombre);
    empresa.setDireccion(direccion);
    empresa.setTelefono(telefono);
    empresa.setEmail(email);
    empresa.setTipoIndustria(tipoIndustria);

    return empresaDAO.insert(empresa);
  }

  public Integer modificar(
    Integer id,
    String nombre,
    String direccion,
    String telefono,
    String email,
    String tipoIndustria
  ) throws SQLException {
    Empresa empresa = new Empresa();

    empresa.setId(id);
    empresa.setNombre(nombre);
    empresa.setDireccion(direccion);
    empresa.setTelefono(telefono);
    empresa.setEmail(email);
    empresa.setTipoIndustria(tipoIndustria);

    return this.empresaDAO.update(id, empresa);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return this.empresaDAO.delete(id);
  }

  public ArrayList<Empresa> listarTodos() throws SQLException {
    return new ArrayList<>(this.empresaDAO.findAll());
  }

  public Optional<Empresa> obtenerPorId(Integer id) throws SQLException {
    return this.empresaDAO.findById(id);
  }
}
