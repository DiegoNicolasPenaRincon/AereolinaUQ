package co.edu.uniquindio.aerolineauq.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AsientosViewController {

    @FXML
    private GridPane gridAsientos;

    public void inicializarAsientos(String tipoAvion, String claseVuelo) {
        int filas, columnas;
        String[][] numeracion;

        // Configurar la matriz de asientos según el tipo de avión y clase de vuelo
        switch (tipoAvion) {
            case "Airbus A320":
                if (claseVuelo.equals("EJECUTIVA")) {
                    filas = 4;
                    columnas = 2;
                    numeracion = new String[][]{{"A", "C"}, {"D", "F"}};
                } else {
                    filas = 6;
                    columnas = 3;
                    numeracion = new String[][]{{"A", "B", "C"}, {"D", "E", "F"}};
                }
                break;
            case "Airbus A330":
                if (claseVuelo.equals("EJECUTIVA")) {
                    filas = 6;
                    columnas = 3;
                    numeracion = new String[][]{{"A", "C"}, {"D", "F"}, {"H", "K"}};
                } else {
                    filas = 8;
                    columnas = 4;
                    numeracion = new String[][]{{"A", "C"}, {"D", "E", "F", "G"}, {"H", "K"}};
                }
                break;
            case "Boeing 787":
                if (claseVuelo.equals("EJECUTIVA")) {
                    filas = 3;
                    columnas = 2;
                    numeracion = new String[][]{{"A", "B"}, {"D", "E"}, {"L", "K"}};
                } else {
                    filas = 3;
                    columnas = 3;
                    numeracion = new String[][]{{"A", "B", "C"}, {"D", "E", "F"}, {"J", "K", "L"}};
                }
                break;
            default:
                return;
        }

        // Limpiar el GridPane
        gridAsientos.getChildren().clear();

        // Crear botones para cada asiento según la numeración
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int filaIndex = i % numeracion.length;
                int colIndex = j % numeracion[filaIndex].length;

                Button asientoBtn = new Button(numeracion[filaIndex][colIndex] + (i + 1));
                asientoBtn.setOnAction(e -> seleccionarAsiento(asientoBtn));

                // Añadir botón al GridPane en la posición correspondiente
                gridAsientos.add(asientoBtn, j, i);
            }
        }

    }

    private void seleccionarAsiento(Button asientoBtn) {
        // Marcar el asiento como seleccionado
        asientoBtn.setStyle("-fx-background-color: green;");
        asientoBtn.setDisable(true);
    }

    public void cerrarVentana() {
        Stage stage = (Stage) gridAsientos.getScene().getWindow();
        stage.close();
    }
}
