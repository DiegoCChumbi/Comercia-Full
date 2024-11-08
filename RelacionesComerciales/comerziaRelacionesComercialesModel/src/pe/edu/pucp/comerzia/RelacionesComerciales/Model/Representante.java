package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

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
