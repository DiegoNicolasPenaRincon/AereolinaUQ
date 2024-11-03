package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Silla implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fila;
    private String posicion;
    private boolean esDisponible;
}
