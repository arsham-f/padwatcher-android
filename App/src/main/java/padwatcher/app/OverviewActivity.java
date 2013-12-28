package padwatcher.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OverviewActivity extends Activity {
    public int locationID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        this.overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        Intent intent = getIntent();

        TextView locationName = (TextView)findViewById(R.id.locationName);
        TextView changeLocationLink = (TextView)findViewById(R.id.changeLocationLink);
        ListView listingsList = (ListView)findViewById(R.id.listingsList);

        locationName.setText(intent.getStringExtra("location_name"));
        locationID = intent.getIntExtra("location_id", -1);

        getListingsThread getListings = new getListingsThread();
        ArrayList<Listing> listings = null;
        try {
            listings = getListings.execute(locationID).get();
            ListingAdapter listingsAdapter = new ListingAdapter(this, R.layout.listing_list_item);
            listingsList.setAdapter(listingsAdapter);
            listingsAdapter.notifyDataSetChanged();

            for (int i = 0; i < listings.size(); i++) {
                Listing listing = listings.get(i);
                listingsAdapter.add(listing);
            }
        } catch (Exception e) {
            Log.e("HEYHEY", e.getMessage(), e);}






        changeLocationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationSelection = new Intent(OverviewActivity.this, LocationSelectActivity.class);
                startActivity(locationSelection);
            }
        });

    }

    public class getListingsThread extends AsyncTask<Integer, Void, ArrayList<Listing>> {

        @Override
        protected ArrayList<Listing> doInBackground(Integer... id) {
            return API.getListings(id[0]);
        }
    }


}
