package clasesItem;
import Interfaces.I_toJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * La clase Item representa un objeto genérico en el juego. Contiene información como el precio, el nombre del dueño,
 * la descripción y el ID del item.
 */
public class Item implements Serializable, I_toJSON, Cloneable
{
    private static final long serialVersionUID = -2301449725724955484L;
    private double precio; //chek
    private String nombreDuenio; //es el nombre del usuario
    private String descrip; //chek
    private String id; //chek

    /**
     * Constructor sin argumentos de la clase Item. Inicializa todas las variables con valores predeterminados.
     */
    public Item() {
        precio = 0;
        nombreDuenio = "";
        descrip = "";
        id = "";
    }

    /**
     * Constructor de la clase Item que acepta los valores iniciales para precio, nombre del dueño, descripción e ID.
     *
     * @param precio        El precio del item.
     * @param nombreDuenio  El nombre del dueño del item.
     * @param descrip       La descripción del item.
     * @param id            El ID del item.
     */
    public Item(double precio, String nombreDuenio, String descrip, String id) {
        this.precio = 0;
        this.nombreDuenio = nombreDuenio;
        this.descrip = descrip;
        this.id = id;
    }

    /**
     * Obtiene el precio del item.
     *
     * @return El precio del item.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del item.
     *
     * @param precio El precio del item.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del dueño del item.
     *
     * @return El nombre del dueño del item.
     */
    public String getNombreDuenio() {
        return nombreDuenio;
    }

    /**
     * Establece el nombre del dueño del item.
     *
     * @param nombreDuenio El nombre del dueño del item.
     */
    public void setNombreDuenio(String nombreDuenio) {
        this.nombreDuenio = nombreDuenio;
    }

    /**
     * Obtiene la descripción del item.
     *
     * @return La descripción del item.
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * Establece la descripción del item.
     *
     * @param descrip La descripción del item.
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    /**
     * Obtiene el ID del item.
     *
     * @return El ID del item.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del item.
     *
     * @param id El ID del item.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Compara si este item es igual a otro objeto especificado.
     *
     * @param o El objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Double.compare(item.getPrecio(), getPrecio()) == 0 && Objects.equals(getNombreDuenio(), item.getNombreDuenio()) && Objects.equals(getDescrip(), item.getDescrip()) && Objects.equals(getId(), item.getId());
    }

    /**
     * Calcula el código hash para el objeto Item basado en los valores de precio, nombre del dueño, descripción e ID.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getPrecio(), getNombreDuenio(), getDescrip(), getId());
    }

    /**
     * Retorna una representación en forma de cadena del objeto Item, mostrando sus atributos como precio, nombre del dueño,
     * descripción e ID.
     *
     * @return La representación en forma de cadena del objeto Item.
     */
    @Override
    public String toString() {
        return  "\n\n\t\t| ******************** Item ********************\n"+
                "\t\t| precio = " + precio +"\n"+
                "\t\t| nombreDuenio = " + nombreDuenio +"\n"+
                "\t\t| descrip = " + descrip +"\n"+
                "\t\t| id = " + id +"\n";
    }
    /**
     * Convierte el objeto Item a su representación JSON correspondiente.
     *
     * @return El objeto Item convertido a JSON.
     * @throws JSONException Si ocurre algún error al crear el objeto JSON.
     */
    @Override
    public JSONObject toJson() throws JSONException {
        return null;
    }

    /**
     * Convierte un objeto JSON en un objeto Item, asignando los valores correspondientes a los atributos del Item.
     *
     * @param cartaJson El objeto JSON que contiene los valores del Item.
     * @throws JSONException Si ocurre algún error al obtener los valores del objeto JSON.
     */
    @Override
    public void fromJson(JSONObject cartaJson) throws JSONException
    {

    }

    /**
     * Crea y devuelve una copia (clon) del objeto Item.
     *
     * @return Una copia del objeto Item.
     */
    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de que la clonación no sea compatible
            e.printStackTrace();
            return null;
        }
    }

}
