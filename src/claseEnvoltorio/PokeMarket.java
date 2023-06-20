package claseEnvoltorio;

import Archivos.ControladoraArchivos;
import clasesItem.Item;
import clasesPersonas.Administrador;
import clasesPersonas.Usuario;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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


    public String mostrarMapaUsuarios()
    {
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

        leerUsuariosArchivo();
        System.out.println(mapaUsuarios.toString());
        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();

        System.out.println("entro a la funcion ");
        int k = 0;


       // System.out.println(mapaUsuarios.toString());
        while(iterator.hasNext()) { //mientras haya usuarios
            System.out.println(" hay usuarios");

            while (k < cartas.size()) { //mientras haya cartas
                System.out.println(" hay cartas todavia");
                Map.Entry<String, Usuario> entrada = iterator.next();
                for (int j = 0; j < 5; j++) {
                    Item item = cartas.get(k);
                    item.setNombreDuenio(entrada.getKey());
                    entrada.getValue().agregarCarta(item);
                    k++;
                }
                System.out.println(k);
                System.out.println("\nINVENTARIO CARGADO DE: " + entrada.getKey());
                System.out.println(entrada.getValue().mostrarInventario());
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

}

