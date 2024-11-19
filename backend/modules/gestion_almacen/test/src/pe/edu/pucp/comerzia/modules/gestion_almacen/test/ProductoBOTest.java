package pe.edu.pucp.comerzia.modules.gestion_almacen.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;

public class ProductoBOTest {

  private static ProductoBO productoBO;
  private static ArrayList<Producto> listaProductos;

  public static void testProductoBO() throws SQLException {
    System.out.println("\ntestProductoBO");
    productoBO = new ProductoBO();

    ArrayList<Integer> listaId = new ArrayList<>();
    String nombre = "1";
    testProductoBOBuscarPorNombre(nombre);
  }

  private static void testProductoBOEliminar() throws SQLException {
    System.out.println("\ntestProductoBOEliminar");
    for (Producto producto : listaProductos) {
      productoBO.eliminar(producto.getId());
    }
  }

  private static void testProductoBOObtenerPorId(ArrayList<Integer> listaId)
    throws SQLException {
    System.out.println("\ntestProductoBOObtenerPorId");
    for (Integer id : listaId) {
      Optional<Producto> producto = productoBO.obtenerPorId(id);
      if (producto.isPresent()) {
        System.out.println(
          "id: " +
          producto.get().getId() +
          " " +
          producto.get().getNombre() +
          " " +
          producto.get().getPrecio().toString()
        );
      } else {
        System.out.println("No se encontró el producto con id: " + id);
      }
    }
  }

  private static void testProductoBOListarTodos() throws SQLException {
    System.out.println("\ntestProductoBOListarTodos");
    listaProductos = productoBO.listarTodos();
    for (Producto producto : listaProductos) {
      System.out.print(producto.getId().toString());
      System.out.print(", ");
      System.out.print(producto.getNombre());
      System.out.print(", ");
      System.out.print(producto.getPrecio().toString());
      System.out.print(", ");
      System.out.println(producto.getStockMinimo().toString());
    }
  }

  private static void testProductoBOModificar(ArrayList<Integer> listaId)
    throws SQLException {
    System.out.println("\ntestProductoBOModificar");
    Integer resultado = productoBO.modificar(
      listaId.get(0),
      "producto1",
      100.00,
      80
    );
    resultado = productoBO.modificar(listaId.get(1), "producto2", 200.00, 80);
  }

  private static void testProductoBOInsertar(ArrayList<Integer> listaId)
    throws SQLException {
    System.out.println("\ntestProductoBOInsertar");
    int resultado;

    resultado = productoBO.insertar("producto1", 100.00, 50);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);

    resultado = productoBO.insertar("producto2", 200.00, 50);
    System.out.println("Llave primaria insertada: " + resultado);
    listaId.add(resultado);
  }

  private static void testProductoBOBuscarPorNombre(String nombre)
    throws SQLException {
    System.out.println("\ntestProductoBOBuscarPorNombre");
    listaProductos = productoBO.buscarProductosPorNombre(nombre);
    for (Producto producto : listaProductos) {
      System.out.print(producto.getId().toString());
      System.out.print(", ");
      System.out.print(producto.getNombre());
      System.out.print(", ");
      System.out.print(producto.getPrecio().toString());
      System.out.print(", ");
      System.out.println(producto.getStockMinimo().toString());
    }
  }
}
