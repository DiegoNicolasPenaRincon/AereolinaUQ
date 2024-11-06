package co.edu.uniquindio.aerolineauq;

import co.edu.uniquindio.aerolineauq.ViewController.CompraViewController;
import co.edu.uniquindio.aerolineauq.ViewController.InicioSesionViewController;
import co.edu.uniquindio.aerolineauq.ViewController.RegistroViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
            primaryStage.setWidth(primaryStage.getWidth());
            primaryStage.setHeight(primaryStage.getHeight());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaRegistro(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/RegistroView.fxml"));
            Parent root = loader.load();

            RegistroViewController registroViewController = loader.getController();
            registroViewController.setAplicacion(this);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Compra de Tickets");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(primaryStage.getWidth());
            primaryStage.setHeight(primaryStage.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaCompras(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/aerolineauq/CompraView.fxml"));
            Parent root = loader.load();

            CompraViewController compraViewController = loader.getController();
            compraViewController.setAplicacion(this);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Compra de Tickets");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setWidth(primaryStage.getWidth());
            primaryStage.setHeight(primaryStage.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}