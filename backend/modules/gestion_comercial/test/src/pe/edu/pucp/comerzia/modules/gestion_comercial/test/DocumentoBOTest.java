package pe.edu.pucp.comerzia.modules.gestion_comercial.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_comercial.bo.DocumentoBO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.Documento;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.TipoDocumentoEnum;

public class DocumentoBOTest {

  private static DocumentoBO documentoBO = new DocumentoBO();
  private static List<Documento> listaDocumento = new ArrayList<>();
  private static List<Integer> listaIds = new ArrayList<>();

  public static void main(String[] args) throws SQLException {
    System.out.println("Running DocumentoBO Tests");

    testInsert();
    testListAll();
    testModify();
    testListAll();
    testGetById();
    testDocumentExists();
    testDelete();
    testListAll();

    System.out.println("All DocumentoBO Tests Completed");
  }

  private static void testInsert() throws SQLException {
    System.out.println("\n== testInsert ==");

    insertDocumento(
      1,
      EstadoDocumentoEnum.COTIZACION,
      TipoDocumentoEnum.COMPRA,
      1,
      1,
      1
    );
    insertDocumento(
      1,
      EstadoDocumentoEnum.PAGADO,
      TipoDocumentoEnum.FACTURA,
      1,
      1,
      1
    );

    assert listaIds.size() == 2 : "Expected 2 documentos inserted.";
  }

  private static void insertDocumento(
    int id,
    EstadoDocumentoEnum estado,
    TipoDocumentoEnum tipo,
    int idVendedor,
    int idAdmin,
    int idAlmacen
  ) throws SQLException {
    int documentoId = documentoBO.insertar(
      id,
      estado,
      tipo,
      idVendedor,
      idAdmin,
      idAlmacen
    );
    System.out.println("Inserted Documento ID: " + documentoId);
    listaIds.add(documentoId);
  }

  private static void testListAll() throws SQLException {
    System.out.println("\n== testListAll ==");

    listaDocumento = documentoBO.listarTodos();
    for (Documento documento : listaDocumento) {
      System.out.printf(
        "ID: %d, Estado: %s, Tipo: %s, Vendedor: %d, Administrador: %d, Almacen: %d%n",
        documento.getId(),
        documento.getEstado(),
        documento.getTipo(),
        documento.getIdVendedor(),
        documento.getIdAdministrador(),
        documento.getIdTrabajadorDeAlmacen()
      );
    }

    assert !listaDocumento.isEmpty() : "Documentos list should not be empty.";
  }

  private static void testModify() throws SQLException {
    System.out.println("\n== testModify ==");

    if (listaIds.size() >= 2) {
      modifyDocumento(
        listaIds.get(0),
        1,
        EstadoDocumentoEnum.COTIZACION,
        TipoDocumentoEnum.VENTA,
        1,
        1,
        1
      );
      modifyDocumento(
        listaIds.get(1),
        1,
        EstadoDocumentoEnum.ANULADO,
        TipoDocumentoEnum.COMPRA,
        1,
        1,
        1
      );
    }
  }

  private static void modifyDocumento(
    int id,
    int idUser,
    EstadoDocumentoEnum estado,
    TipoDocumentoEnum tipo,
    int idVendedor,
    int idAdmin,
    int idAlmacen
  ) throws SQLException {
    int result = documentoBO.modificar(
      id,
      idUser,
      estado,
      tipo,
      idVendedor,
      idAdmin,
      idAlmacen
    );
    System.out.println("Modified Documento ID: " + id + ", Result: " + result);
    assert result > 0 : "Modification should return a positive result.";
  }

  private static void testGetById() throws SQLException {
    System.out.println("\n== testGetById ==");

    for (int id : listaIds) {
      Optional<Documento> documentoOpt = documentoBO.obtenerPorId(id);
      if (documentoOpt.isPresent()) {
        Documento documento = documentoOpt.get();
        System.out.printf(
          "Found Documento - ID: %d, Estado: %s, Tipo: %s%n",
          documento.getId(),
          documento.getEstado(),
          documento.getTipo()
        );
      } else {
        System.out.println("Documento not found for ID: " + id);
      }
    }
  }

  private static void testDocumentExists() throws SQLException {
    System.out.println("\n== testDocumentExists ==");

    boolean exists = documentoBO.buscarDocumento(
      1,
      EstadoDocumentoEnum.COTIZACION,
      TipoDocumentoEnum.VENTA,
      1,
      1,
      1
    );
    System.out.println("Documento exists (COTIZACION, VENTA): " + exists);

    exists = documentoBO.buscarDocumento(
      1,
      EstadoDocumentoEnum.PAGADO,
      TipoDocumentoEnum.FACTURA,
      1,
      1,
      1
    );
    System.out.println("Documento exists (PAGADO, FACTURA): " + exists);
  }

  private static void testDelete() throws SQLException {
    System.out.println("\n== testDelete ==");

    for (int id : listaIds) {
      int result = documentoBO.eliminar(id);
      System.out.println("Deleted Documento ID: " + id + ", Result: " + result);
      assert result > 0 : "Deletion should return a positive result.";
    }

    listaIds.clear(); // Clear the list after deletion
  }
}
