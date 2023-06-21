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

public class Pokemon implements Serializable, I_toJSON {

    private String nombre; //chek
    private int ps; //puntos de salud //chek
    private String evolucionA; //evolvesTo //chek
    private String evolucionaDe; //chek
    private Energia tipo; //chek
    private Energia debilidad; //chek
    private HashSet<Ataque> hsAtaques; //chek

    public Pokemon() {
        nombre = "";
        ps = 0;
        evolucionA = "";
        evolucionaDe = "";
        tipo = Energia.EMPTY;
        debilidad = Energia.EMPTY;
        hsAtaques = new HashSet<>();
    }

    public Pokemon(String nombre, int ps, String evolucionA, String evolucionaDe) {
        this.nombre = nombre;
        this.ps = ps;
        this.evolucionA = evolucionA;
        this.evolucionaDe = evolucionaDe;
        tipo = Energia.EMPTY;
        debilidad = Energia.EMPTY;
        hsAtaques = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getEvolucionA() {
        return evolucionA;
    }

    public void setEvolucionA(String evolucion) {
        this.evolucionA = evolucion;
    }

    public void setDebilidad(Energia debilidad) {
        this.debilidad = debilidad;
    }

    public Energia getTipo() {
        return tipo;
    }

    public void setTipo(Energia tipo) {
        this.tipo = tipo;
    }

    public String getEvolucionaDe() {
        return evolucionaDe;
    }

    public void setEvolucionaDe(String evolucionaDe) {
        this.evolucionaDe = evolucionaDe;
    }

    public Energia getDebilidad() {
        return debilidad;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return ps == pokemon.ps && nombre.equals(pokemon.nombre) && evolucionA.equals(pokemon.evolucionA) && evolucionaDe.equals(pokemon.evolucionaDe) && tipo == pokemon.tipo && debilidad == pokemon.debilidad && hsAtaques.equals(pokemon.hsAtaques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, ps, evolucionA, evolucionaDe, tipo, debilidad, hsAtaques);
    }

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

    public void agregarAtaque(Ataque a) {
        hsAtaques.add(a);
    }

    public String listarAtaques() {
        return hsAtaques.toString();
    }

    @Override
    public JSONObject toJson() throws JSONException {
        return null;
    }

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
}





