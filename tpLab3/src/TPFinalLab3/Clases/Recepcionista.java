package TPFinalLab3.Clases;
import java.io.Serializable;
import java.util.Scanner;

public class Recepcionista extends Persona implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -739450841937426159L;

    /** ATRIBUTOS **/
    private String password;
    private double sueldo;
    private static Scanner teclado = new Scanner(System.in);

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

    public Cliente existeCliente(int dato, Hotel datos) /** Si no existe lanzar excepcion **/
    {
        Cliente encontrado = null;
        for(Cliente aux: datos.listaClientes)
        {
            if(dato == aux.getDni())
            {
                encontrado = aux;
            }
        }
        return encontrado;
    }
    public Cliente agregarCliente(Hotel datos)
    {
        Cliente aux = new Cliente();

        System.out.println("\nPor favor ingrese su DNI: ");
        int dni = teclado.nextInt();

        aux = existeCliente(dni, datos);

        if(aux == null)
        {
            aux.setDni(dni);

            System.out.println("\nPor favor ingrese su nombre: ");
            aux.setNombre(teclado.next());

            System.out.println("\nIngrese su direccion: ");
            aux.setDireccion(teclado.next());

            System.out.println("\nIngrese el saldo de su cuenta: ");
            aux.setSaldo(teclado.nextInt());
        }

        return aux;
    }
    public void checkIn(Hotel datos) /** Llama a "agregarCliente()" **/
    {
        Cliente aux = agregarCliente(datos);


    }

    public void checkOut()
    {

    }

//    public void verHabitacionesPorEstado(ColeccionGenerica<Habitacion> lista, String estado)
//    {
//        for(Habitacion aux : lista)
//        {
//            if(lista.get)
//            {
//
//            }
//        }
//    }
    public void hacerReserva(Hotel unHotel)
    {
        System.out.println("Que habitacion desea reservar?");


    }
    public void cancelarReserva()
    {

    }

    public void verHabitaciones(Hotel datos) /** MIRA TODAS LAS HABITACIONES JUNTO A SUS ESTADOS **/
    {

    }

}
