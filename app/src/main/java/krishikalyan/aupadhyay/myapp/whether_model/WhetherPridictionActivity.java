package krishikalyan.aupadhyay.myapp.whether_model;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import krishikalyan.aupadhyay.myapp.R;
import krishikalyan.aupadhyay.myapp.adapters.ViewPagerAdapter;
import krishikalyan.aupadhyay.myapp.whether_model.fragments.CloudcoverPredictionFragment;
import krishikalyan.aupadhyay.myapp.whether_model.fragments.EvatranspirationPredictionFragment;
import krishikalyan.aupadhyay.myapp.whether_model.fragments.GroundfrostPredictionFragment;
import krishikalyan.aupadhyay.myapp.whether_model.fragments.RainfallPredictionFragment;
import krishikalyan.aupadhyay.myapp.whether_model.fragments.TemperaturePredictionFragment;
import krishikalyan.aupadhyay.myapp.whether_model.fragments.WetdaysfrequencyPredictionFragment;

public class WhetherPridictionActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private void initTabLayouts()
    {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPagerAdapter.addFragment(new RainfallPredictionFragment(), "Rainfall");
        viewPagerAdapter.addFragment(new TemperaturePredictionFragment(), "Temperature");
        viewPagerAdapter.addFragment(new WetdaysfrequencyPredictionFragment(), "Wet Days Prediction");
        viewPagerAdapter.addFragment(new CloudcoverPredictionFragment(), "Cloud Cover");
        viewPagerAdapter.addFragment(new EvatranspirationPredictionFragment(), "Evatranspiration");
        viewPagerAdapter.addFragment(new GroundfrostPredictionFragment(), "Ground Frost");

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether_pridiction);

        // Load toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_prediction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Whether Prediction");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initTabLayouts();
    }
}
