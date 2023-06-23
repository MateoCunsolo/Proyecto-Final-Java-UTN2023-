import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
/**
 * La clase `ConsumoAPI` proporciona funcionalidad para realizar solicitudes GET a URL externas
 * y obtener la información devuelta como una cadena de texto.
 */
public class ConsumoAPI
{
    /**
     * Realiza una solicitud GET a una URL externa y devuelve la información obtenida como una cadena de texto.
     *
     * @param external_url La URL externa a la que se realizará la solicitud.
     * @return La información obtenida de la URL externa como una cadena de texto.
     */
    public static String getInfo(String external_url)
    {
        try
        {
            URL url = new URL(external_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200)
            {
                throw new RuntimeException("Codigo de error: "+responseCode);
            }
            else
            {
                StringBuilder stringBuilder = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext())
                {
                    stringBuilder.append(scanner.nextLine());
                }
                scanner.close();
                return stringBuilder.toString();
            }
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
        return "";
    }
}
