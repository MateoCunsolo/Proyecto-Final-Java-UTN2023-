package claseEnvoltorio;

import Archivos.ControladoraArchivos;
import Excepciones.*;
import Transacciones.Carrito;
import Transacciones.Intercambio;
import clasesItem.Carta;
import clasesItem.Item;
import clasesPersonas.Administrador;
import clasesPersonas.Usuario;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.Serializable;
import java.util.*;


/**
 * La clase PokeMarket representa un mercado virtual de Cartas de Pokémon.
 * Esta clase es serializable, lo que permite guardar y cargar objetos de tipo PokeMarket en archivos.
 */
public class PokeMarket implements Serializable {
    private Administrador administrador;
    private TreeMap<String, Usuario> mapaUsuarios;

    /**
     * Constructor de la clase PokeMarket.
     * Crea una instancia de la clase Administrador y un TreeMap vacío para almacenar usuarios.
     */
    public PokeMarket() {
        administrador = new Administrador();
        mapaUsuarios = new TreeMap<>();
    }

    /**
     * Constructor de la clase PokeMarket.
     * Crea una instancia de la clase Administrador y un TreeMap con los usuarios proporcionados.
     *
     * @param administrador El administrador del programa.
     */
    public PokeMarket(Administrador administrador) {
        this.administrador = administrador;
        this.mapaUsuarios = new TreeMap<>();
    }

    /**
            * Devuelve el administrador del programa.
            *
            * @return El administrador del programa.
     */
    public Administrador getAdministrador() {
        return administrador;
    }

    /**
     * Establece el administrador del programa.
     *
     * @param administrador El administrador del programa
     */
    private void setAdministrador(Administrador administrador) { //!!!!
        this.administrador = administrador;
    }

    /**
     * Devuelve el TreeMap que contiene a los usuarios del programa
     *
     * @return El TreeMap de usuarios.
     */
    public TreeMap<String, Usuario> getMapaUsuarios() { //!!!!
        return mapaUsuarios;
    }

