package clasesPersonas;
import ClasesGenericas.ContenedorLHS;
import ClasesGenericas.ContenedorV;
import Transacciones.Carrito;
import Transacciones.Intercambio;
import Transacciones.Venta;
import clasesItem.Item;

import java.io.Serializable;

public class Usuario extends Persona implements Serializable
{
    private String email;
    private double saldo;
    private Carrito carrito;
    private ContenedorV<Carrito> historialCompras;
    private ContenedorV<Venta> historialVentas;
    private ContenedorV<Intercambio> historialIntercambio;
    private ContenedorLHS<Item>itemsPublicados;
    private ContenedorLHS<Item> inventario;

    private final double ctteSaldo = 5000;

    //CONSTRUCTORES
    public Usuario()
    {
        super();
        email =  " ";
        saldo = ctteSaldo;
        carrito = null;
        historialCompras = new ContenedorV<>();
        historialVentas = new ContenedorV<>();
        historialIntercambio = new ContenedorV<>();
        itemsPublicados = new ContenedorLHS<>();
        inventario = new ContenedorLHS<>();
    }

    public Usuario(String nombre, String contrasenia, String email)
    {
        super(nombre, contrasenia);
        this.email = email;
        this.saldo = ctteSaldo;
        this.carrito = new Carrito();
        historialCompras = new ContenedorV<>();
        historialVentas = new ContenedorV<>();
        historialIntercambio = new ContenedorV<>();
        itemsPublicados = new ContenedorLHS<>();
        inventario = new ContenedorLHS<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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


