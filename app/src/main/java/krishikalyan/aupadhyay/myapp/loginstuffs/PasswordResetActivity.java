package krishikalyan.aupadhyay.myapp.loginstuffs;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import krishikalyan.aupadhyay.myapp.R;

public class PasswordResetActivity extends AppCompatActivity {

    @BindView(R.id.user_email_password_reset)
    EditText userEmail;
    @BindView(R.id.clear_text_password_reset)
    Button clearEmailTextView;
    @BindView(R.id.send_email_password_reset)
    Button sendEmailButton;
    @BindView(R.id.password_reset_form)
    ScrollView passwordResetForm;
    @BindView(R.id.reset_email_progress)
    ProgressBar resetEmailProgress;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_password_reset);
        Toolbar toolbar = (Toolbar) findViewById(R.id.trans_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(PasswordResetActivity.this, LoginActivity.class));
                finish();
            }
        });

        ButterKnife.bind(this);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed com
                    finish();
                } else {
                    // User is signed out

                }
                // ...
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        userEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptSendEmail();
                    return true;
                }
                return false;
            }
        });

        ThreeBounce threeBounce = new ThreeBounce();
        threeBounce.setColor(getResources().getColor(R.color.colorPrimary));
        resetEmailProgress.setIndeterminateDrawable(threeBounce);

    }

    @OnClick(R.id.send_email_password_reset)
    void sentEmailPasswordReset()
    {
        attemptSendEmail();
    }

    @OnClick(R.id.clear_text_password_reset)
    void clearTextPasswordReset()
    {
        String userEnteredEmail = userEmail.getText().toString().trim();
        if (userEnteredEmail != null)
        {
            userEmail.setText("");
            userEmail.setFocusable(true);
        }
    }

    private void attemptSendEmail() {

        // Reset errors.
        userEmail.setError(null);

        // Store values at the time of the login attempt.
        String useremail = userEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid useremail.
        if (TextUtils.isEmpty(useremail)) {
            userEmail.setError(getString(R.string.error_field_required));
            focusView = userEmail;
            cancel = true;
        } else if(!isEmailValid(useremail)){
            userEmail.setError(getString(R.string.error_invalid_email));
            focusView = userEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            //mAuthTask = new SignTask(username, password, email);
            //mAuthTask.execute((Void) null);
            actionSendEmail(useremail);
        }
    }
    private boolean isEmailValid(String email){
        return isEmailAddress(email);
    }

    private boolean isEmailAddress(String email){
        return email.contains("@");
    }

    private void actionSendEmail(final String emailAddress)
    {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Email Sent
                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PasswordResetActivity.this);
                            dialogBuilder.setTitle("Password reset email sent successfully");
                            dialogBuilder.setMessage("A message is sent to "+emailAddress+" containing a link to reset your password. For security reasons the link can only be used for one hour");
                            dialogBuilder.setCancelable(false);
                            dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    showProgress(false);
                                    //finish the activity
                                    finish();
                                }
                            });
                            dialogBuilder.show();
                        }
                        else
                        {
                            showProgress(false);
                            // Email not sent
                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PasswordResetActivity.this);
                            dialogBuilder.setTitle("Email can't be sent");
                            dialogBuilder.setMessage("A message can't be sent to "+emailAddress+" because "+task.getException().getMessage());
                            dialogBuilder.setCancelable(false);
                            dialogBuilder.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    //restart this activity
                                    overridePendingTransition(0, 0);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(getIntent());
                                }
                            });
                            dialogBuilder.setNegativeButton("GO BACK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    // finish the activity
                                    finish();

                                }
                            });
                            dialogBuilder.show();
                        }
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            passwordResetForm.setVisibility(show ? View.GONE : View.VISIBLE);
            resetEmailProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        } else {
            resetEmailProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            passwordResetForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
