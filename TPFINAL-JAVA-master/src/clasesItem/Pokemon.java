package clasesItem;

import Interfaces.I_toJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * La clase Pokemon representa un Pokémon en el programa.
 * Esta clase es serializable y clonable, y también implementa la interfaz I_toJSON.
 * Permite almacenar información sobre un Pokémon y realizar operaciones como serialización,
 * clonación y conversión a formato JSON.
 */
public class Pokemon implements Serializable, I_toJSON, Cloneable {

    private static final long serialVersionUID = -4325708085462147315L;

    private String nombre; // Nombre del Pokémon
    private int ps; // Puntos de salud
    private String evolucionA; // Evolución a la que puede llegar el Pokémon
    private String evolucionaDe; // Pokémon del que puede evolucionar
    private Energia tipo; // Tipo de energía del Pokémon
    private Energia debilidad; // Debilidad del Pokémon
    private HashSet<Ataque> hsAtaques; // Conjunto de ataques del Pokémon

    /**
     * Constructor sin argumentos que inicializa los atributos del Pokémon con valores predeterminados.
     */
    public Pokemon() {
        nombre = "";
        ps = 0;
        evolucionA = "";
        evolucionaDe = "";
        tipo = Energia.EMPTY;
        debilidad = Energia.EMPTY;
        hsAtaques = new HashSet<>();
    }

    /**
     * Constructor que inicializa los atributos del Pokémon con los valores proporcionados.
     *
     * @param nombre       El nombre del Pokémon.
     * @param ps           Los puntos de salud del Pokémon.
     * @param evolucionA   La evolución a la que puede llegar el Pokémon.
     * @param evolucionaDe El Pokémon del que puede evolucionar el Pokémon actual.
     */
    public Pokemon(String nombre, int ps, String evolucionA, String evolucionaDe) {
        this.nombre = nombre;
        this.ps = ps;
        this.evolucionA = evolucionA;
        this.evolucionaDe = evolucionaDe;
        tipo = Energia.EMPTY;
        debilidad = Energia.EMPTY;
        hsAtaques = new HashSet<>();
    }
    // Métodos getters y setters para acceder y modificar los atributos del Pokémon

    /**
     * Retorna el nombre del Pokémon.
     *
     * @return El nombre del Pokémon.
     */

    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre del Pokémon.
     *
     * @param nombre El nombre del Pokémon.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Retorna los puntos de salud del Pokémon.
     *
     * @return Los puntos de salud del Pokémon.
     */
    public int getPs() {
        return ps;
    }
    /**
     * Establece los puntos de salud del Pokémon.
     *
     * @param ps Los puntos de salud del Pokémon.
     */
    public void setPs(int ps) {
        this.ps = ps;
    }
    /**
     * Retorna la evolución a la que puede llegar el Pokémon.
     *
     * @return La evolución a la que puede llegar el Pokémon.
     */
    public String getEvolucionA() {
        return evolucionA;
    }
    /**
     * Establece la evolución a la que puede llegar el Pokémon.
     *
     * @param evolucion La evolución a la que puede llegar el Pokémon.
     */
    public void setEvolucionA(String evolucion) {
        this.evolucionA = evolucion;
    }

    /**
     * Establece la debilidad del Pokemon.
     * @param debilidad La energía que representa la debilidad del Pokemon.
     */
    public void setDebilidad(Energia debilidad) {
        this.debilidad = debilidad;
    }

