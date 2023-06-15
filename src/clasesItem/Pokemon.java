package clasesItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Pokemon implements Serializable {

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
        debilidad= Energia.EMPTY;
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
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", ps=" + ps +
                ", evolucionA='" + evolucionA + '\'' +
                ", evolucionaDe='" + evolucionaDe + '\'' +
                ", tipo=" + tipo +
                ", debilidad=" + debilidad +
                ", hsAtaques=" + hsAtaques +
                '}';
    }

    public void agregarAtaque (Ataque a)
    {
        hsAtaques.add(a);
    }

    public String listarAtaques()
    {
        return hsAtaques.toString();
    }
}





