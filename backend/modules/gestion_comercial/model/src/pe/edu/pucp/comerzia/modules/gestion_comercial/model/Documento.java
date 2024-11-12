package pe.edu.pucp.comerzia.modules.gestion_comercial.model;

public class Documento {

  private Integer id;
  private Integer idEmpresa;
  private EstadoDocumentoEnum estado;
  private TipoDocumentoEnum tipo;
  private Integer idVendedor;
  private Integer idAdministrador;
  private Integer idTrabajadorDeAlmacen;

  public Documento() {
    this.id = null;
    this.idEmpresa = null;
    this.estado = null;
    this.tipo = null;
    this.idVendedor = null;
    this.idAdministrador = null;
    this.idTrabajadorDeAlmacen = null;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIdEmpresa() {
    return idEmpresa;
  }

  public void setIdEmpresa(Integer idEmpresa) {
    this.idEmpresa = idEmpresa;
  }

  public EstadoDocumentoEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoDocumentoEnum estado) {
    this.estado = estado;
  }

  public TipoDocumentoEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoDocumentoEnum tipo) {
    this.tipo = tipo;
  }

  public Integer getIdVendedor() {
    return idVendedor;
  }

  public void setIdVendedor(Integer idVendedor) {
    this.idVendedor = idVendedor;
  }

  public Integer getIdAdministrador() {
    return idAdministrador;
  }

  public void setIdAdministrador(Integer idAdministrador) {
    this.idAdministrador = idAdministrador;
  }

  public Integer getIdTrabajadorDeAlmacen() {
    return idTrabajadorDeAlmacen;
  }

  public void setIdTrabajadorDeAlmacen(Integer idTrabajadorDeAlmacen) {
    this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;
  }
}
