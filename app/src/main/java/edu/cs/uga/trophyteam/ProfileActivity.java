package edu.cs.uga.trophyteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Retrieve Primary Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        //Set the user profile details values
        setProfileDetails();
    }

    /***
     * Method that inflates the Primary Menu to the Toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_primary, menu);
        return true;
    }

    /***
     * Method that allows the user to navigate between pages via the Toolbar
     *
     * @param item The MenuItem selected by the user
     * @return true If a valid options was selected
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_account:
                //Send user to profile page
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.action_add_exercise:
                //Send user to add exercise page
                startActivity(new Intent(getApplicationContext(), AddCardioExerciseActivity.class));
                break;
            case R.id.action_home:
                //Send user to home page
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                break;
            case R.id.action_logout:
                //Sign current user out and return to login page
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setProfileDetails(){
        //Get the current user and their display name(username)
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userName = currentUser.getDisplayName();

        //Set username value in TextView
        ((TextView)findViewById(R.id.profile_username)).setText(userName);

        //TODO: Set the values for birthdate, date joined, and name
    }
}
