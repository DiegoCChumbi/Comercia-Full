/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziaDocumentoTest;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.bo.DocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.bo.LineaDocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;

/**
 *
 * @author chumbi
 */
public class LineaDocumentoBOTest {

  private static LineaDocumentoBO lineaBO;
  private static ArrayList<LineaDocumento> listaLineas;

  public static void testLineaDocumentoBO() {
    System.out.println("\ntestPersonaBO");
    lineaBO = new LineaDocumentoBO();
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
    for (LineaDocumento linea : listaLineas) {
      lineaBO.eliminar(linea.getIdLinea());
    }
  }

  private static void testPersonaBOExistePersona() {
    System.out.println("\ntestPersonaBOExistePersona");
    Boolean existe = lineaBO.existeLinea(5, 1, 1, 9.99);
    System.out.println("Existe linea: " + existe);
    existe = lineaBO.existeLinea(6, 1, 1, 49.99);
    System.out.println("Existe linea: " + existe);
  }

  private static void testPersonaBOObtenerPorId(ArrayList<Integer> listaId) {
    System.out.println("\ntestPersonaBOObtenerPorId");
    for (Integer id : listaId) {
      LineaDocumento linea = lineaBO.obtenerPorId(id);
      System.out.println("idLinea: " + linea.getIdLinea() + " ");
    }
  }

  private static void testPersonaBOModificar(ArrayList<Integer> listaId) {
    System.out.println("\ntestPersonaBOModificar");
    Integer resultado = lineaBO.modificar(listaId.get(0), 5, 1, 1, 20.00);
    resultado = lineaBO.modificar(listaId.get(1), 6, 1, 1, 100.00);
  }

  private static void testPersonaBOListarTodos() {
    System.out.println("\ntestPersonaBOListarTodos");
    listaLineas = lineaBO.listarTodos();
    for (LineaDocumento linea : listaLineas) {
      System.out.print(linea.getIdLinea().toString());
      System.out.print(", ");
      System.out.print(linea.getIdDocumento().toString());
      System.out.print(", ");
      System.out.print(linea.getIdProducto().toString());
      System.out.print(", ");
      System.out.print(linea.getCantidad().toString());
      System.out.print(", ");
      System.out.print(linea.getPrecioUnitario().toString());
      System.out.println("");
    }
  }

  private static void testPersonaBOInsertar(ArrayList<Integer> listaId) {
    System.out.println("\ntestPersonaBOInsertar");
    Integer resultado = lineaBO.insertar(5, 1, 1, 9.99);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
    resultado = lineaBO.insertar(6, 1, 1, 49.99);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }
}
