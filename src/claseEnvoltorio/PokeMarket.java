package claseEnvoltorio;

import Archivos.ControladoraArchivosObjetos;
import clasesItem.Item;
import clasesPersonas.Administrador;
import clasesPersonas.Usuario;

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
        ControladoraArchivosObjetos.grabarUsuarios(mapaUsuarios);
    }

    public void leerUsuariosArchivo() {
        this.mapaUsuarios = ControladoraArchivosObjetos.leerUsuarios();
    }

    public String mostrarMapa()
    {
        return this.mapaUsuarios.toString();
    }

    public void repartirCartas(ArrayList<Item> cartas) {

        System.out.printf("CANTIDAD DE USUARIOS: "+mapaUsuarios.size());
        System.out.printf("CANTIDAD DE CARTAS: "+cartas.size());
        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        int k = 1;

            while (k < cartas.size() && iterator.hasNext()) {
                Map.Entry<String, Usuario> entrada = iterator.next();
                for (int j = 0; j < 5 && k < cartas.size(); j++) {
                    Item item = cartas.get(k);
                    entrada.getValue().agregarCarta(item);
                    k++;
                }
                System.out.println("\nINVENTARIO CARGADO DE: " + entrada.getKey());
                System.out.println(entrada.getValue().mostrarInventario());
            }

     }

}

