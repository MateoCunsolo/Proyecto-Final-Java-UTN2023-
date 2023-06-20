package ClasesGenericas;

import Interfaces.IFuncionalidades;

import java.io.Serializable;
import java.util.Vector;

public class ContenedorV <T> implements IFuncionalidades<T>, Serializable
{
    private Vector <T> miVector; //(aplica  items, historia de compras y de ventas)

    public ContenedorV()
    {
        miVector = new Vector<>();
    }

    @Override
    public int contar() {
        return 0;
    }

    @Override
    public String listar() {
        return null;
    }


    @Override
    public boolean eliminar(T o) {
        return false;
    }

    @Override
    public boolean agregar(T o) {
        return false;
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
    public T get(int indice) {
        if (indice >= 0 && indice < miVector.size()) {
            return miVector.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
    }

}
