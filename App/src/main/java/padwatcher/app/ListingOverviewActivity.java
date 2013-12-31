package padwatcher.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class ListingOverviewActivity extends ActionBarActivity {
    Listing listing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();

        String listingJsonString = thisIntent.getStringExtra("listingJsonString");
        listing = Listing.FromJSON(listingJsonString);

        setContentView(R.layout.activity_listing_overview);
        TextView title = (TextView)findViewById(R.id.title);
        TextView description = (TextView)findViewById(R.id.description);

        title.setText(listing.displayTitle);
        description.setText(listing.description);

    }

    public void openMap(View v) {
        String geoUrl = "geo:" + listing.lat + "," + listing.longi;
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUrl));
        startActivity(mapIntent);
    }

}
