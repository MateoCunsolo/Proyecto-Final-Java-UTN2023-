package claseEnvoltorio;

import Archivos.ControladoraArchivosObjetos;
import clasesPersonas.Administrador;
import clasesPersonas.Usuario;

import java.io.Serializable;
import java.util.TreeMap;

public class PokeMarket implements Serializable
{
    private Administrador administrador;
    private TreeMap<String, Usuario> mapaUsuarios;

    public PokeMarket()
    {
        administrador = new Administrador();
        mapaUsuarios = new TreeMap<>();
    }

    public PokeMarket(Administrador administrador)
    {
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

    public boolean agregarUsuario(Usuario usuario)
    {
        boolean rta = false; //por defecto no se pudo guardar
        if(usuario!=null)
        {
            mapaUsuarios.put(usuario.getNombre(),usuario);
            rta = true;
        }
        return rta;
    }

    public void guardarUsuariosArchivo()
    {
        ControladoraArchivosObjetos.grabarUsuarios(mapaUsuarios);
    }

    public void leerUsuariosArchivo()
    {
        this.mapaUsuarios = ControladoraArchivosObjetos.leerUsuarios();
    }

    public String mostrarMapa()
    {
        return this.mapaUsuarios.toString();

    }

}
