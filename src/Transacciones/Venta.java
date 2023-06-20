package Transacciones;
import ClasesGenericas.ContenedorLHS;
import Interfaces.ITransaccionable;
import clasesItem.Item;
import clasesPersonas.Usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Venta  extends Transaccion implements ITransaccionable, Serializable
{
    double totalCobrar;
    private ContenedorLHS<Item> productos;

    public Venta() {
        super();
        totalCobrar = 0;
        productos = new ContenedorLHS<>();
    }

    public Venta(double totalCobrar) {
        super();
        this.totalCobrar = totalCobrar;
        productos = new ContenedorLHS<>();
    }

    public double getTotalCobrar() {
        return totalCobrar;
    }

    public void setTotalCobrar(double totalCobrar) {
        this.totalCobrar = totalCobrar;
    }

    private ContenedorLHS<Item> getProductos() {
        return productos;
    }

    @Override
    public LocalDateTime getFecha() {
        return super.getFecha();
    }

    @Override
    public double calcularTotal() {
        //funcion como calcular
        return 0;
    }

    @Override
    public String toString() {
        return super.toString()+ "Venta{" +
                "totalCobrar=" + totalCobrar +
                ", productos=" + productos +
                '}';
    }

     public String listar()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Fecha: ").append(getFecha()).append("\n")
                .append("Total cobrado: ").append(getTotalCobrar()).append("\n")
                .append("Productos:\n");

        // Iterar sobre los productos y agregar información relevante
        for (Item producto : productos.getMiLHSet()) {
            sb.append(producto.toString()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta venta)) return false;
        return Double.compare(venta.totalCobrar, totalCobrar) == 0 && Objects.equals(productos, venta.productos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCobrar, productos);
    }
}
