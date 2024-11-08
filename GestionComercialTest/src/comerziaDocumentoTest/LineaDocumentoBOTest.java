package comerziaDocumentoTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pe.edu.pucp.comerzia.gestioncomercial.bo.LineaDocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;

public class LineaDocumentoBOTest {

  private static LineaDocumentoBO lineaBO = new LineaDocumentoBO();
  private static List<LineaDocumento> listaLineas;
  private static List<Integer> listaIds = new ArrayList<>();

  public static void main(String[] args) throws SQLException {
    System.out.println("Running LineaDocumentoBO Tests");

    testInsert();
    testListAll();
    testModify();
    testListAll();
    testGetById();
    testExists();
    testDelete();
    testListAll();

    System.out.println("All LineaDocumentoBO Tests Completed");
  }

  private static void testInsert() throws SQLException {
    System.out.println("\n== testInsert ==");

    insertLinea(5, 1, 1, 9.99);
    insertLinea(6, 1, 1, 49.99);

    assert listaIds.size() == 2 : "Expected 2 lineas inserted.";
  }

  private static void insertLinea(
    int idDocumento,
    int idProducto,
    int cantidad,
    double precioUnitario
  ) throws SQLException {
    int id = lineaBO.insertar(
      idDocumento,
      idProducto,
      cantidad,
      precioUnitario
    );
    System.out.println("Inserted LineaDocumento ID: " + id);
    listaIds.add(id);
  }

  private static void testListAll() throws SQLException {
    System.out.println("\n== testListAll ==");

    listaLineas = lineaBO.listarTodos();
    for (LineaDocumento linea : listaLineas) {
      System.out.printf(
        "ID: %d, DocumentoID: %d, ProductoID: %d, Cantidad: %d, PrecioUnitario: %.2f%n",
        linea.getId(),
        linea.getIdDocumento(),
        linea.getIdProducto(),
        linea.getCantidad(),
        linea.getPrecioUnitario()
      );
    }

    assert !listaLineas.isEmpty() : "LineaDocumento list should not be empty.";
  }

  private static void testModify() throws SQLException {
    System.out.println("\n== testModify ==");

    if (listaIds.size() >= 2) {
      modifyLinea(listaIds.get(0), 5, 1, 1, 20.00);
      modifyLinea(listaIds.get(1), 6, 1, 1, 100.00);
    }
  }

  private static void modifyLinea(
    int id,
    int idDocumento,
    int idProducto,
    int cantidad,
    double precioUnitario
  ) throws SQLException {
    int result = lineaBO.modificar(
      id,
      idDocumento,
      idProducto,
      cantidad,
      precioUnitario
    );
    System.out.println(
      "Modified LineaDocumento ID: " + id + ", Result: " + result
    );
    assert result > 0 : "Modification should return a positive result.";
  }

  private static void testGetById() throws SQLException {
    System.out.println("\n== testGetById ==");

    for (int id : listaIds) {
      Optional<LineaDocumento> lineaOpt = lineaBO.obtenerPorId(id);
      if (lineaOpt.isPresent()) {
        LineaDocumento linea = lineaOpt.get();
        System.out.printf(
          "Found LineaDocumento - ID: %d, DocumentoID: %d, ProductoID: %d, Cantidad: %d, PrecioUnitario: %.2f%n",
          linea.getId(),
          linea.getIdDocumento(),
          linea.getIdProducto(),
          linea.getCantidad(),
          linea.getPrecioUnitario()
        );
      } else {
        System.out.println("LineaDocumento not found for ID: " + id);
      }
    }
  }

  private static void testExists() throws SQLException {
    System.out.println("\n== testExists ==");

    boolean exists1 = lineaBO.existeLinea(5, 1, 1, 9.99);
    System.out.println("Exists (5, 1, 1, 9.99): " + exists1);
    assert exists1 : "Expected exists check to return true for existing entry.";

    boolean exists2 = lineaBO.existeLinea(6, 1, 1, 49.99);
    System.out.println("Exists (6, 1, 1, 49.99): " + exists2);
    assert exists2 : "Expected exists check to return true for existing entry.";
  }

  private static void testDelete() throws SQLException {
    System.out.println("\n== testDelete ==");

    for (int id : listaIds) {
      int result = lineaBO.eliminar(id);
      System.out.println(
        "Deleted LineaDocumento ID: " + id + ", Result: " + result
      );
      assert result > 0 : "Deletion should return a positive result.";
    }

    listaIds.clear(); // Clear the list after deletion
  }
}
