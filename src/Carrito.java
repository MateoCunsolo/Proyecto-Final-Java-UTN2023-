import java.time.LocalDateTime;

public class Carrito implements ITransaccionable
{
    private int cantidadItems;
    private double totalAPagar;
    private LocalDateTime fecha;
    private ContenedorV<Item> productos;


    public Carrito()
    {
        cantidadItems = 0;
        totalAPagar = 0;
        fecha = null;
        productos = new ContenedorV<Item>();
    }

    public Carrito(int cantidadItems, double totalAPagar, LocalDateTime fecha, ContenedorV<Item> productos)
    {
        this.cantidadItems = cantidadItems;
        this.totalAPagar = totalAPagar;
        this.fecha = fecha;
        this.productos = productos;
    }

    public boolean eliminarCarrito()
    {
        this.productos = null; //eliminar todos los items del contenedor
        return
    }

    public boolean eliminarUnItem(Item item)
    {
        return
    }

    public void verCarrito()
    {
        return
    }
    public boolean agregarAlCarrito(Item item)
    {
        return
    }

    @Override
    public double calcularTotal()
    {
        return 0;
    }
}
