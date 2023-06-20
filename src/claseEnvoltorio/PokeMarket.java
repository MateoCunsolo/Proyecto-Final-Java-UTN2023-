package claseEnvoltorio;

import Archivos.ControladoraArchivosObjetos;
import ClasesGenericas.ContenedorLHS;
import Excepciones.UsuarioContraseniaInvalidoException;
import clasesItem.Item;
import clasesPersonas.Administrador;
import clasesPersonas.Usuario;

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

    public int cantidadUser() {
        return this.mapaUsuarios.size();
    }

    public String mostrarMapaUsuarios() {
        String mensaje = "";
        Iterator<Map.Entry<String, Usuario>> i = mapaUsuarios.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, Usuario> entrada = (Map.Entry) i.next();
            mensaje = mensaje + entrada.toString();
        }
        return mensaje;
    }

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
        ControladoraArchivosObjetos.grabarUsuarios(mapaUsuarios);
    }

    public void verItemsPublicados() {
        Iterator<Map.Entry<String, Usuario>> iterator = mapaUsuarios.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Usuario> entrada = iterator.next();
            Usuario usuario = entrada.getValue();
            System.out.println(usuario.mostrarItemsPublicados());
        }
    }


    public Usuario iniciarSesion(String nombre, String password) throws UsuarioContraseniaInvalidoException {
        Usuario rta = new Usuario();
        if (mapaUsuarios.containsKey(nombre)) {
            Usuario actual = mapaUsuarios.get(nombre);
            if (actual.compararContrasenias(password)) {
                rta = actual;
            } else {
                throw new UsuarioContraseniaInvalidoException();
            }
        } else {
            throw new UsuarioContraseniaInvalidoException();
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