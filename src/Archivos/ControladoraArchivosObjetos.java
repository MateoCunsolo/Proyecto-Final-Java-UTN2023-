package Archivos;

import clasesPersonas.Usuario;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ControladoraArchivosObjetos
{
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
            } catch (IOException ex) {
                return mensaje2 = "Se produjo un error al cerrar el archivo";
            }
        }

        return mensaje1 + "\n" + mensaje2;
    }

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
            System.out.println(ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            Iterator it = mapaUsuarios.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry miMapa = (Map.Entry) it.next();
                System.out.println(miMapa.getValue());
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
                System.out.println(ex.getMessage());
            }
        }
        return mapaUsuarios;
    }
}
