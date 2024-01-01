package com.example.firebase_read_write;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    EditText ed_user,ed_pass;
    Button btn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ed_user = findViewById(R.id.sign_up_user);
        ed_pass = findViewById(R.id.sign_up_password);
        btn = findViewById(R.id.sign_up_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_user = ed_user.getText().toString();
                String s_pass = ed_pass.getText().toString();
                if(s_user.isEmpty() || s_pass.isEmpty()){
                    Toast.makeText(MainActivity2.this, "Oops!!Look like some fields are empty!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    reference = firebaseDatabase.getReference("Users");
                    Helperclass helperclass = new Helperclass(s_user,s_pass);
                    reference.child(s_user).setValue(helperclass);
                    Toast.makeText(MainActivity2.this, "Sign up successfully!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}