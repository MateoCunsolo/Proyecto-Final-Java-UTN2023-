package Transacciones;

import clasesItem.Item;

import java.util.Objects;

public class Intercambio
{
    Item entrada;
    Item salida;

    public Intercambio()
    {
        entrada = null;
        salida = null;
    }

    public Intercambio(Item entrada, Item salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    //recordar que los metodos lanzaran las siguientes excepciones:
    //1-DiferrenteRarezaExc
    //2-ProductoNoPublicado

}
