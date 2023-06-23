package Interfaces;
/**
 * La interfaz ITransaccionable define el método para calcular el total de una transacción.
 */
public interface ITransaccionable
{
    /**
     * Calcula el total de una transacción.
     *
     * @return El total de la transacción como un valor decimal.
     */
    double calcularTotal();
}
