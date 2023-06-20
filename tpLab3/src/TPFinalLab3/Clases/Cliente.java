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



    /** CONSTRUCTOR **/
    public Cliente()
    {
        super();
        this.listaOcupadas = new ColeccionGenerica<>();
    }
    public Cliente(String nombre, String direccion, int dni, double saldo)
    {
        super(nombre, direccion, dni);
        this.saldo = saldo;
        this.listaOcupadas = new ColeccionGenerica<>();
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
        System.out.println("Su saldo actual es de $" + this.saldo + "pesos.");
    }

    public void agregar(Habitacion habitacion) {listaOcupadas.agregar(habitacion);}

    public void eliminar(Habitacion habitacion)
    {
        listaOcupadas.eliminar(habitacion);
    }

    public int tamanio()
    {
        return listaOcupadas.tamanio();
    } /**  **/
    public void mostrarMisHabitaciones() /** MUESTRA TODAS LAS HABITACIONES A MI NOMBRE **/
    {
        for(Habitacion hab : this.listaOcupadas)
        {
            System.out.println(hab.toString());
        }
    }
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
        this.saldo += monto;
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
            System.out.print("\nIngrese el numero referente a la accion:");
            seleccion = teclado.nextInt();
            if(seleccion < 1 || seleccion > 3)
            {
                System.out.println("Accion invalida. Intente de nuevo");
            }

        }while(seleccion < 1 || seleccion > 3);

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

    }


    /** MENU CLIENTE **/
    public void accionesCliente(Scanner teclado, int ref, Hotel datos)
    {
        int seleccion = ref;
        switch(seleccion)
        {
            case 1 -> /** Hacer CheckIn **/
            {
                System.out.println("1");
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                unRecepcionista.checkIn(datos);
                System.out.println("\n0- Volver");
                do
                {
                    System.out.println("Realice su eleccion: ");
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
                System.out.println("\n0- Volver");
                do
                {
                    System.out.println("Realice su eleccion: ");
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
                System.out.println("\n0- Volver");
                do
                {
                    System.out.println("Realice su eleccion: ");
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
                Cliente aux = unRecepcionista.existeCliente(dniIngresado, datos);
                if(aux == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    unRecepcionista.mostrarHabitacionesReservadasMismaPersona(datos, dniIngresado);
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
            }
            case 5 -> /** Ver Habitaciones Disponibles **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                unRecepcionista.mostrarHabitacionesDisponibles(datos.listaHabitaciones);
                System.out.println("\n0- Volver");
                do
                {
                    System.out.println("Realice su eleccion: ");
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
                Cliente aux = unRecepcionista.existeCliente(dniIngresado, datos);
                if(aux == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    aux.mostrarMisHabitaciones();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
            }
            case 7 -> /** Consultar Saldo **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente aux = unRecepcionista.existeCliente(dniIngresado, datos);
                if(aux == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    aux.consultarSaldo();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
            }
            case 8 -> /** Cargar Saldo **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente aux = unRecepcionista.existeCliente(dniIngresado, datos);
                if(aux == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    //unRecepcionista.cargarSaldoCliente(aux);
                    aux.cargarSaldoCliente();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
            }
            case 9 -> /** Hacer Consumo **/
            {
                Recepcionista unRecepcionista = datos.retornarRecepcionista();
                int dniIngresado = unRecepcionista.validarDNI();
                Cliente aux = unRecepcionista.existeCliente(dniIngresado, datos);
                if(aux == null)
                {
                    System.out.println("El dni ingresado no corresponde a ningun cliente");
                }else
                {
                    aux.realizarConsumo();
                    System.out.println("\n0- Volver");
                    do
                    {
                        System.out.println("Realice su eleccion: ");
                        seleccion = teclado.nextInt();
                        teclado.nextLine();
                        if(seleccion != 0)
                        {
                            System.out.println("Opcion invalida. Intente de nuevo");
                        }
                    }while(seleccion != 0);
                }
            }
            case 0 -> System.out.println("\nVolviendo al inicio...");
            default -> System.out.println("Opcion invalida. Intente de nuevo");
        }
    }
}
