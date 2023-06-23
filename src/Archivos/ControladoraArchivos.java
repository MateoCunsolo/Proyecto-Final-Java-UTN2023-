package Archivos;

import clasesPersonas.Administrador;
import clasesPersonas.Usuario;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ControladoraArchivos
{
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
            //System.out.println("FIN de ARCHIVO");
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
                System.out.println(ex.getMessage());
            }
        }
        return mapaUsuarios;
    }
}
