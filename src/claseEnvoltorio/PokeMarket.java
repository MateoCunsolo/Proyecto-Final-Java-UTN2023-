package claseEnvoltorio;


import Archivos.ControladoraArchivos;
import Excepciones.UsuarioContraseniaInvalidoException;
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
     * Reparte cartas entre los usuarios existentes en el TreeMap y las graba en el archivo "Usuarios.dat"
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


    public Item buscarItemPublicadoXid(String id) {
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


}