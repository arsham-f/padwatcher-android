package padwatcher.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OverviewActivity extends Activity {
    public int locationID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        TextView locationName = (TextView)findViewById(R.id.locationName);
        TextView changeLocationLink = (TextView)findViewById(R.id.changeLocationLink);
        Intent intent = getIntent();

        locationName.setText(intent.getStringExtra("location_name"));
        locationID = intent.getIntExtra("location_id", -1);


        changeLocationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationSelection = new Intent(OverviewActivity.this, LocationSelectActivity.class);
                startActivity(locationSelection);
            }
        });

    }


}
