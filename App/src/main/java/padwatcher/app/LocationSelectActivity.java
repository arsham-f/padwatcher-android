package padwatcher.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LocationSelectActivity extends ActionBarActivity {
    public ArrayList<Location> locationlist = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_select);
        ListView locations = (ListView)findViewById(R.id.locations);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.location_list_item);
        locations.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        getLocationsThread locationsThread = new getLocationsThread();


        try {
            locationlist = locationsThread.execute().get();
            for (int i = 0; i < locationlist.size(); i++) {
                adapter.add(locationlist.get(i).name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Activity thisActivity = this;

        locations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location selected = locationlist.get(position);
                Intent overviewIntent = new Intent(thisActivity, LocationOverviewActivity.class);
                overviewIntent.putExtra("location_name", selected.name);
                overviewIntent.putExtra("location_id", selected.id);
                startActivity(overviewIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class getLocationsThread extends AsyncTask<Void, Void, ArrayList<Location>>{

        @Override
        protected ArrayList<Location> doInBackground(Void... params) {
            return API.getLocations();
        }
    }

}
