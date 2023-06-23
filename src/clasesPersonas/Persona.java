package clasesPersonas;

import java.io.Serializable;
import java.util.Objects;

/**
 * La clase abstracta Persona representa una entidad genérica de persona en el sistema.
 * Contiene los atributos y métodos comunes a todas las personas.
 */
public abstract class Persona implements Serializable
{
    private static final long serialVersionUID = -8259035436897036695L;

    private String nombre;
    private String contrasenia;

    /**
     * Constructor que crea una instancia de Persona con un nombre y contraseña especificados.
     * @param nombre El nombre de la persona.
     * @param contrasenia La contraseña de la persona.
     */
    public Persona(String nombre, String contrasenia)
    {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }
    /**
     * Constructor por defecto que crea una instancia de Persona con valores predeterminados.
     * Inicializa el nombre y la contraseña con cadenas vacías.
     */
    public Persona()
    {
        nombre = " ";
        contrasenia= " ";
    }
    /**
     * Obtiene el nombre de la persona.
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre de la persona.
     * @param nombre El nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Compara si la persona actual es igual a un objeto dado.
     * Dos personas son iguales si tienen el mismo nombre y contraseña.
     * @param o El objeto a comparar.
     * @return true si la persona es igual al objeto dado, false en caso contrario.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(nombre, persona.nombre) && Objects.equals(contrasenia, persona.contrasenia);
    }
    /**
     * Calcula el valor hash de la persona basado en su nombre y contraseña.
     * @return El valor hash de la persona.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, contrasenia);
    }
    /**
     * Compara si la contraseña dada coincide con la contraseña de la persona.
     * @param password La contraseña a comparar.
     * @return true si la contraseña coincide, false en caso contrario.
     */
    public boolean compararContrasenias(String passoword)
    {
        boolean rta = false;
        if(this.contrasenia.equals(passoword))
        {
            rta = true;
        }
        return rta;
    } /**
 * Devuelve una representación en forma de cadena de la persona.
 * @return Una cadena que representa a la persona.
 */

    @Override
    public String toString() {
        return  "\n\n\n\n\n\n==================================================================================================================================================================================================================================================================================="+
                "\n\n\n\n\n\n*****************************************\n"+
                "\t\tNombre de usuario: " + nombre +" \n"+
                "*****************************************\n";
    }

}
