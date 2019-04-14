package com.example.healdyoilnp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

   EditText email;
   EditText password;
   Button loginb,registerb;

   private ProgressBar progressBar;

   private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar=new ProgressBar(this);

        firebaseAuth=FirebaseAuth.getInstance();

        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        loginb=(Button)findViewById(R.id.login);
        registerb=(Button)findViewById(R.id.signup);

    }

    private int loginUser(){
        String emailtext=email.getText().toString().trim();
        String passwordtext=password.getText().toString().trim();

        if(TextUtils.isEmpty(emailtext)){
            Toast.makeText(this,"Plase Enter email",Toast.LENGTH_SHORT).show();
            return 0;
        }

        if(TextUtils.isEmpty(passwordtext)){
            Toast.makeText(this,"Plase Enter password",Toast.LENGTH_SHORT).show();
            return 0;
        }


     firebaseAuth.createUserWithEmailAndPassword(emailtext,passwordtext)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Plese Try Again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    return 1;

    }

    public void login(View view){

        if(loginUser()==1) {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    public void register(View view){

        Intent i=new Intent(this,SignUpActivity.class);
        startActivity(i);

    }


}