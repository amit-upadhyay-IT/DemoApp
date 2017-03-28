package krishikalyan.aupadhyay.myapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import krishikalyan.aupadhyay.myapp.R;

public class HomeActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
