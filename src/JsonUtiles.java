import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * La clase `JsonUtiles` proporciona métodos de utilidad para trabajar con archivos JSON.
 */
public class JsonUtiles {
	/**
	 * Escribe un JSONArray en un archivo JSON.
	 *
	 * @param array   El JSONArray a escribir.
	 * @param archivo El nombre del archivo JSON (sin la extensión .json).
	 */
	public static void grabar(JSONArray array, String archivo) {
		try {
			FileWriter file = new FileWriter(archivo+".json");
			file.write(array.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Escribe un JSONObject en un archivo JSON.
	 *
	 * @param jsonObject El JSONObject a escribir.
	 * @param archivo    El nombre del archivo JSON (sin la extensión .json).
	 */

	public static void grabar(JSONObject jsonObject, String archivo) {
		try {
			FileWriter file = new FileWriter(archivo+".json");
			file.write(jsonObject.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Lee el contenido de un archivo JSON y lo devuelve como una cadena de texto.
	 *
	 * @param archivo El nombre del archivo JSON (sin la extensión .json).
	 * @return El contenido del archivo JSON como una cadena de texto.
	 */
	public static String leer(String archivo) 
	{
		String contenido = "";
		try 
		{
			contenido = new String(Files.readAllBytes(Paths.get(archivo+".json")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return contenido;
	}
}
