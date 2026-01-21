package pertemuan_6;

public class MainTurunanElektronik {
    public static void main(String[] args) {
        Elektronik laptop = new Elektronik("Laptop ASUS", 9500000, 10, "1 Tahun");

        laptop.tambahStok(5);
        laptop.kurangiStok(3);

        System.out.println("=== Data Produk Elektronik ===");
        laptop.tampilkanInfo();
    }
}