package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.ClaseVuelo;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.IOException;

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

    private AerolineaApplication aplicacion;

    public EquipajeViewController() throws IOException {
    }

    public void setAplicacion(AerolineaApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    private final ModelFactoryController modelFactory = ModelFactoryController.getInstance();

    @FXML
    public void initialize() {
        txtPesoMascota.setDisable(true); // Por defecto, no se muestra
        ckMascotaSi.setOnAction(event -> toggleMascota(true));
        ckMascotaNo.setOnAction(event -> toggleMascota(false));

        // Añadir opciones al ComboBox
        cbTipoEquipaje.getItems().addAll("Economica", "Ejecutiva");
    }

    private void toggleMascota(boolean isMascota) {
        txtPesoMascota.setDisable(!isMascota);
    }

    @FXML
    private void registrarEquipaje(ActionEvent event) {
        try {
            String numeroVuelo = txtNumeroVuelo.getText();

            if (!verificarVuelo(numeroVuelo)) {
                mostrarError("El número de vuelo ingresado no existe.");
                return;
            }

            double pesoEquipaje = Double.parseDouble(txtPesoEquipaje.getText());
            boolean esMascota = ckMascotaSi.isSelected();
            double pesoMascota = esMascota ? Double.parseDouble(txtPesoMascota.getText()) : 0;

            String tipoEquipaje = cbTipoEquipaje.getValue();
            ClaseVuelo claseVuelo = ClaseVuelo.valueOf(tipoEquipaje.toUpperCase());

            String categoriaViaje = tipoEquipaje.equals("Ejecutiva") ? "Internacional" : "Nacional";

            modelFactory.registrarEquipaje(numeroVuelo, pesoEquipaje, esMascota, pesoMascota, categoriaViaje, claseVuelo);
            registrarAccionesSistema("Registro equipaje ", 1, "Se registro el equipaje al vuelo  " + numeroVuelo);

            double precioFinal = calcularPrecioFinal(claseVuelo, pesoEquipaje, esMascota, pesoMascota);
            mostrarConfirmacion(numeroVuelo, pesoEquipaje, esMascota, pesoMascota, claseVuelo, precioFinal);

        } catch (NumberFormatException e) {
            mostrarError("Error en el formato de peso.");
        }
    }

    private boolean verificarVuelo(String numeroVuelo) {
        return modelFactory.buscarTiquetePorNumero(numeroVuelo) != null;
    }

    private double calcularPrecioFinal(ClaseVuelo claseVuelo, double pesoEquipaje, boolean esMascota, double pesoMascota) {
        double precioBase = claseVuelo == ClaseVuelo.ECONOMICA ? 100 : 200;
        double cargoPorEquipaje = pesoEquipaje * 2;
        double cargoPorMascota = esMascota ? pesoMascota * 5 : 0;
        return precioBase + cargoPorEquipaje + cargoPorMascota;
    }

    private void mostrarConfirmacion(String numeroVuelo, double pesoEquipaje, boolean esMascota, double pesoMascota, ClaseVuelo claseVuelo, double precioFinal) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación de Equipaje");
        alert.setHeaderText("Equipaje registrado exitosamente");
        alert.setContentText("Número de Vuelo: " + numeroVuelo +
                "\nClase de Vuelo: " + claseVuelo +
                "\nPeso del equipaje: " + pesoEquipaje + " kg" +
                "\nMascota: " + (esMascota ? "Sí" : "No") +
                (esMascota ? "\nPeso de la mascota: " + pesoMascota + " kg" : "") +
                "\nPrecio Final: $" + precioFinal);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
}
