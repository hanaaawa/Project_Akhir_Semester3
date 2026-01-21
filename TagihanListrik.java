
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

// SESI 1: OOP → Class & Object
public class TagihanListrik {

    // SESI 2 & 5: Data Type + Encapsulation
    private String idPelanggan;
    private double kwh;
    private double tarif;
    protected double total; // protected untuk inheritance

    // SESI 12: Standard Template Library (ArrayList)
    protected static ArrayList<TagihanListrik> daftarTagihan = new ArrayList<>();

    // SESI 4: Function & Parameter + Konstruktor
    public TagihanListrik(String idPelanggan, double kwh, double tarif) {
        this.idPelanggan = idPelanggan;
        this.kwh = kwh;
        this.tarif = tarif;
        this.total = hitungTagihan();
        daftarTagihan.add(this);
    }

    // Getter (Encapsulation)
    public String getIdPelanggan() {
        return idPelanggan;
    }

    public double getTotal() {
        return total;
    }

    // SESI 4: Function
    public double hitungTagihan() {
        return kwh * tarif;
    }

    // SESI 3: Control Statement (if-else)
    public void hitungDenda(int tanggalBayar) {
        if (tanggalBayar > 20) {
            double denda = total * 5 / 100;
            total += denda;
            System.out.println("Pembayaran lewat tanggal 20, dikenakan denda 5%");
            System.out.println("Denda : Rp " + denda);
        } else {
            System.out.println("Pembayaran tepat waktu, tidak ada denda");
        }
    }

    // SESI 10: Polymorphism
    public void tampilkanTagihan() {
        System.out.println("ID Pelanggan : " + idPelanggan);
        System.out.println("Jumlah kWh   : " + kwh);
        System.out.println("Tarif / kWh  : Rp " + tarif);
        System.out.println("Total Bayar  : Rp " + total);
    }

    // ================= INHERITANCE =================

    public static class RumahTangga extends TagihanListrik {
        public RumahTangga(String id, double kwh) {
            super(id, kwh, 1500);
        }

        @Override
        public void tampilkanTagihan() {
            System.out.println("\n=== Tagihan Rumah Tangga ===");
            super.tampilkanTagihan();
        }
    }

    public static class Bisnis extends TagihanListrik {
        public Bisnis(String id, double kwh) {
            super(id, kwh, 2000);
        }

        @Override
        public void tampilkanTagihan() {
            System.out.println("\n=== Tagihan Bisnis ===");
            super.tampilkanTagihan();
        }
    }

    public static class Industri extends TagihanListrik {
        public Industri(String id, double kwh) {
            super(id, kwh, 2500);
        }

        @Override
        public void tampilkanTagihan() {
            System.out.println("\n=== Tagihan Industri ===");
            super.tampilkanTagihan();
        }
    }

    // ================= DATABASE =================

    // Method simpan ke PostgreSQL
    public void simpanKeDatabase(String golongan, double denda) {
        try {
            Connection conn = KoneksiDatabase.getConnection();

            String sql = "INSERT INTO tagihan " +
                         "(id_pelanggan, golongan, kwh, tarif, subtotal, denda, total) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPelanggan);
            ps.setString(2, golongan);
            ps.setDouble(3, kwh);
            ps.setDouble(4, tarif);
            ps.setDouble(5, total - denda);
            ps.setDouble(6, denda);
            ps.setDouble(7, total);

            ps.executeUpdate();

            ps.close();
            conn.close();

            System.out.println("Data tagihan berhasil disimpan ke database!");

        } catch (Exception e) {
            System.out.println("Gagal menyimpan ke database: " + e.getMessage());
        }
    }
}
