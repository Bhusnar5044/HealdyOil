package com.example.healdyoilnp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText contact;
    EditText password;
    EditText address;
    Button loginb,registerb;

    private String nametxt,emailtxt,contacttxt,passtxt,addresstxt,id;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar=new ProgressBar(this);

        try {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }



        name=(EditText) findViewById(R.id.txtName);
        email=(EditText) findViewById(R.id.txtEmail);
        contact=(EditText) findViewById(R.id.txtcontact);
        password=(EditText) findViewById(R.id.txtpassword);
        address=(EditText) findViewById(R.id.txtaddress);
        loginb=(Button)findViewById(R.id.btnLogin);
        registerb=(Button)findViewById(R.id.btnLogin2);

    }

    public void submit(View view)
    {
        //Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        validate();
    }

    private void validate()
    {
        nametxt=name.getText().toString();
        emailtxt=email.getText().toString();
        contacttxt=contact.getText().toString();
        passtxt=password.getText().toString();
        addresstxt=address.getText().toString();


        if (nametxt.isEmpty()  || emailtxt.isEmpty() || passtxt.isEmpty() || contacttxt.isEmpty()||addresstxt.isEmpty())
        {
            Toast.makeText(this, "Please enter all thr details", Toast.LENGTH_SHORT).show();

        }
        else
        {

            emailtxt=email.getText().toString().trim();
            passtxt=password.getText().toString().trim();

            firebaseAuth.createUserWithEmailAndPassword(emailtxt,passtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        id=firebaseAuth.getUid();
                        senduserData();

                        Toast.makeText(SignUpActivity.this, "Registration Succssesfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                    }
                    else
                    {
                        FirebaseAuthException e=(FirebaseAuthException) task.getException();
                        Toast.makeText(SignUpActivity.this, "Registration Failed"+e.toString(), Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }

    private void senduserData()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myref=firebaseDatabase.getReference("Users");
        Userprofile userprofile=new Userprofile(nametxt,emailtxt,contacttxt,passtxt,addresstxt);
        myref.child(id).setValue(userprofile);
    }

    public void login1(View view){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

}