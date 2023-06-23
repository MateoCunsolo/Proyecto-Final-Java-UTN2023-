package clasesItem;

import Interfaces.I_toJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;
/**
 * La clase Carta representa una carta de Pokemon y extiende de la clase abstracta Item.
 * Contiene atributos como el número de la carta, el Pokémon asociado, la rareza y el artista.
 * Implementa las interfaces Serializable, I_toJSON y Cloneable.
 */
public class Carta extends Item implements Serializable, I_toJSON, Cloneable {

    private static final long serialVersionUID = 8882570970347050606L;
    private String numero; // Número de la carta
    private Pokemon pokemon; // Pokémon asociado a la carta
    private String rareza; // Rareza de la carta
    private String artista; // Artista de la carta

    /**
     * Constructor predeterminado de la clase Carta.
     * Inicializa los atributos con valores vacíos o por defecto.
     */
    public Carta() {
        numero = "";
        pokemon = new Pokemon();
        rareza = "";
        artista = "";
    }

    /**
     * Constructor de la clase Carta que recibe los valores iniciales de los atributos.
     *
     * @param numero  El número de la carta.
     * @param pokemon El Pokémon asociado a la carta.
     * @param rareza  La rareza de la carta.
     * @param artista El artista de la carta.
     */
    public Carta(String numero, Pokemon pokemon, String rareza, String artista) {
        this.numero = numero;
        this.pokemon = pokemon;
        this.rareza = rareza;
        this.artista = artista;
    }

    /**
     * Obtiene el número de la carta.
     *
     * @return El número de la carta.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la carta.
     *
     * @param numero El número de la carta.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el Pokémon asociado a la carta.
     *
     * @return El Pokémon asociado a la carta.
     */

    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Establece el Pokémon asociado a la carta.
     *
     * @param pokemon El Pokémon asociado a la carta.
     */

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    /**
     * Obtiene la rareza de la carta.
     *
     * @return La rareza de la carta.
     */

    public String getRareza() {
        return rareza;
    }

    /**
     * Establece la rareza de la carta.
     *
     * @param rareza La rareza de la carta.
     */

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    /**
     * Obtiene el artista de la carta.
     *
     * @return El artista de la carta.
     */

    public String getArtista() {
        return artista;
    }

    /**
     * Establece el artista de la carta.
     *
     * @param artista El artista de la carta.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Compara si la carta es igual a otro objeto dado.
     * Dos cartas se consideran iguales si tienen el mismo número de carta,
     * el mismo Pokémon, la misma rareza y el mismo artista.
     *
     * @param o El objeto a comparar.
     * @return true si las cartas son iguales, false en caso contrario.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return numero == carta.numero && pokemon.equals(carta.pokemon) && rareza.equals(carta.rareza) && artista.equals(carta.artista);
    }

    /**
     * Calcula el código hash de la carta.
     * El código hash se calcula utilizando los valores del número de carta,
     * el Pokémon, la rareza y el artista.
     *
     * @return El código hash de la carta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numero, pokemon, rareza, artista);
    }

    /**
     * Devuelve una representación en forma de cadena de la carta.
     * La cadena contiene información sobre el número de carta, el Pokémon,
     * la rareza y el artista de la carta.
     *
     * @return La representación en forma de cadena de la carta.
     */

    @Override
    public String toString() {
        return super.toString() +"\t\t\t\t-------------Tipo Carta---------------\n"+
                                 "\t\t\t\t| numero = " + numero +              " \n"+
                                 "\t\t\t\t| Pokemon = " + pokemon +            " \n"+
                                 "\t\t\t\t| rareza = " + rareza +              " \n"+
                                 "\t\t\t\t| artista = " + artista+"\n";
    }

    /**
     * Convierte la carta a un objeto JSONObject.
     * Actualmente, este método no está implementado y devuelve null.
     *
     * @return El objeto JSONObject de la carta.
     * @throws JSONException Si ocurre algún error al crear el JSONObject.
     */
    @Override
    public JSONObject toJson() throws JSONException {
        return null;
    }

    /**
     * Deserializa una carta a partir de un objeto JSONObject.
     * El método utiliza la información del objeto JSON para inicializar los atributos
     * de la carta, como el Pokémon asociado, la rareza, el artista, el número, etc.
     *
     * @param cartaJson El objeto JSONObject que contiene la información de la carta.
     * @throws JSONException Si ocurre algún error al leer el JSONObject.
     */
    @Override
    public void fromJson(JSONObject cartaJson) throws JSONException {



        //****** { CREACION DE LA CARTA } ******

        // ( 1 ) Pokemon
        pokemon.fromJson(cartaJson);

        // ( 2 ) Rareza
        try {
            setRareza(cartaJson.getString("rarity"));
        } catch (JSONException e) {
            setRareza("No definido");
        }

        // ( 3 ) Artista
        try {
            setArtista(cartaJson.getString("artist"));
        } catch (JSONException e) {
            setArtista("No definido");
        }


        // ( 4 ) Numero
        try {
            setNumero(cartaJson.getString("number"));
        } catch (JSONException e) {
            setNumero("XXX");
        }

        // ( 5 ) Id
        try {
            setId(cartaJson.getString("id"));
        } catch (JSONException e) {
            setId("XXX");
        }

        // ( 6 ) Descripcion
        try {
            setDescrip(cartaJson.getString("flavorText"));
        } catch (JSONException a) {
            setDescrip("Sin descripcion");
        }

        // ( 7 ) Precio
        if (getRareza().equals("Rare Holo")) //5
        {
            setPrecio(1620);

        } else if (getRareza().equals("Rare Holo GX")) //2
        {
            setPrecio(3500);

        } else if (getRareza().equals("Common")) //8
        {
            setPrecio(752);

        } else if (getRareza().equals("Uncommon")) //7
        {
            setPrecio(1300);

        } else if (getRareza().equals("Rare")) //6
        {
            setPrecio(1543);

        } else if (getRareza().equals("Rare Holo V")) //4
        {
            setPrecio(2600);

        } else if (getRareza().equals("Promo")) //9
        {
            setPrecio(513);

        } else if (getRareza().equals("Rare Holo EX")) //3
        {
            setPrecio(3000);

        } else if (getRareza().equals("Rare Ultra"))// Rare Ultra +cara 1
        {
            setPrecio(5000);
        }
        else if (getRareza().equals("No definido"))
        {
            setPrecio(1000);
        }
    }

    /**
     * Compara la rareza de la carta con una rareza dada.
     * Comprueba si la rareza de la carta es igual a la rareza especificada.
     *
     * @param tipoRareza La rareza a comparar.
     * @return true si la rareza de la carta es igual a la rareza especificada, false en caso contrario.
     */
    public boolean compararRareza(String tipoRareza)
    {
        boolean rta = false;

        if(rareza.equals(tipoRareza)) //si son iguales
        {
            rta = true;
        }
        return rta;
    }

    /**
     * Crea y devuelve una copia exacta de la carta actual.
     * Realiza una copia profunda de la carta, incluyendo el clonado del objeto Pokémon asociado.
     *
     * @return La copia clonada de la carta.
     */
    @Override
    public Carta clone(){
        Carta clonedCarta = (Carta) super.clone();
        clonedCarta.setPokemon(this.pokemon.clone());
        return clonedCarta;
    }

}