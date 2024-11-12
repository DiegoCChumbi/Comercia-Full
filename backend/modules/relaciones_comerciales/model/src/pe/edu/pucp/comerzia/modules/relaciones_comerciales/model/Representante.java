package pe.edu.pucp.comerzia.modules.relaciones_comerciales.model;

import pe.edu.pucp.comerzia.modules.recursos_humanos.model.Persona;

public class Representante extends Persona {

  private Integer idEmpresa;

  public Representante() {
    super();
    this.idEmpresa = null;
  }

  public Integer getIdEmpresa() {
    return idEmpresa;
  }

  public void setIdEmpresa(Integer idEmpresa) {
    this.idEmpresa = idEmpresa;
  }
}
