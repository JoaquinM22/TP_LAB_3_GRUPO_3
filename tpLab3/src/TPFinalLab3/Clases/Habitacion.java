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
    private double precio;
    private Estado estado;
    private String descripcion;
    private Cliente ocupante;
    private LocalDate fechaInicioReserva = null;
    private LocalDate fechaFinReserva = null;

    public enum Estado
    {
        /** ATRIBUTOS **/
        OCUPADO("Ocupado"),
        DISPONIBLE("Disponible"),
        RESERVADO("Reservado"),
        MANTENIMIENTO("Mantenimiento");

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
    public Habitacion()
    {
        super();
    }
    public Habitacion(int id, double precio, String descripcion)
    {
        this.id = id;
        this.ocupante = null;
        this.precio = precio;
        this.estado = Estado.DISPONIBLE;
        this.descripcion = descripcion;
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
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
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
    public String getDescripcion()
    {
        return descripcion;
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
                "\nDescripcion: " + descripcion +
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
        System.out.println("Precio: $" + nuevoPrecio);
        System.out.println("Descripcion: " + descripcion);
        System.out.println(this.getOcupante().toString());
        System.out.println("Fecha inicio Reserva: " + fechaInicioReserva);
        System.out.println("Fecha Fin Reserva: " + fechaFinReserva);
        System.out.println("Total de Dias: " + cantDias);
        System.out.println("Estado: " + estado.getAbreviatura());
        System.out.println("----------------------");
    }
    public void datosHabitacionReserva()
    {
        System.out.println("----------------------");
        System.out.println("DATOS RESERVA");
        System.out.println("----------------------");
        System.out.println("Num Habitacion: " + id);
        int cantDias = (int) ChronoUnit.DAYS.between(this.getFechaFinReserva(), this.getFechaInicioReserva());
        cantDias = cantDias * (-1);
        double nuevoPrecio = cantDias*precio;
        System.out.println("Precio: $" + nuevoPrecio);
        System.out.println("Descripcion: " + descripcion);
        //System.out.println(this.getOcupante().toString());
        System.out.println("Fecha inicio Reserva: " + fechaInicioReserva);
        System.out.println("Fecha Fin Reserva: " + fechaFinReserva);
        System.out.println("Total de Dias: " + cantDias);
        System.out.println("Estado: " + estado.getAbreviatura());
        System.out.println("----------------------");
    }

    public void datosHabitacion()
    {
        System.out.println("\n\n----------------------");
        System.out.println("DATOS HABITACION");
        System.out.println("----------------------");
        System.out.println("Num Habitacion: " + id);
        System.out.println("Precio: $" + precio + "/dia");
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Estado: " + estado);
        System.out.println("----------------------");
        if(this.ocupante != null)
        {
            System.out.println(this.ocupante.toString());
        }else
        {
            System.out.println("Ocupante: Ninguno");
        }

    }
}
