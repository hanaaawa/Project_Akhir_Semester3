package Project_Akhir_Semester3;

import java.sql.Connection;
import java.sql.DriverManager;

public class KoneksiDatabase {

    private static final String URL = "jdbc:postgresql://localhost:5432/tagihan_listrik";
    private static final String USER = "postgres";
    private static final String PASS = "password_kamu"; // ganti dengan password postgres kamu

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
            return null;
        }
    }
}
