package krishikalyan.aupadhyay.myapp.homefeatures;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import krishikalyan.aupadhyay.myapp.R;
import krishikalyan.aupadhyay.myapp.activities.MainActivity;

/**
 * Created by aupadhyay on 4/1/17.
 */

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.ViewHolder>{

    private Context context;
    private ArrayList<HomeCardDataSet> dataSetList;
    private int resource;

    public HomeCardAdapter(Context context, ArrayList<HomeCardDataSet> dataSetList, int resource) {
        this.context = context;
        this.dataSetList = dataSetList;
        this.resource = resource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HomeCardDataSet dataSet = dataSetList.get(position);

        holder.centerMessageTV.setText(dataSet.getTitleMessage());
        //holder.centerMessageTV.setBackgroundColor(context.getResources().getColor(dataSet.getBgColor()));
        holder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss = dataSet.getHintMessage();
                Toast.makeText(context, ss, Toast.LENGTH_SHORT).show();
            }
        });

        if (position == 1)
        {
            holder.myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, MainActivity.class));
                }
            });


        }


        holder.myView.setBackgroundColor(context.getResources().getColor(dataSet.getBgColor()));
    }

    @Override
    public int getItemCount() {
        return dataSetList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView centerMessageTV;
        ImageButton description;
        View myView;

        public ViewHolder(View itemView) {
            super(itemView);

            centerMessageTV = (TextView) itemView.findViewById(R.id.name);
            description = (ImageButton) itemView.findViewById(R.id.description);
            myView = itemView;
        }
    }

}
