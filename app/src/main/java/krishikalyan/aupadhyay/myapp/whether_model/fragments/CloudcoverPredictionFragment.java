package krishikalyan.aupadhyay.myapp.whether_model.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import krishikalyan.aupadhyay.myapp.R;

public class CloudcoverPredictionFragment extends Fragment {

    public CloudcoverPredictionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cloudcover_prediction, container, false);
    }

}
