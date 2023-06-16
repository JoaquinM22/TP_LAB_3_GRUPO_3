package TPFinalLab3.Clases;
import java.io.Serializable;

public class Recepcionista extends Persona implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -739450841937426159L;

    /** ATRIBUTOS **/
    private String password;
    private double sueldo;

    /** CONSTRUCTOR **/
    public Recepcionista(String nombre, String direccion, int dni, String password, double sueldo)
    {
        super(nombre, direccion, dni);
        this.password = password;
        this.sueldo = sueldo;
    }

    /** SETTERS **/
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setSueldo(double sueldo)
    {
        this.sueldo = sueldo;
    }

    /** GETTERS **/
    public String getPassword()
    {
        return password;
    }
    public double getSueldo()
    {
        return sueldo;
    }

    /** METODOS **/
    @Override
    public String toString()
    {
        return  "\n--------------------" +
                "\nDATOS RECEPCIONISTA" +
                "\n--------------------" +
                "\nNombre: " + super.getNombre() +
                "\nDomicilio: " + super.getDireccion() +
                "\nDNI: " + super.getDni() +
                "\nSueldo: $" + sueldo +
                "\n--------------------";
    }

    public Cliente existeCliente(Cliente dato) /** Si no existe lanzar excepcion **/
    {
        return null;
    }
    public void agregarCliente()
    {

    }
    public void checkIn() /** Llama a "agregarCliente()" **/
    {

    }

    public void checkOut()
    {

    }

    public void hacerReserva()
    {

    }
    public void cancelarReserva()
    {

    }

    public void verHabitaciones() /** MIRA TODAS LAS HABITACIONES JUNTO A SUS ESTADOS **/
    {

    }

}
