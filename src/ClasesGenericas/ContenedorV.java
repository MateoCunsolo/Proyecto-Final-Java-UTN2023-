package ClasesGenericas;

import Interfaces.IFuncionalidades;
import clasesItem.Carta;
import clasesItem.Item;

import java.io.Serializable;
import java.util.Vector;

public class ContenedorV <T> implements IFuncionalidades<T>, Serializable {
    private static final long serialVersionUID = -2561961538353586188L;

    private Vector<T> miVector; //(aplica  items, historia de compras y de ventas)

    public ContenedorV() {
        miVector = new Vector<>();
    }

    @Override
    public int contar() {
        return  miVector.size();
    }

    @Override
    public String listar() {
        return "";
    }

    public boolean vacio() {
        return miVector.isEmpty();
    }

    @Override
    public boolean eliminar(T o) {
        return miVector.remove(o);
    }

    public void eliminarCompleto()
    {
         miVector.removeAllElements();
    }

    @Override
    public boolean agregar(T o) {
       return miVector.add(o);
    }

    @Override
    public String toString() {
        return "ContenedorV{" +
                "miVector=" + miVector +
                '}';
    }

    public int tamanio()
    {
        return miVector.size();
    }

    public T borrarUltimo()
    {
        return (T) miVector.lastElement();
    }

    public T get(int indice) {
        if (indice >= 0 && indice < miVector.size()) {
            return miVector.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
    }

}
