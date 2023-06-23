package Transacciones;

import clasesItem.Item;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Intercambio extends Transaccion implements Serializable
{
    Item entrada;
    Item salida;

    /**
     * Constructor de la clase Intercambio sin argumentos.
     * Crea una instancia de Intercambio con valores predeterminados.
     */
    public Intercambio()
    {
        super();
        entrada = new Item();
        salida = new Item();
    }

    /**
     * Constructor de la clase Intercambio con argumentos.
     * Crea una instancia de Intercambio con los ítems de entrada y salida especificados.
     * @param entrada El ítem que se recibirá en el intercambio.
     * @param salida El ítem que se dará a cambio en el intercambio.
     */
    public Intercambio(Item entrada, Item salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    //recordar que los metodos lanzaran las siguientes excepciones:
    //1-DiferrenteRarezaExc
    //2-ProductoNoPublicado

    /**
     * Obtiene el ítem de entrada del intercambio.
     * @return El ítem de entrada.
     */
    public Item getEntrada() {
        return entrada;
    }
    /**
     * Establece el ítem de entrada del intercambio.
     * @param entrada El ítem de entrada a establecer.
     */
    public void setEntrada(Item entrada) {
        this.entrada = entrada;
    }
    /**
     * Obtiene el ítem de salida del intercambio.
     * @return El ítem de salida.
     */
    public Item getSalida() {
        return salida;
    }
    /**
     * Establece el ítem de salida del intercambio.
     * @param salida El ítem de salida a establecer.
     */
    public void setSalida(Item salida) {
        this.salida = salida;
    }
    /**
     * Obtiene la fecha del intercambio.
     * @return La fecha del intercambio.
     */
    @Override
    public LocalDateTime getFecha() {
        return super.getFecha();
    }
    /**
     * Devuelve una representación en forma de cadena de texto del objeto Intercambio.
     * @return La representación en forma de cadena de texto del objeto Intercambio.
     */
    @Override
    public String toString() {
        return super.toString() +
                "| Item entrado: = " + entrada +"\n"+
                "| Item intercambiado por:  " + salida + "\n";
    }
    /**
     * Compara si el objeto actual es igual a otro objeto dado.
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intercambio that)) return false;
        return Objects.equals(getEntrada(), that.getEntrada()) && Objects.equals(getSalida(), that.getSalida());
    }
    /**
     * Calcula el código hash del objeto Intercambio.
     * @return El código hash del objeto Intercambio.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getEntrada(), getSalida());
    }


}