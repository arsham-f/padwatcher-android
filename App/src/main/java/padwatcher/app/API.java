package padwatcher.app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class API {
    final static String baseURL = "http://padwatcher.com/scripts/";

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
            Log.e("HEYHEY", "failed", e);
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static ArrayList<Location> getLocations() {
        ArrayList<Location> builder = new ArrayList<Location>();
        try {
            JSONObject locations = new JSONObject(GET("locations.php"));

            Iterator it = locations.keys();
            while (it.hasNext()) {
                String id = (String)it.next();
                JSONObject this_location = new JSONObject(locations.getString(id));
                String name = this_location.getString("name");
                try {
                    builder.add(new Location(name, Integer.parseInt(id)));
                } catch (NumberFormatException e) {}
            }
            return builder;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<Listing> getListings(int id) {
        ArrayList<Listing> listings = new ArrayList<Listing>();

        try {
            String response = GET("location_listings.php?id=" + Integer.toString(id));
            JSONArray listingsJson = new JSONArray(response);

            for (int i = 0; i < listingsJson.length(); i++) {
                JSONObject listingObject = listingsJson.getJSONObject(i);

                String title = listingObject.getString("title");
                String url = listingObject.getString("url");
                String price = listingObject.getString("price");
                listings.add(new Listing(title, url, price));
            }

            return listings;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
