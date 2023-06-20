package clasesItem;
import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {

    private double precio; //chek
    private String nombreDuenio; //es el nombre del usuario
    private String descrip; //chek
    private String id; //chek

    public Item() {
        precio = 0;
        nombreDuenio = "";
        descrip = "";
        id = "";
    }

    public Item(double precio, String nombreDuenio, String descrip, String id) {
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
        return Double.compare(item.precio, precio) == 0 && nombreDuenio.equals(item.nombreDuenio) && Objects.equals(descrip, item.descrip) && id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(precio, nombreDuenio, descrip, id);
    }

    @Override
    public String toString() {
        return "\n\n\n| ******************** Item ********************\n"+
                "| precio = " + precio +"\n"+
                "| nombreDuenio = " + nombreDuenio +"\n"+
                "| descrip = " + descrip +"\n"+
                "| id = " +id+"\n";
    }
}
