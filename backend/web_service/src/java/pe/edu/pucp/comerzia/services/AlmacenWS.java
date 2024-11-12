package pe.edu.pucp.comerzia.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.AlmacenBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.ProductoAlmacenadoBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Almacen;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.Producto;
import pe.edu.pucp.comerzia.modules.gestion_almacen.model.ProductoAlmacenado;

@WebService(
  serviceName = "AlmacenWS",
  targetNamespace = "http://services.comerziasoft.pucp.edu.pe"
)
public class AlmacenWS {

  private final AlmacenBO daoAlmacen;
  private final ProductoAlmacenadoBO daoProductoAlmacenado;
  private final ProductoBO daoProducto;

  public AlmacenWS() {
    daoAlmacen = new AlmacenBO();
    daoProductoAlmacenado = new ProductoAlmacenadoBO();
    daoProducto = new ProductoBO();
  }

  @WebMethod(operationName = "listarAlmacenes")
  public ArrayList<Almacen> listarAlmacenes() {
    try {
      return daoAlmacen.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerPorId")
  public Almacen obtenerPorId(@WebParam(name = "id") String id) {
    try {
      Optional<Almacen> almacen = daoAlmacen.obtenerPorId(Integer.valueOf(id));
      return almacen.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // Ahora los metodos de los productos almacenados.
  @WebMethod(operationName = "listarPorAlmacen")
  public ArrayList<ProductoAlmacenado> listarPorAlmacen(
    @WebParam(name = "idAlmacen") String id
  ) {
    try {
      return daoProductoAlmacenado.listarPorAlmacen(Integer.valueOf(id));
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "buscarPorNombre")
  public ArrayList<Producto> buscarProductos(
    @WebParam(name = "nombreProd") String nombre
  ) {
    try {
      return daoProducto.buscarProductos(nombre);
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "buscarProductoPorId")
  public Producto obtenerPorIdProd(
    @WebParam(name = "idProd") Integer idProducto
  ) {
    try {
      Optional<Producto> producto = daoProducto.obtenerPorId(idProducto);
      return producto.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "insertarProductoAlmacenado")
  public Integer insertar(
    @WebParam(name = "idAlmacen") Integer idAlmacen,
    @WebParam(name = "fechaAlmacenado") Date fechaAlmacenado,
    @WebParam(name = "stock") Integer stockActual,
    @WebParam(name = "idProd") Integer idProducto
  ) {
    try {
      return daoProductoAlmacenado.insertar(
        idAlmacen,
        fechaAlmacenado,
        stockActual,
        idProducto
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
