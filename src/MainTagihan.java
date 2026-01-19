package Project_Akhir_Semester3;

import java.util.Scanner;

public class MainTagihan {
    public static void main(String[] args) {

        // SESI 7: Memory Management (Scanner + Object)
        Scanner input = new Scanner(System.in);

        try { // SESI 11: Exception Handling

            System.out.print("Masukkan ID Pelanggan : ");
            String id = input.nextLine();

            System.out.print("Masukkan jumlah kWh  : ");
            double kwh = input.nextDouble();

            System.out.println("Pilih golongan:");
            System.out.println("1. Rumah Tangga");
            System.out.println("2. Bisnis");
            System.out.println("3. Industri");
            System.out.print("Pilihan : ");
            int pilihan = input.nextInt();

            System.out.print("Masukkan tanggal pembayaran : ");
            int tanggalBayar = input.nextInt();

            TagihanListrik tagihan;
            String golongan = "";

            // SESI 3: Control Statement (switch)
            switch (pilihan) {
                case 1:
                    tagihan = new TagihanListrik.RumahTangga(id, kwh);
                    golongan = "Rumah Tangga";
                    break;
                case 2:
                    tagihan = new TagihanListrik.Bisnis(id, kwh);
                    golongan = "Bisnis";
                    break;
                case 3:
                    tagihan = new TagihanListrik.Industri(id, kwh);
                    golongan = "Industri";
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    return;
            }

            double sebelum = tagihan.getTotal();
            tagihan.hitungDenda(tanggalBayar);
            double sesudah = tagihan.getTotal();
            double denda = sesudah - sebelum;

            System.out.println("\n=== HASIL AKHIR ===");
            tagihan.tampilkanTagihan();

            // ================= TAMBAHAN DATABASE =================
            tagihan.simpanKeDatabase(golongan, denda);

        } catch (Exception e) {
            System.out.println("Input tidak valid, silakan masukkan angka dengan benar");
        } finally {
            input.close();
        }
    }
};
