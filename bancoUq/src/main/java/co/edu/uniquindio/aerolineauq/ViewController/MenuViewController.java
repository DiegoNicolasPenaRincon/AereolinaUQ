package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.model.ClaseVuelo;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class MenuViewController {

    @FXML
    private AnchorPane AnchorCompras;

    @FXML
    private AnchorPane AnchorNavegacion;

    @FXML
    private TableColumn<?, ?> HistorialAsientos;

    @FXML
    private TableColumn<?, ?> HistorialPersonas;

    @FXML
    private TableColumn<?, ?> HistorialPrecio;

    @FXML
    private TableColumn<?, ?> HistorialRegreso;

    @FXML
    private TableColumn<?, ?> HistorialSalida;

    @FXML
    private Spinner<Integer> SpinPersonas;

    @FXML
    private AnchorPane anchorHistorial;

    @FXML
    private AnchorPane anchorPerfil;

    @FXML
    private Button btnActualizarPerfil;

    @FXML
    private Button btnAsientos;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCompra;

    @FXML
    private Button btnComprarTiquetes;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnVerHistorial;

    @FXML
    private TableColumn<?, ?> columnDestino;

    @FXML
    private TableColumn<?, ?> columnHora;

    @FXML
    private TableColumn<?, ?> columnOrigen;

    @FXML
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private ComboBox<?> comboCiudadOrigen;

    @FXML
    private ComboBox<String> comboClase;

    @FXML
    private ComboBox<String> comboDestinos;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private DatePicker dateRegresoViaje;

    @FXML
    private DatePicker dateSalidaViaje;

    @FXML
    private TableColumn<?, ?> historialClase;

    @FXML
    private TableColumn<?, ?> historialDestino;

    @FXML
    private TableColumn<?, ?> historialOrigen;

    @FXML
    private Label labelRegreso;

    @FXML
    private RadioButton radioIda;

    @FXML
    private RadioButton radioIdaVuelta;

    @FXML
    private TableView<?> tableHistorial;

    @FXML
    private TableView<?> tableInformacion;

    @FXML
    private TextField txtApellido;

    @FXML
    private PasswordField txtContraseniaNueva;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIDNueva;

    @FXML
    private TextField txtNombre;

    private ToggleGroup toggleGroupViaje;

    private AerolineaApplication aplicacion;

    public void setAplicacion(AerolineaApplication aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    public void cerrarSesion(){
        try{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(("Confirmación"));
            alert.setHeaderText(null);
            alert.setHeaderText("¿Esta seguro de que desea cerrar sesión?");
            Optional<ButtonType> option=alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                aplicacion.mostrarVentanaPrincipal();
            }
        }catch(Exception e){
            System.out.println("Error al abrir la ventana."+e);
        }
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
        for (ClaseVuelo clase : ClaseVuelo.values()) {
            comboClase.getItems().add(clase.toString());
        }
        radioIda.setSelected(true);
        comboClase.getSelectionModel().selectFirst();
        SpinPersonas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        AnchorCompras.setVisible(true);
        anchorPerfil.setVisible(false);
        anchorHistorial.setVisible(false);
    }

    private void actualizarVisibilidadDateRegreso() {
        dateRegresoViaje.setVisible(radioIdaVuelta.isSelected());
        labelRegreso.setVisible(radioIdaVuelta.isSelected());
    }

    public void realizarCompra() {
        // Validación de campos requeridos
        if (comboDestinos.getValue() == null || comboClase.getValue() == null ||
                dateSalidaViaje.getValue() == null || SpinPersonas.getValue() == null ||
                (radioIdaVuelta.isSelected() && dateRegresoViaje.getValue() == null)) {

            mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
            return;
        }

        // Obtener datos de la compra
        String ruta = comboDestinos.getValue();
        String clase = comboClase.getValue();
        System.out.println(comboClase.getValue());
        int cantidadPersonas = SpinPersonas.getValue();
        String tipoViaje = radioIdaVuelta.isSelected() ? "Ida y vuelta" : "Solo ida";
        String fechaSalida = dateSalidaViaje.getValue().toString();
        String fechaRegreso = radioIdaVuelta.isSelected() ? dateRegresoViaje.getValue().toString() : "N/A";

        // Lógica para procesar la compra (ej. guardar en base de datos o lista)
        // Aquí podrías llamar a un método en tu clase de modelo para almacenar la compra.
        // Por ejemplo: aplicacion.realizarCompra(ruta, clase, cantidadPersonas, tipoViaje, fechaSalida, fechaRegreso);
        //  modelFactoryController.registrarCompra();
        // Mostrar confirmación de compra

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

    @FXML
    public void mostrarCompras() {
        AnchorCompras.setVisible(true);
        anchorPerfil.setVisible(false);
        anchorHistorial.setVisible(false);
    }

    @FXML
    public void mostrarPerfil() {
        AnchorCompras.setVisible(false);
        anchorPerfil.setVisible(true);
        anchorHistorial.setVisible(false);
    }

    @FXML
    public void mostrarHistorial() {
        AnchorCompras.setVisible(false);
        anchorPerfil.setVisible(false);
        anchorHistorial.setVisible(true);
    }

}
