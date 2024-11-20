package pe.edu.pucp.comerzia.modules.relaciones_comerciales.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.recursos_humanos.dao.mapper.PersonaMapper;
import pe.edu.pucp.comerzia.modules.relaciones_comerciales.model.Representante;

public class RepresentanteMapper extends PersonaMapper<Representante> {

  public static class Columns extends PersonaMapper.Columns {

    public static final Column<Integer> idEmpresa = Column.of(
      "id_empresa",
      Integer.class
    );
  }

  @Override
  public Representante createEntity() {
    return new Representante();
  }

  @Override
  public Representante mapResultSetToEntity(ResultSet rs) throws SQLException {
    Representante representante = super.mapResultSetToEntity(rs);

    representante.setIdEmpresa(rs.getInt(Columns.idEmpresa.getName()));

    return representante;
  }

  @Override
  public Map<Column<?>, Object> mapEntityToColumns(Representante entity) {
    Map<Column<?>, Object> columns = super.mapEntityToColumns(entity);

    columns.put(Columns.idEmpresa, entity.getIdEmpresa());

    return columns;
  }

  @Override
  public Map<String, Object> getDiscriminatorColumns() {
    Map<String, Object> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("person_type", "representative_person");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, Object> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "representative_person".equals(
        discriminatorValues.get("person_type")
      );
  }
}
