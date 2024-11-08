package pe.edu.pucp.comerzia.gestioncomercial.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.gestioncomercial.dao.DocumentoDAO;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.gestioncomercial.model.TipoDocumentoEnum;

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

    return documentoDAO.update(documento);
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
}
