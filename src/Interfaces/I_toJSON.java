package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * La interfaz I_toJSON define los métodos para convertir un objeto en formato JSON y viceversa.
 */
public interface I_toJSON {
    /**
     * Convierte el objeto en un objeto JSON.
     *
     * @return El objeto JSON que representa al objeto actual.
     * @throws JSONException Si ocurre un error durante la conversión a JSON.
     */
    public JSONObject toJson() throws JSONException;

    /**
     * Convierte un objeto JSON en el objeto actual.
     *
     * @param jsonObject El objeto JSON que contiene la información para ser asignada al objeto actual.
     * @throws JSONException Si ocurre un error durante la conversión desde JSON.
     */
    public void fromJson (JSONObject jsonObject) throws JSONException;
}
