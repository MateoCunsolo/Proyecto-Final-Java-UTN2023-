package clasesItem;

import java.util.Objects;

/**
 *
 *
 */
public class Ataque {

    private String nombreAtaque;
    private String daño;
    private String descripcion;

    public Ataque()
    {
        nombreAtaque = "";
        daño = "";
        descripcion = "";
    }

    public Ataque(String nombreAtaque, String daño, String descripcion)
    {
        this.nombreAtaque = nombreAtaque;
        this.daño = daño;
        this.descripcion = descripcion;
    }

    public String getNombreAtaque() {
        return nombreAtaque;
    }

    public void setNombreAtaque(String nombreAtaque) {
        this.nombreAtaque = nombreAtaque;
    }

    public String getDaño() {
        return daño;
    }

    public void setDaño(String daño) {
        this.daño = daño;
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
        return nombreAtaque.equals(ataque.nombreAtaque) && daño.equals(ataque.daño) && descripcion.equals(ataque.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreAtaque, daño, descripcion);
    }

    @Override
    public String toString() {
        return "Ataque{" +
                "nombreAtaque='" + nombreAtaque + '\'' +
                ", daño='" + daño + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
