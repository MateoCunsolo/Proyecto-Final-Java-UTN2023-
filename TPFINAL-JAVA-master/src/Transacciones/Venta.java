package Transacciones;
import ClasesGenericas.ContenedorLHS;
import Interfaces.ITransaccionable;
import clasesItem.Item;
import clasesPersonas.Usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

public class Venta  extends Transaccion implements ITransaccionable, Serializable
{
    double totalCobrar;
    private ContenedorLHS<Item> productos;

    /**
     * Constructor de la clase Venta.
     * Crea una instancia de Venta con valores predeterminados.
     */
    public Venta() {
        super();
        totalCobrar = 0;
        productos = new ContenedorLHS<>();
    }
    /**
     * Constructor de la clase Venta.
     * Crea una instancia de Venta con el total a cobrar especificado.
     * @param totalCobrar El total a cobrar de la venta.
     */
    public Venta(double totalCobrar) {
        super();
        this.totalCobrar = totalCobrar;
        productos = new ContenedorLHS<>();
    }

    /**
     * Obtiene el total a cobrar de la venta.
     * @return El total a cobrar de la venta.
     */
    public double getTotalCobrar() {
        return totalCobrar;
    }
    /**
     * Establece el total a cobrar de la venta.
     * @param totalCobrar El total a cobrar de la venta a establecer.
     */
    public void setTotalCobrar(double totalCobrar) {
        this.totalCobrar = totalCobrar;
    }

    /**
     * Obtiene el contenedor de productos de la venta.
     * @return El contenedor de productos de la venta.
     */
    private ContenedorLHS<Item> getProductos() {
        return productos;
    }

    /**
     * Devuelve la fecha de la transacción.
     *
     * @return La fecha de la transacción como un objeto LocalDateTime.
     */
    @Override
    public LocalDateTime getFecha() {
        return super.getFecha();
    }
    /**
     * Calcula el total a cobrar de la venta sumando el precio de todos los productos.
     *
     * @return El total a cobrar de la venta como un valor double.
     */
    @Override
    public double calcularTotal() {
        LinkedHashSet<Item> LHSaux = productos.getMiLHSet();
        double resultado = 0;
        Iterator iterator = LHSaux.iterator();
        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();
            resultado = resultado + item.getPrecio();
        }
        return resultado;
    }
    /**
     * Devuelve una cadena de texto que representa la venta y sus atributos.
     *
     * @return Una cadena de texto que muestra la fecha, el total a cobrar y los productos de la venta.
     */
    @Override
    public String toString() {
        return super.toString()+ "Venta{" +
                "totalCobrar=" + totalCobrar +
                ", productos=" + productos +
                '}';
    }
    /**
     * Devuelve una cadena de texto que representa la venta y sus productos de forma detallada.
     *
     * @return Una cadena de texto que muestra la fecha, el total a cobrar y los productos de la venta con información adicional.
     */
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
    /**
     * Compara si la venta es igual a otro objeto especificado.
     *
     * @param o El objeto a comparar con la venta.
     * @return true si la venta es igual al objeto especificado, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta venta)) return false;
        return Double.compare(venta.totalCobrar, totalCobrar) == 0 && Objects.equals(productos, venta.productos);
    }
    /**
     * Calcula el código hash de la venta.
     *
     * @return El código hash calculado para la venta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(totalCobrar, productos);
    }
    /**
     * Agrega un item al contenedor de productos de la venta.
     *
     * @param item El item a agregar a la venta.
     */
    public void agregarItem(Item item)
    {
        productos.agregar(item);
    }

}
