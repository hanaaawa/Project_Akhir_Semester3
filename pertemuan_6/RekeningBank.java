package pertemuan_6;

public class RekeningBank {
    private String nomorRekening;
    private double saldo;

    // Konstruktor dengan validasi saldo
    public RekeningBank(String nomorRekening, double saldo) {
        this.nomorRekening = nomorRekening;

        if (saldo < 0) {
            System.out.println("Saldo tidak boleh negatif!");
            this.saldo = 0; // otomatis jadi 0 kalau input negatif
        } else {
            this.saldo = saldo;
        }
    }

    // Getter
    public String getNomorRekening() {
        return nomorRekening;
    }

    public double getSaldo() {
        return saldo;
    }

    // Setter
    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            System.out.println("Saldo tidak boleh negatif!");
        } else {
            this.saldo = saldo;
        }
    }
}