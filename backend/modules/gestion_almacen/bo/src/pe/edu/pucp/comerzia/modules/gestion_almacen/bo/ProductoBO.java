package pe.edu.pucp.comerzia.modules.gestion_almacen.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_almacen.dao.ProductoDAO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;

public class ProductoBO {

  private ProductoDAO productoDAO;

  public ProductoBO() {
    this.productoDAO = new ProductoDAO();
  }

  public Integer insertar(String nombre, Double precio, Integer stockMinimo)
    throws SQLException {
    Producto producto = new Producto();

    producto.setNombre(nombre);
    producto.setPrecio(precio);
    producto.setStockMinimo(stockMinimo);

    return productoDAO.insert(producto);
  }

  public Integer modificar(
    Integer id,
    String nombre,
    Double precio,
    Integer stockMinimo
  ) throws SQLException {
    Producto producto = new Producto();

    producto.setId(id);
    producto.setNombre(nombre);
    producto.setPrecio(precio);
    producto.setStockMinimo(stockMinimo);

    return productoDAO.update(id, producto);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return productoDAO.delete(id);
  }

  public ArrayList<Producto> listarTodos() throws SQLException {
    return new ArrayList<>(productoDAO.findAll());
  }

  public Optional<Producto> obtenerPorId(Integer id) throws SQLException {
    return this.productoDAO.findById(id);
  }

  public ArrayList<Producto> buscarProductos(String nombre)
    throws SQLException {
    return new ArrayList<>(
      productoDAO.query().where(ProductoDAO.nombre.eq(nombre)).list()
    );
  }
}
