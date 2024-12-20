package pe.edu.pucp.comerzia.modules.gestion_comercial.dao;

import pe.edu.pucp.comerzia.core.dao.BaseDAO;
import pe.edu.pucp.comerzia.core.dao.utils.Column;
import pe.edu.pucp.comerzia.modules.gestion_comercial.dao.mapper.DocumentoMapper;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.Documento;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.EstadoDocumentoEnum;
import pe.edu.pucp.comerzia.modules.gestion_comercial.model.TipoDocumentoEnum;

public class DocumentoDAO extends BaseDAO<Documento, Integer> {

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

  // Default
  public DocumentoDAO() {
    super(Documento.class, new DocumentoMapper());
  }

  @Override
  protected String getTableName() {
    return "documento";
  }

  @Override
  protected String getPrimaryKeyColumnName() {
    return "id";
  }
}
