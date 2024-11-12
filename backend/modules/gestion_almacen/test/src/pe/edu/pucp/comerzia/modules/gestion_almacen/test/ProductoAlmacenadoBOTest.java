package pe.edu.pucp.comerzia.modules.gestion_almacen.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.ProductoAlmacenadoBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.ProductoAlmacenado;

public class ProductoAlmacenadoBOTest {

  private static ProductoAlmacenadoBO productoAlmacenadoBO;
  private static ArrayList<ProductoAlmacenado> listaProductoAlmacenados;

  public static void testProductoAlmacenadoBO() throws SQLException {
    System.out.println("\ntestProductoAlmacenadoBO");
    productoAlmacenadoBO = new ProductoAlmacenadoBO();

    ArrayList<Integer> listaId = new ArrayList<>();
    testProductoAlmacenadoBOInsertar(listaId);
    testProductoAlmacenadoBOListarTodos();
    Integer idAlmacen = 22;
    testProductoAlmacenadoBOListarPorAlmacen(idAlmacen);
  }

  private static void testProductoAlmacenadoBOEliminar() throws SQLException {
    System.out.println("\ntestProductoAlmacenadoBOEliminar");
    for (ProductoAlmacenado productoAlmacenado : listaProductoAlmacenados) {
      productoAlmacenadoBO.eliminar(productoAlmacenado.getId());
    }
  }

  private static void testProductoAlmacenadoBOObtenerPorId(
    ArrayList<Integer> listaId
  ) throws SQLException {
    System.out.println("\ntestProductoAlmacenadoBOObtenerPorId");
    for (Integer id : listaId) {
      Optional<ProductoAlmacenado> productoAlmacenado =
        productoAlmacenadoBO.obtenerPorId(id);
      if (productoAlmacenado.isPresent()) {
        System.out.println(
          "idProductoAlmacenado: " +
          productoAlmacenado.get().getId() +
          " " +
          productoAlmacenado.get().getStockActual() +
          " " +
          productoAlmacenado.get().getIdProducto() +
          " " +
          productoAlmacenado.get().getIdAlmacen()
        );
      } else {
        System.out.println(
          "No se encontró el producto almacenado con id: " + id
        );
      }
    }
  }

  private static void testProductoAlmacenadoBOListarTodos()
    throws SQLException {
    System.out.println("\ntestProductoAlmacenadoBOListarTodos");
    listaProductoAlmacenados = productoAlmacenadoBO.listarTodos();
    for (ProductoAlmacenado productoAlmacenado : listaProductoAlmacenados) {
      System.out.print(productoAlmacenado.getId().toString());
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
  ) throws SQLException {
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
  ) throws SQLException {
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
  ) throws SQLException {
    System.out.println("\ntestProductoAlmacenadoBOListarPorAlmacen");
    listaProductoAlmacenados = productoAlmacenadoBO.listarPorAlmacen(idAlmacen);
    for (ProductoAlmacenado productoAlmacenado : listaProductoAlmacenados) {
      System.out.print(productoAlmacenado.getId().toString());
      System.out.print(", ");
      System.out.print(productoAlmacenado.getStockActual());
      System.out.print(", ");
      System.out.print(productoAlmacenado.getIdProducto());
      System.out.print(", ");
      System.out.println(productoAlmacenado.getIdAlmacen());
    }
  }
}
