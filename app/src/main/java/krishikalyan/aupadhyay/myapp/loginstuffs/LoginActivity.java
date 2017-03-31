package krishikalyan.aupadhyay.myapp.loginstuffs;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import krishikalyan.aupadhyay.myapp.R;
import krishikalyan.aupadhyay.myapp.activities.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username_signin)
    EditText usernameSignin;
    /*@BindView(R.id.password_signin)
    EditText passwordSignin;*/

    EditText passwordSignin;

    @BindView(R.id.sign_commit_button)
    Button signinBtn;
    @BindView(R.id.login_form)
    ScrollView loginForm;
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.forget_password_login_activity)
    TextView passwordResetEmailTextView;
    @BindView(R.id.signin_with_social_media)
    Button signinWithSocialMedia;
    @BindView(R.id.choose_signup_button)
    Button signUpButton;

    private boolean onceChecked = false;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        passwordSignin = (EditText) findViewById(R.id.password_signin_login);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed com
                    // now check if the profile is filled or not.
                    showProgress(true);
                    //setValuesInProfileSharedPref();

                    checkIfEmailVerified();

                } else {
                    // User is signed out
                }
                // ...
            }
        };

        passwordSignin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptSign();
                    return true;
                }
                return false;
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSign();
            }
        });

        passwordResetEmailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class));
                finish();
            }
        });

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

        ThreeBounce threeBounce = new ThreeBounce();
        threeBounce.setColor(getResources().getColor(R.color.colorPrimary));
        loginProgress.setIndeterminateDrawable(threeBounce);
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

    private void attemptSign() {

        // Reset errors.
        usernameSignin.setError(null);
        passwordSignin.setError(null);

        // Store values at the time of the login attempt.
        String username = usernameSignin.getText().toString();
        String password = passwordSignin.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            passwordSignin.setError(getString(R.string.error_invalid_password));
            focusView = passwordSignin;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            usernameSignin.setError(getString(R.string.error_field_required));
            focusView = usernameSignin;
            cancel = true;
        } else if (!isEmailValid(username)) {
            usernameSignin.setError(getString(R.string.error_invalid_email));
            focusView = usernameSignin;
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
            actionSign(username, password);
            //checkIfEmailVerified(username, password);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 2;
    }

    /*    private boolean isUsernameValid(String username) {
            return username.length() > 2;
        }*/
    private boolean isEmailValid(String email) {
        return isEmailAddress(email);
    }

    private boolean isEmailAddress(String email) {
        return email.contains("@");
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        } else {
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity where he can fill his profile details.
            Toast.makeText(LoginActivity.this, "Welcome "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
        else
        {
            // email is not verified, so just prompt the message to user and restart this activity.
            // NOTE: don't forget to logout the user.
            FirebaseAuth.getInstance().signOut();
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            dialogBuilder.setTitle("Email not verified");
            dialogBuilder.setMessage("You haven't verified your email. Try again after verifying your email");
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //restart this activity
                    overridePendingTransition(0, 0);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                }
            });
            dialogBuilder.show();
        }
    }

    private void actionSign(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign com fails, display a message to the user. If sign com succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed com user can be handled com the listener.
                        if (!task.isSuccessful()) {
                            //Log.w("TAG", "signInWithEmail:failed", task.getException());
                            String the_exception = task.getException().toString();
                            String exceptioni_message = "";
                            if (the_exception.contains("NetworkException"))
                            {
                                exceptioni_message = "You are offline\nTry:\n" +
                                        "\t1. Turning off airplance mode\n" +
                                        "\t2. Turning on mobile data or Wi-Fi";
                            }
                            else if (the_exception.contains("InvalidUserException"))
                            {
                                exceptioni_message = "You are not a registered user. Kindly register and try again.";
                            }
                            else if (the_exception.contains("InvalidCredentialsException"))
                            {
                                exceptioni_message = "The password you entered is incorrect. Kindly enter the correct password and try again";
                            }
                            else
                            {
                                exceptioni_message = the_exception.substring(25, the_exception.length()-1);
                            }
                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                            dialogBuilder.setTitle("Authentication Failed");
                            dialogBuilder.setMessage(exceptioni_message);
                            dialogBuilder.setCancelable(false);
                            dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //restart this activity
                                    overridePendingTransition(0, 0);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(getIntent());
                                }
                            });
                            dialogBuilder.show();
                        } else {
                            // if the user is logged com then check if the email is verified or not.
                            //checkIfEmailVerified();
                        }
                        // ...
                    }
                });
    }


    /*private void alreadyLoggedInDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        dialogBuilder.setTitle("You are already logged com");
        dialogBuilder.setMessage("Hey, you are successfully logged on. To log-off go to Settings");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Go back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showProgress(false);
                finish();
            }
        });
        dialogBuilder.setNegativeButton("Go To Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(LoginActivity.this, SettingsActivity.class));
                finish();
            }
        });
        dialogBuilder.show();
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        showProgress(false);
    }

    @OnClick(R.id.signin_with_social_media)
    void signInUsingSocialMedia()
    {
        startActivity(new Intent(LoginActivity.this, SocialLoginActivity.class));
        finish();
    }

    @OnClick(R.id.choose_signup_button)
    void openSignupActivity()
    {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }
}

