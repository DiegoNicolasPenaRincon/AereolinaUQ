package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.Aerolinea;
import co.edu.uniquindio.aerolineauq.model.Tiquete;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.*;

public class EquipajeViewController {

    @FXML
    private Button btnRegistrar;
    @FXML
    private ComboBox<String> cbTipoEquipaje;
    @FXML
    private CheckBox ckMascotaNo;
    @FXML
    private CheckBox ckMascotaSi;
    @FXML
    private TextField txtNumeroVuelo;
    @FXML
    private TextField txtPesoMascota;
    @FXML
    private TextField txtPesoEquipaje;

    Aerolinea aerolinea;

    @FXML
    public void initialize() {
        ckMascotaSi.setOnAction(e -> txtPesoMascota.setDisable(!ckMascotaSi.isSelected()));
        ckMascotaNo.setOnAction(e -> txtPesoMascota.setDisable(ckMascotaNo.isSelected()));
    }

    /*
    @FXML
    private void registrarEquipaje() {
        String numeroVuelo = txtNumeroVuelo.getText();
        double pesoEquipaje = Double.parseDouble(txtPesoEquipaje.getText());
        boolean esMascota = ckMascotaSi.isSelected();
        double pesoMascota = esMascota ? Double.parseDouble(txtPesoMascota.getText()) : 0;

        Tiquete tiquete = buscarTiquetePorNumero(numeroVuelo); // metodo para buscar el tiquete
        if (tiquete != null) {
            ModelFactoryController.getInstance().getAerolinea().registrarEquipaje(tiquete, pesoEquipaje, esMascota, pesoMascota, "internacional"); // Considerando "internacional" como ejemplo
            mostrarDatosEquipaje(pesoEquipaje, esMascota, pesoMascota);
        } else {
            mostrarError("Tiquete no encontrado");
        }
    }

    private Tiquete buscarTiquetePorNumero(String numeroVuelo) {
        // Iterar sobre la lista de tiquetes en la aerolínea
        for (Tiquete tiquete : aerolinea.getTiquetes()) {
            // Verificar si el número de vuelo del tiquete coincide con el número proporcionado
            if (tiquete.getRuta().getVuelo().getNumeroVuelo().equals(numeroVuelo)) {
                return tiquete; // Retornar el tiquete si se encuentra una coincidencia
            }
        }
        // Si no se encuentra ningún tiquete con el número de vuelo dado, retornar null
        return null;
    }

     */


    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarDatosEquipaje(double pesoEquipaje, boolean esMascota, double pesoMascota) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación de Equipaje");
        alert.setHeaderText("Detalles del Equipaje Registrado");
        alert.setContentText("Peso del equipaje: " + pesoEquipaje + " kg\n" +
                "Mascota: " + (esMascota ? "Sí" : "No") +
                (esMascota ? "\nPeso de la mascota: " + pesoMascota + " kg" : ""));
        alert.showAndWait();
    }
}

