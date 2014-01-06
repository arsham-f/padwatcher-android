package padwatcher.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationOverviewActivity extends Activity {
    public int locationID = 0;
    public ArrayList<Listing> listings = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_overview);

        this.overridePendingTransition(R.anim.right_to_stage, R.anim.stage_to_left);
        final Intent intent = getIntent();

        TextView locationName = (TextView)findViewById(R.id.locationName);
        TextView changeLocationLink = (TextView)findViewById(R.id.changeLocationLink);
        ListView listingsList = (ListView)findViewById(R.id.listingsList);

        locationName.setText(intent.getStringExtra("location_name"));
        locationID = intent.getIntExtra("location_id", -1);

        getListingsThread getListings = new getListingsThread();

        try {
            listings = getListings.execute(locationID).get();
            ListingAdapter listingsAdapter = new ListingAdapter(this, R.layout.listing_list_item);
            listingsList.setAdapter(listingsAdapter);
            listingsAdapter.notifyDataSetChanged();

            for (int i = 0; i < listings.size(); i++) {
                Listing listing = listings.get(i);
                listingsAdapter.add(listing);
            }

            listingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Listing selectedListing = listings.get(position);
                    Intent listingOverview = new Intent(LocationOverviewActivity.this, ListingOverviewActivity.class);
                    listingOverview.putExtra("listingJsonString", selectedListing.JsonObject.toString());
                    listingOverview.putExtra("cityName", intent.getStringExtra("location_name"));
                    startActivity(listingOverview);
                    overridePendingTransition(R.anim.right_to_stage, R.anim.stage_to_left);

                }
            });
        } catch (Exception e) {}

        changeLocationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationSelection = new Intent(LocationOverviewActivity.this, LocationSelectActivity.class);
                startActivity(locationSelection);
                overridePendingTransition(R.anim.left_to_stage, R.anim.stage_to_right);
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
