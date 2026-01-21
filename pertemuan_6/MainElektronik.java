package pertemuan_6;
public class MainElektronik {
    public static void main(String[] args) {
        // Membuat objek elektronik
        Elektronik laptop = new Elektronik("Laptop Asus", 8500000, 5, "2 Tahun");

        // Menampilkan informasi lengkap
        laptop.tampilkanInfo();

        // Uji method pewarisan
        laptop.tambahStok(3);
        laptop.kurangiStok(2);

        System.out.println("\nSetelah update stok:");
        laptop.tampilkanInfo();
    }
}