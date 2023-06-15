package Transacciones;

import ClasesGenericas.ContenedorV;
import Interfaces.ITransaccionable;
import clasesItem.Item;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Carrito implements ITransaccionable, Serializable
{
    private int cantidadItems;
    private double totalAPagar;
    private LocalDateTime fecha;
    private ContenedorV<Item> productos;


    public Carrito() {
        cantidadItems = 0;
        totalAPagar = 0;
        fecha = null;
        productos = new ContenedorV<>();
    }

    public Carrito(int cantidadItems, double totalAPagar, LocalDateTime fecha, ContenedorV<Item> productos) {
        this.cantidadItems = cantidadItems;
        this.totalAPagar = totalAPagar;
        this.fecha = fecha;
        this.productos = productos;
    }

    public boolean eliminarCarrito() {
        this.productos = null; //eliminar todos los items del contenedor
        return false;
    }

    public boolean eliminarUnItem(Item item) {
        return false;
    }

    public void verCarrito()
    {
    }
    public boolean agregarAlCarrito(Item item)
    {
        return false;
    }

    @Override
    public double calcularTotal()
    {
        return 0;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "cantidadItems=" + cantidadItems +
                ", totalAPagar=" + totalAPagar +
                ", fecha=" + fecha +
                ", productos=" + productos +
                '}';
    }
}
