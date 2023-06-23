package Archivos;

import clasesPersonas.Administrador;
import clasesPersonas.Usuario;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ControladoraArchivos
{
    /**
     * El método `grabarAdministrador` se utiliza para grabar la información de un administrador en un archivo.
     * Se hace en un bloque try-catch ya que capturan una excepcion del tipo IOException
     * @param nombre El nombre del administrador.
     * @param contra La contraseña del administrador.
     * @return Un mensaje indicando si la grabación fue exitosa o si se produjo un error.
     */
    public static String grabarAdministrador(String nombre, String contra)
    {
        String mensaje1 = " ";
        String mensaje2 = " ";
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream= null;

        try
        {
            fileOutputStream = new FileOutputStream("Administrador.dat");
            dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeUTF(nombre);
            dataOutputStream.writeUTF(contra);

        }
        catch (IOException ex)
        {
            mensaje1 = "No fue posible guardar los usuarios";
        }
        finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();

                if (dataOutputStream != null)
                    dataOutputStream.close();
            } catch (IOException ex) {
                return mensaje2 = "Se produjo un error al cerrar el archivo";
            }
        }

        return mensaje1 + "\n" + mensaje2;
    }

    /**
     * El método `leerAdministrador` se utiliza para leer la información de un administrador desde un archivo.
     * @return Un objeto `Administrador` con los datos leídos del archivo.
     */

    public static Administrador leerAdministrador()
    {
        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;

        String nombre = "";
        String contra = "";
        Administrador admin = new Administrador();

        try
        {
            fileInputStream = new FileInputStream("Administrador.dat");
            dataInputStream = new DataInputStream(fileInputStream);

            while (true)
            {
                nombre = dataInputStream.readUTF();
                contra = dataInputStream.readUTF();
            }

        }
        catch (EOFException ex)
        {
            //System.out.println("FIN del archivo");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            try
            {
                if (fileInputStream!=null)
                    fileInputStream.close();

                if (dataInputStream!=null)
                    dataInputStream.close();
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

            admin = new Administrador(nombre, contra);

        }
        return admin;
    }

    /**
     * El método `grabarUsuarios` se utiliza para grabar la información de los usuarios en un archivo.
     * @param mapaUsuarios Un TreeMap que contiene los usuarios a ser grabados.
     * @return Un mensaje indicando si la grabación fue exitosa o si se produjo un error.
     */

    public static String grabarUsuarios(TreeMap<String, Usuario> mapaUsuarios)
    {
        String mensaje1 = " ";
        String mensaje2 = " ";
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream("Usuarios.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Iterator it = mapaUsuarios.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry miMapa = (Map.Entry) it.next();
                objectOutputStream.writeObject(miMapa.getValue());
            }

        }
        catch (IOException ex)
        {
            mensaje1 = "No fue posible guardar los usuarios";
        }
        finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();

                if (objectOutputStream != null)
                    objectOutputStream.close();
            } catch (IOException ex)
            {
                return mensaje2 = "Se produjo un error al cerrar el archivo";
            }
        }

        return mensaje1 + "\n" + mensaje2;
    }

    /**
     * Lee y carga los usuarios almacenados en un archivo binario en un TreeMap.
     *
     * @return TreeMap que contiene los usuarios leídos del archivo. La clave es el nombre del usuario y el valor es un objeto Usuario.
     * @throws EOFException si se alcanza el final del archivo durante la lectura de objetos.
     * @throws ClassNotFoundException si no se encuentra la clase Usuario al deserializar los objetos.
     * @throws IOException si ocurre algún error de entrada/salida durante la lectura del archivo.
     */
    public static TreeMap<String,Usuario> leerUsuarios()
    {

        TreeMap<String,Usuario>mapaUsuarios = new TreeMap<>();

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try
        {
            fileInputStream = new FileInputStream("Usuarios.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            while (true)
            {
                Usuario aux = (Usuario) objectInputStream.readObject();
                mapaUsuarios.put(aux.getNombre(),aux);

            }

        }
        catch (EOFException ex)
        {
            System.out.println("FIN de ARCHIVO");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.printf("aca 1");
            System.out.println(ex.getMessage());

        }
        catch (IOException ex)
        {
            System.out.printf("aca 2");
            System.out.println(ex.getMessage());
        }
        finally
        {
            Iterator it = mapaUsuarios.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry miMapa = (Map.Entry) it.next();
                //System.out.println(miMapa.getValue());
            }
            try
            {
                if (fileInputStream!=null)
                    fileInputStream.close();

                if (objectInputStream!=null)
                    objectInputStream.close();
            }
            catch (IOException ex)
            {
                System.out.printf("aca 3");
                System.out.println(ex.getMessage());
            }
        }
        return mapaUsuarios;
    }
}
