package Interfaces;
/**
 * La interfaz IFuncionalidades define un conjunto de métodos para realizar operaciones comunes en una colección de elementos de tipo T.
 * @param <T> El tipo de elementos en la colección.
 */
public interface IFuncionalidades<T>
{
    /**
 * Cuenta la cantidad de elementos en la colección.
 *
 * @return El número de elementos en la colección.
 */
    int contar();

    /**
     * Genera una representación en forma de texto de los elementos en la colección.
     *
     * @return Una cadena de caracteres que representa los elementos en la colección.
     */
    String listar();

    /**
     * Elimina un elemento de la colección.
     *
     * @param o El elemento a eliminar.
     * @return true si se elimina correctamente, false en caso contrario.
     */
    boolean eliminar(T o);

    /**
     * Agrega un elemento a la colección.
     *
     * @param o El elemento a agregar.
     * @return true si se agrega correctamente, false en caso contrario.
     */
    boolean agregar(T o);
}
