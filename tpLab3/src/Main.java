import TPFinalLab3.Clases.*;
import TPFinalLab3.Excepciones.ErrorEnArchivoException;

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
//        Habitacion unaHab1 = new Habitacion(1, 35000, "Habitacion con 2 camas y un baño");
//        Habitacion unaHab2 = new Habitacion(2, 40000, "Habitacion con 3 camas y un baño");
//        Habitacion unaHab3 = new Habitacion(3, 20000, "Habitacion con una cama y un baño");
//        Habitacion unaHab4 = new Habitacion(4, 50000, "Habitacion con 3 cama, un baño y un jacuzzi");
//        Habitacion unaHab5 = new Habitacion(5, 30000, "Habitacion con 1 cama matrimonial y un baño");
//        Habitacion unaHab6 = new Habitacion(6, 40000, "Habitacion con 2 camas matrimoniales y un baño");
//        Habitacion unaHab7 = new Habitacion(7, 55000, "Habitacion con 3 camas matrimoniales y un baño");
//        Habitacion unaHab8 = new Habitacion(8, 55000, "Habitacion con 3 camas matrimoniales, un baño y un jacuzzi");
//        Habitacion unaHab9 = new Habitacion(9, 60000, "Habitacion con 3 camas matrimoniales, una cama idividual, un baño y un jacuzzi");
//        Habitacion unaHab10 = new Habitacion(10, 70000, "Habitacion con 3 camas matrimoniales, 2 camas idividuales, un baño y un jacuzzi");
//
//
//        Recepcionista unRecepcionista = new Recepcionista("Mario Rodriguez", "Luro 1648", 22345164, "recepcionista123", 150000);
//        Administrador unAdministrador = new Administrador("Maria Gutierrez", "Constitucion 5643", 21785124, "admin123", 300000);
//
//
//        Hotel unHotel = new Hotel("CostaGalana");
//        unHotel.agregarEmpleado(unRecepcionista);
//        unHotel.agregarEmpleado(unAdministrador);
//
//        unHotel.agregarHabitacion(unaHab1);
//        unHotel.agregarHabitacion(unaHab2);
//        unHotel.agregarHabitacion(unaHab3);
//        unHotel.agregarHabitacion(unaHab4);
//        unHotel.agregarHabitacion(unaHab5);
//        unHotel.agregarHabitacion(unaHab6);
//        unHotel.agregarHabitacion(unaHab7);
//        unHotel.agregarHabitacion(unaHab8);
//        unHotel.agregarHabitacion(unaHab9);
//        unHotel.agregarHabitacion(unaHab10);


        /** LEER ARCHIVO HOTEL **/
        Hotel unHotel;
        try
        {
            unHotel = leerArchivoHotel();
//            if(unHotel != null)
//            {
//                unHotel.datosHotel();
//            }
        }catch(ErrorEnArchivoException e)
        {
            throw new RuntimeException(e);
        }

