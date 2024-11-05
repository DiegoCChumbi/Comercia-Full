/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziaproductoAlmacenadotest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoAlmacenadoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;

public class ProductoAlmacenadoBOTest {

  private static ProductoAlmacenadoBO productoAlmacenadoBO;
  private static ArrayList<ProductoAlmacenado> listaProductoAlmacenados;

  public static void testProductoAlmacenadoBO() {
    System.out.println("\ntestProductoAlmacenadoBO");
    productoAlmacenadoBO = new ProductoAlmacenadoBO();

    ArrayList<Integer> listaId = new ArrayList<>();
    testProductoAlmacenadoBOInsertar(listaId);
    testProductoAlmacenadoBOListarTodos();
    Integer idAlmacen = 22;
    testProductoAlmacenadoBOListarPorAlmacen(idAlmacen);
    //        testProductoAlmacenadoBOModificar(listaId);
    //        testProductoAlmacenadoBOListarTodos();
    //        testProductoAlmacenadoBOObtenerPorId(listaId);
    //        testProductoAlmacenadoBOEliminar();
  }

  private static void testProductoAlmacenadoBOEliminar() {
    System.out.println("\ntestProductoAlmacenadoBOEliminar");
    for (ProductoAlmacenado productoAlmacenado : listaProductoAlmacenados) {
      productoAlmacenadoBO.eliminar(
        productoAlmacenado.getIdProductoAlmacenado()
      );
    }
  }

  private static void testProductoAlmacenadoBOObtenerPorId(
    ArrayList<Integer> listaId
  ) {
    System.out.println("\ntestProductoAlmacenadoBOObtenerPorId");
    for (Integer id : listaId) {
      ProductoAlmacenado productoAlmacenado = productoAlmacenadoBO.obtenerPorId(
        id
      );
      System.out.println(
        "idProductoAlmacenado: " +
        productoAlmacenado.getIdProductoAlmacenado() +
        " " +
        productoAlmacenado.getStockActual() +
        " " +
        productoAlmacenado.getIdProducto() +
        " " +
        productoAlmacenado.getIdAlmacen()
      );
    }
  }

  private static void testProductoAlmacenadoBOListarTodos() {
    System.out.println("\ntestProductoAlmacenadoBOListarTodos");
    listaProductoAlmacenados = productoAlmacenadoBO.listarTodos();
    for (ProductoAlmacenado productoAlmacenado : listaProductoAlmacenados) {
      System.out.print(productoAlmacenado.getIdProductoAlmacenado().toString());
      System.out.print(", ");
      System.out.print(productoAlmacenado.getStockActual());
      System.out.print(", ");
      System.out.print(productoAlmacenado.getIdProducto());
      System.out.print(", ");
      System.out.println(productoAlmacenado.getIdAlmacen());
    }
  }

  private static void testProductoAlmacenadoBOModificar(
    ArrayList<Integer> listaId
  ) {
    System.out.println("\ntestProductoAlmacenadoBOModificar");
    Date fechaModificacion = new Date();
    Integer resultado = productoAlmacenadoBO.modificar(
      listaId.get(0),
      22,
      fechaModificacion,
      10,
      11
    );
    resultado = productoAlmacenadoBO.modificar(
      listaId.get(1),
      22,
      fechaModificacion,
      15,
      12
    );
  }

  private static void testProductoAlmacenadoBOInsertar(
    ArrayList<Integer> listaId
  ) {
    System.out.println("\ntestProductoAlmacenadoBOInsertar");
    int resultado;
    Date fechaCreacion = new Date();

    resultado = productoAlmacenadoBO.insertar(22, fechaCreacion, 20, 11);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);

    resultado = productoAlmacenadoBO.insertar(22, fechaCreacion, 20, 12);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }

  private static void testProductoAlmacenadoBOListarPorAlmacen(
    Integer idAlmacen
  ) {
    System.out.println("\ntestProductoAlmacenadoBOListarPorAlmacen");
    listaProductoAlmacenados = productoAlmacenadoBO.listarPorAlmacen(idAlmacen);
    for (ProductoAlmacenado productoAlmacenado : listaProductoAlmacenados) {
      System.out.print(productoAlmacenado.getIdProductoAlmacenado().toString());
      System.out.print(", ");
      System.out.print(productoAlmacenado.getStockActual());
      System.out.print(", ");
      System.out.print(productoAlmacenado.getIdProducto());
      System.out.print(", ");
      System.out.println(productoAlmacenado.getIdAlmacen());
    }
  }
}
