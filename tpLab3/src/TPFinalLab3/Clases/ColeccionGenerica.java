package TPFinalLab3.Clases;
import java.io.Serializable;
import java.util.*;

public class ColeccionGenerica <T> implements Serializable
{
    /** SERIAL VERSION UID **/
    private static final long serialVersionUID = -502083389422603888L;

    /** ATRIBUTOS **/
    List<T> listaGenerica = new ArrayList<>();

    /** CONSTRUCTOR **/
    public ColeccionGenerica()
    {

    }

    /** SETTERS **/
    public void setListaGenerica(List<T> listaGenerica)
    {
        this.listaGenerica = listaGenerica;
    }

    /** GETTERS **/
    public List<T> getListaGenerica()
    {
        return listaGenerica;
    }

    /** METODOS **/
    public T obtener(int index)
    {
        T aux = listaGenerica.get(index);
        return aux;
    }
    public void agregar(T t)
    {
        listaGenerica.add(t);
    }

    public void eliminar(T t)
    {
        listaGenerica.remove(t);
    }

    public void eliminar(int index)
    {
        listaGenerica.remove(index);
    }

    public void listar()
    {
//        for(int i = 0; i < listaGenerica.size(); i++)
//        {
//            System.out.println(listaGenerica.get(i).toString());
//        }

        for(T t : listaGenerica)
        {
            System.out.println(t.toString());
        }
    }
}
