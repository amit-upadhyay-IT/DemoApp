package krishikalyan.aupadhyay.myapp.whether_model.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import krishikalyan.aupadhyay.myapp.R;
import krishikalyan.aupadhyay.myapp.whether_model.Rainfall_Model_Dataset;

public class TemperaturePredictionFragment extends Fragment {

    private RecyclerView theRecyclerView;
    private DatabaseReference mDatabase;

    public TemperaturePredictionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temperature_prediction, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        theRecyclerView = (RecyclerView) view.findViewById(R.id.rainfall_prediction_recyclerview);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("railfall_ml_model");
        mDatabase.keepSynced(true);
        theRecyclerView.setHasFixedSize(true);
        theRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Rainfall_Model_Dataset, PredictionListViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Rainfall_Model_Dataset, PredictionListViewHolder>(
                Rainfall_Model_Dataset.class,
                R.layout.prediction_list_row,
                PredictionListViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(PredictionListViewHolder viewHolder, Rainfall_Model_Dataset model, final int position) {

                viewHolder.prediction_month.setVisibility(View.VISIBLE);
                viewHolder.prediction_month.setText(model.getMonth());

                double avgT = model.getAverage_Temperature();

                viewHolder.first_line_prediction.setVisibility(View.VISIBLE);
                viewHolder.first_line_prediction.setText("Average Temp: "+String.format("%.02f", avgT));


                double minT = model.getMaximum_Temperature();
                viewHolder.second_line_prediction.setVisibility(View.VISIBLE);
                viewHolder.second_line_prediction.setText("Minimum Temp: "+String.format("%.2f", minT));


                double maxT = model.getMinimum_Temperature();
                viewHolder.third_line_prediction.setVisibility(View.VISIBLE);
                viewHolder.third_line_prediction.setText("Maximum Temp: "+String.format("%.2f", maxT));

                viewHolder.image_prediction.setVisibility(View.VISIBLE);
                viewHolder.image_prediction.setBackgroundResource(R.drawable.ic_thermometer_black_36dp);

                viewHolder.item_detail_prediction.setText(String.format("%.3f", maxT));

            }
        };

        theRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class PredictionListViewHolder extends RecyclerView.ViewHolder
    {
        TextView prediction_month, prediction_description, first_line_prediction, second_line_prediction;
        TextView third_line_prediction, image_prediction, item_detail_prediction;

        public PredictionListViewHolder(View itemView) {
            super(itemView);

            prediction_month = (TextView) itemView.findViewById(R.id.prediction_month);
            prediction_description = (TextView) itemView.findViewById(R.id.prediction_description);
            first_line_prediction = (TextView) itemView.findViewById(R.id.first_line_prediction);
            second_line_prediction = (TextView) itemView.findViewById(R.id.second_line_prediction);
            third_line_prediction = (TextView) itemView.findViewById(R.id.third_line_prediction);
            image_prediction = (TextView) itemView.findViewById(R.id.image_prediction);
            item_detail_prediction = (TextView) itemView.findViewById(R.id.item_detail_prediction);
        }


    }
}
