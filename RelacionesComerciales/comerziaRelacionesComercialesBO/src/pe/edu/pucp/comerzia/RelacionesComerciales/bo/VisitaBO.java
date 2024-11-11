package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.VisitaDAO;

public class VisitaBO {

  private VisitaDAO visitaDAO;

  public VisitaBO() {
    this.visitaDAO = new VisitaDAO();
  }

  public Integer insertar(
    Date fecha,
    Double duracion,
    Integer idCliente,
    Integer idVendedor
  ) throws SQLException {
    Visita visita = new Visita();

    visita.setFecha(fecha);
    visita.setDuracion(duracion);
    visita.setIdCliente(idCliente);
    visita.setIdVendedor(idVendedor);

    return this.visitaDAO.insert(visita);
  }

  public Integer modificar(
    Integer id,
    Date fecha,
    Double duracion,
    Integer idCliente,
    Integer idVendedor
  ) throws SQLException {
    Visita visita = new Visita();

    visita.setId(id);
    visita.setFecha(fecha);
    visita.setDuracion(duracion);
    visita.setIdCliente(idCliente);
    visita.setIdVendedor(idVendedor);

    return visitaDAO.update(id, visita);
  }

  public Integer eliminar(Integer id) throws SQLException {
    return this.visitaDAO.delete(id);
  }

  public Optional<Visita> obtenerPorId(Integer id) throws SQLException {
    return this.visitaDAO.findById(id);
  }

  public ArrayList<Visita> listarTodos() throws SQLException {
    return new ArrayList<>(this.visitaDAO.findAll());
  }
}
