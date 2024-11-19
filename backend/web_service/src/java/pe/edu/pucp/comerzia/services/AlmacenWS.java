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
  targetNamespace = "http://services.comerzia.pucp.edu.pe"
)
public class AlmacenWS {

  private final AlmacenBO boAlmacen;
  private final ProductoAlmacenadoBO boProductoAlmacenado;
  private final ProductoBO boProducto;

  public AlmacenWS() {
    boAlmacen = new AlmacenBO();
    boProductoAlmacenado = new ProductoAlmacenadoBO();
    boProducto = new ProductoBO();
  }

  @WebMethod(operationName = "insertar_almacen")
  public Integer insertar_almacen(
    @WebParam(name = "nombre") String nombre,
    @WebParam(name = "estado") String estado,
    @WebParam(name = "descripcion") String descripcion
  ) {
    try {
      return boAlmacen.insertar(nombre, estado, descripcion);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificar_almacen")
  public Integer modificar_almacen(
    @WebParam(name = "idAlmacen") Integer idAlmacen,
    @WebParam(name = "nombre") String nombre,
    @WebParam(name = "estado") String estado,
    @WebParam(name = "descripcion") String descripcion
  ) {
    try {
      return boAlmacen.modificar(idAlmacen, nombre, estado, descripcion);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "eliminar_almacen")
  public Integer eliminar_almacen(
    @WebParam(name = "idAlmacen") Integer idAlmacen
  ) {
    try {
      return boAlmacen.eliminar(idAlmacen);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "insertar_producto")
  public Integer insertar_producto(
    @WebParam(name = "nombreProducto") String nombreProducto,
    @WebParam(name = "precio") Double precio,
    @WebParam(name = "stockMinimo") Integer stockMinimo
  ) {
    try {
      return boProducto.insertar(nombreProducto, precio, stockMinimo);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificar_producto")
  public Integer modificar_producto(
    @WebParam(name = "idProducto") Integer idProducto,
    @WebParam(name = "nombreProducto") String nombreProducto,
    @WebParam(name = "precio") Double precio,
    @WebParam(name = "stockMinimo") Integer stockMinimo
  ) {
    try {
      return boProducto.modificar(
        idProducto,
        nombreProducto,
        precio,
        stockMinimo
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "eliminar_producto")
  public Integer eliminar_producto(
    @WebParam(name = "idProducto") Integer idProducto
  ) {
    try {
      return boProducto.eliminar(idProducto);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "listarAlmacenes")
  public ArrayList<Almacen> listarAlmacenes() {
    try {
      return boAlmacen.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarAlmacenesParaIndex")
  public ArrayList<Almacen> listarAlmacenesParaIndex() {
    try {
      return boAlmacen.listarParaIndex();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarProductoParaIndex")
  public ArrayList<Producto> listarProductoParaIndex() {
    try {
      return boProducto.listarParaIndex();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerPorId")
  public Almacen obtenerPorId(@WebParam(name = "id") String id) {
    try {
      Optional<Almacen> almacen = boAlmacen.obtenerPorId(Integer.valueOf(id));
      return almacen.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "listarPorAlmacen")
  public ArrayList<ProductoAlmacenado> listarPorAlmacen(
    @WebParam(name = "idAlmacen") String id
  ) {
    try {
      return boProductoAlmacenado.listarPorAlmacen(Integer.valueOf(id));
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "buscarPorNombre")
  public ArrayList<Producto> buscarProductosPorNombre(
    @WebParam(name = "nombreProd") String nombre
  ) {
    try {
      return boProducto.buscarProductosPorNombre(nombre);
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
      Optional<Producto> producto = boProducto.obtenerPorId(idProducto);
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
      return boProductoAlmacenado.insertar(
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

  @WebMethod(operationName = "eliminarProductoAlmacenado")
  public Integer eliminarPorIdProductoAlmacenado(
    @WebParam(name = "idProductoAlmacenado") Integer idProdAlm
  ) {
    try {
      return boProductoAlmacenado.eliminar(idProdAlm);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
