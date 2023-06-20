package TPFinalLab3.Clases;
import java.io.Serializable;

public class Hotel implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = 2885413278418867930L;


    /** ATRIBUTOS **/
    String nombreHotel;
    ColeccionGenerica<Habitacion> listaHabitaciones;
    ColeccionGenerica<Cliente> listaClientes;
    ColeccionGenerica<Persona> listaEmpleados;
    ColeccionGenerica<Registro> listaRegistro;


    /** CONSTRUCTOR **/
    public Hotel()
    {
        this.nombreHotel = null;
        this.listaHabitaciones = new ColeccionGenerica<>();
        this.listaClientes = new ColeccionGenerica<>();
        this.listaEmpleados = new ColeccionGenerica<>();
        this.listaRegistro = new ColeccionGenerica<>();
    }
    public Hotel(String nombreHotel)
    {
        this.nombreHotel = nombreHotel;
        this.listaHabitaciones = new ColeccionGenerica<>();
        this.listaClientes = new ColeccionGenerica<>();
        this.listaEmpleados = new ColeccionGenerica<>();
        this.listaRegistro = new ColeccionGenerica<>();
    }



    /** GETTERS **/
    public String getNombreHotel()
    {
        return nombreHotel;
    }
    public ColeccionGenerica<Habitacion> getListaHabitaciones()
    {
        return listaHabitaciones;
    }
    public ColeccionGenerica<Cliente> getListaClientes()
    {
        return listaClientes;
    }
    public ColeccionGenerica<Persona> getListaEmpleados()
    {
        return listaEmpleados;
    }
    public ColeccionGenerica<Registro> getListaRegistro()
    {
        return listaRegistro;
    }



    /** SETTERS **/
    public void setNombreHotel(String nombreHotel)
    {
        this.nombreHotel = nombreHotel;
    }
    public void setListaHabitaciones(ColeccionGenerica<Habitacion> listaHabitaciones)
    {
        this.listaHabitaciones = listaHabitaciones;
    }
    public void setListaClientes(ColeccionGenerica<Cliente> listaClientes)
    {
        this.listaClientes = listaClientes;
    }
    public void setListaEmpleados(ColeccionGenerica<Persona> listaEmpleados)
    {
        this.listaEmpleados = listaEmpleados;
    }
    public void setListaRegistro(ColeccionGenerica<Registro> listaRegistro)
    {
        this.listaRegistro = listaRegistro;
    }



    /** METODOS **/
    public void agregarCliente(Cliente cliente)
    {
        listaClientes.agregar(cliente);
    }
    public void agregarEmpleado(Persona empleado)
    {
        listaEmpleados.agregar(empleado);
    }
    public void agregarHabitacion(Habitacion habitacion)
    {
        listaHabitaciones.agregar(habitacion);
    }
    public void agregarRegistro(Registro registro)
    {
        listaRegistro.agregar(registro);
    }



    public void mostrarEmpleados()
    {
        listaEmpleados.listar();
    }
    public void mostrarRecepcionistas()
    {
        for(Persona aux : listaEmpleados)
        {
            if(aux instanceof Recepcionista)
            {
                System.out.println(aux.toString());
            }
        }
    }
    public void mostrarClientes()
    {
        listaClientes.listar();
    }
    public void mostrarHabitaciones()
    {
        for(Habitacion aux : listaHabitaciones)
        {
            aux.datosHabitacion();
        }
    }
    public void mostrarHabitacionesOrdenado()
    {
        System.out.println("HABITACIONES DISPONIBLES:");
        for(Habitacion auxHab : listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.DISPONIBLE)
            {
                auxHab.datosHabitacion();
            }
        }

        System.out.println("\n\n\nHABITACIONES OCUPADAS:");
        for(Habitacion auxHab : listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.OCUPADO)
            {
                auxHab.datosHabitacion();
            }
        }

        System.out.println("\n\n\nHABITACIONES RESERVADAS:");
        for(Habitacion auxHab : listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.RESERVADO)
            {
                auxHab.datosHabitacion();
            }
        }

        System.out.println("\n\n\nHABITACIONES EN MANTENIMIENTO:");
        for(Habitacion auxHab : listaHabitaciones)
        {
            if(auxHab.getEstado() == Habitacion.Estado.MANTENIMIENTO)
            {
                auxHab.datosHabitacion();
            }
        }
    }
    public void mostrarRegistro()
    {
        listaRegistro.listar();
    }

    public Registro buscarRegistro(int dniBuscado)
    {
        Registro encontrado = null;

        for(Registro aux: listaRegistro)
        {
            if(aux.getOcupante().getDni() == dniBuscado)
            {
                encontrado = aux;
            }
        }
        return encontrado;
    }


    public void datosHotel()
    {
        System.out.println("-------------------------");
        System.out.println("DATOS HOTEL");
        System.out.println("-------------------------");
        System.out.println("Nombre Hotel: " + nombreHotel);
        System.out.println("Lista Empleados: ");
        mostrarEmpleados();
        System.out.println("Lista Habitaciones: ");
        mostrarHabitaciones();
        System.out.println("-------------------------");
    }
    public Administrador retornarAdministrador()
    {
        Administrador admin = null;
        for(Persona aux : listaEmpleados)
        {
            if(aux instanceof Administrador)
            {
                admin = (Administrador) aux;
            }
        }
        return admin;
    }
    public Recepcionista retornarRecepcionista()
    {
        Recepcionista empleado = null;
        for(Persona aux : listaEmpleados)
        {
            if(aux instanceof Recepcionista)
            {
                empleado = (Recepcionista) aux;
            }
        }
        return empleado;
    }

}
