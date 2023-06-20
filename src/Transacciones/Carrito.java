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

    public int getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(int cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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

    public String listar()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Fecha: ").append(getFecha()).append("\n")
                .append("Total pagado: ").append(getTotalAPagar()).append("\n")
                .append("Productos:\n");

        // Iterar sobre los productos y agregar información relevante
        for (int i = 0; i < productos.tamanio(); i++) { //recorre los productos comprados

            Item item = productos.get(i);

            sb.append(item.toString())
                    .append("\n");
        }

        return sb.toString();
    }
}
