package pertemuan_6;

public class TurunanElektronik extends Produk {
    private String garansi;

    // Konstruktor (memanggil konstruktor dari superclass)
    public TurunanElektronik(String namaProduk, double harga, int stok, String garansi) {
        super(namaProduk, harga, stok); // panggil konstruktor Produk
        this.garansi = garansi;
    }

    // Getter dan Setter
    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
    }

    public void tampilkanInfo() {
        super.tampilkanInfo(); // tampilkan info dari Produk
        System.out.println("Garansi     : " + garansi);
    }
}