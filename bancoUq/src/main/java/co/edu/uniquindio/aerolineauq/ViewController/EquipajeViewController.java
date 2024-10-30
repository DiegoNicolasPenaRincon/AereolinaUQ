package co.edu.uniquindio.aerolineauq.ViewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EquipajeViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRegistrar;

    @FXML
    private ComboBox<?> cbTipoEquipaje;

    @FXML
    private CheckBox ckMascotaNo;

    @FXML
    private CheckBox ckMascotaSi;

    @FXML
    private TextField txtNumeroVuelo;

    @FXML
    private TextField txtPesoMascota;

    @FXML
    void registrarEquipaje(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }

}
