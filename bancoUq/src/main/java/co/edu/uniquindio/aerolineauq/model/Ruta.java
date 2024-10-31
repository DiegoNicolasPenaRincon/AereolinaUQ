package co.edu.uniquindio.aerolineauq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {
    private final String origen="Ciudad de Mexico";
    private Destino destino;
    private LocalDate duracion;
    private LocalDate horaSalida;
}
