package clasesItem;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 *
 */
public class Ataque implements Serializable, Cloneable
{
    private static final long serialVersionUID = -1746184358533812907L;
    private String nombreAtaque; //chek
    private String danio; //chek
    private String descripcion; //chek


    public Ataque()
    {
        nombreAtaque = "";
        danio = "";
        descripcion = "";
    }

    public Ataque(String nombreAtaque, String danio, String descripcion)
    {
        this.nombreAtaque = nombreAtaque;
        this.danio = danio;
        this.descripcion = descripcion;
    }

    public String getNombreAtaque() {
        return nombreAtaque;
    }

    public void setNombreAtaque(String nombreAtaque) {
        this.nombreAtaque = nombreAtaque;
    }

    public String getDanio() {
        return danio;
    }

    public void setDanio(String danio) {
        this.danio = danio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ataque ataque = (Ataque) o;
        return nombreAtaque.equals(ataque.nombreAtaque) && danio.equals(ataque.danio) && descripcion.equals(ataque.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreAtaque, danio, descripcion);
    }

    @Override
    public String toString() {
        return  "\n\t\t\t\t|\t\t\t\t( * )" + nombreAtaque +"\n"+
                "\t\t\t\t|\t\t\t\t danio = " + danio + "\n"+
                "\t\t\t\t|\t\t\t\t descripcion = " + descripcion+" ";
    }

    @Override
    public Ataque clone() {
        try {
            return (Ataque) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de que la clonación no sea compatible
            e.printStackTrace();
            return null;
        }
    }
}
