package Project_Akhir_Semester3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class TagihanListrikSwing extends JFrame {

    private JTextField txtId, txtKwh, txtTanggal;
    private JComboBox<String> cmbGolongan;
    private JTextArea txtStruk;

    public TagihanListrikSwing() {
        setTitle("Aplikasi Tagihan Listrik");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Panel utama (background) =====
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(44, 62, 80));

        // ===== Card =====
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== Title =====
        JLabel title = new JLabel("Aplikasi Tagihan Listrik");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(15));

        // ===== Form =====
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setOpaque(false);

        txtId = new JTextField();
        txtKwh = new JTextField();
        txtTanggal = new JTextField();

        cmbGolongan = new JComboBox<>(new String[]{"Rumah Tangga", "Bisnis", "Industri"});

        form.add(new JLabel("ID Pelanggan"));
        form.add(txtId);
        form.add(new JLabel("Jumlah kWh"));
        form.add(txtKwh);
        form.add(new JLabel("Golongan"));
        form.add(cmbGolongan);
        form.add(new JLabel("Tanggal Bayar"));
        form.add(txtTanggal);

        card.add(form);
        card.add(Box.createVerticalStrut(15));

        // ===== Buttons =====
        JButton btnHitung = new JButton("Hitung Tagihan");
        JButton btnCetak = new JButton("Cetak Struk");

        btnHitung.setBackground(new Color(76, 175, 80));
        btnHitung.setForeground(Color.WHITE);

        btnCetak.setBackground(new Color(33, 150, 243));
        btnCetak.setForeground(Color.WHITE);

        JPanel panelBtn = new JPanel();
        panelBtn.setOpaque(false);
        panelBtn.add(btnHitung);
        panelBtn.add(btnCetak);

        card.add(panelBtn);
        card.add(Box.createVerticalStrut(15));

        // ===== Output =====
        txtStruk = new JTextArea(10, 30);
        txtStruk.setEditable(false);
        txtStruk.setFont(new Font("Consolas", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(txtStruk);
        card.add(scroll);

        root.add(card);
        add(root);

        // ===== Event Hitung =====
        btnHitung.addActionListener(e -> hitungTagihan());

        // ===== Event Cetak =====
        btnCetak.addActionListener(e -> simpanStruk());

    }

    private void hitungTagihan() {
        try {
            String id = txtId.getText();
            double kwh = Double.parseDouble(txtKwh.getText());
            int tanggal = Integer.parseInt(txtTanggal.getText());
            String golongan = cmbGolongan.getSelectedItem().toString();

            TagihanListrik tagihan;

            if (golongan.equals("Rumah Tangga")) {
                tagihan = new TagihanListrik.RumahTangga(id, kwh);
            } else if (golongan.equals("Bisnis")) {
                tagihan = new TagihanListrik.Bisnis(id, kwh);
            } else {
                tagihan = new TagihanListrik.Industri(id, kwh);
            }

            double sebelum = tagihan.getTotal();
            tagihan.hitungDenda(tanggal);
            double sesudah = tagihan.getTotal();
            double denda = sesudah - sebelum;

            String struk =
                    "========== STRUK PEMBAYARAN ==========\n" +
                    "ID Pelanggan : " + id + "\n" +
                    "Golongan     : " + golongan + "\n" +
                    "Pemakaian    : " + kwh + " kWh\n" +
                    "-------------------------------------\n" +
                    "Subtotal     : Rp " + sebelum + "\n" +
                    "Denda        : Rp " + denda + "\n" +
                    "-------------------------------------\n" +
                    "TOTAL BAYAR  : Rp " + sesudah + "\n" +
                    "=====================================\n";

            txtStruk.setText(struk);

            // simpan ke database (kalau method ada)
            tagihan.simpanKeDatabase(golongan, denda);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void simpanStruk() {
        if (txtStruk.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hitung tagihan dulu!");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                fw.write(txtStruk.getText());
                JOptionPane.showMessageDialog(this, "Struk berhasil disimpan!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TagihanListrikSwing().setVisible(true));
    }
}
