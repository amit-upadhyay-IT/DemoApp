package krishikalyan.aupadhyay.myapp.farmersfield.fragments;


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
import krishikalyan.aupadhyay.myapp.farmersfield.MonthDataSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCropFragment extends Fragment {

    private RecyclerView recyclerViewApril, recyclerViewJune;

    private DatabaseReference aprilDbRef, juneDbRef;
    private DatabaseReference usersExistingCrops;

    public NewCropFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_crop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usersExistingCrops = FirebaseDatabase.getInstance().getReference().child("farmers_existing_crops");

        recyclerViewApril = (RecyclerView) view.findViewById(R.id.april_month_recycler_view);
        recyclerViewJune = (RecyclerView) view.findViewById(R.id.june_month_recycler_view);


        aprilDbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://krishi-kalyan.firebaseio.com/field_details/tamilnadu/month04/");
        juneDbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://krishi-kalyan.firebaseio.com/field_details/tamilnadu/month06/");

        aprilDbRef.keepSynced(true);
        juneDbRef.keepSynced(true);

        recyclerViewApril.setHasFixedSize(true);
        recyclerViewJune.setHasFixedSize(true);



    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<MonthDataSet, FieldDetailsHolder> firebaseRecyclerAdapterApril = new FirebaseRecyclerAdapter<MonthDataSet, FieldDetailsHolder>(
                MonthDataSet.class,
                R.layout.list_row_field_new,
                FieldDetailsHolder.class,
                aprilDbRef
        ) {
            @Override
            protected void populateViewHolder(final FieldDetailsHolder viewHolder, final MonthDataSet model, final int position) {


                viewHolder.crop_name_field_new.setText("Crop Name: "+model.getName());
                viewHolder.minimum_rainfall.setText(String.valueOf("Minimum Rainfall :"+model.getMin_rainfall())+"mm");
                viewHolder.maximum_rainfall.setText(String.valueOf("Maximum Rainfall"+model.getMax_rainfall())+"mm");
                viewHolder.germination_min_temp.setText(String.valueOf("Min Germination Temp:"+model.getMin_germ_temp())+" °C");
                viewHolder.germination_max_temp.setText(String.valueOf("Max Germination Temp:"+model.getMax_germ_temp())+" °C");
                viewHolder.ripening_min_temp.setText(String.valueOf("Min Ripening Temp:"+model.getMin_rip_temp())+" °C");
                viewHolder.ripening_max_temp.setText(String.valueOf("Max Ripening Temp:"+model.getMax_rip_temp())+" °C");
                viewHolder.ph_min_value.setText(String.valueOf("pH Minimum value :"+model.getMin_ph()));
                viewHolder.ph_max_value.setText(String.valueOf("pH Maximum value :"+model.getMax_ph()));

                viewHolder.add_button_field_new.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        usersExistingCrops.child(String.valueOf(position)).child("name").setValue(model.getName());
                        usersExistingCrops.child(String.valueOf(position)).child("planting_date").setValue("2-04-2017");
                        usersExistingCrops.child(String.valueOf(position)).child("min_rainfall").setValue(model.getMin_rainfall());
                        usersExistingCrops.child(String.valueOf(position)).child("max_rainfall").setValue(model.getMax_rainfall());
                        usersExistingCrops.child(String.valueOf(position)).child("min_germ_temp").setValue(model.getMin_germ_temp());
                        usersExistingCrops.child(String.valueOf(position)).child("min_rip_temp").setValue(model.getMin_rip_temp());
                        usersExistingCrops.child(String.valueOf(position)).child("max_rip_temp").setValue(model.getMax_rip_temp());
                        usersExistingCrops.child(String.valueOf(position)).child("min_ph").setValue(model.getMin_ph());
                        usersExistingCrops.child(String.valueOf(position)).child("max_ph").setValue(model.getMax_ph());

                        viewHolder.myView.setVisibility(View.GONE);

                    }
                });

            }
        };

        recyclerViewApril.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewJune.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewApril.setAdapter(firebaseRecyclerAdapterApril);


        FirebaseRecyclerAdapter<MonthDataSet, FieldDetailsHolder> firebaseRecyclerAdapterJune = new FirebaseRecyclerAdapter<MonthDataSet, FieldDetailsHolder>(
                MonthDataSet.class,
                R.layout.list_row_field_new,
                FieldDetailsHolder.class,
                juneDbRef
        ) {
            @Override
            protected void populateViewHolder(final FieldDetailsHolder viewHolder, final MonthDataSet model, final int position) {


                viewHolder.crop_name_field_new.setText("Crop Name: "+model.getName());
                viewHolder.minimum_rainfall.setText(String.valueOf("Minimum Rainfall :"+model.getMin_rainfall())+"mm");
                viewHolder.maximum_rainfall.setText(String.valueOf("Maximum Rainfall"+model.getMax_rainfall())+"mm");
                viewHolder.germination_min_temp.setText(String.valueOf("Min Germination Temp:"+model.getMin_germ_temp())+" °C");
                viewHolder.germination_max_temp.setText(String.valueOf("Max Germination Temp:"+model.getMax_germ_temp())+" °C");
                viewHolder.ripening_min_temp.setText(String.valueOf("Min Ripening Temp:"+model.getMin_rip_temp())+" °C");
                viewHolder.ripening_max_temp.setText(String.valueOf("Max Ripening Temp:"+model.getMax_rip_temp())+" °C");
                viewHolder.ph_min_value.setText(String.valueOf("pH Minimum value :"+model.getMin_ph()));
                viewHolder.ph_max_value.setText(String.valueOf("pH Maximum value :"+model.getMax_ph()));

                viewHolder.add_button_field_new.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        usersExistingCrops.child(String.valueOf(position+2)).child("name").setValue(model.getName());
                        usersExistingCrops.child(String.valueOf(position+2)).child("planting_date").setValue("2-04-2017");
                        usersExistingCrops.child(String.valueOf(position+2)).child("min_rainfall").setValue(model.getMin_rainfall());
                        usersExistingCrops.child(String.valueOf(position+2)).child("max_rainfall").setValue(model.getMax_rainfall());
                        usersExistingCrops.child(String.valueOf(position+2)).child("min_germ_temp").setValue(model.getMin_germ_temp());
                        usersExistingCrops.child(String.valueOf(position+2)).child("min_rip_temp").setValue(model.getMin_rip_temp());
                        usersExistingCrops.child(String.valueOf(position+2)).child("max_rip_temp").setValue(model.getMax_rip_temp());
                        usersExistingCrops.child(String.valueOf(position+2)).child("min_ph").setValue(model.getMin_ph());
                        usersExistingCrops.child(String.valueOf(position+2)).child("max_ph").setValue(model.getMax_ph());

                        viewHolder.myView.setVisibility(View.GONE);

                    }
                });

            }
        };

        recyclerViewJune.setAdapter(firebaseRecyclerAdapterJune);

    }

    public static class FieldDetailsHolder extends RecyclerView.ViewHolder
    {
        TextView crop_name_field_new, minimum_rainfall, maximum_rainfall, germination_min_temp, germination_max_temp;
        TextView ripening_min_temp, ripening_max_temp, ph_min_value, ph_max_value;

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

            add_button_field_new = (Button) itemView.findViewById(R.id.add_button_field_new);

            myView = itemView;
        }
    }
}
