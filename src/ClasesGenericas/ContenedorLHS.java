package ClasesGenericas;

import Interfaces.IFuncionalidades;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class ContenedorLHS <E> implements IFuncionalidades, Serializable
{

    private LinkedHashSet <E> miLHSet;

    public ContenedorLHS()
    {
        miLHSet = new LinkedHashSet<>();
    }

    @Override
    public int contar() {
        return 0;
    }

    @Override
    public void listar()
    {

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
        return "ContenedorLHS{" +
                "miLHSet=" + miLHSet +
                '}';
    }
}
