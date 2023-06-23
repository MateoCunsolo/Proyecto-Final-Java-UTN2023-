package Transacciones;

import ClasesGenericas.ContenedorV;
import Interfaces.ITransaccionable;
import clasesItem.Item;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Carrito implements ITransaccionable, Serializable, Cloneable {
    private static final long serialVersionUID = -7604144828635493406L;

    private int cantidadItems;
    private double totalAPagar;
    private LocalDateTime fecha;
    private ContenedorV<Item> productos;


    public Carrito() {
        cantidadItems = 0;
        totalAPagar = calcularTotal();
        fecha = null;
        productos = new ContenedorV<>();
    }

    public Carrito(int cantidadItems, LocalDateTime fecha) {
        this.cantidadItems = cantidadItems;
        this.totalAPagar = calcularTotal();
        this.fecha = fecha;
        this.productos = new ContenedorV<>();
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
        cantidadItems = cantidadItems - 1 ;
        totalAPagar = calcularTotal();
        return productos.eliminar(item);
    }

    public void verCarrito() {
    }

    public boolean vacio() {
        return productos.vacio();
    }


    public void agregarAlCarrito(Item item) {
        if (productos.vacio()) {
            fecha = LocalDateTime.now();
        }
        if (!productos.contiene(item)) {
            productos.agregar(item);
            setCantidadItems(getCantidadItems() + 1);
        }
    }

    public Item buscarItemEnCarritoXid(String id) {
        Item respuesta = new Item();
        for (int i = 0; i < productos.contar(); i++) {
            Item aux = productos.get(i);
            if (aux.getId().equals(id)) {
                respuesta = aux;
            }
        }
        return respuesta;
    }

    @Override
    public double calcularTotal() {
        double resultado = 0;
        if (this.productos != null)
            if (!this.productos.vacio()) {
                for (int i = 0; i < this.productos.tamanio(); i++) {
                    resultado = resultado + this.productos.get(i).getPrecio();
                }
            }
        return resultado;
    }

    public Item ultimo() {
        return productos.get(productos.contar() - 1);
    }


    @Override
    public String toString() {
        return "Carrito{" +
                "Cantidad de Items=" + cantidadItems +
                ", Total A Pagar=" + totalAPagar +
                ", Fecha =" + fecha +
                ", Productos=" + productos +
                '}';
    }

    @Override
    public Carrito clone() {
        Carrito clonedCarrito = new Carrito();
        for (int i =0; i< productos.contar(); i++)
        {
            Item item = productos.get(i);
            clonedCarrito.agregarAlCarrito(item.clone());
        }
        return clonedCarrito;
    }

    public int tamanioCarrito() {
        return productos.contar();
    }

    public Item getItem(int index) {
        return productos.get(index);
    }
}
