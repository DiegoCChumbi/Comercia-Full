package RecursosHumanosTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.TrabajadorDeAlmacenBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleadoEnum;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

public class TrabajadorTest {

  private static TrabajadorDeAlmacenBO trabajadorBO =
    new TrabajadorDeAlmacenBO();
  private static List<TrabajadorDeAlmacen> listaTrabajadores =
    new ArrayList<>();
  private static List<Integer> listaIds = new ArrayList<>();

  public static void main(String[] args) throws SQLException {
    System.out.println("Running Trabajador Tests");

    testInsert();
    testListAll();
    testModify();
    testListAll();
    testGetById();
    testDelete();
    testListAll();

    System.out.println("All Trabajador Tests Completed");
  }

  private static void testInsert() throws SQLException {
    System.out.println("\n== testInsert ==");

    insertTrabajador(
      "12345678",
      "Carlos Torres",
      "987654321",
      "carlos.torres@example.com",
      "Av. Principal 123",
      EstadoEmpleadoEnum.INACTIVO,
      "ctorres",
      "password789",
      2800.0,
      1,
      true
    );
    insertTrabajador(
      "87654321",
      "Ana Gómez",
      "912345678",
      "ana.gomez@example.com",
      "Calle Secundaria 456",
      EstadoEmpleadoEnum.ACTIVO,
      "agomez",
      "password456",
      3500.0,
      1,
      false
    );

    assert listaIds.size() == 2 : "Expected 2 Trabajador records inserted.";
  }

  private static void insertTrabajador(
    String dni,
    String nombre,
    String telefono,
    String correo,
    String direccion,
    EstadoEmpleadoEnum estado,
    String nombreUsuario,
    String contrasenha,
    double salario,
    int idAlmacen,
    boolean disponibilidad
  ) throws SQLException {
    int id = trabajadorBO.insertar(
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
      idAlmacen,
      disponibilidad
    );
    System.out.println("Inserted Trabajador ID: " + id);
    listaIds.add(id);
  }

  private static void testListAll() throws SQLException {
    System.out.println("\n== testListAll ==");

    listaTrabajadores = trabajadorBO.listarTodos();
    for (TrabajadorDeAlmacen trabajador : listaTrabajadores) {
      System.out.printf(
        "ID: %d, DNI: %s, Nombre: %s, Teléfono: %s, Correo: %s, Dirección: %s, Estado: %s, Usuario: %s, Salario: %.2f, Fecha Contratación: %s, ID Almacen: %d, Licencia Montacarga: %b%n",
        trabajador.getId(),
        trabajador.getDni(),
        trabajador.getNombre(),
        trabajador.getTelefono(),
        trabajador.getCorreo(),
        trabajador.getDireccion(),
        trabajador.getEstado(),
        trabajador.getNombreUsuario(),
        trabajador.getSalario(),
        trabajador.getFechaContratacion(),
        trabajador.getIdAlmacen(),
        trabajador.getLicenciaMontacarga()
      );
    }

    assert !listaTrabajadores.isEmpty() : "Trabajador list should not be empty.";
  }

  private static void testModify() throws SQLException {
    System.out.println("\n== testModify ==");

    if (listaIds.size() >= 2) {
      modifyTrabajador(
        listaIds.get(0),
        "12345678",
        "Carlos Torres",
        "987654321",
        "2222@example.com",
        "Av. Principal 123",
        EstadoEmpleadoEnum.INACTIVO,
        "ctorres",
        "password789",
        2800.0,
        1,
        true
      );
      modifyTrabajador(
        listaIds.get(1),
        "87654321",
        "Ana Gómez",
        "912345678",
        "ana.gomez@example.com",
        "Calle Secundaria 456",
        EstadoEmpleadoEnum.ACTIVO,
        "agomez",
        "password456",
        3500.0,
        1,
        false
      );
    }
  }

  private static void modifyTrabajador(
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
    int idAlmacen,
    boolean disponibilidad
  ) throws SQLException {
    int result = trabajadorBO.modificar(
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
      idAlmacen,
      disponibilidad
    );
    System.out.println("Modified Trabajador ID: " + id + ", Result: " + result);
    assert result > 0 : "Modification should return a positive result.";
  }

  private static void testGetById() throws SQLException {
    System.out.println("\n== testGetById ==");

    for (int id : listaIds) {
      Optional<TrabajadorDeAlmacen> trabajadorOpt = trabajadorBO.obtenerPorId(
        id
      );
      if (trabajadorOpt.isPresent()) {
        TrabajadorDeAlmacen trabajador = trabajadorOpt.get();
        System.out.printf(
          "Found Trabajador - ID: %d, DNI: %s, Nombre: %s, Teléfono: %s, Correo: %s, Dirección: %s, Estado: %s, Usuario: %s, Salario: %.2f, Fecha Contratación: %s, ID Almacen: %d, Licencia Montacarga: %b%n",
          trabajador.getId(),
          trabajador.getDni(),
          trabajador.getNombre(),
          trabajador.getTelefono(),
          trabajador.getCorreo(),
          trabajador.getDireccion(),
          trabajador.getEstado(),
          trabajador.getNombreUsuario(),
          trabajador.getSalario(),
          trabajador.getFechaContratacion(),
          trabajador.getIdAlmacen(),
          trabajador.getLicenciaMontacarga()
        );
      } else {
        System.out.println("Trabajador not found for ID: " + id);
      }
    }
  }

  private static void testDelete() throws SQLException {
    System.out.println("\n== testDelete ==");

    for (int id : listaIds) {
      int result = trabajadorBO.eliminar(id);
      System.out.println(
        "Deleted Trabajador ID: " + id + ", Result: " + result
      );
      assert result > 0 : "Deletion should return a positive result.";
    }

    listaIds.clear(); // Clear the list after deletion to reflect that IDs are no longer valid
  }
}
