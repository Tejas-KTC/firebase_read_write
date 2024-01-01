package com.example.firebase_read_write;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText luser,lpass;
    Button btn;

    int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        luser = findViewById(R.id.ed_username);
        lpass = findViewById(R.id.ed_password);
        btn = findViewById(R.id.login_btn);
        
        String s_user = luser.getText().toString();
        String s_pass = lpass.getText().toString();
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkuser();
            }

            private void checkuser() {
                String username = luser.getText().toString().trim();
                String userpass = lpass.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkuserdetails = reference.orderByChild("username").equalTo(username);

                checkuserdetails.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String passwordfromdb = snapshot.child(username).child("password").getValue(String.class);

                            if(passwordfromdb.equals(userpass)){
                                Toast.makeText(MainActivity.this, "Sign in successfully!!", Toast.LENGTH_SHORT).show();
                            }
                            else if(!passwordfromdb.equals(userpass)){
                                Toast.makeText(MainActivity.this, "User does not exist!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}