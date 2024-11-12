package pe.edu.pucp.comerzia.modules.gestion_almacen.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.AlmacenBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;

public class AlmacenBOTest {

  private static AlmacenBO almacenBO = new AlmacenBO();
  private static List<Almacen> listaAlmacenes = new ArrayList<>();
  private static List<Integer> listaIds = new ArrayList<>();

  public static void main(String[] args) throws SQLException {
    System.out.println("Running AlmacenBO Tests");

    testInsert();
    testListAll();
    testModify();
    testListAll();
    testGetById();
    testDelete();
    testListAll();

    System.out.println("All AlmacenBO Tests Completed");
  }

  private static void testInsert() throws SQLException {
    System.out.println("\n== testInsert ==");

    insertAlmacen("Almacen1", "operativo", "elalmacen1");
    insertAlmacen("Almacen2", "operativo", "elalmacen2");

    assert listaIds.size() == 2 : "Expected 2 almacenes inserted.";
  }

  private static void insertAlmacen(
    String nombre,
    String estado,
    String descripcion
  ) throws SQLException {
    int id = almacenBO.insertar(nombre, estado, descripcion);
    System.out.println("Inserted Almacen ID: " + id);
    listaIds.add(id);
  }

  private static void testListAll() throws SQLException {
    System.out.println("\n== testListAll ==");

    listaAlmacenes = almacenBO.listarTodos();
    for (Almacen almacen : listaAlmacenes) {
      System.out.printf(
        "ID: %d, Nombre: %s, Estado: %s, Descripcion: %s%n",
        almacen.getId(),
        almacen.getNombre(),
        almacen.getEstado(),
        almacen.getDescripcion()
      );
    }

    assert !listaAlmacenes.isEmpty() : "Almacenes list should not be empty.";
  }

  private static void testModify() throws SQLException {
    System.out.println("\n== testModify ==");

    if (listaIds.size() >= 2) {
      modifyAlmacen(listaIds.get(0), "Almacen1Mod", "inactivo", "modificado1");
      modifyAlmacen(listaIds.get(1), "Almacen2Mod", "inactivo", "modificado2");
    }
  }

  private static void modifyAlmacen(
    int id,
    String nombre,
    String estado,
    String descripcion
  ) throws SQLException {
    int result = almacenBO.modificar(id, nombre, estado, descripcion);
    System.out.println("Modified Almacen ID: " + id + ", Result: " + result);
    assert result > 0 : "Modification should return a positive result.";
  }

  private static void testGetById() throws SQLException {
    System.out.println("\n== testGetById ==");

    for (int id : listaIds) {
      Optional<Almacen> almacenOpt = almacenBO.obtenerPorId(id);
      if (almacenOpt.isPresent()) {
        Almacen almacen = almacenOpt.get();
        System.out.printf(
          "Found Almacen - ID: %d, Estado: %s, Descripcion: %s%n",
          almacen.getId(),
          almacen.getEstado(),
          almacen.getDescripcion()
        );
      } else {
        System.out.println("Almacen not found for ID: " + id);
      }
    }
  }

  private static void testDelete() throws SQLException {
    System.out.println("\n== testDelete ==");

    for (int id : listaIds) {
      int result = almacenBO.eliminar(id);
      System.out.println("Deleted Almacen ID: " + id + ", Result: " + result);
      assert result > 0 : "Deletion should return a positive result.";
    }

    listaIds.clear(); // Clear the list after deletion
  }
}
