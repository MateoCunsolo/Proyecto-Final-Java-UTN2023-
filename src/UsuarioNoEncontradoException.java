public class UsuarioNoEncontradoException extends Exception
{
    private String nombreUsuario;

    public UsuarioNoEncontradoException(String elNombreUsuario)
    {
        this.nombreUsuario = elNombreUsuario;
    }

    @Override
    public String getMessage()
    {
        return nombreUsuario + " no encontrado ";
    }
}
