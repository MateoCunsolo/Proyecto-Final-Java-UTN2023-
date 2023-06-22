package clasesPersonas;

import Archivos.ControladoraArchivos;
import ClasesGenericas.ContenedorLHS;
import ClasesGenericas.ContenedorV;
import Transacciones.Carrito;
import Transacciones.Intercambio;
import Transacciones.Venta;
import clasesItem.Carta;
import clasesItem.Item;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;


public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 7394283964424643481L;
    private String email;
    private double saldo;
    private Carrito carrito;
    private ContenedorV<Carrito> historialCompras;
    private ContenedorV<Venta> historialVentas;
    private ContenedorV<Intercambio> historialIntercambio;
    private ContenedorLHS<Item> itemsPublicados;
    private ContenedorLHS<Item> inventario;

    private final double ctteSaldo = 5000;

    //CONSTRUCTORES
    public Usuario() {
        super();
        email = " ";
        saldo = ctteSaldo;
        carrito = null;
        historialCompras = new ContenedorV<>();
        historialVentas = new ContenedorV<>();
        historialIntercambio = new ContenedorV<>();
        itemsPublicados = new ContenedorLHS<>();
        inventario = new ContenedorLHS<>();
    }

    public Usuario(String nombre, String contrasenia, String email) {
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

    /**
     * aaaaaaaaaaaaaaaaaaaa
     */
    public ContenedorV<Venta> getHistorialVentas() ///!!!!???
    {
        return historialVentas;
    }

    /**
     * aaaaaaaaaaaaaaaaaaaaaaaaaaaaa
     */
    public ContenedorV<Intercambio> getHistorialIntercambio() {
        return historialIntercambio;
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
        return super.toString() +
                "\t|---» email='" + email +
                "\n\t|---» saldo=" + saldo +
                "\n\t|---» carrito=" + carrito +
                "\n\t|---» historialCompras=" + historialCompras +
                "\n\t|---» historialVentas=" + historialVentas +
                "\n\t|---» historialIntercambio=" + historialIntercambio +
                "\n\t|---» itemsPublicados=" + itemsPublicados +
                "\n\t|---» inventario=" + inventario;
    }


    public String mostrarHistorialVentas() {

        StringBuilder sb = new StringBuilder();
        int contV = 1;

        sb.append("------------HISTORIAL DE VENTAS-------------\n");

        for (int i = 0; i < historialVentas.tamanio(); i++) {

            Venta venta = historialVentas.get(i);
            sb.append("Venta N°" + contV + ":").append("\n")
                    .append(venta.listar())
                    .append("\n");
            contV++;
        }
        return sb.toString();
    }

    public String mostrarHistorialCompras()
    {

        StringBuilder sb = new StringBuilder();
        int contV = 1;

        sb.append("------------HISTORIAL DE COMPRAS-------------\n");

        for (int i = 0; i < historialCompras.tamanio(); i++) {

            Carrito carro = historialCompras.get(i);
            sb.append("Compra N°" + contV + ":").append("\n")
                    .append(carro.listar())
                    .append("\n");
            contV++;
        }
        return sb.toString();

    }

    public boolean agregarCarta(Item item) {
        return this.inventario.agregar(item);
    }

    public String mostrarInventario()
    {
        String msj = inventario.listar();
        return msj;
    }

    public String mostrarItemsPublicados() {
        return itemsPublicados.toString();
    }

    public void agregarItemAlCarrito(Item item) {
        this.carrito.agregarAlCarrito(item);
    }

    public Item buscarEnInventario(String id)
    {
        LinkedHashSet<Item> LHSaux = inventario.getMiLHSet();
        Item buscado = null;
        int flag = 1;
        Iterator iterator = LHSaux.iterator();
        while (iterator.hasNext() && flag != 0) {
            buscado = (Item) iterator.next();
            if(buscado.getId().equals(id))
            {
                flag = 0;
            }
        }
        return buscado;
    }

    public Item buscarEnItemsPublicadosPropios(String id) {
        LinkedHashSet<Item> LHSaux = itemsPublicados.getMiLHSet();
        Item buscado = new Carta();
        int flag = 1;

        Iterator iterator = LHSaux.iterator();
        while (iterator.hasNext() && flag != 0) {
            buscado = (Item) iterator.next();
            if(buscado.getId().equals(id))
            {
                flag = 0;
            }
        }
        return buscado;
    }

    public String verInventario()
    {
        return inventario.toString();
    }

    public String mostrarHistorialIntercambios()
    {
        StringBuilder sb = new StringBuilder();
        int contV = 1;

        sb.append("------------HISTORIAL DE INTERCAMBIOS-------------\n");

        for (int i = 0; i < historialIntercambio.tamanio(); i++) {

            Intercambio intercambio = historialIntercambio.get(i);
            sb.append("\n| ** INTERCAMBIO N°"+ contV + " **\n").append("\n")
                    .append(intercambio.toString())
                    .append("\n");
            contV++;
        }
        return sb.toString();
    }

    public void eliminarItemDelCarrito(String id) {
        Item item = carrito.buscarItemEnCarritoXid(id);
        carrito.eliminarUnItem(item);
    }

    public void publicarItem(Item item) {
       inventario.eliminar(item);
        itemsPublicados.agregar(item);
    }

    public void eliminarCarritoTotal() {
        carrito.eliminarCarrito();
    }

    public String mostrarCarrito() {
        return carrito.toString();
    }

    public void confirmarCarrito()
    {
        // agrego carrito al historial de compra ( 1 )
        historialCompras.agregar(carrito);

        // !!!!! comprobar saldo mayor a lo que se quiere gastar ( 2 )
        setSaldo(getSaldo() - getCarrito().getTotalAPagar());

        for(int i = 0; i < carrito.getCantidadItems(); i++)
        {
            inventario.agregar(carrito.ultimo());
            carrito.eliminarUnItem(carrito.ultimo());
        }

        carrito.setCantidadItems(0);
        carrito.setTotalAPagar(0);
        carrito.setFecha(null);

    }
    public String mostrarHistorialIntercambio()
    {
        String mensaje = historialIntercambio.listar();
        return mensaje;
    }


}




