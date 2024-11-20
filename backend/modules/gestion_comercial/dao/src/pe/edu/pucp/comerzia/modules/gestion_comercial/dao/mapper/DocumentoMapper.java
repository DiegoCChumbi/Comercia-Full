package pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.BaseDAO.EntityMapper;
import pe.edu.pucp.comerzia.core.dao.BaseEntityMapper;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.Documento;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.TipoDocumentoEnum;

public class DocumentoMapper implements EntityMapper<Documento> {

  public static class Columns extends BaseEntityMapper.Columns {

    public static final Column<Integer> id = Column.of("id", Integer.class);
    public static final Column<Integer> idEmpresa = Column.of(
      "id_empresa",
      Integer.class
    );
    public static final Column<EstadoDocumentoEnum> estado = Column.of(
      "estado",
      EstadoDocumentoEnum.class
    );
    public static final Column<TipoDocumentoEnum> tipo = Column.of(
      "tipo",
      TipoDocumentoEnum.class
    );
    public static final Column<Integer> idVendedor = Column.of(
      "id_vendedor",
      Integer.class
    );
    public static final Column<Integer> idAdministrador = Column.of(
      "id_administrador",
      Integer.class
    );
    public static final Column<Integer> idTrabajadorDeAlmacen = Column.of(
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

    documento.setId(rs.getInt(Columns.id.getName()));
    documento.setIdEmpresa(rs.getInt(Columns.idEmpresa.getName()));
    documento.setEstado(
      EstadoDocumentoEnum.valueOf(rs.getString(Columns.estado.getName()))
    );
    documento.setTipo(
      TipoDocumentoEnum.valueOf(rs.getString(Columns.tipo.getName()))
    );
    documento.setIdVendedor(rs.getInt(Columns.idVendedor.getName()));
    documento.setIdAdministrador(rs.getInt(Columns.idAdministrador.getName()));
    documento.setIdTrabajadorDeAlmacen(
      rs.getInt(Columns.idTrabajadorDeAlmacen.getName())
    );

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
