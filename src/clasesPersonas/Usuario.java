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
/**
 * La clase Usuario representa a un usuario del sistema.
 * Hereda de la clase Persona y proporciona funcionalidades específicas para un usuario.
 * Implementa la interfaz Serializable para permitir la serialización de objetos de esta clase.
 */

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
     * Obtiene el historial de ventas del usuario.
     *
     * @return El contenedor de ventas del historial de ventas.
     */
    public ContenedorV<Venta> getHistorialVentas() ///!!!!???
    {
        return historialVentas;
    }
    /**
     * Obtiene el historial de intercambios del usuario.
     *
     * @return El contenedor de intercambios del historial de intercambios.
     */
    public ContenedorV<Intercambio> getHistorialIntercambio() {
        return historialIntercambio;
    }
    /**
     * Obtiene el email del usuario.
     *
     * @return El email del usuario.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Establece el email del usuario.
     *
     * @param email El nuevo email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Obtiene el saldo del usuario.
     *
     * @return El saldo del usuario.
     */
    public double getSaldo() {
        return saldo;
    }
    /**
     * Establece el saldo del usuario.
     *
     * @param saldo El nuevo saldo del usuario.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Devuelve el carrito del usuario.
     */
    public Carrito getCarrito() {
        return carrito;
    }

    /**
     * Establece el carrito del usuario con el carrito proporcionado.
     *
     * Parámetros:
     *   - carrito: El carrito a establecer.
     */
    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    /**
     * Devuelve una representación en forma de cadena de caracteres del objeto Usuario y sus atributos.
     * Incluye email, saldo, carrito, historialCompras, historialVentas, historialIntercambio,
     * itemsPublicados e inventario.
     */
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

    /**
     * Devuelve una cadena de caracteres que representa el historial de ventas del usuario.
     * Itera sobre los elementos del historial de ventas y los muestra en un formato específico.
     */
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

    /**
     * Devuelve una cadena de caracteres que representa el historial de compras del usuario.
     * Itera sobre los elementos del historial de compras y los muestra en un formato específico.
     */
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

    /**
     * Agrega un artículo al inventario del usuario.
     *
     * Parámetros:
     *   - item: El artículo a agregar.
     *
     * Retorna:
     *   - true si se agregó correctamente, o false si el artículo ya existe en el inventario.
     */
    public boolean agregarItemAlInventario(Item item) {
        return this.inventario.agregar(item);
    }

    /**
     * Agrega todos los artículos del carrito proporcionado al inventario del usuario.
     *
     * @param carro El carrito que contiene los artículos a agregar al inventario.
     */
    public void agregarItemsAlInventario(Carrito carro) {
        for (int i = 0; i < carro.tamanioCarrito(); i++) {
            agregarItemAlInventario(carro.getItem(i));
        }
    }
    /**
     * Muestra el inventario del usuario en forma de cadena de caracteres.
     *
     * @return Una cadena de caracteres que representa el inventario del usuario.
     */

    public String mostrarInventario() {
        String msj = inventario.listar();
        return msj;
    }

    /**
     * Muestra los items publicados por el usuario en forma de cadena de caracteres.
     *
     * @return Una cadena de caracteres que representa los items publicados por el usuario.
     */
    public String mostrarItemsPublicados() {
        return itemsPublicados.toString();
    }

    /**
     * Agrega un artículo al carrito del usuario.
     *
     * @param item El artículo a agregar al carrito.
     */
    public void agregarItemAlCarrito(Item item) {

        if (!item.getId().equals("")) {
            this.carrito.agregarAlCarrito(item);

        }
    }
    /**
     * Busca un artículo en el inventario del usuario por su ID.
     *
     * @param id El ID del artículo a buscar.
     * @return El artículo encontrado en el inventario, o null si no se encuentra.
     */

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
    /**
     * Busca un artículo en los items publicados por el usuario por su ID.
     *
     * @param id El ID del artículo a buscar.
     * @return El artículo encontrado en los items publicados, o lanza una excepción ItemNoEncontradoException si no se encuentra.
     * @throws ItemNoEncontradoException Excepción lanzada cuando no se encuentra el artículo en los items publicados.
     */
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

    /**
     * Verifica si un artículo se encuentra en los items publicados por el usuario.
     *
     * @param id El ID del artículo a buscar.
     * @return true si el artículo se encuentra en los items publicados, false de lo contrario.
     */
    public boolean encontrarItemsPublicado(String id) {
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
    /**
     * Devuelve una representación en forma de cadena de caracteres del inventario del usuario.
     *
     * @return Una cadena de caracteres que representa el inventario del usuario.
     */
    public String verInventario() {
        return inventario.toString();
    }
    /**
     * Muestra el historial de intercambios del usuario en forma de cadena de caracteres.
     *
     * @return Una cadena de caracteres que representa el historial de intercambios del usuario.
     */
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
    /**
     * Elimina un artículo del carrito del usuario por su ID.
     *
     * @param id El ID del artículo a eliminar del carrito.
     */
    public void eliminarItemDelCarrito(String id) {
        Item item = carrito.buscarItemEnCarritoXid(id);
        carrito.eliminarUnItem(item);

    }
    /**
     * Publica un artículo, eliminándolo del inventario y agregándolo a los items publicados del usuario.
     *
     * @param item El artículo a publicar.
     */
    public void publicarItem(Item item) {
        if (!inventario.contiene(item)) {
            inventario.eliminar(item);
        }

        itemsPublicados.agregar(item);
    }
    /**
     * Elimina todos los artículos del carrito del usuario y crea un nuevo carrito vacío.
     */
    public void eliminarCarritoTotal() {
        carrito.eliminarCarrito();
        carrito = new Carrito();

    }
    /**
     * Muestra el contenido del carrito del usuario en forma de cadena de caracteres.
     *
     * @return Una cadena de caracteres que representa el contenido del carrito del usuario.
     */
    public String mostrarCarrito() {
        this.carrito.setTotalAPagar(carrito.calcularTotal());
        this.carrito.setFecha(LocalDateTime.now());
        return this.carrito.toString();
    }
    /**
     * Muestra el historial de intercambios del usuario en forma de cadena de caracteres.
     *
     * @return Una cadena de caracteres que representa el historial de intercambios del usuario.
     */

    public String mostrarHistorialIntercambio() {
        String mensaje = historialIntercambio.listar();
        return mensaje;
    }
    /**
     * Compara el email del usuario con el email proporcionado.
     *
     * @param email El email a comparar.
     * @return true si el email coincide con el del usuario, false de lo contrario.
     */

    public boolean compararEmail(String email) {
        boolean rta = false;
        if (this.email.equals(email)) {
            rta = true;
        }
        return rta;
    }
    /**
     * Agrega un intercambio al historial de intercambios del usuario.
     *
     * @param inter El intercambio a agregar.
     */
    public void agregarAlHistorialIntercambios(Intercambio inter) {
        historialIntercambio.agregar(inter);
    }
    /**
     * Agrega un carrito al historial de compras del usuario.
     *
     * @param carro El carrito a agregar.
     */
    public void agregarAlHistorialCompras(Carrito carro) {
        historialCompras.agregar(carro);
    }

    /**
     * Elimina un artículo de los items publicados del usuario.
     *
     * @param item El artículo a eliminar.
     * @return true si se elimina exitosamente, false si el artículo no se encuentra en los items publicados.
     */
    public boolean eliminarItemDePublicados(Item item) {
        return  itemsPublicados.eliminar(item);
    }

    /**
     * Crea una venta a partir del carrito de compras del usuario.
     * El vendedor incrementa su saldo, se guarda el historial de la venta (que incluye el nombre del comprador) y se saca el artículo de la publicación.
     *
     * @param carrito         El carrito de compras del usuario.
     * @param usuario         El usuario vendedor.
     * @param nombreComprador El nombre del comprador.
     */
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






