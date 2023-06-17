package TPFinalLab3.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Registro implements Serializable
{
    private static final long serialVersionUID = -6232542664770078482L;

    LocalDateTime fechaEntrada;
    LocalDateTime fechaSalida;
    Habitacion ocupada;
    double precioPagado;

    public Registro()
    {

    }
    public Registro(LocalDateTime fechaEntrada, Habitacion ocupada, double precioPagado) {
        this.fechaEntrada = fechaEntrada;
        //this.fechaSalida = fechaSalida;
        this.ocupada = ocupada;
        this.precioPagado = precioPagado;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Habitacion getOcupada() {
        return ocupada;
    }

    public void setOcupada(Habitacion ocupada) {
        this.ocupada = ocupada;
    }

    public double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(double precioPagado) {
        this.precioPagado = precioPagado;
    }


}
