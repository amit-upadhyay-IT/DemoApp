package krishikalyan.aupadhyay.myapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import krishikalyan.aupadhyay.myapp.R;

public class MachinelearningGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinelearning_graph);

        Toolbar toolbar = (Toolbar) findViewById(R.id.graph_toolbar_ml);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Prediction Graphs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
