package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.claseVuelo;
import co.edu.uniquindio.aerolineauq.model.Ruta;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class CompraViewController {

    @FXML
    private Spinner<Integer> SpinPersonas;

    @FXML
    private Button btnAsientos;

    @FXML
    private Button btnCompra;

    @FXML
    private ComboBox<String> comboClase;

    @FXML
    private ComboBox<String> comboDestinos;

    @FXML
    private DatePicker dateRegreso;

    @FXML
    private DatePicker dateSalida;

    @FXML
    private RadioButton radioIda;

    @FXML
    private Label labelRegreso;

    @FXML
    private RadioButton radioIdaVuelta;

    private ToggleGroup toggleGroupViaje;
    
    private AerolineaApplication aplicacion;

    private String tipoAvionSeleccionado="Airbus A320";
    private String claseVueloSeleccionada;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();


    public void setAplicacion(AerolineaApplication aplicacion) {
            this.aplicacion = aplicacion;
        }

    @FXML
    public void initialize() {
        toggleGroupViaje = new ToggleGroup();
        radioIda.setToggleGroup(toggleGroupViaje);
        radioIdaVuelta.setToggleGroup(toggleGroupViaje);
        toggleGroupViaje.selectedToggleProperty().addListener((observable, oldValue, newValue) -> actualizarVisibilidadDateRegreso());
        actualizarVisibilidadDateRegreso();
        comboDestinos.getItems().addAll("Monterrey", "Cancún", "Buenos Aires", "Los Angeles", "Bogotá", "Panamá");
        comboDestinos.getSelectionModel().selectFirst();
        for (claseVuelo clase : claseVuelo.values()) {
            comboClase.getItems().add(clase.toString());
        }
        radioIda.setSelected(true);
        comboClase.getSelectionModel().selectFirst();
        SpinPersonas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
    }

    private void actualizarVisibilidadDateRegreso() {
        dateRegreso.setVisible(radioIdaVuelta.isSelected());
        labelRegreso.setVisible(radioIdaVuelta.isSelected());
    }

    @FXML
    public void realizarCompra() {
        // Validación de campos requeridos
        if (comboDestinos.getValue() == null || comboClase.getValue() == null ||
                dateSalida.getValue() == null || SpinPersonas.getValue() == null ||
                (radioIdaVuelta.isSelected() && dateRegreso.getValue() == null)) {

            mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
            return;
        }

        // Obtener datos de la compra
        String ruta = comboDestinos.getValue();
        String clase = comboClase.getValue();
        System.out.println(comboClase.getValue());
        claseVueloSeleccionada=comboClase.getValue();
        int cantidadPersonas = SpinPersonas.getValue();
        String tipoViaje = radioIdaVuelta.isSelected() ? "Ida y vuelta" : "Solo ida";
        String fechaSalida = dateSalida.getValue().toString();
        String fechaRegreso = radioIdaVuelta.isSelected() ? dateRegreso.getValue().toString() : "N/A";

        // Lógica para procesar la compra (ej. guardar en base de datos o lista)
        // Aquí podrías llamar a un método en tu clase de modelo para almacenar la compra.
        // Por ejemplo: aplicacion.realizarCompra(ruta, clase, cantidadPersonas, tipoViaje, fechaSalida, fechaRegreso);
      //  modelFactoryController.registrarCompra();
        // Mostrar confirmación de compra
        mostrarAlerta("Compra Realizada", "¡Su compra ha sido realizada exitosamente!\n" +
                "Ruta: " + ruta + "\n" +
                "Clase: " + clase + "\n" +
                "Cantidad de personas: " + cantidadPersonas + "\n" +
                "Tipo de viaje: " + tipoViaje + "\n" +
                "Fecha de salida: " + fechaSalida + "\n" +
                "Fecha de regreso: " + fechaRegreso);
    }

    @FXML
    private void seleccionarAsiento() {
        System.out.println(comboClase.getValue());
        claseVueloSeleccionada=comboClase.getValue();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/AsientosView.fxml"));
            Parent root = loader.load();

            AsientosViewController controller = loader.getController();
            controller.inicializarAsientos(tipoAvionSeleccionado, claseVueloSeleccionada); // Pasa el tipo y clase de avión seleccionados

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Seleccionar Asiento");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

}