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

    public boolean vacio()
    {
        return productos.vacio();
    }


    public void agregarAlCarrito(Item item)
    {
        if(productos.vacio())
        {
            fecha = LocalDateTime.now();
        }
        if(!productos.contiene(item))
        {
            productos.agregar(item);
            setCantidadItems(getCantidadItems()+1);
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
        double resultado = 0;
        for(int i = 0; i < productos.tamanio(); i++)
        {
            resultado = resultado + productos.get(i).getPrecio();
        }
        return resultado;
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

    public int tamanioCarrito()
    {
        return productos.contar();
    }

    public Item getItem(int index)
    {
        return productos.get(index);
    }
}
