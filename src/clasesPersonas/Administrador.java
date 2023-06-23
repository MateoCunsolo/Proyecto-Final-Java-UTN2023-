package clasesPersonas;

import Archivos.ControladoraArchivos;
import ClasesGenericas.ContenedorV;
import Excepciones.UsuarioContraseniaInvalidoException;
import Excepciones.UsuarioNoEncontradoException;
import Transacciones.Intercambio;
import Transacciones.Venta;
import claseEnvoltorio.PokeMarket;
import clasesPersonas.Persona;
import clasesPersonas.Usuario;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * La clase Administrador representa a un administrador del sistema.
 * Hereda de la clase Persona y es serializable.
 */
public class Administrador extends Persona implements Serializable
{
    /**
     * Crea un objeto Administrador con el nombre y la contraseña especificados.
     * @param nombre El nombre del administrador.
     * @param contrasenia La contraseña del administrador.
     */
    public Administrador(String nombre, String contrasenia)
    {
        super(nombre, contrasenia);
    }

    /**
     * Crea un objeto Administrador sin especificar nombre ni contraseña.
     */
    public Administrador()
    {
        super();
    }

    /**
     * Borra un usuario del sistema.
     * @param nombre El nombre del usuario a borrar.
     * @param pokeMarket El objeto PokeMarket que contiene el mapa de usuarios.
     * @return true si el usuario se borró exitosamente, false de lo contrario.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra en el sistema.
     */

    public boolean borrarUsuario (String nombre, PokeMarket pokeMarket) throws UsuarioNoEncontradoException
    {
        boolean rta = false;
        TreeMap<String, Usuario> usuarios = pokeMarket.getMapaUsuarios();

        if(usuarios.containsKey(nombre))
        {
            usuarios.remove(nombre);
            ControladoraArchivos.grabarUsuarios(usuarios);
            rta = true;

        }else
        {
            throw new UsuarioNoEncontradoException("El usuario" +nombre + "no existe en el sistema");
        }

        return rta;
    }
    /**
     * Genera una representación en forma de cadena de texto de todos los usuarios del sistema.
     * @param usuarios El mapa de usuarios del sistema.
     * @return Una cadena que contiene la información de todos los usuarios.
     */
    public String verUsuarios(TreeMap<String, Usuario> usuarios) {

        StringBuilder sb = new StringBuilder();
        int cont = 1;

        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            String nombreUsuario = entry.getKey();
            Usuario usuario = entry.getValue();

            sb.append("------------USUARIO N°" +cont + "-------------\n")
            .append("Nombre de usuario: ").append(nombreUsuario).append("\n")
                    .append("Email: ").append(usuario.getEmail()).append("\n")
                    .append("Saldo: ").append(usuario.getSaldo()).append("\n")
                    .append("\n");
            cont++;
        }

        return sb.toString();
    }

    /**
     * Genera una representación en forma de cadena de texto de todo el historial de ventas de los usuarios del sistema.
     * @param usuarios El mapa de usuarios del sistema.
     * @return Una cadena que contiene el historial de ventas de todos los usuarios.
     */
    public String verTodosHistorialVentas(TreeMap<String, Usuario> usuarios) {

        StringBuilder sb = new StringBuilder();
        int cont = 1;
        int contV =1;

        sb.append("------------HISTORIAL DE VENTAS-------------\n");

        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) { //recorre los usuarios
            String nombreUsuario = entry.getKey();
            Usuario usuario = entry.getValue();

            sb.append("-------------------USUARIO N°" + cont +"-----------").append("\n")
                    .append("Nombre de usuario: ").append(nombreUsuario).append("\n");

            ContenedorV<Venta> historialVentas = usuario.getHistorialVentas();
            for (int i = 0; i < historialVentas.tamanio(); i++) { //recorre las ventas

                Venta venta = historialVentas.get(i);

                sb.append(venta.listar())
                        .append("\n");
                contV++;
            }
            cont++;

        }

        return sb.toString();
    }

    /**
     * Genera una representación en forma de cadena de texto de todo el historial de intercambios de los usuarios del sistema.
     * @param usuarios El mapa de usuarios del sistema.
     * @return Una cadena que contiene el historial de intercambios de todos los usuarios.
     */
    public String verTodosHistorialIntercambios(TreeMap<String, Usuario> usuarios) {

        StringBuilder sb = new StringBuilder();
        int cont = 1;

        sb.append("------------HISTORIAL DE INTERCAMBIOS-------------\n");

        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            String nombreUsuario = entry.getKey();
            Usuario usuario = entry.getValue();

            ContenedorV<Intercambio> historialIntercambio = usuario.getHistorialIntercambio();
            for (int i = 0; i < historialIntercambio.tamanio(); i++) {

                Intercambio intercambio = historialIntercambio.get(i);

                sb.append("------------USUARIO N°" +cont + "-------------\n")
                        .append("Nombre de usuario: ").append(nombreUsuario).append("\n")
                        .append("Fecha: ").append(intercambio.getFecha()).append("\n")
                        .append("Item ingresado: ").append(intercambio.getEntrada()).append("\n")
                        .append("Intercambiado por: ").append(intercambio.getSalida()).append("\n")
                        .append("\n");
            }
            cont++;
        }
        return sb.toString();
    }
}
