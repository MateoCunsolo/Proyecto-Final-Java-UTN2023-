package clasesItem;

import java.io.Serializable;

public enum Energia implements Serializable
{

    EMPTY(" "), LIGHTNING("Trueno"), COLORLESS("Incolora"), GRASS("Planta"),
    WATER("Agua"), PSYCHIC("Psiquico"), DARKNESS("Oscuro"), FIRE("Fuego"),
    METAL("Metal"), FIGHTING("Lucha"), DRAGON("Dragon"), FAIRY("Hada");

    private String nombre;

    private Energia(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Energia{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
