package pe.edu.pucp.comerzia.gestioncomercial.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.db.BaseDAOImpl.EntityMapper;
import pe.edu.pucp.comerzia.db.utils.Column;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.gestioncomercial.model.TipoDocumentoEnum;

public class DocumentoMapper implements EntityMapper<Documento> {

  public static class Columns {

    public static final Column<Integer> id = new Column<>("id", Integer.class);
    public static final Column<Integer> idEmpresa = new Column<>(
      "id_empresa",
      Integer.class
    );
    public static final Column<EstadoDocumentoEnum> estado = new Column<>(
      "estado",
      EstadoDocumentoEnum.class
    );
    public static final Column<TipoDocumentoEnum> tipo = new Column<>(
      "tipo",
      TipoDocumentoEnum.class
    );
    public static final Column<Integer> idVendedor = new Column<>(
      "id_vendedor",
      Integer.class
    );
    public static final Column<Integer> idAdministrador = new Column<>(
      "id_administrador",
      Integer.class
    );
    public static final Column<Integer> idTrabajadorDeAlmacen = new Column<>(
      "id_trabajador_de_almacen",
      Integer.class
    );
  }

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
  public Map<Column<?>, Object> mapEntityToColumns(Documento entity) {
    Map<Column<?>, Object> columns = new HashMap<>();

    columns.put(Columns.idEmpresa, entity.getIdEmpresa());
    columns.put(Columns.estado, entity.getEstado());
    columns.put(Columns.tipo, entity.getTipo());
    columns.put(Columns.idVendedor, entity.getIdVendedor());
    columns.put(Columns.idAdministrador, entity.getIdAdministrador());
    columns.put(
      Columns.idTrabajadorDeAlmacen,
      entity.getIdTrabajadorDeAlmacen()
    );

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
