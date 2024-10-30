package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.model.Ruta;
import co.edu.uniquindio.aerolineauq.model.claseVuelo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

public class CompraViewController {

    @FXML
    private Spinner<Integer> SpinPersonas;

    @FXML
    private Button btnCompra;

    @FXML
    private ComboBox<claseVuelo> comboClase;

    @FXML
    private ComboBox<Ruta> comboRuta;

    @FXML
    private DatePicker dateRegreso;

    @FXML
    private DatePicker dateSalida;

}