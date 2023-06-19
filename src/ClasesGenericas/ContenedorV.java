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
    public String  listar() {

        return "";

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

}
