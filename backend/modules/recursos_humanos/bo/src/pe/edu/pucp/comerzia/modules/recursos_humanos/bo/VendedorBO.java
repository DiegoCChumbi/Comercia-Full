package pe.edu.pucp.comerzia.modules.recursos_humanos.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.VendedorDAO;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.VendedorMapper;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.EstadoEmpleadoEnum;
import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Vendedor;

public class VendedorBO {

  private VendedorDAO vendedorDAO;

  public VendedorBO() {
    this.vendedorDAO = VendedorDAO.getVendedorInstance();
  }

  public Integer insertar(
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion,
    Double ingresosVentas,
    Double porcentajeComision
  ) throws SQLException {
    Vendedor vendedor = new Vendedor();

    vendedor.setDni(dni);
    vendedor.setNombre(nombreCompleto);
    vendedor.setTelefono(telefono);
    vendedor.setCorreo(correo);
    vendedor.setDireccion(direccion);

    vendedor.setEstado(estado);
    vendedor.setNombreUsuario(nombreUsuario);
    vendedor.setContrasenha(contrasenha);
    vendedor.setSalario(salario);
    vendedor.setFechaContratacion(fechaContratacion);

    vendedor.setIngresosVentas(ingresosVentas);
    vendedor.setPorcentajeComision(porcentajeComision);

    return vendedorDAO.insert(vendedor);
  }

  public Integer modificar(
    Integer id,
    String dni,
    String nombreCompleto,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    Double salario,
    Date fechaContratacion,
    Double ingresosVentas,
    Double porcentajeComision
  ) throws SQLException {
    Vendedor vendedor = new Vendedor();

    vendedor.setId(id);
    vendedor.setDni(dni);
    vendedor.setNombre(nombreCompleto);
    vendedor.setTelefono(telefono);
    vendedor.setCorreo(correo);
    vendedor.setDireccion(direccion);

    vendedor.setEstado(estado);
    vendedor.setNombreUsuario(nombreUsuario);
    vendedor.setContrasenha(contrasenha);
    vendedor.setSalario(salario);
    vendedor.setFechaContratacion(fechaContratacion);

    vendedor.setIngresosVentas(ingresosVentas);
    vendedor.setPorcentajeComision(porcentajeComision);

    return vendedorDAO.update(id, vendedor);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return vendedorDAO.delete(id);
  }

  public ArrayList<Vendedor> listarTodos() throws SQLException {
    // return new ArrayList<>(vendedorDAO.findAll());
    // for debugging purposes
    ArrayList<Vendedor> vendedores = new ArrayList<>(vendedorDAO.findAll());
    for (Vendedor vendedor : vendedores) {
      System.out.println(vendedor);
    }
    return vendedores;
  }

  public Optional<Vendedor> obtenerPorId(Integer id) throws SQLException {
    return vendedorDAO.findById(id);
  }

  public ArrayList<Vendedor> listarParaIndex() throws SQLException {
    return new ArrayList<>(vendedorDAO.query().limit(3).list());
  }

  public ArrayList<Vendedor> buscarVendedores(String nombreVendedor)
    throws SQLException {
    return new ArrayList<>(
      vendedorDAO
        .query()
        .where(VendedorMapper.Columns.nombre.like(nombreVendedor))
        .list()
    );
  }
}
