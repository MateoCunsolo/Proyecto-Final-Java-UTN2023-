package clasesPersonas;

import Archivos.ControladoraArchivos;
import ClasesGenericas.ContenedorLHS;
import ClasesGenericas.ContenedorV;
import Excepciones.CarritoVacioException;
import Excepciones.ItemNoEncontradoException;
import Transacciones.Carrito;
import Transacciones.Intercambio;
import Transacciones.Venta;
import clasesItem.Carta;
import clasesItem.Item;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    public String mostrarHistorialCompras() {

        StringBuilder sb = new StringBuilder();
        int contV = 1;

        sb.append("------------HISTORIAL DE COMPRAS-------------\n");

        for (int i = 0; i < historialCompras.tamanio(); i++) {

            Carrito carro = historialCompras.get(i);
            sb.append("Compra N°" + contV + ":").append("\n")
                    .append(carro.toString())
                    .append("\n");
            contV++;
        }
        return sb.toString();

    }

    public boolean agregarItemAlInventario(Item item) {
        return this.inventario.agregar(item);
    }

    public void agregarItemsAlInventario(Carrito carro) {
        for (int i = 0; i < carro.tamanioCarrito(); i++) {
            agregarItemAlInventario(carro.getItem(i));
        }
    }

    public String mostrarInventario() {
        String msj = inventario.listar();
        return msj;
    }

    public String mostrarItemsPublicados() {
        return itemsPublicados.toString();
    }

    public void agregarItemAlCarrito(Item item) {

        if (!item.getId().equals("")) {
            this.carrito.agregarAlCarrito(item);

        }
    }


    public Item buscarEnInventario(String id) {
        LinkedHashSet<Item> LHSaux = inventario.getMiLHSet();

        Item buscado = null;
        int flag = 1;
        Iterator iterator = LHSaux.iterator();
        while (iterator.hasNext() && flag != 0) {
            buscado = (Item) iterator.next();
            if (buscado.getId().equals(id)) {
                flag = 0;
            }
        }
        return buscado;
    }

    public Item buscarEnItemsPublicadosPropios(String id) throws ItemNoEncontradoException {
        Item buscado = new Item();
        int flag = 1;

        LinkedHashSet<Item> LHSaux = itemsPublicados.getMiLHSet();
        Iterator iterator = LHSaux.iterator();
        while (iterator.hasNext() && flag == 1) {
            buscado = (Item) iterator.next();
            if (buscado.getId().equals(id)) {
                flag = 0;
            }
        }
        if (flag == 1) {
            throw new ItemNoEncontradoException("Id de item no encontrado dentro de publicados");
        }
        return buscado;
    }

    public boolean encontrarItemsPublicado(String id) {
        System.out.printf("ESTOY ACA :) ITEMS PUBLIC");
        Item buscado = new Item();
        boolean flag = false;

        LinkedHashSet<Item> LHSaux = itemsPublicados.getMiLHSet();
        Iterator iterator = LHSaux.iterator();
        while (iterator.hasNext()) {
            buscado = (Item) iterator.next();
            if (buscado.getId().equals(id)) {
                flag = true;
            }
        }

        return flag;
    }

    public String verInventario() {
        return inventario.toString();
    }

    public String mostrarHistorialIntercambios() {
        StringBuilder sb = new StringBuilder();
        int contV = 1;

        sb.append("------------HISTORIAL DE INTERCAMBIOS-------------\n");

        for (int i = 0; i < historialIntercambio.tamanio(); i++) {

            Intercambio intercambio = historialIntercambio.get(i);
            sb.append("\n| ** INTERCAMBIO N°" + contV + " **\n").append("\n")
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
        if (!inventario.contiene(item)) {
            inventario.eliminar(item);
        }

        itemsPublicados.agregar(item);
    }

    public void eliminarCarritoTotal() {
        carrito.eliminarCarrito();
        carrito = new Carrito();

    }

    public String mostrarCarrito() {
        this.carrito.setTotalAPagar(carrito.calcularTotal());
        this.carrito.setFecha(LocalDateTime.now());
        return this.carrito.toString();
    }


    public String mostrarHistorialIntercambio() {
        String mensaje = historialIntercambio.listar();
        return mensaje;
    }

    public boolean compararEmail(String email) {
        boolean rta = false;
        if (this.email.equals(email)) {
            rta = true;
        }
        return rta;
    }

    public void agregarAlHistorialIntercambios(Intercambio inter) {
        historialIntercambio.agregar(inter);
    }

    public void agregarAlHistorialCompras(Carrito carro) {
        historialCompras.agregar(carro);
    }

    public boolean eliminarItemDePublicados(Item item) {
        return  itemsPublicados.eliminar(item);
    }


//    public void crearVenta(Carrito carrito, Usuario usuario, String nombreComprador) {
//    /* Vendedor
//       Sube el saldo
//       Se guarda el historial de la venta (ya incluye el nombre de quien me compra)
//       Se saca el artículo de la publicación
//    */
//
//        Venta venta = new Venta();
//        if (!carrito.vacio())
//        {
//            for (int i = 0; i < carrito.tamanioCarrito(); i++) {
//
//                Item item = carrito.getItem(i);
//
//                if (item.getNombreDuenio().equals(usuario.getNombre()))
//                {
//                    Item clonedItem = item.clone();
//
//                    usuario.eliminarItemDePublicados(item);
//
//                    clonedItem.setNombreDuenio(nombreComprador);
//
//                    venta.agregarItem(clonedItem);
//
//                    carrito.eliminarUnItem(item);
//
//                    venta.setTotalCobrar(venta.getTotalCobrar() + clonedItem.getPrecio());
//                }
//            }
//            usuario.setSaldo(getSaldo() + venta.getTotalCobrar());
//            usuario.historialVentas.agregar(venta);
//        }
//    }

    public void crearVenta(Carrito carrito, Usuario usuario, String nombreComprador) {
    /* Vendedor
       Sube el saldo
       Se guarda el historial de la venta (ya incluye el nombre de quien me compra)
       Se saca el artículo de la publicación
    */
        Carrito copia = carrito.clone();


        Venta venta = new Venta();
        if (!carrito.vacio())
        {
            for (int i = 0; i < carrito.tamanioCarrito(); i++)
            {
                Item item = carrito.getItem(i);
                if (item.getNombreDuenio().equals(usuario.getNombre()))
                {
                    Item itemcopia = copia.getItem(i);
                    itemcopia.setNombreDuenio(nombreComprador);
                    venta.agregarItem(itemcopia);
                    usuario.eliminarItemDePublicados(item);
                    carrito.eliminarUnItem(item);
                }
            }
            venta.setTotalCobrar(venta.calcularTotal());
            venta.setFecha(LocalDateTime.now());
            usuario.setSaldo(getSaldo() + venta.getTotalCobrar());
            usuario.historialVentas.agregar(venta);

        }
    }


}






