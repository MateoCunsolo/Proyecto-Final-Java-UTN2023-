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

    public String mostrarMapaUsuarios()
    {
        String mensaje = "";
        Iterator<Map.Entry<String,Usuario>> i = mapaUsuarios.entrySet().iterator();

        while(i.hasNext())
        {
            Map.Entry<String,Usuario> entrada = (Map.Entry) i.next();
            mensaje = mensaje + entrada.toString();
        }

        return mensaje;
    }

    public void repartirCartas(ArrayList cartas) {

        Iterator<Map.Entry<String, Usuario>> i = mapaUsuarios.entrySet().iterator();

        int k = 0;
        while (k < cartas.size())//hasta que se terminen las cartas
        {
            while (i.hasNext())  //mientras haya usuarios
            {
                Map.Entry<String, Usuario> entrada = (Map.Entry) i.next();

                    for (int j = 0; j < 5; j++) //hasta 5 cartas
                    {
                        Item itemsito = (Item) cartas.get(k);
                        entrada.getValue().agregarCarta(itemsito);
                        System.out.println("\nUN INVENTARIO CARGADO DE: " +entrada.getKey());
                        System.out.println(entrada.getValue().mostrarInventario());
                    }
                    k = k + 5;
            }
        }
    }

}
