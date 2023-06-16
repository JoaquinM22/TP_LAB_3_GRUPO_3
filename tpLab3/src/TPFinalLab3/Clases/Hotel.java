package TPFinalLab3.Clases;

import java.io.Serializable;

public class Hotel implements Serializable
{
    /** ATRIBUTOS **/
    private static final long serialVersionUID = 2885413278418867930L;
    String nombre_hotel;
    ColeccionGenerica<Habitacion> listaHabitaciones = new ColeccionGenerica<>();
    ColeccionGenerica<Cliente> listaClientes = new ColeccionGenerica<>();
    ColeccionGenerica<Empleado> listaEmpleados = new ColeccionGenerica<>();
    ColeccionGenerica<Registro> listaRegistro = new ColeccionGenerica<>();

    /** CONSTRUCTOR **/
    public Hotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    /** GETTERS **/
    public String getNombreHotel() {
        return nombreHotel;
    }

    /** GETTERS **/
    public ColeccionGenerica<Habitacion> getListaHabitaciones() {return listaHabitaciones;}
    public ColeccionGenerica<Cliente> getListaClientes() {return listaClientes;}
    public ColeccionGenerica<Empleado> getListaEmpleados() {return listaEmpleados;}
    public ColeccionGenerica<Registro> getListaRegistro() {return listaRegistro;}

    /** SETTERS **/
    public void setNombreHotel(String nombreHotel) {this.nombreHotel = nombreHotel;}
    public void setListaHabitaciones(ColeccionGenerica<Habitacion> listaHabitaciones) {this.listaHabitaciones = listaHabitaciones;}
    public void setListaClientes(ColeccionGenerica<Cliente> listaClientes) {this.listaClientes = listaClientes;}
    public void setListaEmpleados(ColeccionGenerica<Empleado> listaEmpleados) {this.listaEmpleados = listaEmpleados;}
    public void setListaRegistro(ColeccionGenerica<Registro> listaRegistro) {this.listaRegistro = listaRegistro;}

    /** METODOS **/
    public void agregarCliente(Cliente cliente){
        listaClientes.agregar(cliente);
    }

    public void agregarEmpleado(Empleado empleado){
        listaEmpleados.agregar(empleado);
    }

    public void agregarHabitacion(Habitacion habitacion){
        listaHabitaciones.agregar(habitacion);
    }

    public void agregarRegistro(Registro registro){
        listaRegistro.agregar(registro);
    }
    public void mostrarEmpleados(){listaEmpleados.listar();}

    public void mostrarClientes(){listaClientes.listar();}

    public void mostrarHabitaciones(){listaHabitaciones.listar();}

    public void mostrarRegistro(){listaRegistro.listar();}

}
