package co.edu.uniquindio.aerolineauq.exceptions;

public class TripulanteAsignadoException extends Exception {

    public TripulanteAsignadoException(){
        super("El estudiante ya esta asignado a este avion o se encuentra asignado a otro avion");
    }
}
