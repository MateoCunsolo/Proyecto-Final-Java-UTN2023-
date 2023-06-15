package Transacciones;
import ClasesGenericas.ContenedorLHS;
import Interfaces.ITransaccionable;
import clasesItem.Item;

import java.io.Serializable;
import java.util.Objects;

public class Venta implements ITransaccionable, Serializable
{
    double totalCobrar;
    private ContenedorLHS<Item> productos;

    public Venta() {
        totalCobrar = 0;
        productos = new ContenedorLHS<>();
    }

    public Venta(double totalCobrar) {
        this.totalCobrar = totalCobrar;
        productos = new ContenedorLHS<>();
    }

    public double getTotalCobrar() {
        return totalCobrar;
    }

    public void setTotalCobrar(double totalCobrar) {
        this.totalCobrar = totalCobrar;
    }


    @Override
    public double calcularTotal() {
        //funcion como calcular
        return 0;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "totalCobrar=" + totalCobrar +
                ", productos=" + productos +
                '}';
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
