package Excepciones;

public class DiferenteRarezaException extends Exception
{
    public DiferenteRarezaException()
    {
    }
    @Override
    public String getMessage() {
        return "Cartas de distinta rareza, intercambio invalido";
    }
}
