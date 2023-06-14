public class ValorInvalidoException extends Exception
{
    private String mensaje;

    public ValorInvalidoException(String elMensaje)
    {
        this.mensaje = elMensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
