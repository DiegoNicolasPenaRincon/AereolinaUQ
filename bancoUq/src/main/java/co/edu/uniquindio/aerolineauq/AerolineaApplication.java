package co.edu.uniquindio.aerolineauq;

import co.edu.uniquindio.aerolineauq.ViewController.EquipajeViewController;
import co.edu.uniquindio.aerolineauq.ViewController.InicioSesionViewController;
import co.edu.uniquindio.aerolineauq.ViewController.MenuViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AerolineaApplication extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Aerolinea UQ");
        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AerolineaApplication.class.getResource("/co/edu/uniquindio/aerolineauq/InicioSesion.fxml"));
            Parent root= loader.load();
            InicioSesionViewController inicioSesionViewController=loader.getController();
            inicioSesionViewController.setAplicacion(this);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Aerolinea UQ");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(600);
            primaryStage.setHeight(405);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/MenuView.fxml"));
            Parent root = loader.load();

            MenuViewController menuViewController = loader.getController();
            menuViewController.setAplicacion(this);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Menú de Usuario");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(1100);
            primaryStage.setHeight(600);
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaEquipaje(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/Equipaje.fxml"));
            Parent root = loader.load();

            EquipajeViewController equipajeViewController = loader.getController();
            equipajeViewController.setAplicacion(this);

            Scene scene = new Scene(root);
            primaryStage.setTitle("equipaje");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(1100);
            primaryStage.setHeight(600);
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}