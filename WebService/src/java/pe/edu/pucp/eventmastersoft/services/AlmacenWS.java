package pe.edu.pucp.eventmastersoft.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.AlmacenBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoAlmacenadoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;

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
    return daoAlmacen.listarTodos();
  } // Funciona correctamente.

  @WebMethod(operationName = "obtenerPorId")
  public Almacen obtenerPorId(@WebParam(name = "id") String id) {
    return daoAlmacen.obtenerPorId(Integer.valueOf(id));
  } // Funciona correctamente.

  // Ahora los metodos de los productos almacenados.
  @WebMethod(operationName = "listarPorAlmacen")
  public ArrayList<ProductoAlmacenado> listarPorAlmacen(
    @WebParam(name = "idAlmacen") String id
  ) {
    return daoProductoAlmacenado.listarPorAlmacen(Integer.valueOf(id));
  }

  @WebMethod(operationName = "buscarPorNombre")
  public ArrayList<Producto> buscarProductos(
    @WebParam(name = "nombreProd") String nombre
  ) {
    return daoProducto.buscarProductos(nombre);
  }

  @WebMethod(operationName = "buscarProductoPorId")
  public Producto obtenerPorIdProd(
    @WebParam(name = "idProd") Integer idProducto
  ) {
    return daoProducto.obtenerPorId(idProducto);
  }

  @WebMethod(operationName = "insertarProductoAlmacenado")
  public Integer insertar(
    @WebParam(name = "idAlmacen") Integer idAlmacen,
    @WebParam(name = "fechaAlmacenado") Date fechaAlmacenado,
    @WebParam(name = "stock") Integer stockActual,
    @WebParam(name = "idProd") Integer idProducto
  ) {
    return daoProductoAlmacenado.insertar(
      idAlmacen,
      fechaAlmacenado,
      stockActual,
      idProducto
    );
  }
}
