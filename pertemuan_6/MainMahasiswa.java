package pertemuan_6;

public class MainMahasiswa {
    public static void main(String[] args) {
        Mahasiswa mhs = new Mahasiswa("Farhana Wahidiyah", "20240803091", 4.00);

        // Menampilkan data menggunakan getter
        System.out.println("Nama : " + mhs.getNama());
        System.out.println("NIM  : " + mhs.getNim());
        System.out.println("IPK  : " + mhs.getIpk());
    }
}