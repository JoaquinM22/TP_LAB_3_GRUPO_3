import TPFinalLab3.Clases.Administrador;
import TPFinalLab3.Clases.Habitacion;
import TPFinalLab3.Clases.Hotel;
import TPFinalLab3.Clases.Recepcionista;

import java.time.LocalDate;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Habitacion unaHab1 = new Habitacion(1, 5000);
        Habitacion unaHab2 = new Habitacion(2, 8000);
        Habitacion unaHab3 = new Habitacion(3, 3000);

        Recepcionista unRecepcionista = new Recepcionista("Pepe", "Luro 1212", 12345, "boca", 1200);
        Administrador unAdministrador = new Administrador("Mario", "Colon 3434", 4567, "river", 5000);


        Hotel unHotel = new Hotel("CostaGalana");
        unHotel.agregarEmpleado(unRecepcionista);
        unHotel.agregarEmpleado(unAdministrador);

        unHotel.agregarHabitacion(unaHab1);
        unHotel.agregarHabitacion(unaHab2);
        unHotel.agregarHabitacion(unaHab3);
        unHotel.datosHotel();

        unRecepcionista.hacerReserva(unHotel);
        unRecepcionista.cancelarReserva(unHotel);
        //unRecepcionista.checkIn(unHotel);
        System.out.println("Lista Habitaciones");
        unHotel.mostrarHabitaciones();

        //menu();
    }



    /** Funciones aparte del main **/
    private static void menu()
    {
        Scanner teclado = new Scanner(System.in);
        int seleccion;
        char confirmacion;
        String pass;
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
                System.out.println("\nIngrese su password por favor")
                pass = teclado.next();

                //Aca iria la funcion de busqueda de el personal/admin dentro del archivo
                do
                {
                    Persona aux = //Lo que se carga del archivo
                    if(aux.password == pass)
                    {
                        valido = true;
                    }
                }while(valido == false)
            }
             */


            if(seleccion <= 3 && seleccion > 0)
            {
                menus_especificos(teclado, seleccion);
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

    private static void menus_especificos(Scanner teclado, int ref)
    {
        int seleccion;

        do
        {
            if(ref != 0)
            {
                System.out.println("\n--------------------Menu--------------------");
                System.out.println("\n1- Opcion 1 (Placeholder)");
                System.out.println("\n2- Opcion 2 (Placeholder)");
                System.out.println("\n3- Opcion 3 (Placeholder)");
                System.out.println("\n4- Opcion 4 (Placeholder)");
            }

            //Restricciones de menu segun el tipo de usuario
            if(ref == 1 || ref == 2)
            {
                System.out.println("\n5- Opcion 5 de Personal(Placeholder)");
                System.out.println("\n6- Opcion 6 de Personal (Placeholder)");

                if(ref == 1)
                {
                    System.out.println("\nOpcion 7 ADMIN (Placeholder)");
                }
            }

            System.out.println("\n\n\n");
            System.out.println("\n0- Atras");
            System.out.println("\nSeleccione la opcion deseada: ");
            seleccion = teclado.nextInt();

            switch (seleccion)
            {
                case 1 -> System.out.println("\nSe ejecuta la opcion 1");
                case 2 -> System.out.println("\nSe ejecuta la opcion 2");
                case 3 -> System.out.println("\nSe ejecuta la opcion 3");
                case 4 -> System.out.println("\nSe ejecuta la opcion 4");
                case 5, 6 ->
                {
                    if(ref != 1 && ref != 2)
                    {
                        System.out.println("\nNo tiene permiso para realizar esta accion.");
                    }else
                    {
                        System.out.println("\nSe ejecuta la opcion 5 o 6");
                    }
                }
                case 7 ->
                {
                    if(ref != 1)
                    {
                        System.out.println("\nNo tiene permiso para realizar esta accion.");
                    }else
                    {
                        System.out.println("\nSe ejecuta la opcion 7");
                    }
                }
                case 0 -> System.out.println("\nVolviendo al inicio...");
                default -> System.out.println("\nPor favor seleccione una opcion valida.");
            }


        }while(seleccion != 0);

    }
}