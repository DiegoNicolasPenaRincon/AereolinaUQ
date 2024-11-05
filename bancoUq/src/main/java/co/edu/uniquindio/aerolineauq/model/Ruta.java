package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String origen="Ciudad de Mexico";
    private Destino destino;
    private LocalDate duracion;
    private LocalDate horaSalida;
    private Avion avionAsignado;
}
