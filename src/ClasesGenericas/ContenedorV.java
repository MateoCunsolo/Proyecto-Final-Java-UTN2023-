package ClasesGenericas;

import Interfaces.IFuncionalidades;

import java.io.Serializable;
import java.util.Vector;

public class ContenedorV <T> implements IFuncionalidades, Serializable
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
    public void listar() {

    }

    @Override
    public boolean eliminar() {
        return false;
    }

    @Override
    public boolean agregar(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return "ContenedorV{" +
                "miVector=" + miVector +
                '}';
    }
}
