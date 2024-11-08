package pe.edu.pucp.comerzia.RelacionesComerciales.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.mapper.PersonaMapper;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Representante;

public class RepresentanteMapper extends PersonaMapper<Representante> {

  @Override
  public Representante createEntity() {
    return new Representante();
  }

  @Override
  public Representante mapResultSetToEntity(ResultSet rs) throws SQLException {
    Representante representante = super.mapResultSetToEntity(rs);

    representante.setIdEmpresa(rs.getInt("id_empresa"));

    return representante;
  }

  @Override
  public Map<String, Object> mapEntityToColumns(Representante entity) {
    Map<String, Object> columns = super.mapEntityToColumns(entity);

    columns.put("id_empresa", entity.getIdEmpresa());

    return columns;
  }

  @Override
  public Map<String, String> getDiscriminatorColumns() {
    Map<String, String> discriminators = new HashMap<>(
      super.getDiscriminatorColumns()
    );
    discriminators.put("person_type", "representative_person");
    return discriminators;
  }

  @Override
  public boolean canMap(Map<String, String> discriminatorValues) {
    if (!super.canMap(discriminatorValues)) {
      return false;
    }
    return "representative_person".equals(
        discriminatorValues.get("person_type")
      );
  }
}
