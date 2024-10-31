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
    private Usuario usuario;
    private Ruta ruta;
    private double precio;
    private claseVuelo claseVuelo;
    private Silla silla;
    private TipoViaje tipoViaje;
    //Lista de sillas seleccionadas
}
