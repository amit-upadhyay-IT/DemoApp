package krishikalyan.aupadhyay.myapp.farmersfield.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import krishikalyan.aupadhyay.myapp.R;
import krishikalyan.aupadhyay.myapp.farmersfield.CropsMonitoringActivity;
import krishikalyan.aupadhyay.myapp.farmersfield.ExistingCropDataSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExistingCropFragment extends Fragment {

    private RecyclerView recyclerViewCrops;
    private DatabaseReference usersExistingCrops;


    public ExistingCropFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_existing_crop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewCrops = (RecyclerView) view.findViewById(R.id.users_crop_existing_recycler_view);

        usersExistingCrops = FirebaseDatabase.getInstance().getReference().child("farmers_existing_crops");

        usersExistingCrops.keepSynced(true);

        recyclerViewCrops.setHasFixedSize(true);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ExistingCropDataSet, FieldDetailsHolder> firebaseRecyclerAdapterApril = new FirebaseRecyclerAdapter<ExistingCropDataSet, FieldDetailsHolder>(
                ExistingCropDataSet.class,
                R.layout.list_row_existing_crops,
                FieldDetailsHolder.class,
                usersExistingCrops
        ) {
            @Override
            protected void populateViewHolder(final FieldDetailsHolder viewHolder, final ExistingCropDataSet model, final int position) {


                viewHolder.crop_name_field_new.setText("Crop Name: "+model.getName());
                viewHolder.crop_date_field_new.setText("Sowing Date : "+model.getPlanting_date());
/*                viewHolder.minimum_rainfall.setText(String.valueOf("Minimum Rainfall :"+model.getMin_rainfall())+"mm");
                viewHolder.maximum_rainfall.setText(String.valueOf("Maximum Rainfall"+model.getMax_rainfall())+"mm");
                viewHolder.germination_min_temp.setText(String.valueOf("Min Germination Temp:"+model.getMin_germ_temp())+" °C");
                viewHolder.germination_max_temp.setText(String.valueOf("Max Germination Temp:"+model.getMax_germ_temp())+" °C");
                viewHolder.ripening_min_temp.setText(String.valueOf("Min Ripening Temp:"+model.getMin_rip_temp())+" °C");
                viewHolder.ripening_max_temp.setText(String.valueOf("Max Ripening Temp:"+model.getMax_rip_temp())+" °C");
                viewHolder.ph_min_value.setText(String.valueOf("pH Minimum value :"+model.getMin_ph()));
                viewHolder.ph_max_value.setText(String.valueOf("pH Maximum value :"+model.getMax_ph()));*/

                viewHolder.myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), CropsMonitoringActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("CROP_NAME", model.getName());
                        bundle.putString("PLANTING_DATE", model.getPlanting_date());
                        bundle.putString("MIN_TEMP", String.valueOf(model.getMin_germ_temp()));
                        bundle.putString("MAX_TEMP", String.valueOf(model.getMax_germ_temp()));
                        intent.putExtras(bundle);

                        getActivity().startActivity(intent);
                    }
                });

            }
        };

        recyclerViewCrops.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewCrops.setAdapter(firebaseRecyclerAdapterApril);

    }

    public static class FieldDetailsHolder extends RecyclerView.ViewHolder
    {
        TextView crop_name_field_new, minimum_rainfall, maximum_rainfall, germination_min_temp, germination_max_temp;
        TextView ripening_min_temp, ripening_max_temp, ph_min_value, ph_max_value;
        TextView crop_date_field_new;

        Button add_button_field_new;

        View myView;

        public FieldDetailsHolder(View itemView) {
            super(itemView);

            crop_name_field_new = (TextView) itemView.findViewById(R.id.crop_name_field_new);
            minimum_rainfall = (TextView) itemView.findViewById(R.id.minimum_rainfall);
            maximum_rainfall = (TextView) itemView.findViewById(R.id.maximum_rainfall);
            germination_min_temp = (TextView) itemView.findViewById(R.id.germination_min_temp);
            germination_max_temp = (TextView) itemView.findViewById(R.id.germination_max_temp);
            ripening_min_temp = (TextView) itemView.findViewById(R.id.ripening_min_temp);
            ripening_max_temp = (TextView) itemView.findViewById(R.id.ripening_max_temp);
            ph_min_value = (TextView) itemView.findViewById(R.id.ph_min_value);
            ph_max_value = (TextView) itemView.findViewById(R.id.ph_max_value);

            crop_date_field_new = (TextView) itemView.findViewById(R.id.crop_date_field_new);

            add_button_field_new = (Button) itemView.findViewById(R.id.add_button_field_new);

            myView = itemView;
        }
    }

}
