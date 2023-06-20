package clasesItem;

import java.io.Serializable;
import java.util.Objects;

public class Carta extends Item implements Serializable
{

    private String numero; //chek
    private Pokemon pokemon; //chek
    private String rareza; //chek
    private String artista; //chek


    public Carta()
    {
        numero = "";
        pokemon = new Pokemon();
        rareza = "";
        artista = "";
    }

    public Carta(String numero, Pokemon pokemon, String rareza, String artista )
    {
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
        return "Carta{" +
                "numero='" + numero + '\'' +
                ", pokemon=" + pokemon +
                ", rareza='" + rareza + '\'' +
                ", artista='" + artista + '\'' +
                '}';
    }
}
