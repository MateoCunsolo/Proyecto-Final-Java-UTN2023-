package ClasesGenericas;

import Interfaces.IFuncionalidades;

import java.util.LinkedHashSet;

public class ContenedorLHS <E> implements IFuncionalidades
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
}
