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
    Button loginb,registerb;

    private String nametxt,emailtxt,contacttxt,passtxt;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar=new ProgressBar(this);

        firebaseAuth=FirebaseAuth.getInstance();

        name=(EditText) findViewById(R.id.txtName);
        email=(EditText) findViewById(R.id.txtEmail);
        contact=(EditText) findViewById(R.id.txtcontact);
        password=(EditText) findViewById(R.id.txtpassword);
        loginb=(Button)findViewById(R.id.btnLogin);
        registerb=(Button)findViewById(R.id.btnLogin2);

    }

    public void register(View view)
    {

        if(validate())
        {
            firebaseAuth.createUserWithEmailAndPassword(emailtxt,passtxt)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                insertDataIntoDatabase();
                                Toast.makeText(getApplicationContext(), "Succesful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                insertDataIntoDatabase();
                            }
                            else {

                                FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                Toast.makeText(SignUpActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }
        else
        {
            Toast.makeText(this,"Enter the all Details",Toast.LENGTH_SHORT).show();
        }
    }

    private void insertDataIntoDatabase()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        Members members=new Members(nametxt,emailtxt,contacttxt,passtxt);
        databaseReference.setValue(members);
        Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();

    }
    public  boolean  validate()
    {
        boolean result=false;
        nametxt=name.getText().toString();
        emailtxt=email.getText().toString();
        contacttxt=contact.getText().toString();
        passtxt=password.getText().toString();

        if(nametxt.isEmpty()&&emailtxt.isEmpty()&&passtxt.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }
        else {
            result=true;
        }
        return result;
    }


}