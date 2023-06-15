package Transacciones;

import clasesItem.Item;

import java.io.Serializable;
import java.util.Objects;

public class Intercambio implements Serializable
{
    Item entrada;
    Item salida;

    public Intercambio()
    {
        entrada = new Item();
        salida = new Item();
    }

    public Intercambio(Item entrada, Item salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    //recordar que los metodos lanzaran las siguientes excepciones:
    //1-DiferrenteRarezaExc
    //2-ProductoNoPublicado


    public Item getEntrada() {
        return entrada;
    }

    public void setEntrada(Item entrada) {
        this.entrada = entrada;
    }

    public Item getSalida() {
        return salida;
    }

    public void setSalida(Item salida) {
        this.salida = salida;
    }

    @Override
    public String toString() {
        return "Intercambio{" +
                "entrada=" + entrada +
                ", salida=" + salida +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intercambio that)) return false;
        return Objects.equals(getEntrada(), that.getEntrada()) && Objects.equals(getSalida(), that.getSalida());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntrada(), getSalida());
    }
}
