package Transacciones;
import ClasesGenericas.ContenedorLHS;
import clasesItem.Item;

public class Venta
{
    double totalCobrar;
    private ContenedorLHS<Item> productos;

    public Venta() {
    }

    public Venta(double totalCobrar, ContenedorLHS<Item> productos) {
        this.totalCobrar = totalCobrar;
        this.productos = productos;
    }
}
