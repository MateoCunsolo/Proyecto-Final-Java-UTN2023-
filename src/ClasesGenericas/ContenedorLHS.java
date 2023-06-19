package ClasesGenericas;

import Interfaces.IFuncionalidades;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class ContenedorLHS <T> implements IFuncionalidades<T>, Serializable
{

    private LinkedHashSet <T> miLHSet;

    public ContenedorLHS()
    {
        miLHSet = new LinkedHashSet<>();
    }

    @Override
    public int contar() {
        return 0;
    }

    @Override
    public String listar()
    {
        String msj = "";

        Iterator it = miLHSet.iterator();
        while(it.hasNext())
        {
            msj = msj + it.next().toString();
        }
        return msj;
    }

    @Override
    public boolean eliminar(T o) {

        boolean rta = false;
        if(o != null)
        {
            miLHSet.remove(o);
            rta = true;
        }

        return rta;
    }

    @Override
    public boolean agregar(T o) {

        boolean rta = false;
        if(o != null)
        {
            miLHSet.add(o);
            rta = true;
        }

        return rta;
    }

    @Override
    public String toString() {
        return "ContenedorLHS{" +
                "miLHSet=" + miLHSet +
                '}';
    }

    private static final long serialVersionUID = -4787901688912755069L;

}
