package Interfaces;

public interface IFuncionalidades<T>
{
    int contar();
    String listar();
    boolean eliminar(T o);
    boolean agregar(T o);
}
