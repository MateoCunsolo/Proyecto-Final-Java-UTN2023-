package clasesItem;
import java.util.Objects;

public  class Item {

    private double precio;
    private String nombreDuenio; //es el nombre del usuario
    private String descrip;
    private int id;

    public Item()
    {
        precio = 0;
        nombreDuenio = "";
        descrip = "";
        id = 0;
    }

    public Item (double precio, String nombreDuenio, String descrip, int id)
    {
        this.precio = 0;
        this.nombreDuenio = nombreDuenio;
        this.descrip = descrip;
        this.id = 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.precio, precio) == 0 && id == item.id && nombreDuenio.equals(item.nombreDuenio) && descrip.equals(item.descrip);
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
