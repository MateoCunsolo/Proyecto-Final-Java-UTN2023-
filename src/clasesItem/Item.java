package clasesItem;

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



}
