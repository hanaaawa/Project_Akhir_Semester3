package pertemuan_6;

public class Elektronik extends Produk {
    private String garansi;

    // Konstruktor (memanggil konstruktor dari superclass)
    public Elektronik(String namaProduk, double harga, int stok, String garansi) {
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

    // Override tampilkanInfo untuk menambah info garansi
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo(); // tampilkan info dari Produk
        System.out.println("Garansi     : " + garansi);
    }
}