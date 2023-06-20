package Transacciones;

import ClasesGenericas.ContenedorV;
import Interfaces.ITransaccionable;
import clasesItem.Item;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Carrito implements ITransaccionable, Serializable
{
    private static final long serialVersionUID = -7604144828635493406L;

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

    public void eliminarCarrito() {
         productos.eliminarCompleto();
    }

    public boolean eliminarUnItem(Item item) {
       return  productos.eliminar(item);
    }

    public void verCarrito()
    {
    }
    public void agregarAlCarrito(Item item)
    {
        if(productos.vacio())
        {
            fecha = LocalDateTime.now();
        }
        if(productos.agregar(item))
        {
            setCantidadItems(getCantidadItems()+1);
            setTotalAPagar(getTotalAPagar()+item.getPrecio());
        }
    }

    public Item buscarItemEnCarritoXid(String id)
    {
        Item respuesta = new Item();
        for(int i =0; i<productos.contar(); i++)
        {
            Item aux = productos.get(i);
            if(aux.getId().equals(id))
            {
                respuesta = aux;
            }
        }
        return  respuesta;
    }

    @Override
    public double calcularTotal()
    {
        return 0;
    }

    public Item ultimo()
    {
        return productos.get(productos.contar()-1);
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
