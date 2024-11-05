/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziaDocumentoTest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.gestioncomercial.bo.DocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;

public class DocumentoBOTest {

  private static DocumentoBO documentoBO;
  private static ArrayList<Documento> listaDocumento;

  public static void testDocumentoBO() {
    System.out.println("\ntestPersonaBO");
    documentoBO = new DocumentoBO();
    ArrayList<Integer> listaId = new ArrayList<>();
    testPersonaBOInsertar(listaId);
    testPersonaBOListarTodos();
    testPersonaBOModificar(listaId);
    testPersonaBOListarTodos();
    testPersonaBOObtenerPorId(listaId);
    testPersonaBOExistePersona();
    testPersonaBOEliminar(listaId);
  }

  private static void testPersonaBOEliminar(ArrayList<Integer> listaId) {
    System.out.println("\ntestPersonaBOEliminar");
    for (Documento documento : listaDocumento) {
      documentoBO.eliminar(documento.getIdDocumento());
    }
  }

  private static void testPersonaBOExistePersona() {
    System.out.println("\ntestPersonaBOExistePersona");
    Boolean existe = documentoBO.existeDocumento(
      1,
      Estado.COTIZACION,
      Tipo.COMPRA,
      1,
      1,
      1
    );
    System.out.println("Existe documento: " + existe);
    existe = documentoBO.existeDocumento(
      1,
      Estado.PAGADO,
      Tipo.FACTURA,
      1,
      1,
      1
    );
    System.out.println("Existe documento: " + existe);
  }

  private static void testPersonaBOObtenerPorId(ArrayList<Integer> listaId) {
    System.out.println("\ntestPersonaBOObtenerPorId");
    for (Integer id : listaId) {
      Documento documento = documentoBO.obtenerPorId(id);
      System.out.println(
        "idDocumento: " +
        documento.getIdDocumento() +
        " " +
        documento.getEstado().toString() +
        " " +
        documento.getTipo().toString()
      );
    }
  }

  private static void testPersonaBOModificar(ArrayList<Integer> listaId) {
    System.out.println("\ntestPersonaBOModificar");
    Integer resultado = documentoBO.modificar(
      listaId.get(0),
      1,
      Estado.COTIZACION,
      Tipo.VENTA,
      1,
      1,
      1
    );
    resultado = documentoBO.modificar(
      listaId.get(1),
      1,
      Estado.ANULADO,
      Tipo.COMPRA,
      1,
      1,
      1
    );
  }

  private static void testPersonaBOListarTodos() {
    System.out.println("\ntestPersonaBOListarTodos");
    listaDocumento = documentoBO.listarTodos();
    for (Documento documento : listaDocumento) {
      System.out.print(documento.getIdDocumento().toString());
      System.out.print(", ");
      System.out.print(documento.getIdEmpresa().toString());
      System.out.print(", ");
      System.out.print(documento.getEstado().toString());
      System.out.print(", ");
      System.out.print(documento.getTipo().toString());
      System.out.print(", ");
      System.out.print(documento.getIdVendedor().toString());
      System.out.print(", ");
      System.out.print(documento.getIdAdministrador().toString());
      System.out.print(", ");
      System.out.print(documento.getIdTrabajadorDeAlmacen().toString());
      System.out.println("");
    }
  }

  private static void testPersonaBOInsertar(ArrayList<Integer> listaId) {
    System.out.println("\ntestPersonaBOInsertar");
    Integer resultado = documentoBO.insertar(
      1,
      Estado.COTIZACION,
      Tipo.COMPRA,
      1,
      1,
      1
    );
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
    resultado = documentoBO.insertar(1, Estado.PAGADO, Tipo.FACTURA, 1, 1, 1);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }
}
