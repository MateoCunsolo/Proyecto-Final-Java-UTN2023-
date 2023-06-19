package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface I_toJSON {

    public JSONObject toJson() throws JSONException;
    public void fromJson (JSONObject jsonObject) throws JSONException;
}
