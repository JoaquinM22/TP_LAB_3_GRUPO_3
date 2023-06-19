import TPFinalLab3.Clases.*;
import TPFinalLab3.Excepciones.ErrorEnArchivoException;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Habitacion unaHab1 = new Habitacion(1, 50000000, "Habitacion con 2 camas y un baño");
        Habitacion unaHab2 = new Habitacion(2, 80000000, "Habitacion con 3 camas y un baño");
        Habitacion unaHab3 = new Habitacion(3, 30000000, "Habitacion con una cama y un baño");

        Recepcionista unRecepcionista = new Recepcionista("Pepe", "Luro 1212", 12345, "boca", 1200);
        Administrador unAdministrador = new Administrador("Mario", "Colon 3434", 4567, "river", 5000);


        Hotel unHotel = new Hotel("CostaGalana");
        unHotel.agregarEmpleado(unRecepcionista);
        unHotel.agregarEmpleado(unAdministrador);

        unHotel.agregarHabitacion(unaHab1);
        unHotel.agregarHabitacion(unaHab2);
        unHotel.agregarHabitacion(unaHab3);
//        unHotel.datosHotel();
//
//        unRecepcionista.hacerReserva(unHotel);
//        unRecepcionista.cancelarReserva(unHotel);
//        //unRecepcionista.checkIn(unHotel);
//        System.out.println("Lista Habitaciones");
//        unHotel.mostrarHabitaciones();

        menu(unHotel);
    }



