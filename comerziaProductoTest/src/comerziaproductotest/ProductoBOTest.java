/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziaproductotest;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;

public class ProductoBOTest {

  private static ProductoBO productoBO;
  private static ArrayList<Producto> listaProductoes;

  public static void testProductoBO() {
    System.out.println("\ntestProductoBO");
    productoBO = new ProductoBO();

    ArrayList<Integer> listaId = new ArrayList<>();
    String nombre = "1";
    testProductoBOBuscarPorNombre(nombre);
    //        testProductoBOInsertar(listaId);
    //        testProductoBOListarTodos();
    //        testProductoBOModificar(listaId);
    //        testProductoBOListarTodos();
    //        testProductoBOObtenerPorId(listaId);
    //        testProductoBOEliminar();
  }

  private static void testProductoBOEliminar() {
    System.out.println("\ntestProductoBOEliminar");
    for (Producto producto : listaProductoes) {
      productoBO.eliminar(producto.getIdProducto());
    }
  }

  private static void testProductoBOObtenerPorId(ArrayList<Integer> listaId) {
    System.out.println("\ntestProductoBOObtenerPorId");
    for (Integer id : listaId) {
      Producto producto = productoBO.obtenerPorId(id);
      System.out.println(
        "idProducto: " +
        producto.getIdProducto() +
        " " +
        producto.getNombreProducto() +
        " " +
        producto.getPrecio().toString()
      );
    }
  }

  private static void testProductoBOListarTodos() {
    System.out.println("\ntestProductoBOListarTodos");
    listaProductoes = productoBO.listarTodos();
    for (Producto producto : listaProductoes) {
      System.out.print(producto.getIdProducto().toString());
      System.out.print(", ");
      System.out.print(producto.getNombreProducto());
      System.out.print(", ");
      System.out.print(producto.getPrecio().toString());
      System.out.print(", ");
      System.out.println(producto.getStockMinimo().toString());
    }
  }

  private static void testProductoBOModificar(ArrayList<Integer> listaId) {
    System.out.println("\ntestProductoBOModificar");
    Integer resultado = productoBO.modificar(
      listaId.get(0),
      "producto1",
      100.00,
      80
    );
    resultado = productoBO.modificar(listaId.get(1), "producto2", 200.00, 80);
  }

  private static void testProductoBOInsertar(ArrayList<Integer> listaId) {
    System.out.println("\ntestProductoBOInsertar");
    int resultado;

    resultado = productoBO.insertar("producto1", 100.00, 50);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);

    resultado = productoBO.insertar("producto2", 200.00, 50);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }

  private static void testProductoBOBuscarPorNombre(String nombre) {
    System.out.println("\ntestProductoBOBuscarPorNombre");
    listaProductoes = productoBO.buscarProductos(nombre);
    for (Producto producto : listaProductoes) {
      System.out.print(producto.getIdProducto().toString());
      System.out.print(", ");
      System.out.print(producto.getNombreProducto());
      System.out.print(", ");
      System.out.print(producto.getPrecio().toString());
      System.out.print(", ");
      System.out.println(producto.getStockMinimo().toString());
    }
  }
}
