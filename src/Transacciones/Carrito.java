package Transacciones;

import ClasesGenericas.ContenedorV;
import Interfaces.ITransaccionable;
import clasesItem.Item;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * La clase Carrito representa un carrito de compras que contiene varios ítems.
 * Implementa la interfaz ITransaccionable para calcular el total a pagar del carrito.
 * Es serializable para permitir su almacenamiento y transferencia.
 * Es clonable para realizar copias de carritos.
 */
public class Carrito implements ITransaccionable, Serializable, Cloneable {
    private static final long serialVersionUID = -7604144828635493406L;

    private int cantidadItems;
    private double totalAPagar;
    private LocalDateTime fecha;
    private ContenedorV<Item> productos;

    /**
     * Constructor de la clase Carrito sin parámetros.
     * Inicializa las propiedades del carrito.
     */
    public Carrito() {
        cantidadItems = 0;
        totalAPagar = calcularTotal();
        fecha = null;
        productos = new ContenedorV<>();
    }
    /**
     * Constructor de la clase Carrito con parámetros.
     * Inicializa las propiedades del carrito con los valores proporcionados.
     *
     * @param cantidadItems La cantidad de ítems en el carrito.
     * @param fecha         La fecha del carrito.
     */
    public Carrito(int cantidadItems, LocalDateTime fecha) {
        this.cantidadItems = cantidadItems;
        this.totalAPagar = calcularTotal();
        this.fecha = fecha;
        this.productos = new ContenedorV<>();
    }
    /**
     * Obtiene la cantidad de ítems en el carrito.
     *
     * @return La cantidad de ítems en el carrito.
     */
    public int getCantidadItems() {
        return cantidadItems;
    }
    /**
     * Establece la cantidad de ítems en el carrito.
     *
     * @param cantidadItems La cantidad de ítems a establecer.
     */
    public void setCantidadItems(int cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    /**
     * Obtiene el total a pagar del carrito.
     *
     * @return El total a pagar del carrito.
     */
    public double getTotalAPagar() {
        return totalAPagar;
    }
    /**
     * Establece el total a pagar del carrito.
     *
     * @param totalAPagar El total a pagar del carrito.
     */
    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
    /**
     * Obtiene la fecha del carrito.
     *
     * @return La fecha del carrito.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }
    /**
     * Establece la fecha del carrito.
     *
     * @param fecha La fecha del carrito.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    /**
     * Elimina todos los ítems del carrito.
     */
    public void eliminarCarrito() {
        productos.eliminarCompleto();

    }
    /**
     * Elimina un ítem del carrito.
     *
     * @param item El ítem a eliminar.
     * @return `true` si se elimina el ítem con éxito, `false` de lo contrario.
     */
    public boolean eliminarUnItem(Item item) {
        cantidadItems = cantidadItems - 1 ;
        totalAPagar = calcularTotal();
        return productos.eliminar(item);
    }

    /**
     * Verifica si el carrito está vacío.
     * @return true si el carrito está vacío, false si contiene al menos un ítem.
     */
    public boolean vacio() {
        return productos.vacio();
    }

    /**
     * Agrega un ítem al carrito.
     * @param item El ítem a agregar al carrito.
     */
    public void agregarAlCarrito(Item item) {
        if (productos.vacio()) {
            fecha = LocalDateTime.now();
        }
        if (!productos.contiene(item)) {
            productos.agregar(item);
            setCantidadItems(getCantidadItems() + 1);
        }
    }
    /**
     * Busca un ítem en el carrito por su ID.
     * @param id El ID del ítem a buscar.
     * @return El ítem encontrado en el carrito con el ID especificado. Si no se encuentra ningún ítem con el ID dado, se devuelve un ítem vacío.
     */
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
    /**
     * Calcula el total a pagar del carrito.
     * @return El total a pagar del carrito.
     */
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
    /**
     * Obtiene el último ítem agregado al carrito.
     * @return El último ítem agregado al carrito.
     */
    public Item ultimo() {
        return productos.get(productos.contar() - 1);
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Carrito.
     * @return La representación en forma de cadena del objeto Carrito.
     */
    @Override
    public String toString() {
        return "Carrito{" +
                "Cantidad de Items=" + cantidadItems +
                ", Total A Pagar=" + totalAPagar +
                ", Fecha =" + fecha +
                ", Productos=" + productos +
                '}';
    }
    /**
     * Crea y devuelve una copia del objeto Carrito.
     * @return Una copia del objeto Carrito.
     */
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

    /**
     * Obtiene el tamaño del carrito, es decir, la cantidad de ítems en el carrito.
     * @return El tamaño del carrito.
     */
    public int tamanioCarrito() {
        return productos.contar();
    }

    /**
     * Obtiene el ítem en la posición especificada en el carrito.
     * @param index La posición del ítem en el carrito.
     * @return El ítem en la posición especificada.
     */
    public Item getItem(int index) {
        return productos.get(index);
    }
}
