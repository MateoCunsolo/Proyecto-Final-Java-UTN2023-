package clasesItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Pokemon implements Serializable
{

    private String nombre;
    private int ps; //puntos de salud
    private String evolucion;
    private Energia tipo;
    private List<Energia> arregloDebilidades;
    private HashSet<Ataque> hsAtaques;

    public Pokemon()
    {
        nombre = "";
        ps = 0;
        evolucion = "";
        tipo = Energia.EMPTY;
        arregloDebilidades = new ArrayList<>();
        hsAtaques = new HashSet<>();
    }

    public Pokemon(String nombre, int ps, String evolucion)
    {
        this.nombre = nombre;
        this.ps = ps;
        this.evolucion = evolucion;
        tipo = Energia.EMPTY;
        arregloDebilidades = new ArrayList<>();
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

    public String getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
    }

    public Energia getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return ps == pokemon.ps && nombre.equals(pokemon.nombre) && evolucion.equals(pokemon.evolucion) && tipo == pokemon.tipo && arregloDebilidades.equals(pokemon.arregloDebilidades) && hsAtaques.equals(pokemon.hsAtaques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, ps, evolucion, tipo, arregloDebilidades, hsAtaques);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", ps=" + ps +
                ", evolucion='" + evolucion + '\'' +
                ", tipo=" + tipo +
                ", arregloDebilidades=" + arregloDebilidades +
                ", hsAtaques=" + hsAtaques +
                '}';
    }
}