    /**
     * Devuelve una representación en forma de cadena de la clase PokeMarket.
     *
     * @return La representación en forma de cadena de la clase.
     */
    @Override
    public String toString() {
        return "PokeMarket{" + ", mapaUsuarios=" + mapaUsuarios +
                '}';
    }
    /**
     * Agrega un usuario al mapa de usuarios del programa
     *
     * @param usuario El usuario a agregar.
     * @return true si se agrega correctamente, false de lo contrario.
     */
    public boolean agregarUsuario(Usuario usuario) {
        boolean rta = false; //por defecto no se pudo guardar
        if (usuario != null) {
            mapaUsuarios.put(usuario.getNombre(), usuario);
            rta = true;
        }
        return rta;
    }
    /**
     * Guarda los usuarios en un archivo llamado "Usuarios.dat".
     */
    public void guardarUsuariosArchivo() {
        ControladoraArchivos.grabarUsuarios(mapaUsuarios);
    }
    /**
     * Lee los usuarios desde el archivo "Usuarios.dat" y los carga en el mapa de usuarios del programa.
     */
    public void leerUsuariosArchivo() {
        this.mapaUsuarios = ControladoraArchivos.leerUsuarios();
    }
    /**
     * Devuelve la cantidad de usuarios del programa
     *
     * @return La cantidad de usuarios.
     */
    public int cantidadUser() {
        return this.mapaUsuarios.size();
    }
    /**
     * Muestra la información de los usuarios en el mapa.
     *
     * @return La información de los usuarios en forma de cadena.
     */
    public String mostrarMapaUsuarios() {

        String mensaje = "";
        Iterator<Map.Entry<String, Usuario>> i = mapaUsuarios.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry<String, Usuario> entrada = (Map.Entry) i.next();
            mensaje = mensaje + entrada.getValue().toString();
        }
        return mensaje;
    }

    /**
     * Reparte cartas en los diferentes inventarios de los usuarios existentes en el TreeMap y las graba en el archivo "Usuarios.dat"
     *
     * @param cartas: ArrayList de tipo Item que contenga la informacion de las cartas.
     */
    public void repartirCartas(ArrayList<Item> cartas) {

        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();
            for (int j = 0; j < 5; j++) {
                Item item = cartas.remove(0);
                item.setNombreDuenio(entrada.getKey());
                usuario.agregarItemAlInventario(item);
            }
        }
        ControladoraArchivos.grabarUsuarios(mapaUsuarios);
    }

    /**
     * Compara si un objeto Administrador es igual al administrador actual.
     *
     * @param o el objeto Administrador a comparar
     * @return true si los administradores son iguales, false en caso contrario
     */
    public boolean compararAdmin(Administrador o) {
        return this.administrador.equals(o);
    }
    /**
     * Verifica si un usuario está contenido en el mapa de usuarios.
     *
     * @param nombreUsuario el nombre del usuario a verificar
     * @return true si el usuario está contenido en el mapa, false en caso contrario
     */
    public boolean contieneUsuario(String nombreUsuario) {
        boolean rta = false;
        if (mapaUsuarios.containsKey(nombreUsuario)) {
            rta = true;
        }
        return rta;
    }
    /**
     * Muestra la lista de usuarios.
     */
    public void mostrarusu() {
        System.out.println(mapaUsuarios.toString());
    }
    /**
     * Obtiene la lista de items publicados por todos los usuarios.
     *
     * @return una cadena con los items publicados
     */
    public String verItemsPublicados() {
        String itemsPublicados = "";
        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();
            itemsPublicados = itemsPublicados.concat(usuario.mostrarItemsPublicados());
        }
        return itemsPublicados;
    }
    /**
     * Inicia sesión de un usuario con el nombre y la contraseña proporcionados.
     *
     * @param nombre   el nombre del usuario
     * @param password la contraseña del usuario
     * @return el usuario que inició sesión
     * @throws UsuarioContraseniaInvalidoException si el nombre de usuario o la contraseña son inválidos
     */
    public Usuario iniciarSesion(String nombre, String password) throws UsuarioContraseniaInvalidoException {
        Usuario rta = new Usuario();
        if (mapaUsuarios.containsKey(nombre)) {
            Usuario actual = mapaUsuarios.get(nombre);
            if (actual.compararContrasenias(password)) {
                rta = actual;
            } else {
                throw new UsuarioContraseniaInvalidoException("Usuario y/o contrasenia invalida");
            }
        } else {

            throw new UsuarioContraseniaInvalidoException("Usuario y/o contrasenia invalida");
        }
        return rta;
    }
    /**
     * Busca un Item publicado por su ID.
     *
     * @param id el ID del Item a buscar
     * @return el Item encontrado
     * @throws ItemNoEncontradoException si el Item no se encuentra
     */
    public Item buscarItemPublicadoXid(String id) throws ItemNoEncontradoException {
        Item buscado = new Item();
        boolean encontrado = false;
        int flag = 0;

        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext() && flag == 0) {

            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();

            encontrado = usuario.encontrarItemsPublicado(id);
            if (encontrado) {
                buscado = usuario.buscarEnItemsPublicadosPropios(id);
                flag = 1;
            }
        }
        return buscado;
    }

    /**
     * Encuentra un Usuario por el ID de un Item.
     *
     * @param id el ID del Item
     * @return el Usuario que posee el Item
     * @throws ItemNoEncontradoException si el Item no se encuentra
     */
    public Usuario encontrarUsuarioXidItem(String id) throws ItemNoEncontradoException {
        int flag = 1;
        Item buscado = new Item();
        Usuario usuario = new Usuario();

        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();

        while (iterator.hasNext() && flag != 0) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            usuario = entrada.getValue();
            buscado = usuario.buscarEnItemsPublicadosPropios(id);
            if (buscado.getId().equals(id)) {
                flag = 0;
            }
        }
        return usuario;
    }

    /**
     * Muestra el perfil de un Usuario.
     *
     * @param usuario el Usuario cuyo perfil se desea mostrar
     * @return la información del perfil del Usuario
     */
    public String verPerfil(Usuario usuario) //muestra solo el usuario, el meil y el saldo disponible que tiene
    {
        String mensaje = " ";
        if (usuario != null) {
            if (mapaUsuarios.containsKey(usuario.getNombre())) //si esta el usuario
            {
                mensaje = "Nombre Usuario : " + usuario.getNombre() + "\nEmail :" + usuario.getEmail() + "\nSaldo disponible :" + usuario.getSaldo();
            } else {
                mensaje = "El fue posible encontrar el usuario indicado";
            }
        }
        return mensaje;
    }

    /**
     * Elimina la cuenta de un Usuario.
     *
     * @param usuario el Usuario cuya cuenta se desea eliminar
     * @return un mensaje indicando el resultado de la operación
     */
    public String eliminarCuenta(Usuario usuario) {
        String mensaje = " ";
        if (usuario != null) {
            mapaUsuarios.remove(usuario.getNombre());
            mensaje = "Cuenta eliminada correctamente";
        } else {
            mensaje = "No fue posible realizar la operacion";
        }
        return mensaje;
    }

    /**
     * Edita el nombre de un Usuario.
     *
     * @param nuevoNombre el nuevo nombre del Usuario
     * @param usuario el Usuario cuyo nombre se desea editar
     * @return un mensaje indicando el resultado de la operación
     */
    public String editarNombre(String nuevoNombre, Usuario usuario) //los pido al momento que quiere cambiar los datos
    {
        String mensaje = " ";
        if (nuevoNombre != null) {
            if (!mapaUsuarios.containsKey(nuevoNombre)) //si en el mapa no hay alguien con ese nombre(xq uysamos el nombre como key), permite el cambio
            {
                usuario.setNombre(nuevoNombre);
                mensaje = "Nombre actualizado correctamente.";
            } else {
                mensaje = "El nombre indicado ya se encuentra utilizado";
            }
        } else {
            mensaje = "No fue posible realizar los cambios indicados";
        }
        return mensaje;
    }

    /**
     * Edita el email de un Usuario.
     *
     * @param nuevoEmail el nuevo email del Usuario
     * @param usuario el Usuario cuyo email se desea editar
     * @return un mensaje indicando el resultado de la operación
     */
    public String editarEmail(String nuevoEmail, Usuario usuario) //los pido al momento que quiere cambiar los datos

    {
        String mensaje = " ";
        if (nuevoEmail != null) {
            if (!contieneEmail(nuevoEmail)) //si en el mapa no esta ese email
            {
                usuario.setEmail(nuevoEmail);
                mensaje = "Email actualizado correctamente.";
            } else {
                mensaje = "El email indicado ya se encuentra utilizado";
            }
        } else {
            mensaje = "No fue posible realizar los cambios indicados";
        }
        return mensaje;
    }

    /**
     * Verifica si un email está contenido en el mapa de Usuarios.
     *
     * @param nuevoEmail el email a verificar
     * @return true si el email está contenido, false en caso contrario
     */
    public boolean contieneEmail(String nuevoEmail) {
        boolean aux = false;

        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();
            if (usuario.compararEmail(nuevoEmail)) //retorno que ese email ya existe
            {
                aux = true;
            }
        }
        return aux;
    }

    /**
     * Carga los datos del Administrador al inicio.
     */
    public void cargaInicioAdministrador() {
        administrador = ControladoraArchivos.leerAdministrador();
    }

    /**
     * Guarda los cambios realizados en el mapa de Usuarios.
     */
    public void guardarCambios() {
        ControladoraArchivos.grabarUsuarios(mapaUsuarios);
    }

    /**
     * Realiza un intercambio de cartas entre dos Usuarios.
     *
     * @param intercambio el objeto de intercambio
     * @param actual el Usuario actual
     * @throws ItemNoEncontradoException si el Item no se encuentra
     * @throws DiferenteRarezaException si las cartas tienen diferente rareza
     */
    public void intercambiarCartas(Intercambio intercambio, Usuario actual) throws ItemNoEncontradoException, DiferenteRarezaException {

        Item entrado = intercambio.getEntrada();
        Item salido = intercambio.getSalida();

        if(entrado instanceof Carta && salido instanceof Carta)
        {
            if(((Carta) entrado).compararRareza(((Carta) salido).getRareza()))
            {
                //busco a mi intercambiador
                Usuario intercambiador = mapaUsuarios.get(entrado.getNombreDuenio());

                //(1) AGREGAMOS AL HISTORIAL DE INTERCAMBIO DE AMBOS
                actual.agregarAlHistorialIntercambios(intercambio);
                Intercambio aux = new Intercambio(salido, entrado);
                intercambiador.agregarAlHistorialIntercambios(aux);

                ///USUARIO
                //(2) SACO EL ITEM DE PUBLICADOS DEL "VENDEDOR"
                intercambiador.eliminarItemDePublicados(entrado);

                //(3) cambio el nombre del dueño
                entrado.setNombreDuenio(actual.getNombre());

                //(4) SE AGREGA EL ITEM AL INVENTARIO
                actual.agregarItemAlInventario(entrado);

                //PARA EL INTERCAMBIADOR
                //(1) cambio el nombre del dueño
                salido.setNombreDuenio(intercambiador.getNombre());

                // (2) agrego el item al inventario
                intercambiador.agregarItemAlInventario(salido);

                //(4) le saco al actual el item de sus publicados
                actual.eliminarItemDePublicados(salido);

            }
            else
            {
                throw new DiferenteRarezaException();
            }
        }
    }

    /**
     * Confirma y realiza la compra de los elementos del carrito del usuario actual.
     *
     * @param actual El usuario actual.
     * @throws CarritoVacioException   Si el carrito está vacío.
     * @throws ValorInvalidoException Si el saldo del usuario no es suficiente para pagar el carrito.
     */

    public void confirmarCarrito(Usuario actual) throws CarritoVacioException, ValorInvalidoException {

        Carrito carrito = actual.getCarrito();

        if (!carrito.vacio()) //si el carrito tiene elementos
        {
            Carrito copiaCalcTotal = carrito.clone();
            if (actual.getSaldo() >= copiaCalcTotal.calcularTotal()) //si el saldo alcanza
            {
                // DESCUENTO MI SALDO, CON EL VALOR TOTAL DE MI CARRITOV OK

                Carrito aux3 = carrito.clone();
                aux3.setTotalAPagar(aux3.calcularTotal());
                double saldoApagar = aux3.getTotalAPagar();
                double saldoNuevo = actual.getSaldo() - saldoApagar;
                actual.setSaldo(saldoNuevo);


                // AGREGO CARRITO AL HISOTRAL DE COMPRAS OK
                Carrito aux2 = carrito.clone();
                aux2.setTotalAPagar(aux2.calcularTotal());
                actual.agregarAlHistorialCompras(aux2);

                //hacemos copia del carrito para poder agregarlo al inventario (*) OK
                Carrito aux;
                aux = carrito.clone();
                for (int i = 0; i < aux.tamanioCarrito(); i++) {
                    Item item = aux.getItem(i);
                    item.setNombreDuenio(actual.getNombre());
                }

                while (!carrito.vacio()) //OK NO TOCAR
                {
                    boolean encontrado = false;
                    //recorro usuarios
                    Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
                    while (iterator.hasNext() && !carrito.vacio()) {

                        Map.Entry<String, Usuario> entrada = iterator.next();
                        Usuario usuario = entrada.getValue();

                        encontrado = usuario.encontrarItemsPublicado(carrito.getItem(0).getId());
                        if (encontrado) //encuentro al dueño(usuario) el item que esta en el carro
                        {
                            usuario.crearVenta(carrito, usuario, actual.getNombre());
                        }
                    }
                }


                //paso carrito para agregar al inventario de actual (*)
                actual.agregarItemsAlInventario(aux); //OK

            } else {
                throw new ValorInvalidoException("El saldo es insuficiente para efectuar la compra :(");
            }
        } else {
            System.out.println("VACIO");
            throw new CarritoVacioException();
        }
    }
    /**
     * Elimina un item específico del carrito del usuario actual.
     *
     * @param actual El usuario actual.
     * @param id     El identificador del item a eliminar.
     * @throws CarritoVacioException Si el carrito está vacío.
     */
    public void eliminarItemDelCarrito(Usuario actual, String id) throws CarritoVacioException {
        if (!actual.getCarrito().vacio()) //si el carrito tiene items
        {
            Item item = actual.getCarrito().buscarItemEnCarritoXid(id);
            if (mapaUsuarios.containsKey(item.getNombreDuenio())) {
                Usuario aux = mapaUsuarios.get(item.getNombreDuenio());
                aux.publicarItem(item);

            }
            actual.eliminarItemDelCarrito(id);
        } else {
            throw new CarritoVacioException();
        }
    }

    /**
     * Elimina todos los items del carrito del usuario actual.
     *
     * @param actual El usuario actual.
     * @throws CarritoVacioException Si el carrito está vacío.
     */
    public void eliminarCarritoTotal(Usuario actual) throws CarritoVacioException {
        int total = actual.getCarrito().tamanioCarrito();
        for (int i = 0; i < total; i++) {
            Item item = actual.getCarrito().getItem(i);
            eliminarItemDelCarrito(actual, item.getId());
        }
        actual.eliminarCarritoTotal();
    }

}