//        unHotel.datosHotel();
//        unRecepcionista.hacerReserva(unHotel);
//        unRecepcionista.cancelarReserva(unHotel);
//        //unRecepcionista.checkIn(unHotel);
//        System.out.println("Lista Habitaciones");
//        unHotel.mostrarHabitaciones();

        menu(unHotel);
    }


    /** MENU MODULARIZADO **/
    private static void menu(Hotel datos)
    {
        Scanner teclado = new Scanner(System.in);
        int seleccion;
        char confirmacion;


        do
        {
            System.out.println("\n                    Bienvenido al hotel (Placeholder)!!");
            System.out.println("\n¿Como desea ingresar al sistema?");
            System.out.println("\n1- Administrador          2- Recepcionistas          3- Cliente");
            System.out.println("\n\n\n");
            System.out.println("\n0- Salir");

            System.out.print("\nRealice su eleccion: ");
            seleccion = teclado.nextInt();

            if(seleccion <= 3 && seleccion > 0)
            {
                menus_especificos(teclado, seleccion, datos);
            }else if(seleccion == 0)
            {
                System.out.println("\nSeguro que desea salir? s/n");
                System.out.print("\nRealice su eleccion: ");
                confirmacion = teclado.next().charAt(0);

                if(confirmacion == 'n')
                {
                    seleccion = -1;
                }
            }else
            {
                System.out.println("\nOpcion invalida. Intente de nuevo.");
            }
        }while(seleccion != 0);


        //Se guardan los datos del Hotel en el archivo
        try
        {
            guardarArchivoHotel(datos);
        }catch(ErrorEnArchivoException e)
        {
            throw new RuntimeException(e);
        }

        teclado.close();
    }
    private static void menus_especificos(Scanner teclado, int ref, Hotel datos)
    {

        switch(ref)
        {
            case 1 -> /** ADMINISTRADOR **/
            {
                Persona empleado = ingresarComoAdminOEmpleado(teclado, datos, 1);
                if(empleado == null)
                {
                    System.out.println("La contrasenia es incorrecta");
                }else
                {
                    menuAdministrador(teclado, (Administrador) empleado, datos);
                }

            }
            case 2 ->/** RECEPCIONISTA **/
            {
                Persona empleado = ingresarComoAdminOEmpleado(teclado, datos, 2);
                if(empleado == null)
                {
                    System.out.println("La contrasenia es incorrecta");
                }else
                {
                    menuRecepcionista(teclado, (Recepcionista) empleado, datos);
                }
            }
            case 3 ->/** CLIENTE **/
            {
                Cliente auxCliente = new Cliente();
                menuCliente(teclado, auxCliente, datos);
            }
            case 0 -> System.out.println("\nVolviendo al inicio...");
            default -> System.out.println("Opcion invalida. Intente de nuevo");
        }
    }
    private static void menuRecepcionista(Scanner teclado, Recepcionista empleado, Hotel datos)
    {
        int seleccionado;

        do
        {
            do
            {
                System.out.println("\nBienvenido al Sistema Recepcionista " + empleado.getNombre() + "!\n\n");
                System.out.println("1- Ver Habitaciones");
                System.out.println("2- Ver Clientes");
                System.out.println("3- Consultar Sueldo");
                System.out.println("4- Hacer backUp");
                System.out.println("\n0- Volver");

                System.out.print("\nRealice su eleccion: ");
                seleccionado = teclado.nextInt();
                teclado.nextLine();

                if(seleccionado > 4 || seleccionado < 0)
                {
                    System.out.println("\nOpcion invalida. Intente de nuevo");
                }else
                {
                    empleado.accionesRecepcionista(teclado, seleccionado, datos);
                }

            }while(seleccionado > 4 || seleccionado < 0);

        }while (seleccionado != 0);

    }
    private static void menuAdministrador(Scanner teclado, Administrador empleado, Hotel datos)
    {
        int seleccionado;

        do
        {
            do
            {
                System.out.println("\nBienvenido al Sistema Administrador " + empleado.getNombre() + "!\n\n");
                System.out.println("1- Agregar Recepcionsta");
                System.out.println("2- Modificar datos Recepcionista");
                System.out.println("3- Ver Recepcionistas");
                System.out.println("4- Ver Clientes");
                System.out.println("5- Ver Habitaciones");
                System.out.println("6- Modificar Datos Habitaciones");
                System.out.println("7- Agregar Habitacion");
                System.out.println("8- Consultar Sueldo");
                System.out.println("9- Ver Registros");
                System.out.println("10- Realizar backUp");
                System.out.println("11- Leer Archivo Registros");
                System.out.println("12- Conceder permisos a Recepcionista");
                System.out.println("13- Ver datos del hotel");
                System.out.println("\n0- Volver");

                System.out.print("\nRealice su eleccion: ");
                seleccionado = teclado.nextInt();
                teclado.nextLine();

                if(seleccionado > 13 || seleccionado < 0)
                {
                    System.out.println("\nOpcion invalida. Intente de nuevo");

                }else
                {
                    empleado.accionesAdmin(teclado, seleccionado, datos);
                }

            }while(seleccionado > 13 || seleccionado < 0);

        }while (seleccionado != 0);

    }
    private static void menuCliente(Scanner teclado, Cliente unCliente, Hotel datos)
    {
        int seleccionado;

        do
        {
            do
            {
                Recepcionista aux = datos.retornarRecepcionista();
                aux.checkearReservas(datos);

                System.out.println("1- Hacer ChekIn");
                System.out.println("2- Hacer Reserva");
                System.out.println("3- Cancelar Reserva");
                System.out.println("4- Ver mis Reservas");
                System.out.println("5- Ver habitaciones");
                System.out.println("6- Ver mis habitaciones Ocupadas");
                System.out.println("7- Consultar Saldo");
                System.out.println("8- Cargar Saldo");
                System.out.println("9- Hacer Consumo");
                System.out.println("10- Hacer CheckOut");
                System.out.println("\n0- Volver");

                System.out.println("\nRealice su eleccion: ");
                seleccionado = teclado.nextInt();
                teclado.nextLine();

                if(seleccionado > 10 || seleccionado < 0)
                {
                    System.out.println("\nOpcion invalida. Intente de nuevo");

                }else
                {
                    unCliente.accionesCliente(teclado, seleccionado, datos);
                }

            }while(seleccionado > 10 || seleccionado < 0);

        }while (seleccionado != 0);

    }


    /** ESCRIBIR Y LEER ARCHIVO HOTEL**/
    private static void guardarArchivoHotel(Hotel datos) throws ErrorEnArchivoException
    {
        try
        {
            FileOutputStream fOutput = new FileOutputStream("archivo_hotel.txt");
            ObjectOutputStream objOutput = new ObjectOutputStream(fOutput);
            System.out.println("Guardando Archivo...");
            System.out.println("...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            System.out.println("...");
            pausa(1000);
            objOutput.writeObject(datos);
            objOutput.close();
            System.out.println(".... El Archivo se guardo correctamente ....");
        }catch(IOException e)
        {
            throw new ErrorEnArchivoException(1);
        }
    }
    private static Hotel leerArchivoHotel() throws ErrorEnArchivoException
    {
        Hotel unHotel = null;
        try
        {
            FileInputStream fInput = new FileInputStream("archivo_hotel.txt");
            ObjectInputStream objInput = new ObjectInputStream(fInput);
            System.out.println("Leyendo el Archivo...");
            unHotel = (Hotel) objInput.readObject();
            System.out.println(".... El Archivo se leyo correctamente ....");
        }catch(ClassNotFoundException e)
        {
            System.out.println("Error al buscar la clase");
            e.printStackTrace();
        }catch(IOException e)
        {
            throw new ErrorEnArchivoException(2);
        }

        return unHotel;
    }


    /** METODO PAUSA **/
    public static void pausa(int miliseg)
    {
        try
        {
            Thread.sleep(miliseg);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    /** VALIDAR PASSWORD **/
    public static Persona ingresarComoAdminOEmpleado(Scanner teclado, Hotel datos, int num)
    {
        Persona esCorrecta;
        String password;
        System.out.print("\nIngrese su contrasenia: ");
        teclado.nextLine();
        password = teclado.nextLine();
        if(num == 1)
        {
            esCorrecta = validarPasswordAdministrador(password, datos);
        }else
        {
            esCorrecta = validarPasswordRecepcionista(password, datos);
        }
        return esCorrecta;
    }
    public static Persona validarPasswordAdministrador(String contrasenia, Hotel datos)
    {
        Persona esCorrecta = null;
        for(Persona auxPersona : datos.getListaEmpleados())
        {
            if(auxPersona instanceof Administrador)
            {
                if(((Administrador) auxPersona).getPassword().equals(contrasenia))
                {
                    esCorrecta = auxPersona;
                }
            }
        }
        return esCorrecta;
    }
    public static Persona validarPasswordRecepcionista(String contrasenia, Hotel datos)
    {
        Persona esCorrecta = null;
        for(Persona auxPersona : datos.getListaEmpleados())
        {
            if(auxPersona instanceof Recepcionista)
            {
                if(contrasenia.equals(((Recepcionista) auxPersona).getPassword()))
                {
                    esCorrecta = auxPersona;
                }
            }
        }
        return esCorrecta;
    }

}