package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.AerolineaApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;

public class CompraViewController {

    @FXML
    private Spinner<?> SpinPersonas;

    @FXML
    private Button btnAsientos;

    @FXML
    private Button btnCompra;

    @FXML
    private ComboBox<?> comboClase;

    @FXML
    private ComboBox<?> comboRuta;

    @FXML
    private DatePicker dateRegreso;

    @FXML
    private DatePicker dateSalida;

    @FXML
    private RadioButton radioIda;

    @FXML
    private RadioButton radioIdaVuelta;
    
    private AerolineaApplication aplicacion;

        public void setAplicacion(AerolineaApplication aplicacion) {
            this.aplicacion = aplicacion;
        }

}