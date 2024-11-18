package pe.edu.pucp.comerzia.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_comercial.bo.DocumentoBO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.bo.LineaDocumentoBO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.Documento;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.LineaDocumento;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.TipoDocumentoEnum;

@WebService(
  serviceName = "GestionComercialWS",
  targetNamespace = "http://services.comerziasoft.pucp.edu.pe"
)
public class GestionComercialWS {

  private final DocumentoBO boDocumento;
  private final LineaDocumentoBO boLineaDocumento;

  public GestionComercialWS() {
    boDocumento = new DocumentoBO();
    boLineaDocumento = new LineaDocumentoBO();
  }

  @WebMethod(operationName = "insertar_documento")
  public Integer insertar_documento(
    @WebParam(name = "idEmpresa") Integer idEmpresa,
    @WebParam(name = "estado") EstadoDocumentoEnum estado,
    @WebParam(name = "tipo") TipoDocumentoEnum tipo,
    @WebParam(name = "idVendedor") Integer idVendedor,
    @WebParam(name = "idAdministrador") Integer idAdministrador,
    @WebParam(name = "idTrabajadorDeAlmacen") Integer idTrabajadorDeAlmacen
  ) {
    try {
      return boDocumento.insertar(
        idEmpresa,
        estado,
        tipo,
        idVendedor,
        idAdministrador,
        idTrabajadorDeAlmacen
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificar_documento")
  public Integer modificar_documento(
    @WebParam(name = "idDocumento") Integer idDocumento,
    @WebParam(name = "idEmpresa") Integer idEmpresa,
    @WebParam(name = "estado") EstadoDocumentoEnum estado,
    @WebParam(name = "tipo") TipoDocumentoEnum tipo,
    @WebParam(name = "idVendedor") Integer idVendedor,
    @WebParam(name = "idAdministrador") Integer idAdministrador,
    @WebParam(name = "idTrabajadorDeAlmacen") Integer idTrabajadorDeAlmacen
  ) {
    try {
      return boDocumento.modificar(
        idDocumento,
        idEmpresa,
        estado,
        tipo,
        idVendedor,
        idAdministrador,
        idTrabajadorDeAlmacen
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "eliminar_documento")
  public Integer eliminar_documento(
    @WebParam(name = "idDocumento") Integer idDocumento
  ) {
    try {
      return boDocumento.eliminar(idDocumento);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "listarDocumentos")
  public ArrayList<Documento> listarTodosDocumentos() {
    try {
      return boDocumento.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerDocumentoPorId")
  public Documento obtenerDocumentoPorId(@WebParam(name = "id") String id) {
    try {
      Optional<Documento> documento = boDocumento.obtenerPorId(
        Integer.valueOf(id)
      );
      return documento.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "listarPorEmpresa")
  public ArrayList<Documento> listarPorEmpresa(
    @WebParam(name = "idEmpresa") String id
  ) {
    try {
      return boDocumento.listarPorEmpresa(Integer.valueOf(id));
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "existeDocumento")
  public boolean existeDocumento(
    @WebParam(name = "idEmpresa") Integer idEmpresa,
    @WebParam(name = "estado") EstadoDocumentoEnum estado,
    @WebParam(name = "tipo") TipoDocumentoEnum tipo,
    @WebParam(name = "idVendedor") Integer idVendedor,
    @WebParam(name = "idAdministrador") Integer idAdministrador,
    @WebParam(name = "idTrabajadorDeAlmacen") Integer idTrabajadorDeAlmacen
  ) {
    try {
      return boDocumento.existeDocumento(
        idEmpresa,
        estado,
        tipo,
        idVendedor,
        idAdministrador,
        idTrabajadorDeAlmacen
      );
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @WebMethod(operationName = "insertar_lineaDocumento")
  public Integer insertar_lineaDocumento(
    @WebParam(name = "idDocumento") Integer idDocumento,
    @WebParam(name = "idProducto") Integer idProducto,
    @WebParam(name = "cantidad") Integer cantidad,
    @WebParam(name = "precioUnitario") Double precioUnitario
  ) {
    try {
      return boLineaDocumento.insertar(
        idDocumento,
        idProducto,
        cantidad,
        precioUnitario
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificar_lineaDocumento")
  public Integer modificar_lineaDocumento(
    @WebParam(name = "idLinea") Integer idLinea,
    @WebParam(name = "idDocumento") Integer idDocumento,
    @WebParam(name = "idProducto") Integer idProducto,
    @WebParam(name = "cantidad") Integer cantidad,
    @WebParam(name = "precioUnitario") Double precioUnitario
  ) {
    try {
      return boLineaDocumento.modificar(
        idLinea,
        idDocumento,
        idProducto,
        cantidad,
        precioUnitario
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "eliminar_lineaDocumento")
  public Integer eliminar_lineaDocumento(
    @WebParam(name = "idLineaDocumento") Integer idLineaDocumento
  ) {
    try {
      return boLineaDocumento.eliminar(idLineaDocumento);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "obtenerLineaDocumentoPorId")
  public LineaDocumento obtenerLineaDocumentoPorId(
    @WebParam(name = "idLineaDocumento") Integer idLineaDocumento
  ) {
    try {
      Optional<LineaDocumento> lineaDocumento = boLineaDocumento.obtenerPorId(
        idLineaDocumento
      );
      return lineaDocumento.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "listarLineasPorDocumento")
  public ArrayList<LineaDocumento> listarLineasPorDocumento(
    @WebParam(name = "idDocumento") Integer idDocumento
  ) {
    try {
      return boLineaDocumento.listarPorDocumento(idDocumento);
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}
