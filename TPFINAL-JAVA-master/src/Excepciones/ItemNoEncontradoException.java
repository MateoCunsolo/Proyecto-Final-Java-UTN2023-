package Excepciones;

/**
 * La clase ItemNoEncontradoException es una excepción personalizada que se lanza cuando no se encuentra un item
 * en una colección o contenedor.
 */
public class ItemNoEncontradoException extends Exception
{
    private String mensaje;
    /**
     * Crea una instancia de la excepción ItemNoEncontradoException con un mensaje específico.
     *
     * @param elMensaje El mensaje de la excepción.
     */
    public ItemNoEncontradoException(String elMensaje)
    {
        mensaje = elMensaje;
    }
    /**
     * Obtiene el mensaje de la excepción.
     *
     * @return El mensaje de la excepción.
     */
    public String getMensaje() {
        return mensaje;
    }
}
