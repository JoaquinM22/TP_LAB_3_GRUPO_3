package TPFinalLab3.Clases;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Habitacion implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -2367655082727167829L;

    /** ATRIBUTOS **/
    private int id;
    private Cliente ocupante;
    private double precio;
    private Estado estado;
    private LocalDate fechaInicioReserva = null;
    private LocalDate fechaFinReserva = null;

    public enum Estado
    {
        /** ATRIBUTOS **/
        OCUPADO("O"),
        DISPONIBLE("D"),
        RESERVADO("R"),
        MANTENIMIENTO("M");

        private String abreviatura;

        /** CONSTRUCTOR **/
        Estado(String abreviatura)
        {
            this.abreviatura = abreviatura;
        }

        /** GETTER **/
        public String getAbreviatura()
        {
            return abreviatura;
        }
    }

    /** CONSTRUCTOR **/
    public Habitacion(int id, double precio)
    {
        this.id = id;
        this.ocupante = null;
        this.precio = precio;
        this.estado = Estado.DISPONIBLE;
    }

    /** SETTERS **/
    public void setId(int id)
    {
        this.id = id;
    }
    public void setOcupante(Cliente ocupante)
    {
        this.ocupante = ocupante;
    }
    public void setPrecio(double precio)
    {
        this.precio = precio;
    }
    public void setEstado(Estado estado) {this.estado = estado;}
    public void setFechaInicioReserva(LocalDate fechaInicioReserva)
    {
        this.fechaInicioReserva = fechaInicioReserva;
    }
    public void setFechaFinReserva(LocalDate fechaFinReserva)
    {
        this.fechaFinReserva = fechaFinReserva;
    }

    /** GETTERS **/
    public int getId()
    {
        return id;
    }
    public Cliente getOcupante()
    {
        return ocupante;
    }
    public double getPrecio()
    {
        return precio;
    }
    public Estado getEstado()
    {
        return estado;
    }
    public LocalDate getFechaInicioReserva()
    {
        return fechaInicioReserva;
    }
    public LocalDate getFechaFinReserva()
    {
        return fechaFinReserva;
    }

    /** METODOS **/
    @Override
    public String toString()
    {
        return  "\n------------------------" +
                "\nDATOS HABITACION" +
                "\n--------------------" +
                "\nNro Habitacion: " + id +
                "\nPrecio: $" + precio + "/dia" +
                "\nEstado: " + estado +
                "\n------------------------";
    }
    public void datosReserva()
    {
        System.out.println("----------------------");
        System.out.println("DATOS RESERVA");
        System.out.println("----------------------");
        System.out.println("Num Habitacion: " + id);
        int cantDias = (int) ChronoUnit.DAYS.between(this.getFechaFinReserva(), this.getFechaInicioReserva());
        cantDias = cantDias * (-1);
        double nuevoPrecio = cantDias*precio;
        System.out.println("Precio: " + nuevoPrecio);
        System.out.println(this.getOcupante().toString());
        System.out.println("Fecha inicio Reserva: " + fechaInicioReserva);
        System.out.println("Fecha Fin Reserva: " + fechaFinReserva);
        System.out.println("Total de Dias: " + cantDias);
        System.out.println("Estado: " + estado.getAbreviatura());
        System.out.println("----------------------");
    }
    public void datosHabitacion()
    {
        System.out.println("----------------------");
        System.out.println("DATOS HABITACION");
        System.out.println("----------------------");
        System.out.println("Num Habitacion: " + id);
        if(this.ocupante != null)
        {
            System.out.println(this.ocupante.toString());
        }else
        {
            System.out.println("Ocupante: Ninguno");
        }
        System.out.println("Precio: " + precio + "/dia");
        System.out.println("Estado: " + estado);
        System.out.println("----------------------");
    }
}
