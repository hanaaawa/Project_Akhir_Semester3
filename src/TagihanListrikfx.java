package Project_Akhir_Semester3;

// ===== SESI 13: JavaFX GUI =====

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileWriter;

public class TagihanListrikfx extends Application {

    private TextArea txtStruk;

    @Override
    public void start(Stage stage) {

        // ====== TITLE ======
        Label title = new Label("Aplikasi Tagihan Listrik");
        title.setFont(Font.font("Arial", 22));

        // ====== INPUT ======
        TextField txtId = new TextField();
        TextField txtKwh = new TextField();
        TextField txtTanggal = new TextField();

        ComboBox<String> cmbGolongan = new ComboBox<>();
        cmbGolongan.getItems().addAll("Rumah Tangga", "Bisnis", "Industri");
        cmbGolongan.getSelectionModel().selectFirst();

        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("ID Pelanggan"), 0, 0);
        form.add(txtId, 1, 0);

        form.add(new Label("Jumlah kWh"), 0, 1);
        form.add(txtKwh, 1, 1);

        form.add(new Label("Golongan"), 0, 2);
        form.add(cmbGolongan, 1, 2);

        form.add(new Label("Tanggal Bayar"), 0, 3);
        form.add(txtTanggal, 1, 3);

        // ====== BUTTON ======
        Button btnHitung = new Button("Hitung Tagihan");
        Button btnCetak = new Button("Cetak Struk");

        btnHitung.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnCetak.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        HBox tombolBox = new HBox(10, btnHitung, btnCetak);
        tombolBox.setAlignment(Pos.CENTER);

        // ====== OUTPUT ======
        txtStruk = new TextArea();
        txtStruk.setEditable(false);
        txtStruk.setPrefHeight(250);
        txtStruk.setFont(Font.font("Consolas", 13));

        // ====== CARD LAYOUT ======
        VBox card = new VBox(15, title, form, tombolBox, txtStruk);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(420);
        card.setStyle("""
            -fx-background-color: #f9f9f9;
            -fx-border-radius: 10;
            -fx-background-radius: 10;
            -fx-border-color: #cccccc;
        """);

        StackPane root = new StackPane(card);
        root.setStyle("-fx-background-color: #2c3e50;");

        // ====== EVENT HITUNG ======
        btnHitung.setOnAction(e -> {
            try {
                String id = txtId.getText();
                double kwh = Double.parseDouble(txtKwh.getText());
                int tanggal = Integer.parseInt(txtTanggal.getText());
                String golongan = cmbGolongan.getValue();

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

                String struk = """
                        ========== STRUK PEMBAYARAN ==========
                        ID Pelanggan : %s
                        Golongan     : %s
                        Pemakaian    : %.2f kWh
                        ------------------------------------
                        Subtotal     : Rp %.2f
                        Denda        : Rp %.2f
                        ------------------------------------
                        TOTAL BAYAR  : Rp %.2f
                        ====================================
                        """.formatted(id, golongan, kwh, sebelum, denda, sesudah);

                txtStruk.setText(struk);

                // ========== TAMBAHAN DATABASE ==========
                tagihan.simpanKeDatabase(golongan, denda);

            } catch (Exception ex) {
                showError("Input tidak valid!");
            }
        });

        // ====== EVENT CETAK ======
        btnCetak.setOnAction(e -> {
            if (txtStruk.getText().isEmpty()) {
                showError("Hitung tagihan dulu!");
                return;
            }

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Simpan Struk");
            chooser.setInitialFileName("struk.txt");

            File file = chooser.showSaveDialog(stage);

            if (file != null) {
                try (FileWriter fw = new FileWriter(file)) {
                    fw.write(txtStruk.getText());
                    showInfo("Struk berhasil disimpan!");
                } catch (Exception ex) {
                    showError("Gagal menyimpan file!");
                }
            }
        });

        // ====== STAGE ======
        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("Tagihan Listrik - JavaFX");

        // icon (opsional)
        try {
            stage.getIcons().add(new Image("file:icon.png"));
        } catch (Exception ignored) {}

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(msg);
        a.show();
    }

    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}