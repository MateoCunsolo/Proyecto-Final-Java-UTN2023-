import java.util.Objects;

public abstract class Persona
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(nombre, persona.nombre) && Objects.equals(contrasenia, persona.contrasenia);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(nombre, contrasenia);
    }
}
