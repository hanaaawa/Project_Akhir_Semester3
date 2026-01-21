import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TagihanListrikfx extends Application {

    private VBox invoiceBox;

    @Override
    public void start(Stage stage) {

        // ================= TITLE =================
        Label title = new Label("APLIKASI TAGIHAN LISTRIK");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        title.setStyle("-fx-text-fill: white;");

        // ================= INPUT =================
        TextField tfId = new TextField();
        TextField tfKwh = new TextField();
        TextField tfTanggal = new TextField();

        ComboBox<String> cbGolongan = new ComboBox<>();
        cbGolongan.getItems().addAll("Rumah Tangga", "Bisnis", "Industri");
        cbGolongan.setValue("Rumah Tangga");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("ID Pelanggan"), 0, 0);
        form.add(tfId, 1, 0);
        form.add(new Label("Pemakaian (kWh)"), 0, 1);
        form.add(tfKwh, 1, 1);
        form.add(new Label("Golongan"), 0, 2);
        form.add(cbGolongan, 1, 2);
        form.add(new Label("Tanggal Bayar"), 0, 3);
        form.add(tfTanggal, 1, 3);

        // ================= BUTTON =================
        Button btnHitung = new Button("Hitung Tagihan");
        btnHitung.setStyle("""
            -fx-background-color: #0000c8;
            -fx-text-fill: white;
            -fx-font-weight: bold;
        """);

        Button btnPDF = new Button("Cetak / Simpan PDF");
        btnPDF.setStyle("""
            -fx-background-color: #24a729;
            -fx-text-fill: white;
            -fx-font-weight: bold;
        """);

        HBox tombol = new HBox(10, btnHitung, btnPDF);
        tombol.setAlignment(Pos.CENTER);

        // ================= INVOICE =================
        invoiceBox = new VBox(10);
        invoiceBox.setPadding(new Insets(20));
        invoiceBox.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 15;
            -fx-border-radius: 15;
            -fx-border-color: #000000;
        """);

        // ================= CARD =================
        VBox card = new VBox(20, title, form, tombol, invoiceBox);
        card.setPadding(new Insets(20));
        card.setMaxWidth(500);
        card.setStyle("""
            -fx-background-color: #7c8be1;
            -fx-background-radius: 20;
        """);

        StackPane root = new StackPane(card);
        root.setStyle("-fx-background-color: #1f2a44;");

        // ================= LOGIC =================
        btnHitung.setOnAction(e -> {
            try {
                String id = tfId.getText();
                double kwh = Double.parseDouble(tfKwh.getText());
                int tgl = Integer.parseInt(tfTanggal.getText());
                String gol = cbGolongan.getValue();

                TagihanListrik tagihan;
                if (gol.equals("Rumah Tangga")) {
                    tagihan = new TagihanListrik.RumahTangga(id, kwh);
                } else if (gol.equals("Bisnis")) {
                    tagihan = new TagihanListrik.Bisnis(id, kwh);
                } else {
                    tagihan = new TagihanListrik.Industri(id, kwh);
                }

                double subtotal = tagihan.getTotal();
                tagihan.hitungDenda(tgl);
                double total = tagihan.getTotal();
                double denda = total - subtotal;

                tagihan.simpanKeDatabase(gol, denda);

                buildInvoice(id, gol, kwh, subtotal, denda, total);

            } catch (Exception ex) {
                alert("Input tidak valid!");
            }
        });

        btnPDF.setOnAction(e -> {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(stage)) {
                job.printPage(invoiceBox);
                job.endJob();
            }
        });

        stage.setScene(new Scene(root, 650, 750));
        stage.setTitle("Tagihan Listrik - JavaFX");
        stage.show();
    }

    // ================= INVOICE BUILDER =================
    private void buildInvoice(String id, String gol, double kwh,
                              double subtotal, double denda, double total) {

        invoiceBox.getChildren().clear();

        ImageView logo = new ImageView(new Image("file:logo.png"));
        logo.setFitWidth(70);
        logo.setPreserveRatio(true);

        Label perusahaan = new Label("PT LISTRIK FARHANA");
        perusahaan.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label alamat = new Label("Jl. Bandung Raya No.123 Kota Bandung");

        VBox header = new VBox(5, logo, perusahaan, alamat);
        header.setAlignment(Pos.CENTER);

        GridPane detail = new GridPane();
        detail.setHgap(10);
        detail.setVgap(8);

        detail.addRow(0, new Label("ID Pelanggan"), new Label(": " + id));
        detail.addRow(1, new Label("Golongan"), new Label(": " + gol));
        detail.addRow(2, new Label("Pemakaian"), new Label(": " + kwh + " kWh"));
        detail.addRow(3, new Label("Subtotal"), new Label(": Rp " + subtotal));
        detail.addRow(4, new Label("Denda"), new Label(": Rp " + denda));

        Label totalLbl = new Label("TOTAL BAYAR : Rp " + total);
        totalLbl.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        totalLbl.setStyle("-fx-text-fill: #f70a0a;");

        invoiceBox.getChildren().addAll(
                header,
                new Separator(),
                detail,
                new Separator(),
                totalLbl,
                new Label("Terima kasih telah melakukan pembayaran")
        );
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
