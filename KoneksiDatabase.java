

import java.sql.Connection;
import java.sql.DriverManager;

public class KoneksiDatabase {

    private static final String URL = "jdbc:postgresql://localhost:5432/tagihan_listrik";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Fafitara_04";

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}