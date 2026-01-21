package pertemuan_6;

public class MainRekening {
    public static void main(String[] args) {
        RekeningBank rek1 = new RekeningBank(null,0);
        rek1.setNomorRekening("0403091");
        rek1.setSaldo(200000);
        System.out.println("=== Data Rekening Bank ===");
        System.out.println("Nomor rekening : " + rek1.getNomorRekening());
        System.out.println("Saldo : " + rek1.getSaldo());
        System.out.println("Coba input saldo negatif: ");
        rek1.setSaldo(-500);
    }
}