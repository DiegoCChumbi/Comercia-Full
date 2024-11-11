package RecursosHumanosTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.VendedorBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleadoEnum;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

public class VendedorTest {

  private static VendedorBO vendedorBO = new VendedorBO();
  private static List<Vendedor> listaVendedores = new ArrayList<>();
  private static List<Integer> listaIds = new ArrayList<>();

  public static void main(String[] args) throws SQLException {
    System.out.println("Running Vendedor Tests");

    testInsert();
    testListAll();
    testModify();
    testListAll();
    testGetById();
    testDelete();
    testListAll();

    System.out.println("All Vendedor Tests Completed");
  }

  private static void testInsert() throws SQLException {
    System.out.println("\n== testInsert ==");

    insertVendedor(
      "87654321",
      "Roberto García",
      "998877665",
      "roberto.garcia@email.com",
      "Av. Los Jardines 456",
      EstadoEmpleadoEnum.ACTIVO,
      "rgarcia",
      "password321",
      3000.50,
      1000.0,
      0.7283
    );
    insertVendedor(
      "12345678",
      "Mariana Torres",
      "912345678",
      "mariana.torres@email.com",
      "Calle Las Flores 789",
      EstadoEmpleadoEnum.INACTIVO,
      "mtorres",
      "pass456",
      2500.75,
      1000.0,
      0.3457
    );

    assert listaIds.size() == 2 : "Expected 2 Vendedor records inserted.";
  }

  private static void insertVendedor(
    String dni,
    String nombre,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    double salario,
    double ingresosVentas,
    double porcentajeComision
  ) throws SQLException {
    int id = vendedorBO.insertar(
      dni,
      nombre,
      telefono,
      correo,
      direccion,
      estado,
      nombreUsuario,
      contrasenha,
      salario,
      new Date(),
      ingresosVentas,
      porcentajeComision
    );
    System.out.println("Inserted Vendedor ID: " + id);
    listaIds.add(id);
  }

  private static void testListAll() throws SQLException {
    System.out.println("\n== testListAll ==");

    listaVendedores = vendedorBO.listarTodos();
    for (Vendedor vendedor : listaVendedores) {
      System.out.printf(
        "ID: %d, DNI: %s, Nombre: %s, Teléfono: %s, Correo: %s, Dirección: %s, Estado: %s, Usuario: %s, Salario: %.2f, Fecha Contratación: %s, Ingresos Ventas: %.2f, Comisión: %.4f%n",
        vendedor.getId(),
        vendedor.getDni(),
        vendedor.getNombre(),
        vendedor.getTelefono(),
        vendedor.getCorreo(),
        vendedor.getDireccion(),
        vendedor.getEstado(),
        vendedor.getNombreUsuario(),
        vendedor.getSalario(),
        vendedor.getFechaContratacion(),
        vendedor.getIngresosVentas(),
        vendedor.getPorcentajeComision()
      );
    }

    assert !listaVendedores.isEmpty() : "Vendedor list should not be empty.";
  }

  private static void testModify() throws SQLException {
    System.out.println("\n== testModify ==");

    if (listaIds.size() >= 2) {
      modifyVendedor(
        listaIds.get(0),
        "87654321",
        "Roberto García",
        "998877665",
        "roberto.garcia@email.com",
        "Av. Los Jardines 456",
        EstadoEmpleadoEnum.ACTIVO,
        "rgarcia",
        "password321",
        3000.50,
        5000.0,
        0.7283
      );
      modifyVendedor(
        listaIds.get(1),
        "12345678",
        "Mariana Torres",
        "912345678",
        "mariana.torres@email.com",
        "Calle Las Flores 789",
        EstadoEmpleadoEnum.INACTIVO,
        "mtorres",
        "pass456",
        2500.75,
        9500.0,
        0.3457
      );
    }
  }

  private static void modifyVendedor(
    int id,
    String dni,
    String nombre,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    double salario,
    double ingresosVentas,
    double porcentajeComision
  ) throws SQLException {
    int result = vendedorBO.modificar(
      id,
      dni,
      nombre,
      telefono,
      correo,
      direccion,
      estado,
      nombreUsuario,
      contrasenha,
      salario,
      new Date(),
      ingresosVentas,
      porcentajeComision
    );
    System.out.println("Modified Vendedor ID: " + id + ", Result: " + result);
    assert result > 0 : "Modification should return a positive result.";
  }

  private static void testGetById() throws SQLException {
    System.out.println("\n== testGetById ==");

    for (int id : listaIds) {
      Optional<Vendedor> vendedorOpt = vendedorBO.obtenerPorId(id);
      if (vendedorOpt.isPresent()) {
        Vendedor vendedor = vendedorOpt.get();
        System.out.printf(
          "Found Vendedor - ID: %d, DNI: %s, Nombre: %s, Teléfono: %s, Correo: %s, Dirección: %s, Estado: %s, Usuario: %s, Salario: %.2f, Fecha Contratación: %s, Ingresos Ventas: %.2f, Comisión: %.4f%n",
          vendedor.getId(),
          vendedor.getDni(),
          vendedor.getNombre(),
          vendedor.getTelefono(),
          vendedor.getCorreo(),
          vendedor.getDireccion(),
          vendedor.getEstado(),
          vendedor.getNombreUsuario(),
          vendedor.getSalario(),
          vendedor.getFechaContratacion(),
          vendedor.getIngresosVentas(),
          vendedor.getPorcentajeComision()
        );
      } else {
        System.out.println("Vendedor not found for ID: " + id);
      }
    }
  }

  private static void testDelete() throws SQLException {
    System.out.println("\n== testDelete ==");

    for (int id : listaIds) {
      int result = vendedorBO.eliminar(id);
      System.out.println("Deleted Vendedor ID: " + id + ", Result: " + result);
      assert result > 0 : "Deletion should return a positive result.";
    }

    listaIds.clear(); // Clear the list after deletion to reflect that IDs are no longer valid
  }
}
