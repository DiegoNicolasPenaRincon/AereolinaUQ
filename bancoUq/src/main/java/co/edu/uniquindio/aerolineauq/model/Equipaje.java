package co.edu.uniquindio.aerolineauq.model;
import java.io.Serializable;

public class Equipaje implements Serializable {
    private static final long serialVersionUID = 1L;

    private double pesoEquipaje;
    private boolean esMascota;
    private double pesoMascota;
    private String categoriaViaje; // Nacional o Internacional
    private ClaseVuelo claseVuelo; // Ejecutiva o Económica
    private double costoAdicional;

    public Equipaje() {
    }

    public Equipaje(double pesoEquipaje, boolean esMascota, double pesoMascota, String categoriaViaje, ClaseVuelo claseVuelo, double costoAdicional) {
        this.pesoEquipaje = pesoEquipaje;
        this.esMascota = esMascota;
        this.pesoMascota = pesoMascota;
        this.categoriaViaje = categoriaViaje;
        this.claseVuelo = claseVuelo;
        this.costoAdicional=costoAdicional;
    }

    public double getPesoEquipaje() {
        return pesoEquipaje;
    }

    public void setPesoEquipaje(double pesoEquipaje) {
        this.pesoEquipaje = pesoEquipaje;
    }

    public boolean isEsMascota() {
        return esMascota;
    }

    public void setEsMascota(boolean esMascota) {
        this.esMascota = esMascota;
    }

    public double getPesoMascota() {
        return pesoMascota;
    }

    public void setPesoMascota(double pesoMascota) {
        this.pesoMascota = pesoMascota;
    }

    public String getCategoriaViaje() {
        return categoriaViaje;
    }

    public void setCategoriaViaje(String categoriaViaje) {
        this.categoriaViaje = categoriaViaje;
    }

    public ClaseVuelo getClaseVuelo() {
        return claseVuelo;
    }

    public void setClaseVuelo(ClaseVuelo claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    private void calcularCostoAdicional() {
        double pesoPermitido = 0;

        if (categoriaViaje.equals("Nacional")) {
            pesoPermitido = claseVuelo == ClaseVuelo.EJECUTIVA ? 34 * 2 : 24;
        } else if (categoriaViaje.equals("Internacional")) {
            pesoPermitido = 24 * 2; // 2 piezas de 24 kg para ambas clases
        }

        double excesoPeso = pesoEquipaje > pesoPermitido ? pesoEquipaje - pesoPermitido : 0;
        costoAdicional = excesoPeso * 8 * 1.0675; // Incluye el impuesto del 6.75%

        if (esMascota) {
            costoAdicional += calcularCostoMascota();
        }
    }

    private double calcularCostoMascota() {
        if (pesoMascota < 3) return 0;
        if (pesoMascota <= 9) return 48;
        return 48 + (pesoMascota - 9) * 2;
    }


}

