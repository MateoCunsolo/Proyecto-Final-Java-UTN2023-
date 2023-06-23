package Excepciones;
/**
 * La clase DiferenteRarezaException es una excepción personalizada que se lanza cuando se intenta realizar un
 * intercambio entre cartas de distinta rareza.
 */
public class DiferenteRarezaException extends Exception
{
    /**
     * Crea una instancia de la excepción DiferenteRarezaException sin parámetros.
     */
    public DiferenteRarezaException()
    {
    }
    /**
     * Obtiene el mensaje de la excepción.
     *
     * @return El mensaje de la excepción.
     */
    @Override
    public String getMessage() {
        return "Cartas de distinta rareza, intercambio invalido";
    }
}
