package Excepciones;

public class UsuarioContraseniaInvalidoException extends Exception
{
    public UsuarioContraseniaInvalidoException ()
    {
    }

    @Override
    public String getMessage()
    {
        return "Usuario y/o contraseña invalido ";
    }

}
