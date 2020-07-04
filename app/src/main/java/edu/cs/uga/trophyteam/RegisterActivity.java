package edu.cs.uga.trophyteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    //Instance variables
    private EditText inputEmail;
    private EditText inputUserID;
    private EditText inputPassword;
    private EditText inputConfirmPassword;

    private DatabaseReference mReference;
    private FirebaseAuth mAuth;
    private String TAG = "Log_RegAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*Button Interactions*/

        //User chooses to Submit their account
        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate credentials
                inputEmail = findViewById(R.id.input_email);
                final String inputEmailString = inputEmail.getText().toString();
                if (!(Validator.validateEmail(inputEmailString))) {
                    //Display error Toast
                    Toast.makeText(RegisterActivity.this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                inputUserID = findViewById(R.id.input_userID);
                final String inputUserIDString = inputUserID.getText().toString();
                if (!(Validator.validateUserID(inputUserIDString))) {
                    //Display error Toast
                    Toast.makeText(RegisterActivity.this, "Invalid UserID!", Toast.LENGTH_SHORT).show();
                    return;
                }

                inputPassword = findViewById(R.id.input_password);
                String inputPasswordString = inputPassword.getText().toString();
                if (!(Validator.validatePassword(inputPasswordString))){
                    //Display error Toast
                    Toast.makeText(RegisterActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                inputConfirmPassword = findViewById(R.id.input_confirm_password);
                if (!(Validator.validateMatchingPasswords(inputPassword.getText().toString(),
                        inputConfirmPassword.getText().toString()))){
                    //Display error Toast
                    Toast.makeText(RegisterActivity.this, "Passwords Do Not Match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //All credentials validated by custom validator, try to push new account
                //Create pojo User
                User user = new User(inputUserIDString);
                user.setEmail(inputEmailString);

                //Validate the user does not already exist
                if (Validator.validateNewUser(user)){

                    //If user does not exists, add to Firebase
                    mReference = FirebaseDatabase.getInstance().getReference("users").push();
                    mReference.setValue(user);

                    try {
                        mAuth = FirebaseAuth.getInstance();
                        mAuth.createUserWithEmailAndPassword(inputEmailString, inputPasswordString)
                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            //Update the created user's displayname with the userid they input
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                                                    .Builder().setDisplayName(inputUserIDString).build();
                                            user.updateProfile(profileUpdates);

                                            //If addition was successful, display success message and redirect to login page
                                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        }
                                        else {
                                            Toast.makeText(RegisterActivity.this,
                                                    "Could Not Authenticate User. " +
                                                            "Perhaps email already exists!",
                                                    Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });
                    } catch(Exception e){
                        Toast.makeText(RegisterActivity.this,
                                "Could not create User: "+inputUserIDString,
                                Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(RegisterActivity.this,
                            "Account Created for: "+inputUserIDString, Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(RegisterActivity.this,
                            "That UserID or Email is Already Taken!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        //User chooses to go to Login Page
        TextView toLoginPageButton = findViewById(R.id.link_login);
        toLoginPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }

}
