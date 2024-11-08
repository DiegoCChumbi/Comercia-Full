package RecursosHumanosTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.AdministradorBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleadoEnum;

public class AdministradorTest {

  private static AdministradorBO administradorBO = new AdministradorBO();
  private static List<Administrador> listaAdministradores = new ArrayList<>();
  private static List<Integer> listaIds = new ArrayList<>();

  public static void main(String[] args) throws SQLException {
    System.out.println("Running Administrador Tests");

    testInsert();
    testListAll();
    testModify();
    testListAll();
    testGetById();
    testDelete();
    testListAll();

    System.out.println("All Administrador Tests Completed");
  }

  private static void testInsert() throws SQLException {
    System.out.println("\n== testInsert ==");

    insertAdministrador(
      "12345678",
      "Juan Pérez",
      "987654321",
      "juan.perez@example.com",
      "Av. Principal 123",
      EstadoEmpleadoEnum.ACTIVO,
      "jperez",
      "contrasenha123",
      2500.0,
      1
    );
    insertAdministrador(
      "87654321",
      "María Gómez",
      "912345678",
      "maria.gomez@example.com",
      "Calle Secundaria 456",
      EstadoEmpleadoEnum.ACTIVO,
      "mgomez",
      "password456",
      3000.0,
      2
    );

    assert listaIds.size() == 2 : "Expected 2 Administrador records inserted.";
  }

  private static void insertAdministrador(
    String dni,
    String nombre,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    double salario,
    int idAlmacen
  ) throws SQLException {
    int id = administradorBO.insertar(
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
      idAlmacen
    );
    System.out.println("Inserted Administrador ID: " + id);
    listaIds.add(id);
  }

  private static void testListAll() throws SQLException {
    System.out.println("\n== testListAll ==");

    listaAdministradores = administradorBO.listarTodos();
    for (Administrador administrador : listaAdministradores) {
      System.out.printf(
        "ID: %d, DNI: %s, Nombre: %s, Teléfono: %s, Correo: %s, Dirección: %s, Estado: %s, Usuario: %s, Salario: %.2f, Fecha Contratación: %s, ID Almacen: %d%n",
        administrador.getId(),
        administrador.getDni(),
        administrador.getNombre(),
        administrador.getTelefono(),
        administrador.getCorreo(),
        administrador.getDireccion(),
        administrador.getEstado(),
        administrador.getNombreUsuario(),
        administrador.getSalario(),
        administrador.getFechaContratacion(),
        administrador.getIdAlmacen()
      );
    }

    assert !listaAdministradores.isEmpty() : "Administrador list should not be empty.";
  }

  private static void testModify() throws SQLException {
    System.out.println("\n== testModify ==");

    if (listaIds.size() >= 2) {
      modifyAdministrador(
        listaIds.get(0),
        "12345678",
        "Juan Pérez",
        "987654321",
        "juan.perez@example.com",
        "Av. Principal 123",
        EstadoEmpleadoEnum.INACTIVO,
        "jperez",
        "contrasenha123",
        3000.0,
        1
      );
      modifyAdministrador(
        listaIds.get(1),
        "87654321",
        "María Gómez",
        "912345678",
        "maria.gomez@example.com",
        "Calle Secundaria 456",
        EstadoEmpleadoEnum.ACTIVO,
        "mgomez",
        "password456",
        10000.0,
        2
      );
    }
  }

  private static void modifyAdministrador(
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
    int idAlmacen
  ) throws SQLException {
    int result = administradorBO.modificar(
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
      idAlmacen
    );
    System.out.println(
      "Modified Administrador ID: " + id + ", Result: " + result
    );
    assert result > 0 : "Modification should return a positive result.";
  }

  private static void testGetById() throws SQLException {
    System.out.println("\n== testGetById ==");

    for (int id : listaIds) {
      Optional<Administrador> administradorOpt = administradorBO.obtenerPorId(
        id
      );
      if (administradorOpt.isPresent()) {
        Administrador administrador = administradorOpt.get();
        System.out.printf(
          "Found Administrador - ID: %d, DNI: %s, Nombre: %s, Teléfono: %s, Correo: %s, Dirección: %s, Estado: %s, Usuario: %s, Salario: %.2f, Fecha Contratación: %s, ID Almacen: %d%n",
          administrador.getId(),
          administrador.getDni(),
          administrador.getNombre(),
          administrador.getTelefono(),
          administrador.getCorreo(),
          administrador.getDireccion(),
          administrador.getEstado(),
          administrador.getNombreUsuario(),
          administrador.getSalario(),
          administrador.getFechaContratacion(),
          administrador.getIdAlmacen()
        );
      } else {
        System.out.println("Administrador not found for ID: " + id);
      }
    }
  }

  private static void testDelete() throws SQLException {
    System.out.println("\n== testDelete ==");

    for (int id : listaIds) {
      int result = administradorBO.eliminar(id);
      System.out.println(
        "Deleted Administrador ID: " + id + ", Result: " + result
      );
      assert result > 0 : "Deletion should return a positive result.";
    }

    listaIds.clear(); // Clear the list after deletion to reflect that IDs are no longer valid
  }
}
