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
        System.out.println("\nPor favor ingrese su DNI: ");
        int dni = teclado.nextInt();

        Cliente aux = existeCliente(dni, datos);

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
//        for(int i = 0;  i < listaHabitaciones.tamanio(); i++)
//        {
//            if(listaHabitaciones.obtener(i).getEstado() == Habitacion.Estado.DISPONIBLE)
//            {
//                listaHabitaciones.obtener(i).toString();
//            }
//        }

        for(Habitacion aux : listaHabitaciones)
        {
            if(aux.getEstado() == Habitacion.Estado.DISPONIBLE)
            {
                System.out.println(aux.toString());
            }
        }
    }

    private Habitacion buscarHabitacion(int id, ColeccionGenerica<Habitacion> listaHabitaciones)
    {
//        int i = 0;
//        int pos = -1;
//        while(i < listaHabitaciones.tamanio() && id != listaHabitaciones.obtener(i).getId())
//        {
//            i++;
//        }
//        if(id == listaHabitaciones.obtener(i).getId())
//        {
//            pos = i;
//        }
//
//        return pos;
        Habitacion encontrado = null;

        for(Habitacion aux : listaHabitaciones)
        {
            if(aux.getId() == id)
            {
                encontrado = aux;
            }
        }
        return encontrado;
    }

    private void pagarHabitacion(Cliente aux, double precio)
    {
        //boolean resultado = false;
//        if(aux.getSaldo() >= precio)
//        {
//            resultado = true;
//        }else
        if(aux.getSaldo() < precio)
        {
            //char resp;
            do
            {
                System.out.println("Usted no tiene el saldo suficiente para pagar la habitacion. Cargue saldo a su cuenta a continuacion: ");
                System.out.println("Precio habitacion: $" + precio);
                System.out.println("Ingrese la cantidad a cargar: ");
                aux.setSaldo(aux.getSaldo() + teclado.nextInt());
            }while(aux.getSaldo() < precio);
        }

        //return resultado;
    }

    public void checkIn(Hotel datos) /** Llama a "agregarCliente()" **/
    {
        Cliente aux = agregarCliente(datos);
        mostrarHabitacionesDisponibles(datos.listaHabitaciones);
        int pos;
        Habitacion existeHab;
        do
        {
            System.out.println("Ingrese el id de la habitacion que desea: ");
            existeHab = buscarHabitacion(teclado.nextInt(), datos.listaHabitaciones);
            if(existeHab == null)
            {
                System.out.println("El id ingresado no le corresponde a ninguna habitacion. Intente de nuevo");
            }

        }while(existeHab == null);


        /*boolean tieneSaldo =*/ pagarHabitacion(aux, existeHab.getPrecio());
//        if(tieneSaldo == true)
//        {
//            aux.setSaldo(aux.getSaldo() - datos.listaHabitaciones.obtener(pos).getPrecio());
//            datos.listaHabitaciones.obtener(pos).setEstado(Habitacion.Estado.OCUPADO);
//            System.out.println(".... El CheckIn se realizo exitosamene! ....");
//            /** FALTA METODO PARA CARGAR REGISTRO **/
//        }
        aux.setSaldo(aux.getSaldo() - existeHab.getPrecio());
        existeHab.setEstado(Habitacion.Estado.OCUPADO);
        /** FALTA METODO PARA CARGAR REGISTRO **/
        /** FALTA CARGAR HABITACION ELEGIDA EN LISTAOCUPADAS DE CLIENTE **/
        System.out.println(".... El CheckIn se realizo exitosamene! ....");

        
    }
    

    public void checkOut()
    {

    }

//    public void verHabitacionesPorEstado(ColeccionGenerica<Habitacion> lista, String estado)
//    {
//        for(Habitacion aux : lista)
//        {
//            if(aux.getEstado() == Enum.valueOf(Habitacion.Estado.class, "O"))
//            {
//                System.out.println(aux.toString());
//            }else if(aux.getEstado() == Enum.valueOf(Habitacion.Estado.class, "D"))
//            {
//                System.out.println(aux.toString());
//            }else if(aux.getEstado() == Enum.valueOf(Habitacion.Estado.class, "R"))
//            {
//                System.out.println(aux.toString());
//            }else if(aux.getEstado() == Enum.valueOf(Habitacion.Estado.class, "M"))
//            {
//                System.out.println(aux.toString());
//            }
//        }
//    }



//    private void señarHabitacion(Cliente aux, double precio)
//    {
//        if(aux.getSaldo() < precio)
//        {
//            //char resp;
//            do
//            {
//                System.out.println("Usted no tiene el saldo suficiente para pagar la habitacion. Cargue saldo a su cuenta a continuacion: ");
//                System.out.println("Precio habitacion: $" + precio);
//                System.out.println("Ingrese la cantidad a cargar: ");
//                aux.setSaldo(aux.getSaldo() + teclado.nextInt());
//            }while(aux.getSaldo() < precio);
//        }
//
//        //return resultado;
//    }
//
    public void hacerReserva(Hotel unHotel)
    {
        System.out.println("Que habitacion desea reservar?");
        mostrarHabitacionesDisponibles(unHotel.listaHabitaciones);
        Habitacion existeHab;
        do
        {
            System.out.println("Ingrese el id de la habitacion que desea reservar: ");
            existeHab = buscarHabitacion(teclado.nextInt(), unHotel.listaHabitaciones);
            if(existeHab == null)
            {
                System.out.println("El id ingresado no le corresponde a ninguna habitacion. Intente de nuevo");
            }

        }while(existeHab == null);






    }
    public void cancelarReserva()
    {

    }

    public void verHabitaciones(Hotel datos) /** MIRA TODAS LAS HABITACIONES JUNTO A SUS ESTADOS **/
    {

    }

}
