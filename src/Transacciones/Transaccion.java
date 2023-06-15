package Transacciones;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Transaccion implements Serializable
{
    private LocalDateTime fecha;
}
