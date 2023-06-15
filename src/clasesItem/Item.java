package clasesItem;
import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable
{

    private double precio; //chek
    private String nombreDuenio; //es el nombre del usuario
    private String descrip; //chek
    private String id; //chek

    public Item()
    {
        precio = 0;
        nombreDuenio = "";
        descrip = "";
        id = "";
    }

    public Item (double precio, String nombreDuenio, String descrip, String id)
    {
        this.precio = 0;
        this.nombreDuenio = nombreDuenio;
        this.descrip = descrip;
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombreDuenio() {
        return nombreDuenio;
    }

    public void setNombreDuenio(String nombreDuenio) {
        this.nombreDuenio = nombreDuenio;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.precio, precio) == 0 && id.equals(item.id) && nombreDuenio.equals(item.nombreDuenio) && descrip.equals(item.descrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(precio, nombreDuenio, descrip, id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "precio=" + precio +
                ", nombreDuenio='" + nombreDuenio + '\'' +
                ", descrip='" + descrip + '\'' +
                ", id=" + id +
                '}';
    }
}
