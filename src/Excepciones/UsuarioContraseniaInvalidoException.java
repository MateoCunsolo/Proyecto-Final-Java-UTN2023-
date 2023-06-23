package Excepciones;

/**
 * La clase UsuarioContraseniaInvalidoException es una excepción personalizada que se lanza cuando se produce
 * un error relacionado con un usuario o una contraseña inválidos.
 */
public class UsuarioContraseniaInvalidoException extends Exception
{
    private String mensaje;
    /**
     * Crea una instancia de la excepción UsuarioContraseniaInvalidoException sin un mensaje específico.
     */
    public UsuarioContraseniaInvalidoException ()
    {
        mensaje = "";
    }

    /**
     * Crea una instancia de la excepción UsuarioContraseniaInvalidoException con un mensaje específico.
     *
     * @param mensaje El mensaje de la excepción.
     */
    public UsuarioContraseniaInvalidoException(String mensaje)
    {
        this.mensaje = mensaje;
    }
    /**
     * Obtiene el mensaje de la excepción.
     *
     * @return El mensaje de la excepción.
     */
    @Override
    public String getMessage()
    {
        return mensaje;
    }

}
