package Excepciones;

public class UsuarioContraseniaInvalidoException extends Exception
{
    private String mensaje;
    public UsuarioContraseniaInvalidoException ()
    {
        mensaje = "";
    }

    public UsuarioContraseniaInvalidoException(String mensaje)
    {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage()
    {
        return mensaje;
    }

}
