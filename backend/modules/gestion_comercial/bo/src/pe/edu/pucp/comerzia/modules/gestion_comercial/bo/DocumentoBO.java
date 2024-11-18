package pe.edu.pucp.comerzia.modules.gestion_comercial.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.DocumentoDAO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper.DocumentoMapper;
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
          DocumentoMapper.Columns.idEmpresa.eq(idEmpresa),
          DocumentoMapper.Columns.estado.eq(cotizacion),
          DocumentoMapper.Columns.tipo.eq(compra),
          DocumentoMapper.Columns.idVendedor.eq(idVendedor),
          DocumentoMapper.Columns.idAdministrador.eq(idAdministrador),
          DocumentoMapper.Columns.idTrabajadorDeAlmacen.eq(
            idTrabajadorDeAlmacen
          )
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
        .where(
          DocumentoMapper.Columns.idTrabajadorDeAlmacen.eq(
            idTrabajadorDeAlmacen
          )
        )
        .list()
        .size() >
      0
    );
  }

  public ArrayList<Documento> listarPorEmpresa(int idEmpresa)
    throws SQLException {
    return new ArrayList<>(
      documentoDAO
        .query()
        .where(DocumentoMapper.Columns.idEmpresa.eq(idEmpresa))
        .list()
    );
  }

  // existeDocumento
  public Boolean existeDocumento(
    int idEmpresa,
    EstadoDocumentoEnum estado,
    TipoDocumentoEnum tipo,
    int idVendedor,
    int idAdministrador,
    int idTrabajadorDeAlmacen
  ) throws SQLException {
    return (
      documentoDAO
        .query()
        .whereAll(
          DocumentoMapper.Columns.idEmpresa.eq(idEmpresa),
          DocumentoMapper.Columns.estado.eq(estado),
          DocumentoMapper.Columns.tipo.eq(tipo),
          DocumentoMapper.Columns.idVendedor.eq(idVendedor),
          DocumentoMapper.Columns.idAdministrador.eq(idAdministrador),
          DocumentoMapper.Columns.idTrabajadorDeAlmacen.eq(
            idTrabajadorDeAlmacen
          )
        )
        .count() >
      0
    );
  }
}
