package pe.edu.pucp.comerzia.core.test;

import java.sql.SQLException;
import pe.edu.pucp.comerzia.core.db.DBManager;
import pe.edu.pucp.comerzia.modules.gestion_almacen.test.AlmacenBOTest;
import pe.edu.pucp.comerzia.modules.gestion_comercial.test.DocumentoBOTest;
import pe.edu.pucp.comerzia.modules.gestion_comercial.test.LineaDocumentoBOTest;
import pe.edu.pucp.comerzia.modules.recursos_humanos.test.AdministradorTest;
import pe.edu.pucp.comerzia.modules.recursos_humanos.test.TrabajadorTest;
import pe.edu.pucp.comerzia.modules.recursos_humanos.test.VendedorTest;

public class DBManagerMain {

  public static void main(String[] args) throws SQLException {
    DBManager dbManager = DBManager.getInstance();
    System.out.println("Conexión DBManager: " + dbManager);
    // ProveedorBOTest.main(null);
    // ClienteBOTest.main(null);
    // VisitaBOTest.main(null);
    DocumentoBOTest.main(null);
    LineaDocumentoBOTest.main(null);
    AlmacenBOTest.main(null);
    AdministradorTest.main(null);
    TrabajadorTest.main(null);
    VendedorTest.main(null);
  }
}
