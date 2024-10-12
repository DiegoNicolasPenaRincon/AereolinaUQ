module co.edu.uniquindio.aerolineauq {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.mapstruct;
    requires java.desktop;
    requires java.logging;
    requires static lombok;

    opens co.edu.uniquindio.aerolineauq to javafx.fxml;
    exports co.edu.uniquindio.aerolineauq;
    exports co.edu.uniquindio.aerolineauq.controller;
    exports co.edu.uniquindio.aerolineauq.mapping.dto;
    exports co.edu.uniquindio.aerolineauq.mapping.mappers;
    exports co.edu.uniquindio.aerolineauq.model;
    exports co.edu.uniquindio.aerolineauq.ViewController;
    opens co.edu.uniquindio.aerolineauq.controller to javafx.fxml;
}