package pertemuan_6;

public class Produk {
    private String namaProduk;
    private double harga;
    private int stok;

    // Konstruktor
    public Produk(String namaProduk, double harga, int stok) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter
    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // Method untuk menambah stok
    public void tambahStok(int jumlah) {
        stok += jumlah;
        System.out.println(jumlah + " stok berhasil ditambahkan.");
    }

    // Method untuk mengurangi stok
    public void kurangiStok(int jumlah) {
        if (jumlah > stok) {
            System.out.println("Stok tidak cukup! Tidak dapat mengurangi sebanyak " + jumlah);
        } else {
            stok -= jumlah;
            System.out.println(jumlah + " stok berhasil dikurangi.");
        }
    }

    // Method untuk menampilkan info produk
    public void tampilkanInfo() {
        System.out.println("Nama Produk : " + namaProduk);
        System.out.println("Harga       : Rp " + harga);
        System.out.println("Stok        : " + stok);
    }
}