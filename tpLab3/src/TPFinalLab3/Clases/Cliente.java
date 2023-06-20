package TPFinalLab3.Clases;
import TPFinalLab3.Interfaces.CargarDinero;

import java.io.Serializable;
import java.util.Scanner;

public class Cliente extends Persona implements CargarDinero, Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -1121434585563208736L;



    /** ATRIBUTOS **/
    private double saldo;
    private final static Scanner teclado = new Scanner(System.in);
    private boolean consumi = false;
    ColeccionGenerica<Habitacion> listaOcupadas;
    ColeccionGenerica<Habitacion> listaReservadas;



    /** CONSTRUCTOR **/
    public Cliente()
    {
        super();
        this.listaOcupadas = new ColeccionGenerica<>();
        this.listaReservadas = new ColeccionGenerica<>();
    }
    public Cliente(String nombre, String direccion, int dni, double saldo)
    {
        super(nombre, direccion, dni);
        this.saldo = saldo;
        this.listaOcupadas = new ColeccionGenerica<>();
        this.listaReservadas = new ColeccionGenerica<>();
    }



    /** SETTERS **/
    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }
    public void setConsumi(boolean consumi)
    {
        this.consumi = consumi;
    }
    public void setListaOcupadas(ColeccionGenerica<Habitacion> listaOcupadas)
    {
        this.listaOcupadas = listaOcupadas;
    }
    public void setListaReservadas(ColeccionGenerica<Habitacion> listaReservadas)
    {
        this.listaReservadas = listaReservadas;
    }


    /** GETTERS **/
    public double getSaldo()
    {
        return saldo;
    }
    public boolean isConsumi()
    {
        return consumi;
    }
    public ColeccionGenerica<Habitacion> getListaOcupadas()
    {
        return listaOcupadas;
    }
    public ColeccionGenerica<Habitacion> getListaReservadas()
    {
        return listaReservadas;
    }


    /** METODOS **/
    @Override
    public String toString()
    {
        return  "\n--------------------" +
                "\nDATOS CLIENTE" +
                "\n--------------------" +
                "\nNombre: " + super.getNombre() +
                "\nDomicilio: " + super.getDireccion() +
                "\nDNI: " + super.getDni() +
                "\n--------------------";
    }
    public void consultarSaldo()
    {
        System.out.println("Su saldo actual es de $" + this.saldo + " pesos.");
    }


    /** Funciones que trabajan con habitaciones **/
    public void agregarOcupada(Habitacion habitacion)
    {
        listaOcupadas.agregar(habitacion);
    }
    public void agregarReserva(Habitacion habitacion)
    {
        listaReservadas.agregar(habitacion);
    }

    public void eliminar(Habitacion habitacion)
    {
        listaOcupadas.eliminar(habitacion);
    }

    public void mostrarMisHabitaciones() /** MUESTRA TODAS LAS HABITACIONES OCUPADAS A MI NOMBRE **/
    {
        for(Habitacion hab : this.listaOcupadas)
        {
            System.out.println(hab.toString());
        }
    }
    public void mostrarMisHabitacionesReservadas() /** MUESTRA TODAS LAS HABITACIONES RESERVADAS A MI NOMBRE **/
    {
        for(Habitacion hab : this.listaReservadas)
        {
            System.out.println(hab.toString());
        }
    }


    /** Funciones del saldo del cliente **/
    public void cargarSaldoCliente() /** CARGA SALDO A MI CUENTA **/
    {
        double monto;
        do
        {
            System.out.println("Cuanto dinero quiere cargar?");
            System.out.print("\nIngrese un monto: ");
            monto = teclado.nextDouble();
            if(monto <=0)
            {
                System.out.println("El monto ingresado no puede ser 0 no un numer negativo. Intente de nuevo");
            }
        }while(monto <= 0);
        System.out.println("\nCargando Saldo...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        this.saldo += monto;
        System.out.println("... Saldo Cargado Correctamente ...");
        System.out.println("Saldo Actual: " + this.saldo);
    }
    @Override
    public void cargarSaldo(Cliente aux, double precio) /** ME PIDE CARGAR PARA PODER PAGAR **/
    {
        if(aux.getSaldo() < precio)
        {
            do
            {
                System.out.println("Su saldo es insuficiente");
                System.out.println("Precio producto: $" + precio);
                System.out.println("Saldo: $" + aux.getSaldo());
                double faltan = precio - aux.getSaldo();
                System.out.println("Faltan $" + faltan + " para poder comprar el producto");
                System.out.print("\nIngrese la cantidad a cargar: ");
                aux.setSaldo(aux.getSaldo() + teclado.nextInt());
                teclado.nextLine();
            }while(aux.getSaldo() < precio);
        }
    }


    /** Compra comida o bebida **/
    public void realizarConsumo()
    {
        System.out.println("Que consumo desea realizar?");
        System.out.println("1- Comprar bebida: $800");
        System.out.println("2- Comprar comida: $1200");
        System.out.println("3- Comprar comida y Bebida: $1800");
        System.out.println("\n0- Cancelar");
        int seleccion;
        do
        {
            System.out.print("\nIngrese el numero referente a la accion: ");
            seleccion = teclado.nextInt();
            if(seleccion < 1 || seleccion > 3)
            {
                System.out.println("Accion invalida. Intente de nuevo");
            }

        }while(seleccion < 1 || seleccion > 3);

        System.out.println("\nRealizando compra...");

        if(seleccion == 1)
        {
            cargarSaldo(this, 800);
            this.setSaldo(this.getSaldo() - 800);
        }else if(seleccion == 2)
        {
            cargarSaldo(this, 1200);
            this.setSaldo(this.getSaldo() - 1200);
        }else
        {
            cargarSaldo(this, 1800);
            this.setSaldo(this.getSaldo() - 1800);
        }

        this.setConsumi(true);

        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("...");
        pausa(1000);
        System.out.println("... Compra realizada con exito ...");
        System.out.println("Saldo Actual: " + this.saldo);
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
    /** METODO LIMPIAR PANTALLA **/
    public static void limpiarPantalla()
    {
        for(int i=0; i<100; i++)
        {
            System.out.println("\n\n\n\n\n\n\n");
        }
    }




    /** MENU CLIENTE **/
    public void accionesCliente(Scanner teclado, int ref, Hotel datos)
    {
        int seleccion = ref;
        switch(seleccion)
        {
            case 1 -> /** Hacer CheckIn **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                unRecepcionista.checkIn(datos);
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 2 -> /** Hacer Reserva **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                unRecepcionista.hacerReserva(datos);
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 3 -> /** Cancelar Reserva **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                unRecepcionista.cancelarReserva(datos);
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 4 -> /** Ver mis Reservas **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente auxCliente = unRecepcionista.existeCliente(dniIngresado, datos);
                if(auxCliente == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    System.out.println("Reservas del cliente " + auxCliente.getNombre() + " con DNI: " + auxCliente.getDni());
                    unRecepcionista.mostrarHabitacionesReservadasMismaPersona(datos, dniIngresado);
                }
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 5 -> /** Ver Habitaciones Disponibles **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                unRecepcionista.mostrarHabitacionesDisponibles(datos.listaHabitaciones);
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 6 -> /** Ver Mis Habitaciones Ocupadas **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente auxCliente = unRecepcionista.existeCliente(dniIngresado, datos);
                if(auxCliente == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    System.out.println("Habitaciones Ocupadas del cliente " + auxCliente.getNombre() + " con DNI: " + auxCliente.getDni());
                    auxCliente.mostrarMisHabitaciones();
                }
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 7 -> /** Consultar Saldo **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente auxCliente = unRecepcionista.existeCliente(dniIngresado, datos);
                if(auxCliente == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    auxCliente.consultarSaldo();
                }
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 8 -> /** Cargar Saldo **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente auxCliente = unRecepcionista.existeCliente(dniIngresado, datos);
                if(auxCliente == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    auxCliente.cargarSaldoCliente();
                }
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 9 -> /** Hacer Consumo **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente auxCliente = unRecepcionista.existeCliente(dniIngresado, datos);
                if(auxCliente == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    auxCliente.realizarConsumo();
                }
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 10 -> /** Hacer CheckOut **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                unRecepcionista.checkOut(datos);
                do
                {
                    System.out.print("\nOprima 0 para volver: ");
                    seleccion = teclado.nextInt();
                    teclado.nextLine();
                    if(seleccion != 0)
                    {
                        System.out.println("Opcion invalida. Intente de nuevo");
                    }
                }while(seleccion != 0);
            }
            case 0 -> System.out.println("\nVolviendo al inicio...");
            default -> System.out.println("Opcion invalida. Intente de nuevo");
        }
    }
}
