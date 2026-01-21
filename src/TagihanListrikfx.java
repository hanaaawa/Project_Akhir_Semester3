
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TagihanListrikfx extends Application {

    @Override
    public void start(Stage stage) {

        Label lblNama = new Label("Nama Pelanggan:");
        TextField txtNama = new TextField();

        Label lblGolongan = new Label("Golongan:");
        ComboBox<String> cmbGol = new ComboBox<>();
        cmbGol.getItems().addAll("Rumah Tangga", "Bisnis", "Industri");
        cmbGol.getSelectionModel().selectFirst();

        Label lblKwh = new Label("Pemakaian (kWh):");
        TextField txtKwh = new TextField();

        Button btnHitung = new Button("Hitung");
        TextArea txtHasil = new TextArea();
        txtHasil.setEditable(false);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(lblNama, 0, 0);
        grid.add(txtNama, 1, 0);
        grid.add(lblGolongan, 0, 1);
        grid.add(cmbGol, 1, 1);
        grid.add(lblKwh, 0, 2);
        grid.add(txtKwh, 1, 2);
        grid.add(btnHitung, 1, 3);
        grid.add(txtHasil, 1, 4);

        btnHitung.setOnAction(e -> {
            try {
                String nama = txtNama.getText();
                int kwh = Integer.parseInt(txtKwh.getText());
                String gol = cmbGol.getValue();

                int tarif = gol.equals("Rumah Tangga") ? 1500 :
                            gol.equals("Bisnis") ? 2500 : 3500;

                int total = tarif * kwh;

                txtHasil.setText(
                        "Nama        : " + nama + "\n" +
                        "Golongan    : " + gol + "\n" +
                        "Pemakaian   : " + kwh + " kWh\n" +
                        "Tarif/kWh   : Rp " + tarif + "\n" +
                        "Total Bayar : Rp " + total
                );
            } catch (Exception ex) {
                txtHasil.setText("Input tidak valid!");
            }
        });

        Scene scene = new Scene(grid, 420, 350);
        stage.setTitle("Aplikasi Tagihan Listrik (JavaFX)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
