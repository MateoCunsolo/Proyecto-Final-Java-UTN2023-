package ClasesGenericas;

import Interfaces.IFuncionalidades;
import clasesItem.Carta;
import clasesItem.Item;

import java.io.Serializable;
import java.util.Vector;

/**
 * Esta clase representa un contenedor genérico que implementa la interfaz IFuncionalidades.
 * Permite almacenar y manipular elementos de tipo T utilizando un Vector.
 *
 * @param <T> El tipo de elementos que se almacenarán en el contenedor.
 */
public class ContenedorV <T> implements IFuncionalidades<T>, Serializable {
    private static final long serialVersionUID = -2561961538353586188L;

    private Vector<T> miVector; //(aplica  items, historia de compras y de ventas)

    /**
     * Crea un nuevo objeto ContenedorV con un Vector vacío.
     */
    public ContenedorV() {
        miVector = new Vector<>();
    }

    /**
     * Devuelve la cantidad de elementos almacenados en el contenedor.
     *
     * @return La cantidad de elementos en el contenedor.
     */
    @Override
    public int contar() {
        return  miVector.size();
    }
    /**
     * Devuelve una cadena de texto que representa el contenido del contenedor.
     *
     * @return Una cadena de texto que representa el contenido del contenedor.
     */
    @Override
    public String listar() {
        return "";
    }
    /**
     * Verifica si el contenedor está vacío.
     *
     * @return true si el contenedor está vacío, false en caso contrario.
     */
    public boolean vacio() {
        return miVector.isEmpty();
    }
    /**
     * Elimina un elemento específico del contenedor.
     *
     * @param o El elemento a eliminar.
     * @return true si se eliminó correctamente, false si el elemento no se encontraba en el contenedor.
     */
    @Override
    public boolean eliminar(T o) {
        return miVector.remove(o);
    }
    /**
     * Elimina todos los elementos del contenedor, dejándolo vacío.
     */
    public void eliminarCompleto()
    {
         miVector.removeAllElements();
    }
    /**
     * Agrega un elemento al contenedor.
     *
     * @param o El elemento a agregar.
     * @return true si se agregó correctamente, false en caso contrario.
     */
    @Override
    public boolean agregar(T o) {
       return miVector.add(o);
    }
    /**
     * Devuelve una representación en forma de cadena de texto del contenedor.
     *
     * @return Una cadena de texto que representa el contenedor y su contenido.
     */
    @Override
    public String toString() {
        return "ContenedorV{" +
                "miVector=" + miVector +
                '}';
    }
    /**
     * Verifica si el contenedor contiene un elemento específico.
     *
     * @param o El elemento a verificar.
     * @return true si el contenedor contiene el elemento, false en caso contrario.
     */
    public boolean contiene(T o)
    {
        return miVector.contains(o);
    }
    /**
     * Devuelve el tamaño actual del contenedor.
     *
     * @return El número de elementos almacenados en el contenedor.
     */
    public int tamanio()
    {
        return miVector.size();
    }
    /**
     * Devuelve el último elemento del contenedor y lo elimina.
     *
     * @return El último elemento del contenedor.
     */
    public T borrarUltimo()
    {
        return (T) miVector.lastElement();
    }

    /**
  * Devuelve el elemento del contenedor en la posición especificada.
  *
  * @param indice La posición del elemento a obtener.
  * @return
  * */

    public T get(int indice) {
        if (indice >= 0 && indice < miVector.size()) {
            return miVector.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
    }

}
