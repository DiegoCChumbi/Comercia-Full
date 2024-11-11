package dbmanagermain;

import RecursosHumanosTest.AdministradorTest;
import RecursosHumanosTest.TrabajadorTest;
import RecursosHumanosTest.VendedorTest;
import comerziaDocumentoTest.DocumentoBOTest;
import comerziaDocumentoTest.LineaDocumentoBOTest;
import comerziaalmacentest.AlmacenBOTest;
import java.sql.SQLException;
import pe.edu.pucp.comerzia.config.DBManager;

public class DBManagerMain {

  public static void main(String[] args) throws SQLException {
    DBManager dbManager = DBManager.getInstance();
    System.out.println("Conexión DBManager: " + dbManager);
    // ProveedorBOTest.testProveedorBO();
    // ClienteBOTest.testClienteBO();
    // VisitaBOTest.testVisitaBO();
    DocumentoBOTest.main(null);
    LineaDocumentoBOTest.main(null);
    AlmacenBOTest.main(null);
    AdministradorTest.main(null);
    TrabajadorTest.main(null);
    VendedorTest.main(null);
  }
}
