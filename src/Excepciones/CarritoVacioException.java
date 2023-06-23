package Excepciones;

/**
 * La clase CarritoVacioException es una excepción personalizada que se lanza cuando se intenta
 * realizar una operación en un carrito de compras vacío.
 */
public class CarritoVacioException extends Exception
{
    private String mensaje;

    /**
     * Crea una instancia de la excepción CarritoVacioException con un mensaje predeterminado.
     */
    public CarritoVacioException()
    {
        mensaje = "El carrito se encuentra vacio.";
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
