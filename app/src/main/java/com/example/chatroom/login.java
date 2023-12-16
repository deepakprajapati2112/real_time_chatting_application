package com.example.chatroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button button;
    EditText email,password;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();
        button=findViewById(R.id.logbutton);
        email=findViewById(R.id.editTexLogEmail);
        password=findViewById(R.id.editTextLogPassword);
        signin=findViewById(R.id.logsignup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, registration.class);
                startActivity(intent);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                if ((TextUtils.isEmpty(Email))){
//                    progressDialog.dismiss();
                    Toast.makeText(login.this, "Enter The Email", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(Password)){
//                    progressDialog.dismiss();
                    Toast.makeText(login.this, "Enter The Password", Toast.LENGTH_SHORT).show();
                }else if (!Email.matches(emailPattern)){
//                    progressDialog.dismiss();
                    email.setError("Give Proper Email Address");
                }else if (password.length()<6) {
//                    progressDialog.dismiss();
                    password.setError("More Then Six Characters");
                    Toast.makeText(login.this, "Password Needs To Be Longer Then Six Characters", Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                try {
                                    Intent intent = new Intent(login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                    Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });




    }
}