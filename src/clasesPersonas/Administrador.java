package clasesPersonas;

import clasesPersonas.Persona;
import clasesPersonas.Usuario;

public class Administrador extends Persona
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

        return true;
    }

    public void verHistorialCompra(Usuario unUsuario)
    {

    }
}
