package Excepciones;

public class ItemNoEncontradoException extends Exception
{
    private String mensaje;

    public ItemNoEncontradoException(String elMensaje)
    {
        mensaje = elMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
