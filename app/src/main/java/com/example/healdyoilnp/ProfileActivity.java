package com.example.healdyoilnp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

   TextView name;
    TextView email;
    TextView contact;

    TextView address;
    Userprofile userprofile;
   // private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private  DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       // progressBar=new ProgressBar(this);
        userprofile=new Userprofile();
        try {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        catch(Exception e1)
        {
            Toast.makeText(this,e1.toString(),Toast.LENGTH_LONG).show();
        }

        try {
            user = firebaseAuth.getCurrentUser();
        }
        catch(Exception e2)
        {
            Toast.makeText(this,e2.toString(),Toast.LENGTH_LONG).show();
        }

        try {
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        catch(Exception e3)
        {
            Toast.makeText(this,e3.toString(),Toast.LENGTH_LONG).show();
        }

        name=(TextView) findViewById(R.id.txtName);
        email=(TextView) findViewById(R.id.txtEmail);
        contact=(TextView) findViewById(R.id.txtcontact);
        address=(TextView) findViewById(R.id.address);

    try {
        databaseReference= firebaseDatabase.getReference("Users");
    }
    catch(Exception e3){
        Toast.makeText(getApplicationContext(),"xxxx"+e3.toString(),Toast.LENGTH_LONG).show();
        }

        Query query=databaseReference.orderByChild("mail").equalTo(user.getEmail());

    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot ds: dataSnapshot.getChildren()){
                String name1=""+ ds.child("name").getValue();
                String mail=""+ ds.child("mail").getValue();
                String conta=""+ ds.child("mob").getValue();
                String addr=""+ ds.child("addr").getValue();

                name.setText(name1);
                email.setText(mail);
                contact.setText(conta);
                address.setText(addr);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
      /*  databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userprofile= dataSnapshot.getValue(Userprofile.class);
                name.setText(userprofile.getName());
                email.setText(userprofile.getMail());
                contact.setText(userprofile.getMob());
                address.setText(userprofile.getAddr());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"xxxx",Toast.LENGTH_LONG).show();
            }
        });*/
       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Userprofile userprofile=dataSnapshot.getValue(Userprofile.class);

                name.setText(userprofile.getName());
                email.setText(userprofile.getMail());
                contact.setText(userprofile.getMob());
                address.setText(userprofile.getAddr());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ProfileActivity.this,databaseError.toString(),Toast.LENGTH_LONG);
            }
        });*/
    }
}
