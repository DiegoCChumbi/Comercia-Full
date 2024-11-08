package pe.edu.pucp.comerzia.GestionDeAlmacen.bo;

import java.util.ArrayList;

import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.ProductoDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl.ProductoDAOImpl;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;

public class ProductoBO {
    private ProductoDAO productoDAO;

    public ProductoBO() {
        this.productoDAO = new ProductoDAOImpl();
    }

    public Integer insertar(String nombreProducto, Double precio, Integer stockMinimo) {
        Producto producto = new Producto(null,nombreProducto,precio,stockMinimo);
        return productoDAO.insertar(producto);
    }

    public Integer modificar(Integer idProducto, String nombreProducto, Double precio, Integer stockMinimo) {
        Producto producto = new Producto(idProducto,nombreProducto,precio,stockMinimo);
        return productoDAO.modificar(producto);
    }

    public Integer eliminar(Integer idProducto) {
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        return productoDAO.eliminar(producto);
    }

    public ArrayList<Producto> listarTodos() {
        return this.productoDAO.listarTodos();
    }
    
    public ArrayList<Producto> buscarProductos(String nombreProducto) {
        return this.productoDAO.buscarProductos(nombreProducto);
    }

    public Producto obtenerPorId(Integer idProducto) {
        return this.productoDAO.obtenerPorId(idProducto);
    }
}