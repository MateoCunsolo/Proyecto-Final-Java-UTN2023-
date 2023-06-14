import Transacciones.Intercambio;
import Transacciones.Venta;
import clasesItem.Item;

public class Usuario extends Persona
    private String email;
    private double saldo;
    private Carrito carrito;
    private ContenedorV<Carrito> historialCompras;
    private ContenedorV<Venta> historialVentas;
    private ContenedorV<Intercambio> historialIntercambio;
    private ContenedorLHS<Item>itemsPublicados;
    private ContenedorLHS<Item> inventario;

    //CONSTRUCTORES
    public Usuario()
    {
        super();
        email =  " ";
        saldo = 0;
        carrito = null;
        historialCompras = new ContenedorV<>();
        historialVentas = new ContenedorV<Venta>();
        historialIntercambio = new ContenedorV<Intercambio>();
        itemsPublicados = new ContenedorLHS<Item>();
        inventario = new ContenedorLHS<Item>();
    }

    public Usuario(String nombre, String contrasenia, String email, double saldo, Carrito carrito, ContenedorV<Carrito> historialCompras, ContenedorV<Venta> historialVentas, ContenedorV<Intercambio> historialIntercambio, ContenedorLHS<Item> itemsPublicados, ContenedorLHS<Item> inventario)
    {
        super(nombre, contrasenia);
        this.email = email;
        this.saldo = saldo;
        this.carrito = carrito;
        this.historialCompras = historialCompras;
        this.historialVentas = historialVentas;
        this.historialIntercambio = historialIntercambio;
        this.itemsPublicados = itemsPublicados;
        this.inventario = inventario;
    }


}


