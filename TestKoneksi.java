

import java.sql.Connection;

public class TestKoneksi {
    public static void main(String[] args) {
        try {
            Connection conn = KoneksiDatabase.getConnection();
            if (conn != null) {
                System.out.println("Koneksi ke database BERHASIL!");
            } else {
                System.out.println("Koneksi ke database GAGAL!");
            }
        } catch (Exception e) {
            System.out.println("Koneksi ke database GAGAL: " + e.getMessage());
        }
    }
}
