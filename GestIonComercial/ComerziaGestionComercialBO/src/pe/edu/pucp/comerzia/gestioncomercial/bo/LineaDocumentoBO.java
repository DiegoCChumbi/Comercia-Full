package pe.edu.pucp.comerzia.gestioncomercial.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import pe.edu.pucp.comerzia.gestioncomercial.dao.LineaDocumentoDAO;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;

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
      lineaDocumentoDAO
        .query()
        .whereAll(
          LineaDocumentoDAO.idDocumento.eq(idDocumento),
          LineaDocumentoDAO.idProducto.eq(idProducto),
          LineaDocumentoDAO.cantidad.eq(cantidad),
          LineaDocumentoDAO.precioUnitario.eq(precioUnitario)
        )
        .count() >
      0
    );
  }
}
