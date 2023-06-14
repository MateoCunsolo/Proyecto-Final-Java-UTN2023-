package clasesPersonas;

import ClasesGenericas.ContenedorLHS;
import ClasesGenericas.ContenedorV;
import clasesItem.Item;
import clasesPersonas.Persona;

public class Usuario extends Persona {
    private String email;
    private double saldo;

    private Carrito carrito;
    private ContenedorV<Carrito> historialCompras;
    private ContenedorV<Venta> historialVentas;
    private ContenedorV<Intercambio> historialIntercambio;
    private ContenedorLHS<Item> itemsPublicados;
    private ContenedorLHS<Item> inventario;

    //CONSTRUCTORES
    public Usuario()
    {
        super();
        email =  " ";
        saldo = 0;
        carrito = null;
        historialCompras = new ContenedorV<>();
        historialVentas = new ContenedorV<>();
        historialIntercambio = new ContenedorV<>();
        itemsPublicados = new ContenedorLHS<>();
        inventario = new ContenedorLHS<>();
    }

    public Usuario(String nombre, String contrasenia, String email, double saldo, Carrito carrito)
    {
        super(nombre, contrasenia);
        this.email = email;
        this.saldo = saldo;
        this.carrito = carrito;
        historialCompras = new ContenedorV<>();
        historialVentas = new ContenedorV<>();
        historialIntercambio = new ContenedorV<>();
        itemsPublicados = new ContenedorLHS<>();
        inventario = new ContenedorLHS<>();
    }


    @Override
    public String toString() {
        return super.toString() + "Usuario{" +
                "email='" + email + '\'' +
                ", saldo=" + saldo +
                ", carrito=" + carrito +
                ", historialCompras=" + historialCompras +
                ", historialVentas=" + historialVentas +
                ", historialIntercambio=" + historialIntercambio +
                ", itemsPublicados=" + itemsPublicados +
                ", inventario=" + inventario +
                '}';
    }


}


