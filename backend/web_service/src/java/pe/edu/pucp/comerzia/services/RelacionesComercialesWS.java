package pe.edu.pucp.comerzia.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_comercial.bo.DocumentoBO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.Documento;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.bo.ClienteBO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.bo.ProveedorBO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.bo.RepresentanteBO;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Cliente;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Proveedor;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Representante;

@WebService(
  serviceName = "RelacionesComercialesWS",
  targetNamespace = "http://services.comerziasoft.pucp.edu.pe"
)
public class RelacionesComercialesWS {

  private final ProveedorBO boProveedor;
  private final ClienteBO boCliente;
  private final DocumentoBO boDocumento;
  private final RepresentanteBO boRepresentante;

  public RelacionesComercialesWS() {
    boProveedor = new ProveedorBO();
    boCliente = new ClienteBO();
    boDocumento = new DocumentoBO();
    boRepresentante = new RepresentanteBO();
  }

  @WebMethod(operationName = "listarClientes")
  public ArrayList<Cliente> listarClientes() {
    try {
      return this.boCliente.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarClientesParaIndex")
  public ArrayList<Cliente> listarClientesParaIndex() {
    try {
      return this.boCliente.listarParaIndex();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarProveedores")
  public ArrayList<Proveedor> listarProveedores() {
    try {
      return this.boProveedor.listarTodos();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarProveedoresParaIndex")
  public ArrayList<Proveedor> listarProveedoresParaIndex() {
    try {
      return this.boProveedor.listarParaIndex();
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "obtenerPorIdCliente")
  public Cliente obtenerClientePorId(@WebParam(name = "id") String id) {
    try {
      Optional<Cliente> cliente = boCliente.obtenerPorId(Integer.valueOf(id));
      return cliente.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "obtenerPorIdProveedor")
  public Proveedor obtenerProveedorPorId(@WebParam(name = "id") String id) {
    try {
      Optional<Proveedor> proveedor = boProveedor.obtenerPorId(
        Integer.valueOf(id)
      );
      return proveedor.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "obtenerPorIdRepresentante")
  public Representante obtenerRepresentantePorId(
    @WebParam(name = "id") String id
  ) {
    try {
      Optional<Representante> representante = boRepresentante.obtenerPorId(
        Integer.valueOf(id)
      );
      return representante.orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod(operationName = "listarRepresentantesPorEmpresa")
  public ArrayList<Representante> listarRepresentantes(
    @WebParam(name = "id") String id
  ) {
    try {
      return this.boRepresentante.listarPorEmpresa(Integer.valueOf(id));
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarRepresentantesPorNombre")
  public ArrayList<Representante> listarRepresentantesNombre(
    @WebParam(name = "nombre") String nombre
  ) {
    try {
      return this.boRepresentante.listarPorNombre(nombre);
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "listarDocumentosPorEmpresa")
  public ArrayList<Documento> listarDocumentos(
    @WebParam(name = "id") String id
  ) {
    try {
      return this.boDocumento.listarPorEmpresa(Integer.valueOf(id));
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @WebMethod(operationName = "eliminarRepresentante")
  public Integer eliminarRepresentante(@WebParam(name = "id") String id) {
    try {
      return this.boRepresentante.eliminar(Integer.valueOf(id));
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificarCliente")
  public Integer modificarCliente(
    @WebParam(name = "idEmpresa") Integer idEmpresa,
    @WebParam(name = "nombre") String nombre,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "email") String email,
    @WebParam(name = "tipoIndustria") String tipoIndustria,
    @WebParam(name = "fechaRegistro") Date fechaRegistro,
    @WebParam(name = "fechaUltimaCompra") Date fechaUltimaCompra
  ) {
    try {
      return boCliente.modificar(
        idEmpresa,
        nombre,
        direccion,
        telefono,
        email,
        tipoIndustria,
        fechaRegistro,
        fechaUltimaCompra
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificarProveedor")
  public Integer modificarProveedor(
    @WebParam(name = "idEmpresa") Integer idEmpresa,
    @WebParam(name = "nombre") String nombre,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "email") String email,
    @WebParam(name = "tipoIndustria") String tipoIndustria,
    @WebParam(name = "fechaAfiliacion") Date fecha_afiliacion,
    @WebParam(name = "RUC") String RUC,
    @WebParam(name = "razonSocial") String razonSocial,
    @WebParam(name = "calificacion") Double calificacion,
    @WebParam(name = "pais") String pais
  ) {
    try {
      return boProveedor.modificar(
        idEmpresa,
        nombre,
        direccion,
        telefono,
        email,
        tipoIndustria,
        fecha_afiliacion,
        RUC,
        razonSocial,
        calificacion,
        pais
      );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @WebMethod(operationName = "modificarRepresentante")
  public Integer modificarRepresentante(
    @WebParam(name = "idPersona") Integer idPersona,
    @WebParam(name = "dni") String dni,
    @WebParam(name = "nombreCompleto") String nombreCompleto,
    @WebParam(name = "telefono") String telefono,
    @WebParam(name = "correo") String correo,
    @WebParam(name = "direccion") String direccion,
    @WebParam(name = "idEmpresa") Integer idEmpresa
  ) {
    try {
      return this.boRepresentante.modificar(
          idPersona,
          dni,
          nombreCompleto,
          telefono,
          correo,
          direccion,
          idEmpresa
        );
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
