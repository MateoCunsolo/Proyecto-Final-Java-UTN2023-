package Excepciones;
/**
 * La clase ValorInvalidoException es una excepción personalizada que se lanza cuando se detecta un valor inválido.
 */
public class ValorInvalidoException extends Exception
{
    private String mensaje;
    /**
     * Crea una instancia de la excepción ValorInvalidoException con el mensaje especificado.
     *
     * @param elMensaje El mensaje de error que indica la causa del valor inválido.
     */
    public ValorInvalidoException(String elMensaje)
    {
        this.mensaje = elMensaje;
    }
    /**
     * Obtiene el mensaje de error que indica la causa del valor inválido.
     *
     * @return El mensaje de error.
     */
    @Override
    public String getMessage() {
        return mensaje;
    }
}
