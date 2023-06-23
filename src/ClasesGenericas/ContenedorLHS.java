package ClasesGenericas;

import Interfaces.IFuncionalidades;
import clasesItem.Item;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Esta clase representa un contenedor genérico basado en LinkedHashSet que implementa la interfaz IFuncionalidades.
 * Permite almacenar elementos genéricos que extienden la clase Item y proporciona diversas funcionalidades para
 * manipular y gestionar los elementos contenidos en el contenedor.
 *
 * @param <T> El tipo de elementos que se almacenarán en el contenedor.
 */
public class ContenedorLHS <T extends Item> implements IFuncionalidades<T>, Serializable {
    private static final long serialVersionUID = -6160032998174358819L;

    private LinkedHashSet<T> miLHSet;

    /**
     * Crea una instancia de ContenedorLHS.
     * Inicializa el contenedor LinkedHashSet.
     */
    public ContenedorLHS() {
        miLHSet = new LinkedHashSet<>();
    }

    @Override
    public int contar() {
        return 0;
    }

    /**
     * Obtiene el LinkedHashSet que almacena los elementos del contenedor.
     *
     * @return El LinkedHashSet de elementos.
     */
    public LinkedHashSet<T> getMiLHSet() {
        return miLHSet;
    }
    /**
     * Verifica si el contenedor contiene un elemento específico.
     *
     * @param o El elemento a verificar.
     * @return true si el elemento está contenido en el contenedor, false de lo contrario.
     */
    public boolean contiene(T o)
    {
     return miLHSet.contains(o);
    }

    /**
     * Devuelve una representación en forma de cadena de caracteres de todos los elementos contenidos en el contenedor.
     *
     * @return Una cadena de caracteres que representa la lista de elementos contenidos en el contenedor.
     */
    @Override
    public String listar()
    {
        StringBuilder msj = new StringBuilder();
        for (T dato : miLHSet)
        {
            msj.append(dato.toString());
        }

        return msj.toString();
    }

    /**
     * Elimina el elemento especificado del contenedor.
     *
     * @param o El elemento a eliminar del contenedor.
     * @return `true` si el elemento se eliminó exitosamente, o `false` si no se encontró en el contenedor.
     */
    @Override
    public boolean eliminar(T o) {

        boolean rta = false;
        if (o != null) {
            miLHSet.remove(o);
            rta = true;
        }
        return rta;
    }

    /**
     * Agrega un elemento al contenedor.
     *
     * @param o El elemento a agregar al contenedor.
     * @return `true` si el elemento se agregó exitosamente, o `false` si ya existía en el contenedor.
     */
    @Override
    public boolean agregar(T o) {

        return miLHSet.add(o);
    }

    /**
     * Devuelve una representación en forma de cadena de texto del contenedor.
     *
     * @return Una cadena de texto que representa el contenido del contenedor.
     */
    @Override
    public String toString() {
        return ""+miLHSet;
    }

}
