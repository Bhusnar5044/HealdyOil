package com.example.healdyoilnp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity  {

   EditText email;
   EditText password;
   Button loginb,registerb;
    private String emailtxt,passtxt;
    private ProgressDialog progressDialog;

   private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // progressDialog=new ProgressDialog(this);
        try {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        loginb=(Button)findViewById(R.id.login);
        registerb=(Button)findViewById(R.id.signup);

    }



    public void login(View view){
        emailtxt = email.getText().toString();
        passtxt = password.getText().toString();
        if (emailtxt.isEmpty() || passtxt.isEmpty()) {
            Toast.makeText(this, "Plaese fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            validate(email.getText().toString(), password.getText().toString());

        }

    }

    public void register(View view){

        Intent i=new Intent(this,SignUpActivity.class);
        startActivity(i);

    }

    private void validate(String usrname,String usrpass) {


      //  progressDialog.setMessage("Wait until it verified");
        //progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(usrname, usrpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                   // progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    // FirebaseUser user=firebaseAuth.getCurrentUser();
                   /* if (user!=null)
                    {
                        finish();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }*/

                    // startActivity(new Intent(MainActivity.this,HP.class));

                }
                else
                {
                    FirebaseAuthException e=(FirebaseAuthException) task.getException();
                   // progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Failed"+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}