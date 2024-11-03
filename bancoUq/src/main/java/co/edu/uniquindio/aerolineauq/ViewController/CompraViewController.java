package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.claseVuelo;
import co.edu.uniquindio.aerolineauq.model.Ruta;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private ComboBox<String> comboRuta;

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

     //   comboRuta.getItems().addAll(Ruta.values());
        comboClase.getItems().addAll(Arrays.toString(claseVuelo.values()));

        SpinPersonas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
    }

    private void actualizarVisibilidadDateRegreso() {
        dateRegreso.setVisible(radioIdaVuelta.isSelected());
        labelRegreso.setVisible(radioIdaVuelta.isSelected());
    }

    @FXML
    public void realizarCompra() {
        // Validación de campos requeridos
        if (comboRuta.getValue() == null || comboClase.getValue() == null ||
                dateSalida.getValue() == null || SpinPersonas.getValue() == null ||
                (radioIdaVuelta.isSelected() && dateRegreso.getValue() == null)) {

            mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
            return;
        }

        // Obtener datos de la compra
        String ruta = comboRuta.getValue();
        String clase = comboClase.getValue();
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