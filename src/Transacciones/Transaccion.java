package Transacciones;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Transaccion implements Serializable
{
    private LocalDateTime fecha;
    /**
     * Constructor de la clase Transaccion.
     * Crea una instancia de Transaccion y establece la fecha actual como fecha de la transacción.
     */
    public Transaccion() {
        this.fecha = LocalDateTime.now();
    }
    /**
     * Obtiene la fecha de la transacción.
     * @return La fecha de la transacción.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }
    /**
     * Establece la fecha de la transacción.
     * @param fecha La fecha de la transacción a establecer.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    /**
     * Devuelve una representación en forma de cadena de texto del objeto Transaccion.
     * @return La representación en forma de cadena de texto del objeto Transaccion.
     */
    @Override
    public String toString() {
        return  "| Fecha: = " + fecha +"\n";
    }
}