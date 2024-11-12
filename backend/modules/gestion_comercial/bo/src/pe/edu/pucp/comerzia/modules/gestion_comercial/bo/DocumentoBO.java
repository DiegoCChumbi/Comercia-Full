package pe.edu.pucp.comerzia.modules.gestion_comercial.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.DocumentoDAO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.Documento;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.TipoDocumentoEnum;

public class DocumentoBO {

  private DocumentoDAO documentoDAO;

  public DocumentoBO() {
    this.documentoDAO = new DocumentoDAO();
  }

  public Integer insertar(
    Integer idEmpresa,
    EstadoDocumentoEnum estado,
    TipoDocumentoEnum tipo,
    Integer idVendedor,
    Integer idAdministrador,
    Integer idTrabajadorDeAlmacen
  ) throws SQLException {
    Documento documento = new Documento();

    documento.setIdEmpresa(idEmpresa);
    documento.setEstado(estado);
    documento.setTipo(tipo);
    documento.setIdVendedor(idVendedor);
    documento.setIdAdministrador(idAdministrador);
    documento.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);

    return documentoDAO.insert(documento);
  }

  public Integer modificar(
    Integer id,
    Integer idEmpresa,
    EstadoDocumentoEnum estado,
    TipoDocumentoEnum tipo,
    Integer idVendedor,
    Integer idAdministrador,
    Integer idTrabajadorDeAlmacen
  ) throws SQLException {
    Documento documento = new Documento();

    documento.setId(id);
    documento.setIdEmpresa(idEmpresa);
    documento.setEstado(estado);
    documento.setTipo(tipo);
    documento.setIdVendedor(idVendedor);
    documento.setIdAdministrador(idAdministrador);
    documento.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);

    return documentoDAO.update(id, documento);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return documentoDAO.delete(id);
  }

  public Optional<Documento> obtenerPorId(Integer id) throws SQLException {
    return this.documentoDAO.findById(id);
  }

  public ArrayList<Documento> listarTodos() throws SQLException {
    return new ArrayList<>(this.documentoDAO.findAll());
  }

  public Boolean buscarDocumento(
    int idEmpresa,
    EstadoDocumentoEnum cotizacion,
    TipoDocumentoEnum compra,
    int idVendedor,
    int idAdministrador,
    int idTrabajadorDeAlmacen
  ) throws SQLException {
    return (
      documentoDAO
        .query()
        .whereAll(
          DocumentoDAO.idEmpresa.eq(idEmpresa),
          DocumentoDAO.estado.eq(cotizacion),
          DocumentoDAO.tipo.eq(compra),
          DocumentoDAO.idVendedor.eq(idVendedor),
          DocumentoDAO.idAdministrador.eq(idAdministrador),
          DocumentoDAO.idTrabajadorDeAlmacen.eq(idTrabajadorDeAlmacen)
        )
        .list()
        .size() >
      0
    );
  }

  public Boolean existeDocumentoConTrabajadorDeAlmacen(
    int idTrabajadorDeAlmacen
  ) throws SQLException {
    return (
      documentoDAO
        .query()
        .where(DocumentoDAO.idTrabajadorDeAlmacen.eq(idTrabajadorDeAlmacen))
        .list()
        .size() >
      0
    );
  }
}
