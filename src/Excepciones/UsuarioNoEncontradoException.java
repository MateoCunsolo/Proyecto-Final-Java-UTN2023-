package Excepciones;

public class UsuarioNoEncontradoException extends Exception
{
    private String nombreUsuario;

    public UsuarioNoEncontradoException(String nombreUsuario)
    {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String getMessage()
    {
        return "El usuario" + nombreUsuario + "no ha sido encontrado en el sistema";
    }
}