    /**
     * Obtiene el tipo de energía del Pokemon.
     * @return El tipo de energía del Pokemon.
     */
    public Energia getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de energía del Pokemon.
     * @param tipo La energía que representa el tipo del Pokemon.
     */
    public void setTipo(Energia tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el nombre de la etapa de evolución anterior del Pokemon.
     * @return El nombre de la etapa de evolución anterior del Pokemon.
     */
    public String getEvolucionaDe() {
        return evolucionaDe;
    }

    /**
     * Establece el nombre de la etapa de evolución anterior del Pokemon.
     * @param evolucionaDe El nombre de la etapa de evolución anterior del Pokemon.
     */
    public void setEvolucionaDe(String evolucionaDe) {
        this.evolucionaDe = evolucionaDe;
    }

    /**
     * Obtiene la debilidad del Pokemon.
     * @return La energía que representa la debilidad del Pokemon.
     */
    public Energia getDebilidad() {
        return debilidad;
    }


    /**
     * Compara si un objeto es igual al Pokemon actual.
     * @param o El objeto a comparar con el Pokemon actual.
     * @return true si el objeto es igual al Pokemon actual, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return ps == pokemon.ps && nombre.equals(pokemon.nombre) && evolucionA.equals(pokemon.evolucionA) && evolucionaDe.equals(pokemon.evolucionaDe) && tipo == pokemon.tipo && debilidad == pokemon.debilidad && hsAtaques.equals(pokemon.hsAtaques);
    }

    /**
     * Calcula el valor hash del Pokemon.
     * @return El valor hash del Pokemon.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, ps, evolucionA, evolucionaDe, tipo, debilidad, hsAtaques);
    }

    /**
     * Devuelve una representación en forma de cadena del Pokemon.
     * @return La representación en forma de cadena del Pokemon.
     */
    @Override
    public String toString() {
        return nombre + "\n" +
                "\t\t\t\t|\t\t\tsalud = " + ps + "\n" +
                "\t\t\t\t|\t\t\tevolucionA = " + evolucionA + "\n" +
                "\t\t\t\t|\t\t\tevolucionaDe = " + evolucionaDe + "\n" +
                "\t\t\t\t|\t\t\tenergia = " + tipo + "\n" +
                "\t\t\t\t|\t\t\tdebilidad = " + debilidad + "\n" +
                "\t\t\t\t|\t\t\tataques =" + hsAtaques;
    }

    /**
     * Agrega un ataque al conjunto de ataques del Pokemon.
     * @param a El ataque a agregar al conjunto de ataques.
     */
    public void agregarAtaque(Ataque a) {
        hsAtaques.add(a);
    }
    /**
     * Devuelve una cadena que contiene la lista de ataques del Pokemon.
     * @return La lista de ataques del Pokemon.
     */
    public String listarAtaques() {
        return hsAtaques.toString();
    }
    /**
     * Convierte el objeto Pokemon a un objeto JSON.
     * @return El objeto JSON que representa al Pokemon.
     * @throws JSONException Si ocurre un error al crear el objeto JSON.
     */
    @Override
    public JSONObject toJson() throws JSONException {
        return null;
    }
    /**
     * Crea un objeto Pokemon a partir de un objeto JSON.
     * @param cartaJson El objeto JSON que contiene la información del Pokemon.
     * @throws JSONException Si ocurre un error al leer el objeto JSON.
     */
    @Override
    public void fromJson(JSONObject cartaJson) throws JSONException {

        // ( 2.1 ) Nombre
        try {
            setNombre(cartaJson.getString("name"));
        } catch (JSONException e) {
            setNombre("No definido");
        }

        // ( 2.2 ) Puntos de salud
        try {
            String auxPS = cartaJson.getString("hp");
            int numeroPS = Integer.parseInt(auxPS);
            setPs(numeroPS);
        } catch (JSONException e) {
            setPs(0);
        }

        // ( 2.3.1 ) Evoluciones to
        try {
            JSONArray arregloDeEvolucionaA = cartaJson.getJSONArray("evolvesTo");
            setEvolucionA(arregloDeEvolucionaA.getString(0));
        } catch (JSONException e) {
            setEvolucionA("No definido");
        }

        // ( 2.3.2 ) Evoluciones from
        try {
            setEvolucionaDe(cartaJson.getString("evolvesFrom"));
        } catch (JSONException e) {
            setEvolucionaDe("No definido");
        }

        // ( 2.4 ) Tipo de energia
        try {
            JSONArray arregloType = cartaJson.getJSONArray("types");
            String tipoEnergia = arregloType.getString(0);
            if (tipoEnergia.equals("Lightning")) {
                setTipo(Energia.LIGHTNING);
            } else if (tipoEnergia.equals("Colorless")) {
                setTipo(Energia.COLORLESS);
            } else if (tipoEnergia.equals("Darkness")) {
                setTipo(Energia.DARKNESS);
            } else if (tipoEnergia.equals("Dragon")) {
                setTipo(Energia.DRAGON);
            } else if (tipoEnergia.equals("Fairy")) {
                setTipo(Energia.FAIRY);
            } else if (tipoEnergia.equals("Fighting")) {
                setTipo(Energia.FIGHTING);
            } else if (tipoEnergia.equals("Fire")) {
                setTipo(Energia.FIRE);
            } else if (tipoEnergia.equals("Grass")) {
                setTipo(Energia.GRASS);
            } else if (tipoEnergia.equals("Metal")) {
                setTipo(Energia.METAL);
            } else if (tipoEnergia.equals("Psychic")) {
                setTipo(Energia.PSYCHIC);
            } else {
                setTipo(Energia.WATER);
            }
        } catch (JSONException e) {
            setTipo(Energia.INEXISTENTE);
        }


        // ( 2.5 ) Debilidades
        try {
            JSONArray debilidades = cartaJson.getJSONArray("weaknesses");
            JSONObject nombreDebilidad = debilidades.getJSONObject(0);
            String tipoDebilidad = nombreDebilidad.getString("type");
            if (tipoDebilidad.equals("Lightning")) {
                setDebilidad(Energia.LIGHTNING);
            } else if (tipoDebilidad.equals("Colorless")) {
                setDebilidad(Energia.COLORLESS);
            } else if (tipoDebilidad.equals("Darkness")) {
                setDebilidad(Energia.DARKNESS);
            } else if (tipoDebilidad.equals("Dragon")) {
                setDebilidad(Energia.DRAGON);
            } else if (tipoDebilidad.equals("Fairy")) {
                setDebilidad(Energia.FAIRY);
            } else if (tipoDebilidad.equals("Fighting")) {
                setDebilidad(Energia.FIGHTING);
            } else if (tipoDebilidad.equals("Fire")) {
                setDebilidad(Energia.FIRE);
            } else if (tipoDebilidad.equals("Grass")) {
                setDebilidad(Energia.GRASS);
            } else if (tipoDebilidad.equals("Metal")) {
                setDebilidad(Energia.METAL);
            } else if (tipoDebilidad.equals("Psychic")) {
                setDebilidad(Energia.PSYCHIC);
            } else {
                setDebilidad(Energia.WATER);
            }
        } catch (JSONException e) {
            setDebilidad(Energia.INEXISTENTE);
        }


        // ( 2.6 ) Ataques
        try {
            JSONArray arregloAtaques = cartaJson.getJSONArray("attacks");
            for (int j = 0; j < arregloAtaques.length(); j++) {

                JSONObject objetoAtaque = arregloAtaques.getJSONObject(j);
                Ataque ataquePokemon = new Ataque();

                try {
                    ataquePokemon.setNombreAtaque(objetoAtaque.getString("name"));
                    if (ataquePokemon.getNombreAtaque().equals(""))
                    {
                        ataquePokemon.setNombreAtaque("No definido");
                    }
                } catch (JSONException e) {
                    ataquePokemon.setNombreAtaque("No definido");
                }

                try {
                    ataquePokemon.setDanio(objetoAtaque.getString("damage"));
                    if (ataquePokemon.getDanio().equals(""))
                    {
                        ataquePokemon.setDanio("No definido");
                    }
                } catch (JSONException e) {
                    ataquePokemon.setDanio("No definido");
                }

                try {
                    ataquePokemon.setDescripcion(objetoAtaque.getString("text"));
                    if (ataquePokemon.getDescripcion().equals(""))
                    {
                        ataquePokemon.setDescripcion("No definido");
                    }
                } catch (JSONException e) {
                    ataquePokemon.setDescripcion("No definido");
                }
                agregarAtaque(ataquePokemon);
            }
        } catch (JSONException e1) {
            agregarAtaque(new Ataque());
        }

    }
    /**
     * Crea y devuelve una copia exacta del objeto Pokemon.
     * @return La copia exacta del objeto Pokemon.
     */
    @Override
    public Pokemon clone() {
        try {
            Pokemon clonedPokemon = (Pokemon) super.clone();
            clonedPokemon.hsAtaques = new HashSet<>(this.hsAtaques); // Clonar el HashSet de ataques
            return clonedPokemon;
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de que la clonación no sea compatible
            e.printStackTrace();
            return null;
        }
    }
}





