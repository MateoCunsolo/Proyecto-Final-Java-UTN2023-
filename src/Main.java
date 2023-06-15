import claseEnvoltorio.PokeMarket;
import clasesPersonas.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args)
    {
        PokeMarket pokeMarket = new PokeMarket();

        try
        {
            String archivoJsonUsu = JsonUtiles.leer("MOCK_DATA (11)");
            JSONArray jsonArray = new JSONArray(archivoJsonUsu);

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario aux = new Usuario(jsonObject.getString("first_name"),jsonObject.getString("password"),jsonObject.getString("email"));

                boolean rta = pokeMarket.agregarUsuario(aux);
                if(rta)
                {
                    System.out.printf("\n Usuario creado con exito" + aux.getNombre());
                }
                else
                {
                    System.out.printf("No fue posible crear el usuario :(");
                }
            }
            pokeMarket.guardarUsuariosArchivo();
            pokeMarket.leerUsuariosArchivo();


        }catch (JSONException ex)
        {
            System.out.println("JSON mal formado");
        }

    }
}