package Transacciones;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Transaccion implements Serializable
{
    private LocalDateTime fecha;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return  "| Fecha: = " + fecha +"\n";
    }
}
