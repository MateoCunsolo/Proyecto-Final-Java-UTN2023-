package clasesPersonas;

import clasesPersonas.Persona;
import clasesPersonas.Usuario;

import java.io.Serializable;

public class Administrador extends Persona implements Serializable
{
    public Administrador(String nombre, String contrasenia)
    {
        super(nombre, contrasenia);
    }

    public Administrador()
    {

    }

    public boolean borrarUsuario(String nombre) //true si se hizo con exito, caso contrario false
    {
        boolean a = true;
        return a;
    }

    public void verHistorialCompra(Usuario unUsuario)
    {

    }
}
