package krishikalyan.aupadhyay.myapp.farmersfield;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import krishikalyan.aupadhyay.myapp.R;

public class CropsMonitoringActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    RecyclerView sensor_details_recycler_view;

    private String min_temp, max_temp;

    private double min_moist, max_moist;

    private void getDetails()
    {
        Bundle bundle = getIntent().getExtras();
        min_temp = bundle.getString("MIN_TEMP", "0");
        max_temp = bundle.getString("MAX_TEMP", "0");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crops_monitoring);

        getDetails();
        min_moist = 0.18;
        max_moist = 0.22;

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://krishi-kalyan.firebaseio.com/data_from_sensors/");

        sensor_details_recycler_view = (RecyclerView) findViewById(R.id.sensor_details_recycler_view);

        mDatabase.keepSynced(true);
        sensor_details_recycler_view.setHasFixedSize(true);

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<SoilDataSet, SensorDetailsHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SoilDataSet, SensorDetailsHolder>(
                SoilDataSet.class,
                R.layout.list_row_from_server,
                SensorDetailsHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(SensorDetailsHolder viewHolder, SoilDataSet model, int position) {

                String sT = model.getTemp();
                double tem = Double.parseDouble(sT);

                if (tem >= Double.parseDouble(min_temp) && tem <= Double.parseDouble(max_temp))
                {
                    viewHolder.image_soil_temp.setBackgroundResource(R.drawable.ic_thumb_up_black_24dp);
                }
                else
                {
                    viewHolder.image_soil_temp.setBackgroundResource(R.drawable.ic_thumb_down_black_24dp);
                }

                if (Double.parseDouble(model.getMois()) > 0.18)
                {
                    viewHolder.image_soil_moisture.setBackgroundResource(R.drawable.ic_thumb_up_black_24dp);
                }
                else
                {
                    viewHolder.image_soil_moisture.setBackgroundResource(R.drawable.ic_thumb_down_black_24dp);
                }

                viewHolder.image_light_intensity.setBackgroundResource(R.drawable.ic_thumb_up_black_24dp);

                viewHolder.time_stamp.setText("Time: "+ model.getTime());
                viewHolder.soil_moisture.setText("Soil Moisture : "+model.getMois());
                viewHolder.soil_temperature.setText("Temperature : "+model.getTemp()+" 'C");
                viewHolder.light_intensity.setText("Light Intensity : "+model.getLight());


            }
        };

        sensor_details_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        sensor_details_recycler_view.setAdapter(firebaseRecyclerAdapter);

    }

    public static class SensorDetailsHolder extends RecyclerView.ViewHolder
    {

        TextView soil_moisture, soil_temperature, light_intensity, time_stamp;
        ImageButton image_soil_moisture, image_soil_temp, image_light_intensity;

        public SensorDetailsHolder(View itemView) {
            super(itemView);

            soil_moisture = (TextView) itemView.findViewById(R.id.soil_moisture);
            soil_temperature = (TextView) itemView.findViewById(R.id.soil_temperature);
            light_intensity = (TextView) itemView.findViewById(R.id.light_intensity);
            time_stamp = (TextView) itemView.findViewById(R.id.time_stamp);

            image_soil_moisture = (ImageButton) itemView.findViewById(R.id.image_soil_moisture);
            image_soil_temp = (ImageButton) itemView.findViewById(R.id.image_soil_temp);
            image_light_intensity = (ImageButton) itemView.findViewById(R.id.image_light_intensity);
        }
    }
}
