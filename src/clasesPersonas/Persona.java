package clasesPersonas;

import java.io.Serializable;
import java.util.Objects;

public abstract class Persona implements Serializable
{
    private static final long serialVersionUID = -8259035436897036695L;

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

<<<<<<< HEAD
  public boolean compararContrasenias(String passoword)
=======
    @Override
    public String toString() {
        return "\n| Nombre: " + nombre +              " \n"
                + "\n| contraseña: " + contrasenia +              " \n";

    }

    public boolean compararContrasenias(String passoword)
>>>>>>> 67a036161e046acd035c7ceabd1523fa52fab1c5
    {
        boolean rta = false;
        if(this.contrasenia.equals(passoword))
        {
            rta = true;
        }
        return rta;
    }

    @Override
    public String toString() {
        return  "\n\n\n\n\n\n==================================================================================================================================================================================================================================================================================="+
                "\n\n\n\n\n\n*****************************************\n"+
                "\t\tNombre de usuario: " + nombre +" \n"+
                "\t\tContrasenia      : " + contrasenia +" \n"+
                "*****************************************\n";
    }

}
