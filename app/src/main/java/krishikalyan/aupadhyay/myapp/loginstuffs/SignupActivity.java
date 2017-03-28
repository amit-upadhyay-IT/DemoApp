package krishikalyan.aupadhyay.myapp.loginstuffs;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.LinearLayout;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import krishikalyan.aupadhyay.myapp.R;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.username_edit)
    EditText usernameEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.sign_commit_button)
    Button signCommitButton;
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.login_form)
    ScrollView loginForm;
    @BindView(R.id.email_edit)
    EditText emailEdit;
    @BindView(R.id.password_confirm_edit)
    EditText passwordConfirmEdit;
    @BindView(R.id.username_signup_form)
    LinearLayout usernameSignupForm;
    @BindView(R.id.go_to_signin)
    Button goToSigninActivityBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed com
                    // NOTE: this Activity should get onpen only when the user is not signed com, otherwise
                    // the user will receive another verification email.
                    setUserName();
                    sendVerificationEmail();
                } else {
                    // User is signed out

                }
                // ...
            }
        };

        passwordEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptSign();
                    return true;
                }
                return false;
            }
        });

        signCommitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSign();
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

    @OnClick(R.id.go_to_signin)
    void goToSigninActivity()
    {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
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
        usernameEdit.setError(null);
        passwordEdit.setError(null);

        // Store values at the time of the login attempt.
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String confirmPassword = passwordConfirmEdit.getText().toString();
        String email = emailEdit.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            passwordEdit.setError(getString(R.string.error_invalid_password));
            focusView = passwordEdit;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            usernameEdit.setError(getString(R.string.error_field_required));
            focusView = usernameEdit;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            usernameEdit.setError(getString(R.string.error_invalid_username));
            focusView = usernameEdit;
            cancel = true;
        }

        passwordConfirmEdit.setError(null);
        emailEdit.setError(null);

        if(TextUtils.isEmpty(email)){
            emailEdit.setError(getString(R.string.error_field_required));
            focusView = emailEdit;
            cancel = true;
        } else if(!isEmailValid(email)){
            emailEdit.setError(getString(R.string.error_invalid_email));
            focusView = emailEdit;
            cancel = true;
        }

        if(!confirmPassword.equals(password)){
            passwordConfirmEdit.setError(getString(R.string.error_invalid_confirm_password));
            focusView = passwordConfirmEdit;
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
            actionSign(username, password, email);
        }
    }

    private boolean isUsernameValid(String username) {
        return username.length() > 2 ;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 2;
    }

    private boolean isEmailValid(String email){
        return isEmailAddress(email);
    }

    /**
     * Shows the progress UI and hides the signup form.
     */
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

    private boolean isEmailAddress(String email){
        return email.contains("@");
    }

    private void actionSign(String username, String password, String email){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign com fails, display a message to the user. If sign com succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed com user can be handled com the listener.
                        if (!task.isSuccessful()) {
                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                            dialogBuilder.setTitle("Hey buddy, Sorry!");
                            dialogBuilder.setMessage("Some problem occured while registering you.\nPlease try again"+task.getException());
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
                        else
                        {
                            // successfully account created
                            // now the AuthStateListener runs the onAuthStateChanged callback
                        }

                        // ...
                    }
                });
    }

    private void setUserName()
    {
        String userName = usernameEdit.getText().toString().trim();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                .setPhotoUri(Uri.parse("https://pbs.twimg.com/profile_images/549798510787567616/8qK4g1W4_400x400.jpeg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                        }
                        else
                        {
                            Toast.makeText(SignupActivity.this, "There was error while saving your name", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent

                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                            dialogBuilder.setTitle("Hey buddy, Congratulations!");
                            dialogBuilder.setMessage("You are just one step away from completing your registration." +
                                    " All you have to do now is go straight and verify your email.\nClick the unique link provided com your email message to finish verifying your email address.\n" +
                                    "And login again :)");
                            dialogBuilder.setCancelable(false);
                            dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // after email is sent just logout the user and finish this activity
                                    FirebaseAuth.getInstance().signOut();
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
                                }
                            });
                            dialogBuilder.show();
                        }
                        else
                        {
                            //TODO: this is not properly handled, because if email is not sent then user can't register again with same credentials.
                            // email not sent
                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                            dialogBuilder.setTitle("Hey buddy, Sorry!");
                            dialogBuilder.setMessage("Some problem occured while sending you an email.\nPlease try again"+task.getException());
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
                });
    }

    @OnClick(R.id.signin_with_social_media_on_signup)
    void goToSocialMediaLogin()
    {
        startActivity(new Intent(SignupActivity.this, SocialLoginActivity.class));
        finish();
    }
}

