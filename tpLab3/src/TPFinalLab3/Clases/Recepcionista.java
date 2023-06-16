package TPFinalLab3.Clases;
import java.io.Serializable;
import java.util.Collection;
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

            datos.listaClientes.agregar(aux);
        }

        return aux;
    }

    public void mostrarHabitacionesDisponibles(ColeccionGenerica<Habitacion> listaHabitaciones)
    {
        for(int i = 0;  i < listaHabitaciones.tamanio(); i++)
        {
            if(listaHabitaciones.obtener(i).getEstado() == Habitacion.Estado.DISPONIBLE)
            {
                listaHabitaciones.obtener(i).toString();
            }
        }
    }

    private int buscarHabitacion(int id, ColeccionGenerica<Habitacion> listaHabitaciones)
    {
        int i = 0;
        int pos = -1;
        while(i < listaHabitaciones.tamanio() && id != listaHabitaciones.obtener(i).getId())
        {
            i++;
        }
        if(id == listaHabitaciones.obtener(i).getId())
        {
            pos = i;
        }

        return pos;
    }

    private boolean consultarSaldo(Cliente aux, double precio) 
    {
        boolean resultado = false;
        char resp;
        do 
        {
            System.out.println("Usted no tiene el saldo suficiente para pagar la habitacion");
            System.out.println("Precio habitacion" + precio);
            System.out.println("Quiere cargar mas saldo? (S/N)");
            resp = teclado.nextLine().charAt(0); 
            if(resp == 'S')
            {
                System.out.println("Ingrse la cantidad a cargar: ");
                aux.setSaldo(teclado.nextInt());
            }
        }while(resp != 'N' && aux.getSaldo() < precio);
        if(aux.getSaldo() >= precio)
        {
            resultado = true;
        }
        return resultado;
    }

    public void checkIn(Hotel datos) /** Llama a "agregarCliente()" **/
    {
        Cliente aux = agregarCliente(datos);
        mostrarHabitacionesDisponibles(datos.listaHabitaciones);
        System.out.println("Ingrese el id de la habitacion que desea: ");
        int pos = buscarHabitacion(teclado.nextInt(), datos.listaHabitaciones);
        if(pos == -1)
        {
            System.out.println("El id ingresado no le corresponde a ninguna habitacion");
        }
        else
        {
            boolean resp = consultarSaldo(aux, datos.listaHabitaciones.obtener(pos).getPrecio());
            if(resp == true)
            {
                aux.setSaldo(aux.getSaldo() - datos.listaHabitaciones.obtener(pos).getPrecio());
                datos.listaHabitaciones.obtener(pos).setEstado(Habitacion.Estado.OCUPADO);
                /** FALTA METODO PARA CARGAR REGISTRO **/ 
            }
        }
        
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
