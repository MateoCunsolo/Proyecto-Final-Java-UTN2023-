public class CarritoVacioException extends Exception
{
    private String mensaje;

    public CarritoVacioException(String elMensaje)
    {
        mensaje = elMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
