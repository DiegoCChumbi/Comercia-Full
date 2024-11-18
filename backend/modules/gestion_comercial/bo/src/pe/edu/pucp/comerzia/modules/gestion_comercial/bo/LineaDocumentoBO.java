package pe.edu.pucp.comerzia.modules.gestion_comercial.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.LineaDocumentoDAO;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper.LineaDocumentoMapper;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.LineaDocumento;

public class LineaDocumentoBO {

  private LineaDocumentoDAO lineaDocumentoDAO;

  public LineaDocumentoBO() {
    this.lineaDocumentoDAO = new LineaDocumentoDAO();
  }

  public Integer insertar(
    Integer idDocumento,
    Integer idProducto,
    Integer cantidad,
    Double precioUnitario
  ) throws SQLException {
    LineaDocumento lineaDocumento = new LineaDocumento();
    lineaDocumento.setIdDocumento(idDocumento);
    lineaDocumento.setIdProducto(idProducto);
    lineaDocumento.setCantidad(cantidad);
    lineaDocumento.setPrecioUnitario(precioUnitario);

    return lineaDocumentoDAO.insert(lineaDocumento);
  }

  public Integer modificar(
    Integer id,
    Integer idDocumento,
    Integer idProducto,
    Integer cantidad,
    Double precioUnitario
  ) throws SQLException {
    LineaDocumento lineaDocumento = new LineaDocumento();

    lineaDocumento.setId(id);
    lineaDocumento.setIdDocumento(idDocumento);
    lineaDocumento.setIdProducto(idProducto);
    lineaDocumento.setCantidad(cantidad);
    lineaDocumento.setPrecioUnitario(precioUnitario);

    return lineaDocumentoDAO.update(id, lineaDocumento);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return lineaDocumentoDAO.delete(id);
  }

  public Optional<LineaDocumento> obtenerPorId(Integer idDocumento)
    throws SQLException {
    return this.lineaDocumentoDAO.findById(idDocumento);
  }

  public ArrayList<LineaDocumento> listarTodos() throws SQLException {
    return new ArrayList<>(this.lineaDocumentoDAO.findAll());
  }

  public Boolean existeLinea(
    int idDocumento,
    int idProducto,
    int cantidad,
    double precioUnitario
  ) throws SQLException {
    return (
      this.lineaDocumentoDAO.query()
        .whereAll(
          LineaDocumentoMapper.Columns.idDocumento.eq(idDocumento),
          LineaDocumentoMapper.Columns.idProducto.eq(idProducto),
          LineaDocumentoMapper.Columns.cantidad.eq(cantidad),
          LineaDocumentoMapper.Columns.precioUnitario.eq(precioUnitario)
        )
        .count() >
      0
    );
  }

  public ArrayList<LineaDocumento> listarPorDocumento(Integer idDocumento)
    throws SQLException {
    return new ArrayList<>(
      this.lineaDocumentoDAO.query()
        .where(LineaDocumentoMapper.Columns.idDocumento.eq(idDocumento))
        .list()
    );
  }
}
