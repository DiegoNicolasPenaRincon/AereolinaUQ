package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tiquete {
    private Ruta ruta;
    private double precio;
    private claseVuelo claseVuelo;
    //Lista de sillas seleccionadas
}
