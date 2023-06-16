package TPFinalLab3.Clases;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedHashSet;

public class ColeccionGenerica <T> implements Serializable
{
    /** ATRIBUTOS **/
    private static final long serialVersionUID = -502083389422603888L;
    Set<T> listaGenerica = new LinkedHashSet<>();

    /** CONSTRUCTOR **/
    public ColeccionGenerica() {}

    /** GETTERS **/
    public Set<T> getListaGenerica() {return listaGenerica;}

    /** SETTERS **/
    public void setListaGenerica(Set<T> listaGenerica) {this.listaGenerica = listaGenerica;}

    /** METODOS **/
    public void agregar(T t) {listaGenerica.add(t);}
    public void eliminar(T t) {listaGenerica.remove(t);}
    public void listar(){
        for(T t : listaGenerica){
            t.toString();
        }
    }
}
