package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import co.edu.uniquindio.aerolineauq.model.*;
import co.edu.uniquindio.aerolineauq.utils.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Usuario usuario;
    private Ruta rutaSeleccionada;
    private double precio;
    private ClaseVuelo claseVuelo;
    private List<Silla> sillas=new ArrayList<>();
    private TipoViaje tipoViaje;
    private LocalDate fechaViaje, fechaRegreso;
    private int cantidadPersonas;


    public EquipajeViewController() throws IOException {
    }

    public void setAplicacion(AerolineaApplication aplicacion, Usuario usuarioActual, Ruta rutaSeleccionada, double precio, ClaseVuelo claseVuelo, List<Silla> asientosSeleccionados, TipoViaje tipoViaje, LocalDate fechaSalida, LocalDate fechaRegreso, int cantidadPersonas) {
        this.aplicacion = aplicacion;
        this.usuario=usuarioActual;
        System.out.println(usuarioActual.getId());
        this.rutaSeleccionada=rutaSeleccionada;
        this.precio=precio;
        this.claseVuelo=claseVuelo;
        this.sillas=asientosSeleccionados;
        this.tipoViaje=tipoViaje;
        this.fechaViaje=fechaSalida;
        this.fechaRegreso=fechaRegreso;
        this.cantidadPersonas=cantidadPersonas;
        inicializar();

    }

    private void inicializar() {
        txtPesoMascota.setDisable(true); // Por defecto, no se muestra
        ckMascotaSi.setOnAction(event -> toggleMascota(true));
        ckMascotaNo.setOnAction(event -> toggleMascota(false));

        // Añadir opciones al ComboBox
        cbTipoEquipaje.getItems().add(claseVuelo.toString());
        cbTipoEquipaje.setEditable(false);
        txtNumeroVuelo.setText("0");
        txtNumeroVuelo.setVisible(false);
    }

    private final ModelFactoryController modelFactory = ModelFactoryController.getInstance();



    private void toggleMascota(boolean isMascota) {
        txtPesoMascota.setDisable(!isMascota);
    }

    @FXML
    private void registrarEquipaje(ActionEvent event) {
        try {

            double pesoEquipaje = Double.parseDouble(txtPesoEquipaje.getText());
            boolean esMascota = ckMascotaSi.isSelected();
            double pesoMascota = esMascota ? Double.parseDouble(txtPesoMascota.getText()) : 0;

            String tipoEquipaje = cbTipoEquipaje.getValue();
            ClaseVuelo claseVuelo = ClaseVuelo.valueOf(tipoEquipaje.toUpperCase());

            modelFactory.registrarCompraTiquete(usuario, "", rutaSeleccionada, claseVuelo, sillas, tipoViaje, fechaViaje, fechaRegreso, pesoEquipaje, esMascota, pesoMascota, cantidadPersonas);
            registrarAccionesSistema("Registro equipaje ", 1, "Se registro el equipaje al vuelo  " +"0");

            double precioFinal = modelFactory.calcularPrecio(rutaSeleccionada, claseVuelo, tipoViaje, pesoEquipaje, esMascota, pesoMascota);
            mostrarConfirmacion(usuario.getId(), claseVuelo, rutaSeleccionada, sillas, pesoEquipaje, esMascota , pesoMascota, precioFinal);
            aplicacion.mostrarVentanaMenu();
        } catch (NumberFormatException e) {
            mostrarError("Error en el formato de peso.");
        }
    }

    private boolean verificarVuelo(String numeroVuelo) {
        return modelFactory.buscarTiquetePorNumero(numeroVuelo) != null;
    }


    private void mostrarConfirmacion(String idUsuario, ClaseVuelo claseVuelo, Ruta rutaSeleccionada, List<Silla> sillas, double pesoEquipaje, boolean esMascota, double pesoMascota, double precioFinal) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra realizada con exito");
        alert.setHeaderText("Compra realizada con exito");
        alert.setContentText("Id de Usuario: " + idUsuario +
                "\nClase de Vuelo: " + claseVuelo +
                "\nRuta de Vuelo:"+ rutaSeleccionada.getOrigen()+"-"+rutaSeleccionada.getDestino()+
                "\nSillas Seleccionadas:"+sillas.toString()+
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
}
