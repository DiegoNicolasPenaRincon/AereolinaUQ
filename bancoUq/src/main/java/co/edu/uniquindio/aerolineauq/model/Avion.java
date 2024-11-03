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
public class Avion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tipoAvion;
    private int capacidadPasajeros;
    private double capacidadCarga;
    private claseVuelo claseVuelo;
}
