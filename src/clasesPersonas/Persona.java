package clasesPersonas;

import java.io.Serializable;
import java.util.Objects;

public abstract class Persona implements Serializable
{
    private String nombre;
    private String contrasenia;

    public Persona(String nombre, String contrasenia)
    {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public Persona()
    {
        nombre = " ";
        contrasenia= " ";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(nombre, persona.nombre) && Objects.equals(contrasenia, persona.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, contrasenia);
    }

    @Override
    public String toString()
    {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
