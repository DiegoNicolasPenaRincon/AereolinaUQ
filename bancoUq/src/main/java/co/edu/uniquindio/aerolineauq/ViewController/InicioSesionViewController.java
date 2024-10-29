package co.edu.uniquindio.aerolineauq.ViewController;

import co.edu.uniquindio.aerolineauq.controller.ModelFactoryController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class InicioSesionViewController {

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnSalir;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtID;

    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();



    @FXML
    void Ingresar(ActionEvent event) {
        String id = txtID.getText();
        String contrasenia = txtContrasenia.getText();

        // Validar que los campos no estén vacíos
        if (id.isEmpty() || contrasenia.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        // Verificar las credenciales
        boolean inicioExitoso = modelFactoryController.validarInicioSesion(id, contrasenia);

        if (inicioExitoso) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
            // Aquí puedes redirigir al usuario a la siguiente vista, si es necesario
        } else {
            mostrarAlerta("Error", "ID o contraseña incorrecta", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onSalir(ActionEvent event) {
        // Código para cerrar la aplicación o regresar a la pantalla anterior
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
