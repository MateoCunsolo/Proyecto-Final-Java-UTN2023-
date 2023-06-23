package Excepciones;
/**
 * La clase UsuarioNoEncontradoException es una excepción personalizada que se lanza cuando se intenta acceder a un usuario
 * que no ha sido encontrado en el sistema.
 */
public class UsuarioNoEncontradoException extends Exception
{

    private String nombreUsuario;
    /**
     * Crea una instancia de la excepción UsuarioNoEncontradoException con el nombre de usuario especificado.
     *
     * @param nombreUsuario El nombre de usuario que no ha sido encontrado.
     */
    public UsuarioNoEncontradoException(String nombreUsuario)
    {
        this.nombreUsuario = nombreUsuario;
    }
    /**
     * Obtiene el mensaje de error que indica que el usuario no ha sido encontrado en el sistema.
     *
     * @return El mensaje de error.
     */
    @Override
    public String getMessage()
    {
        return "El usuario" + nombreUsuario + "no ha sido encontrado en el sistema";
    }
}
