package krishikalyan.aupadhyay.myapp.farmersfield;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import krishikalyan.aupadhyay.myapp.R;
import krishikalyan.aupadhyay.myapp.adapters.ViewPagerAdapter;
import krishikalyan.aupadhyay.myapp.farmersfield.fragments.ExistingCropFragment;
import krishikalyan.aupadhyay.myapp.farmersfield.fragments.NewCropFragment;

public class FarmersFieldHomeActivity extends AppCompatActivity {


    private void initViewPagers()
    {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_farmer_field);

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("NEW CROPS", "EXISTING CROPS");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new NewCropFragment(), "New Crops");
        viewPagerAdapter.addFragment(new ExistingCropFragment(), "Existing Crops");

        viewPager.setAdapter(viewPagerAdapter);

        navigationTabStrip.setViewPager(viewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers_field_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initViewPagers();
    }


}
