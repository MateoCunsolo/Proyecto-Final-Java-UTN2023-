package claseEnvoltorio;


import Archivos.ControladoraArchivos;
import Excepciones.ItemNoEncontradoException;
import Excepciones.UsuarioContraseniaInvalidoException;
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
    public void setAdministrador(Administrador administrador) { //!!!!
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
                    usuario.agregarCarta(item);
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
                System.out.println("1");
                throw new UsuarioContraseniaInvalidoException("Usuario y/o contrasenia invalida");
            }
        } else {
            System.out.println("2");
            throw new UsuarioContraseniaInvalidoException("Usuario y/o contrasenia invalida");
        }
        return rta;
    }

    public Item buscarItemPublicadoXid(String id) throws ItemNoEncontradoException
    {
        int flag = 1;
        Item buscado = new Item();

        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();

        while (iterator.hasNext() && flag != 0) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();
            buscado = usuario.buscarEnItemsPublicadosPropios(id);
            if (buscado.getId().equals(id)) {
                flag = 0;
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
        administrador = ControladoraArchivos.leerAdministrador(); //aaaaaaaa
    }



}