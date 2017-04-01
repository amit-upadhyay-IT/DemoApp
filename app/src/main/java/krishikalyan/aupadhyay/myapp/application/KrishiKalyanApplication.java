package krishikalyan.aupadhyay.myapp.application;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by aupadhyay on 4/1/17.
 */

public class KrishiKalyanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
