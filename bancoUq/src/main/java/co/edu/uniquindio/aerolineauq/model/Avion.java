package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avion {
    private String tipoAvion;
    private int capacidadPasajeros;
    private double capacidadCarga;
    private claseVuelo claseVuelo;
}
