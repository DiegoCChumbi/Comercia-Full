package pe.edu.pucp.comerzia.gestioncomercial.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.gestioncomercial.model.TipoDocumentoEnum;

public class DocumentoMapper implements EntityMapper<Documento> {

  @Override
  public Documento createEntity() {
    return new Documento();
  }

  @Override
  public Documento mapResultSetToEntity(ResultSet rs) throws SQLException {
    Documento documento = createEntity();

    documento.setId(rs.getInt("id"));
    documento.setIdEmpresa(rs.getInt("id_empresa"));
    documento.setEstado(EstadoDocumentoEnum.valueOf(rs.getString("estado")));
    documento.setTipo(TipoDocumentoEnum.valueOf(rs.getString("tipo")));
    documento.setIdVendedor(rs.getInt("id_vendedor"));
    documento.setIdAdministrador(rs.getInt("id_administrador"));
    documento.setIdTrabajadorDeAlmacen(rs.getInt("id_trabajador_de_almacen"));

    return documento;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(Documento entity) {
    Map<String, Object> columns = new HashMap<>();

    columns.put("id_empresa", entity.getIdEmpresa());
    columns.put("estado", entity.getEstado().toString());
    columns.put("tipo", entity.getTipo().toString());
    columns.put("id_vendedor", entity.getIdVendedor());
    columns.put("id_administrador", entity.getIdAdministrador());
    columns.put("id_trabajador_de_almacen", entity.getIdTrabajadorDeAlmacen());

    return columns;
  }

  @Override
  public void setGeneratedId(Documento entity, Object id) {
    entity.setId((Integer) id);
  }

  @Override
  public Object getId(Documento entity) {
    return entity.getId();
  }
}
