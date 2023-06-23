package clasesItem;

import java.io.Serializable;
import java.util.Objects;

/**
 * Esta clase representa un ataque del Pokemon de la carta.
 * Un ataque tiene un nombre, un daño asociado y una descripción.
 */
public class Ataque implements Serializable, Cloneable
{
    private static final long serialVersionUID = -1746184358533812907L;
    private String nombreAtaque; // El nombre del ataque
    private String danio; // El daño del ataque
    private String descripcion; // La descripción del ataque

    /**
     * Crea un nuevo objeto Ataque sin valores iniciales.
     */
    public Ataque()
    {
        nombreAtaque = "";
        danio = "";
        descripcion = "";
    }
    /**
     * Crea un nuevo objeto Ataque con los valores especificados.
     *
     * @param nombreAtaque El nombre del ataque.
     * @param danio        El daño del ataque.
     * @param descripcion  La descripción del ataque.
     */
    public Ataque(String nombreAtaque, String danio, String descripcion)
    {
        this.nombreAtaque = nombreAtaque;
        this.danio = danio;
        this.descripcion = descripcion;
    }
    /**
     * Devuelve el nombre del ataque.
     *
     * @return El nombre del ataque.
     */
    public String getNombreAtaque() {
        return nombreAtaque;
    }
    /**
     * Establece el nombre del ataque.
     *
     * @param nombreAtaque El nombre del ataque a establecer.
     */
    public void setNombreAtaque(String nombreAtaque) {
        this.nombreAtaque = nombreAtaque;
    }
    /**
     * Devuelve el daño del ataque.
     *
     * @return El daño del ataque.
     */
    public String getDanio() {
        return danio;
    }
    /**
     * Establece el daño del ataque.
     *
     * @param danio El daño del ataque a establecer.
     */
    public void setDanio(String danio) {
        this.danio = danio;
    }

    /**
     * Devuelve la descripción del ataque.
     *
     * @return La descripción del ataque.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Establece la descripción del ataque.
     *
     * @param descripcion La descripción del ataque a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Compara si el objeto actual es igual a otro objeto especificado.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ataque ataque = (Ataque) o;
        return nombreAtaque.equals(ataque.nombreAtaque) && danio.equals(ataque.danio) && descripcion.equals(ataque.descripcion);
    }
    /**
     * Genera un código hash para el objeto.
     *
     * @return El código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombreAtaque, danio, descripcion);
    }
/**
 * Devuelve una representación en forma de cadena de texto del objeto Ataque.
 *
 * @return Una cadena de texto
 */
    @Override
    public String toString() {
        return  "\n\t\t\t\t|\t\t\t\t( * )" + nombreAtaque +"\n"+
                "\t\t\t\t|\t\t\t\t danio = " + danio + "\n"+
                "\t\t\t\t|\t\t\t\t descripcion = " + descripcion+" ";
    }
    /**
     * Crea y devuelve una copia exacta del objeto Ataque mediante clonación.
     * La clonación se realiza utilizando el método `clone()` heredado de la clase Object.
     * Si la clonación no es compatible, se manejará una excepción y se imprimirá la traza de la excepción.
     *
     * @return Una copia exacta del objeto Ataque.
     */
    @Override
    public Ataque clone() {
        try {
            return (Ataque) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de que la clonación no sea compatible
            e.printStackTrace();
            return null;
        }
    }
}
