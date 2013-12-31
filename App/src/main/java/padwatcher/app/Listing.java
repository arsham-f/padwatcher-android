package padwatcher.app;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Listing {
    String title;
    String displayTitle;
    String url;
    String price;
    String address;
    String description;
    String lat;
    String longi;

    JSONObject JsonObject;

    public Listing(JSONObject listingObject) throws JSONException {
        this.JsonObject = listingObject;
        this.title = listingObject.getString("title");
        this.displayTitle = cleanTitle(title);
        this.url = listingObject.getString("url");
        this.price = listingObject.getString("price");
        this.address = listingObject.getString("address");
        this.description = listingObject.getString("description");
        this.lat = listingObject.getString("lat");
        this.longi = listingObject.getString("long");
    }

    public static Listing FromJSON(String jsonString) {
        try {
            Log.e("HEYHEY", jsonString);
            JSONObject listingObject = new JSONObject(jsonString);
            return new Listing(listingObject);
        } catch (JSONException e) {
            Log.e("HEYHEY", e.getMessage(), e);
        }
        return null;
    }

    public String cleanTitle(String title) {
        String[] titleParts = title.split("\\(");
        String ret = "";
        for (int i = 0; i < titleParts.length - 1; i++) {
            ret += titleParts[i];
        }
        return ret;
    }
}
