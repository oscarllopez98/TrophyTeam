package edu.cs.uga.trophyteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    //Instance variables
    private EditText userID_ET;
    private EditText password_ET;

    private FirebaseAuth mAuth;
    private User currentUser;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDataLists.getUserListInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*  */



        /*Button Interactions*/

        //User chooses to try to Login
        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get EditText Strings
                userID_ET = findViewById(R.id.input_userID);
                password_ET = findViewById(R.id.input_password);
                String userID = userID_ET.getText().toString();
                String password = password_ET.getText().toString();

                //Check that both have some input
                if (userID.length() == 0) return;
                else if (password.length() == 0) return;

                //Try to find user
                currentUser = Validator.validateExistingUser(new User(userID));
                if (currentUser != null) {
                    //Log in user
                    try {
                        mAuth = FirebaseAuth.getInstance();
                        mAuth.signInWithEmailAndPassword(currentUser.getEmail(), password)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            //On success, log in user
                                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                        } else {
                                            Toast.makeText(MainActivity.this,
                                                    "Login Unsuccessful. Please Check Credentials",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } catch (Exception e){
                        Toast.makeText(MainActivity.this,
                                "Login Unsuccessful. Check Credentials", Toast.LENGTH_SHORT).show();
                        Log.d("HELLO",Log.getStackTraceString(e.getCause()));
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Could not find user: "+userID, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //User chooses to go to Registration Page
        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }



}
