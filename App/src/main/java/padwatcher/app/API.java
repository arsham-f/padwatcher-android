package padwatcher.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class API {
    final static String baseURL = "http://padwatcher.com/";

    public static String GET(String surl) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(baseURL + surl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static ArrayList<Location> getLocations() {
        try {
            JSONArray locations = new JSONArray(GET("locations.php"));
            for (int i = 0; i < locations.length(); i++){
                //
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static Array parseJSON(String str) {
        try {
            JSONObject jso = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
