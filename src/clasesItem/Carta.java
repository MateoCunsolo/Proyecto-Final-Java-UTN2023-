package clasesItem;

import Interfaces.I_toJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

public class Carta extends Item implements Serializable, I_toJSON {

    private static final long serialVersionUID = 5815387080044055750L;
    private String numero; //chek
    private Pokemon pokemon; //chek
    private String rareza; //chek
    private String artista; //chek


    public Carta() {
        numero = "";
        pokemon = new Pokemon();
        rareza = "";
        artista = "";
    }

    public Carta(String numero, Pokemon pokemon, String rareza, String artista) {
        this.numero = numero;
        this.pokemon = pokemon;
        this.rareza = rareza;
        this.artista = artista;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return numero == carta.numero && pokemon.equals(carta.pokemon) && rareza.equals(carta.rareza) && artista.equals(carta.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, pokemon, rareza, artista);
    }

    @Override
    public String toString() {
        return super.toString() +"\t\t-------------Tipo Carta---------------\n"+
                                 "\t\t| numero = " + numero +              " \n"+
                                 "\t\t| Pokemon = " + pokemon +            " \n"+
                                 "\t\t| rareza = " + rareza +              " \n"+
                                 "\t\t| artista = " + artista +            " \n\n";
    }

    @Override
    public JSONObject toJson() throws JSONException {
        return null;
    }

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
}