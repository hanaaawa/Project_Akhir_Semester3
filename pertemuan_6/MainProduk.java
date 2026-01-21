package pertemuan_6;

public class MainProduk {

    public static void main(String[] args) {
        // Membuat objek produk
        Produk p1 = new Produk("Buku Tulis", 5000, 10);

        // Menampilkan data awal
        p1.tampilkanInfo();

        // Menambah dan mengurangi stok
        p1.tambahStok(5);
        p1.kurangiStok(3);
        p1.kurangiStok(20); // contoh stok tidak cukup

        // Menampilkan data akhir
        System.out.println("\nSetelah update stok:");
        p1.tampilkanInfo();
    }
}