//    public static void limpiarPantalla()
//    {
//        System.out.print("\r");
//        for(int i=0; i<200; i++)
//        {
//            System.out.println(" ");
//        }
//        System.out.print("\r");
//        System.out.print("\r");
//    }
    /** Funciones aparte del main **/
    private static void menu(Hotel datos)
    {
        Scanner teclado = new Scanner(System.in);
        int seleccion;
        char confirmacion;
        String pass;
        String nombre;
        boolean valido;

        do
        {
            System.out.println("\n                    Bienvenido al hotel (Placeholder)!!");
            System.out.println("\n¿Como desea ingresar al sistema?");
            System.out.println("\n1- Administrador          2- Recepcionistas          3- Cliente");
            System.out.println("\n\n\n");
            System.out.println("\n0- Salir");

            System.out.println("\nRealice su eleccion: ");
            seleccion = teclado.nextInt();

            /*
            if(seleccion == 1 || seleccion == 2)
            {
                //Aca iria la funcion de busqueda de el personal/admin dentro del archivo
                do
                {
                    System.out.println("\nIngrese su nombre:");
                    nombre = teclado.next();
                    teclado.nextLine();

                    System.out.println("\nIngrese su password por favor");
                    pass = teclado.next();
                    teclado.nextLine();

                    Persona aux = Funcion para buscar a una persona(Admin o Recepcionista) por nombre y password, de no encontrarlo retorna null
                    if(aux != null)
                    {
                        System.out.println("\nBienvenid@, " + aux.getNombre);
                        valido = true;
                    }else
                    {
                        System.out.println("\nEl password ingresado no coincide con ningun miembro del personal.")
                    }
                }while(valido == false)
            }
             */


            if(seleccion <= 3 && seleccion > 0)
            {
                menus_especificos(teclado, seleccion, datos);
            }else if(seleccion == 0)
            {
                System.out.println("\nSeguro que desea salir? s/n");
                confirmacion = teclado.next().charAt(0);

                if(confirmacion == 'n')
                {
                    seleccion = -1;
                }
            }else
            {
                System.out.println("\nLa opcion ingresada es invalida.");
            }


        }while(seleccion != 0);
        teclado.close();
    }

    private static void menus_especificos(Scanner teclado, int ref, Hotel datos)
    {
        int seleccion = ref;

//        do
//        {
//            if(ref != 0)
//            {
//                System.out.println("\n--------------------Menu--------------------");
//                System.out.println("\n1- Opcion 1 (Placeholder)");
//                System.out.println("\n2- Opcion 2 (Placeholder)");
//                System.out.println("\n3- Opcion 3 (Placeholder)");
//                System.out.println("\n4- Opcion 4 (Placeholder)");
//            }
//
//            //Restricciones de menu segun el tipo de usuario
//            if(ref == 1 || ref == 2)
//            {
//                System.out.println("\n5- Opcion 5 de Personal(Placeholder)");
//                System.out.println("\n6- Opcion 6 de Personal (Placeholder)");
//
//                if(ref == 1)
//                {
//                    System.out.println("\nOpcion 7 ADMIN (Placeholder)");
//                }
//            }
//
//            System.out.println("\n\n\n");
//            System.out.println("\n0- Atras");
//            System.out.println("\nSeleccione la opcion deseada: ");
//            seleccion = teclado.nextInt();
//
//            switch (seleccion)
//            {
//                case 1 -> System.out.println("\nSe ejecuta la opcion 1");
//                case 2 -> System.out.println("\nSe ejecuta la opcion 2");
//                case 3 -> System.out.println("\nSe ejecuta la opcion 3");
//                case 4 -> System.out.println("\nSe ejecuta la opcion 4");
//                case 5, 6 ->
//                {
//                    if(ref != 1 && ref != 2)
//                    {
//                        System.out.println("\nNo tiene permiso para realizar esta accion.");
//                    }else
//                    {
//                        System.out.println("\nSe ejecuta la opcion 5 o 6");
//                    }
//                }
//                case 7 ->
//                {
//                    if(ref != 1)
//                    {
//                        System.out.println("\nNo tiene permiso para realizar esta accion.");
//                    }else
//                    {
//                        System.out.println("\nSe ejecuta la opcion 7");
//                    }
//                }
//                case 0 -> System.out.println("\nVolviendo al inicio...");
//                default -> System.out.println("\nPor favor seleccione una opcion valida.");
//            }
//
//
//        }while(seleccion != 0);

        switch(seleccion)
        {
            case 1 -> /** ADMINISTRADOR **/
            {
                Persona empleado = ingresarComoAdminOEmpleado(teclado, datos);
                if(empleado == null)
                {
                    System.out.println("La contrasenia es incorrecta");
                }else
                {
                    if(empleado instanceof Administrador)
                    {
                        System.out.println("\nBienvenido al Sistema Administrador " + empleado.getNombre() + "!\n\n");
                        System.out.println("1- Realizar backUp");
                        System.out.println("2- Agregar Recepcionsta");
                        System.out.println("3- Modificar datos Recepcionista");
                        System.out.println("4- Ver Habitaciones");
                        System.out.println("5- Ver Clientes");
                        System.out.println("6- Ver Recepcionistas");
                        System.out.println("7- Consultar Sueldo");
                        System.out.println("\n0- Volver");

                        System.out.println("\nRealice su eleccion: ");
                        seleccion = teclado.nextInt();
                        ((Administrador) empleado).accionesAdmin(teclado, seleccion, datos);
                    }else
                    {
                        System.out.println("La contrasenia es incorrecta");
                    }
                }

            }
            case 2 ->/** RECEPCIONISTA **/
            {
                Persona empleado = ingresarComoAdminOEmpleado(teclado, datos);
                if(empleado == null)
                {
                    System.out.println("La contrasenia es incorrecta");
                }else
                {
                    if(empleado instanceof Recepcionista)
                    {
                        System.out.println("\nBienvenido al Sistema Recepcionista " + empleado.getNombre() + "!\n\n");
                        System.out.println("1- Ver Habitaciones");
                        System.out.println("2- Ver Clientes");
                        System.out.println("3- Consultar Sueldo");
                        System.out.println("4- Hacer backUp");
                        System.out.println("\n0- Volver");

                        System.out.println("\nRealice su eleccion: ");
                        seleccion = teclado.nextInt();
                        ((Recepcionista) empleado).accionesRecepcionista(teclado, seleccion, datos);
                    }else
                    {
                        System.out.println("La contrasenia es incorrecta");
                    }
                }
            }
            case 3 ->/** CLIENTE **/
            {
                System.out.println("1- Hacer ChekIn");
                System.out.println("2- Hacer Reserva");
                System.out.println("3- Cancelar Reserva");
                System.out.println("4- Ver mis Reservas");
                System.out.println("5- Ver habitaciones");
                System.out.println("6- Ver mis habitaciones");
                System.out.println("7- Ver Saldo");
                System.out.println("8- Cargar Saldo");
                System.out.println("9- Hacer Consumo");
                System.out.println("\n0- Volver");

                System.out.println("\nRealice su eleccion: ");
                seleccion = teclado.nextInt();
            }
            case 0 ->
            {
                System.out.println("\nVolviendo al inicio...");
            }
            default ->
            {
                System.out.println("Opcion invalida. Intente de nuevo");
                seleccion = -1;
            }
        }


    }

    private static Hotel leerArchivo() throws ErrorEnArchivoException
    {
        try
        {
            FileInputStream fInput = new FileInputStream(new File("archivo_hotel.txt"));
            ObjectInputStream objInput = new ObjectInputStream(fInput);
            System.out.println("Leyendo el Archivo...");
            Hotel unHotel = (Hotel) objInput.readObject();
            System.out.println(".... El Archivo se leyo correctamente ....");
            return unHotel;
        }catch(ClassNotFoundException e)
        {
            System.out.println("Error al buscar la clase");
            e.printStackTrace();
        }catch(IOException e)
        {
            throw new ErrorEnArchivoException(2);
        }

        return null;
    }

    private static void guardarListaRegistros(ColeccionGenerica<Registro> lista) throws ErrorEnArchivoException
    {
        try
        {
            FileOutputStream fOutput = new FileOutputStream(new File("registros.txt"));
            ObjectOutputStream objOutput = new ObjectOutputStream(fOutput);
            System.out.println("Guardando Archivo...");
            objOutput.writeObject(lista);
            objOutput.close();
            System.out.println(".... El Archivo se guardo correctamente ....");
        }catch(IOException e)
        {
            throw new ErrorEnArchivoException(1);
        }
    }
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

    public static Persona ingresarComoAdminOEmpleado(Scanner teclado, Hotel datos)
    {
        Persona esCorrecta = null;
        String password;
        System.out.print("\nIngrese su contrasenia: ");
        password = teclado.nextLine();
        teclado.nextLine();
        esCorrecta = validarPassword(password, datos);
        return esCorrecta;
    }
    public static Persona validarPassword(String contrasenia, Hotel datos)
    {
        Persona esCorrecta = null;
        for(Persona auxPersona : datos.getListaEmpleados())
        {
            if(auxPersona instanceof Recepcionista || auxPersona instanceof Administrador)
            {
                if(auxPersona instanceof Recepcionista)
                {
                    if(((Recepcionista) auxPersona).getPassword() == contrasenia)
                    {
                        esCorrecta = auxPersona;
                    }
                }else
                {
                    if(((Administrador) auxPersona).getPassword() == contrasenia)
                    {
                        esCorrecta = auxPersona;
                    }
                }
            }
        }
        return esCorrecta;
    }

}