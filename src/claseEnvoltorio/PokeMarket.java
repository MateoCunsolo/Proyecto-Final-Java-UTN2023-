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
import java.util.Scanner;
import java.io.Serializable;
import java.util.*;

public class PokeMarket implements Serializable {
    private Administrador administrador;
    private TreeMap<String, Usuario> mapaUsuarios;

    public PokeMarket() {
        administrador = new Administrador();
        mapaUsuarios = new TreeMap<>();
    }

    public PokeMarket(Administrador administrador) {
        this.administrador = administrador;
        this.mapaUsuarios = new TreeMap<>();
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    /** AAAAAAAAAAAAAAAAAA*/
    private void setAdministrador(Administrador administrador) { //!!!!
        this.administrador = administrador;
    }

    /** OJOOO AL PIOJOOO !!!! */
    public TreeMap<String, Usuario> getMapaUsuarios() { //!!!!
        return mapaUsuarios;
    }

    @Override
    public String toString() {
        return "PokeMarket{" + ", mapaUsuarios=" + mapaUsuarios +
                '}';
    }

    public boolean agregarUsuario(Usuario usuario) {
        boolean rta = false; //por defecto no se pudo guardar
        if (usuario != null) {
            mapaUsuarios.put(usuario.getNombre(), usuario);
            rta = true;
        }
        return rta;
    }

    public void guardarUsuariosArchivo() {
        ControladoraArchivos.grabarUsuarios(mapaUsuarios);
    }

    public void leerUsuariosArchivo() {
        this.mapaUsuarios = ControladoraArchivos.leerUsuarios();
    }

    public int cantidadUser() {
        return this.mapaUsuarios.size();
    }

    public String mostrarMapaUsuarios() {

        String mensaje = "";
        Iterator<Map.Entry<String,Usuario>> i = mapaUsuarios.entrySet().iterator();

        while(i.hasNext())
        {
            Map.Entry<String,Usuario> entrada = (Map.Entry) i.next();
            mensaje = mensaje + entrada.getValue().toString();
        }
        return mensaje;
    }


    /**
     * Reparte cartas en los diferentes inventarios de los usuarios existentes en el TreeMap y las graba en el archivo "Usuarios.dat"
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

    public boolean compararAdmin(Administrador o)
    {
        return this.administrador.equals(o);
    }

    public boolean contieneUsuario(String nombreUsuario)
    {
        boolean rta = false;
        if(mapaUsuarios.containsKey(nombreUsuario))
        {
            rta = true;
        }
        return rta;
    }

    public void mostrarusu()
    {
        System.out.println(mapaUsuarios.toString());
    }

    public void verItemsPublicados() {
        Scanner teclado = new Scanner(System.in);
        String siguiente = "s";
        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext() && siguiente.equals("s")) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();
            System.out.println(usuario.mostrarItemsPublicados());
            System.out.println("Moverse a la siguiente pagina ? (s/n): ");
            siguiente = teclado.nextLine();
        }
    }

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

    public Item buscarItemPublicadoXid(String id) throws ItemNoEncontradoException
    {
        Item buscado = new Item();
        boolean encontrado = false;
        int flag = 0;

        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext() && flag == 0) {

            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();

            encontrado = usuario.encontrarItemsPublicado(id);
            if(encontrado)
            {
                buscado = usuario.buscarEnItemsPublicadosPropios(id);
                flag = 1;
            }
        }
        return buscado;
    }

    public Usuario encontrarUsuarioXidItem(String id) throws ItemNoEncontradoException
    {
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

    public String verPerfil(Usuario usuario) //muestra solo el usuario, el meil y el saldo disponible que tiene
    {
        String mensaje = " ";
        if(usuario!=null) {
            if (mapaUsuarios.containsKey(usuario.getNombre())) //si esta el usuario
            {
                mensaje= "Información de perfil :" + "\nNombre Usuario : " + usuario.getNombre() + "\nEmail :" + usuario.getEmail() + "\nSaldo disponible :" + usuario.getSaldo();
            } else {
                mensaje = "El fue posible encontrar el usuario indicado";
            }
        }
        return mensaje;
    }

    public String eliminarCuenta(Usuario usuario)
    {
        String mensaje = " ";
        if(usuario!=null)
        {
            mapaUsuarios.remove(usuario.getNombre());
            mensaje= "Cuenta eliminada correctamente";
        }
        else
        {
            mensaje = "No fue posible realizar la operacion";
        }
        return mensaje;
    }
    public String editarNombre(String nuevoNombre, Usuario usuario) //los pido al momento que quiere cambiar los datos
    {
        String mensaje = " ";
        if(nuevoNombre!=null)
        {
            if(!mapaUsuarios.containsKey(nuevoNombre)) //si en el mapa no hay alguien con ese nombre(xq uysamos el nombre como key), permite el cambio
            {
                usuario.setNombre(nuevoNombre);
                mensaje = "Nombre actualizado correctamente.";
            }
            else
            {
                mensaje = "El nombre indicado ya se encuentra utilizado";
            }
        }
        else {
            mensaje = "No fue posible realizar los cambios indicados";
        }
        return mensaje;
    }


public String editarEmail(String nuevoEmail,Usuario usuario) //los pido al momento que quiere cambiar los datos

    {
        String mensaje = " ";
        if(nuevoEmail!=null)
        {
            if(!contieneEmail(nuevoEmail)) //si en el mapa no esta ese email
            {
                usuario.setEmail(nuevoEmail);
                mensaje = "Email actualizado correctamente.";
            }
            else
            {
                mensaje = "El email indicado ya se encuentra utilizado";
            }
        }
        else
        {
            mensaje = "No fue posible realizar los cambios indicados";
        }
        return mensaje;
    }


    public boolean contieneEmail(String nuevoEmail)
    {
        boolean aux = false;

        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();
            if(usuario.compararEmail(nuevoEmail)) //retorno que ese email ya existe
            {
                aux = true;
            }
        }
        return aux;
    }


   public void cargaInicioAdministrador()
    {
        administrador = ControladoraArchivos.leerAdministrador();
    }

    public void guardarCambios()
    {
        ControladoraArchivos.grabarUsuarios(mapaUsuarios);
    }

    public void intercambiarCartas(Intercambio intercambio, Usuario actual) throws ItemNoEncontradoException, DiferenteRarezaException
    {
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

    public void confirmarCarrito(Usuario actual) throws CarritoVacioException, ValorInvalidoException
    {
        Carrito carrito = actual.getCarrito();

        if(!carrito.vacio()) //si el carrito tiene elementos
        {
            if(actual.getSaldo() >= carrito.getTotalAPagar()) //si el saldo alcanza
            {
                // DESCUENTO MI SALDO, CON EL VALOR TOTAL DE MI CARRITO
                carrito.calcularTotal();
                double saldoApagar = carrito.getTotalAPagar();
                double saldoNuevo = actual.getSaldo() - saldoApagar;
                actual.setSaldo(saldoNuevo);

                // AGREGO CARRITO AL HISOTRAL DE COMPRAS
                actual.agregarAlHistorialCompras(carrito);

                //paso carrito para agregar al inventario de actual
                actual.agregarItemsAlInventario(carrito);

                while (!carrito.vacio()) //mientras que el carrito tenga elementos
                {
                    boolean encontrado = false;

                    //recorro usuarios
                    Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
                    while (iterator.hasNext() && !carrito.vacio()) {

                        Map.Entry<String, Usuario> entrada = iterator.next();
                        Usuario usuario = entrada.getValue();

                        encontrado = usuario.encontrarItemsPublicado(carrito.getItem(0).getId());
                        if (encontrado) //encue
                        {
                            carrito = usuario.crearVenta(carrito);

                        }
                    }
                }
            }else
            {
                throw new ValorInvalidoException("El saldo es insuficiente para efectuar la compra :(");
            }
        }else
        {
            throw new CarritoVacioException();
        }
    }

}



