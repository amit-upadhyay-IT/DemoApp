package krishikalyan.aupadhyay.myapp.whether_model.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import krishikalyan.aupadhyay.myapp.R;

public class TemperaturePredictionFragment extends Fragment {


    public TemperaturePredictionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temperature_prediction, container, false);
    }

}
