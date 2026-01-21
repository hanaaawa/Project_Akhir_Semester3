import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class TagihanListrikSwing extends JFrame {

    JTextField txtNama, txtKwh;
    JComboBox<String> cmbGolongan;
    JTextArea txtStruk;

    public TagihanListrikSwing() {
        setTitle("Aplikasi Tagihan Listrik");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== PANEL FORM =====
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Nama Pelanggan"));
        txtNama = new JTextField();
        panel.add(txtNama);

        panel.add(new JLabel("Golongan"));
        cmbGolongan = new JComboBox<>(new String[]{
                "Rumah Tangga", "Bisnis", "Industri"
        });
        panel.add(cmbGolongan);

        panel.add(new JLabel("Pemakaian (kWh)"));
        txtKwh = new JTextField();
        panel.add(txtKwh);

        JButton btnHitung = new JButton("Hitung & Simpan");
        panel.add(btnHitung);

        JButton btnReset = new JButton("Reset");
        panel.add(btnReset);

        // ===== TEXT AREA STRUK =====
        txtStruk = new JTextArea();
        txtStruk.setEditable(false);
        txtStruk.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(txtStruk);

        add(panel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // ===== EVENT HITUNG =====
        btnHitung.addActionListener(e -> hitungTagihan());

        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtKwh.setText("");
            txtStruk.setText("");
            cmbGolongan.setSelectedIndex(0);
        });
    }

    private void hitungTagihan() {
        try {
            String nama = txtNama.getText();
            int kwh = Integer.parseInt(txtKwh.getText());
            String gol = cmbGolongan.getSelectedItem().toString();

            int tarif;
            switch (gol) {
                case "Rumah Tangga":
                    tarif = 1500;
                    break;
                case "Bisnis":
                    tarif = 2500;
                    break;
                default:
                    tarif = 3500;
            }

            int total = tarif * kwh;

            String struk =
                    "========= STRUK TAGIHAN LISTRIK =========\n" +
                    "Nama Pelanggan : " + nama + "\n" +
                    "Golongan       : " + gol + "\n" +
                    "Pemakaian      : " + kwh + " kWh\n" +
                    "Tarif / kWh    : Rp " + tarif + "\n" +
                    "----------------------------------------\n" +
                    "TOTAL BAYAR    : Rp " + total + "\n" +
                    "========================================";

            txtStruk.setText(struk);

            // ===== SIMPAN KE FILE =====
            FileWriter fw = new FileWriter("struk_listrik.txt");
            fw.write(struk);
            fw.close();

            JOptionPane.showMessageDialog(this,
                    "Struk berhasil disimpan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Input tidak valid!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TagihanListrikSwing().setVisible(true);
    }
}
