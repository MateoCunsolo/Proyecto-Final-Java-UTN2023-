package Excepciones;

public class CarritoVacioException extends Exception
{
    private String mensaje;

    public CarritoVacioException()
    {
        mensaje = "El carrito se encuentra vacio.";
    }

    public String getMensaje() {
        return mensaje;
    }
}
