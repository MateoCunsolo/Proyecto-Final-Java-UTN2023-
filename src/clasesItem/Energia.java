package clasesItem;

import java.io.Serializable;

/**
 * La clase Energia representa los diferentes tipos de energía utilizados en el juego de cartas.
 * Cada tipo de energía está definido como un elemento del enumerado Energia.
 */
public enum Energia implements Serializable
{

    EMPTY(" "), LIGHTNING("Trueno"), COLORLESS("Incolora"), GRASS("Planta"),
    WATER("Agua"), PSYCHIC("Psiquico"), DARKNESS("Oscuro"), FIRE("Fuego"),
    METAL("Metal"), FIGHTING("Lucha"), DRAGON("Dragon"), FAIRY("Hada"), INEXISTENTE("Inexistente");

    private String nombre;

    /**
     * Constructor de Energia.
     *
     * @param nombre El nombre asociado al tipo de energía.
     */
    private Energia(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del tipo de energía.
     *
     * @return El nombre del tipo de energía.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve una representación en forma de cadena del tipo de energía.
     *
     * @return La representación en forma de cadena del tipo de energía.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
