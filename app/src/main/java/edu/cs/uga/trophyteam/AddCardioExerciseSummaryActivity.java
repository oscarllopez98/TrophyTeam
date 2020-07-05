package edu.cs.uga.trophyteam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCardioExerciseSummaryActivity extends AppCompatActivity {

    private static String TAG = "AddCarExerSum_Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cardio_exercise_summary);

        //OnCreate of this Activity, display the relevant attributes of the CardioExercise object to the user
        displaySummary();

        /*
        Buttons
         */
        //User selects to cancel adding an exercise (Sends user back to beginning of AddCardioExerciseActivity
        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCardioExerciseSummaryActivity.this, AddCardioExerciseActivity.class));
            }
        });


        //User selects to submit the created exercise
        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Access the Firebase instance
                    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

                    //Get reference for the current user and push to give it a unique key
                    String user = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                    DatabaseReference mReference = mDatabase.getReference("user_exercises/" + user + "/cardio_exercises/single_exercises").push();

                    //Add CardioExercise object to the Firebase w/ unique ID
                    mReference.setValue(getIntent().getParcelableExtra("BundleCardioExercise"));
                    mReference.child("exerciseID").setValue(mReference.getKey());

                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    //Confirmation Toast
                    Toast.makeText(AddCardioExerciseSummaryActivity.this, "New Exercise Added!",
                            Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    Log.d(TAG, "Error: Could not submit new exercise!");
                    //Failure Toast
                    Toast.makeText(AddCardioExerciseSummaryActivity.this, "Error: Could not submit exercise!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /***
     * Method that retrieves the CardioExercise object data from the Intent and displays it
     * onto the activity
     */
    @SuppressLint("SetTextI18n")
    public void displaySummary(){

        //Get intent contents
        CardioExercise cardioExercise = getIntent().getParcelableExtra("BundleCardioExercise");
        Log.d(TAG, CardioExercise.toTagString(cardioExercise));

        //TextViews used for displaying the values from the CardioExercise
        TextView exerciseName = findViewById(R.id.summary_textview_exercise_name_value);
        TextView exerciseDistanceAndUnit = findViewById(R.id.summary_textview_exercise_distance_and_unit_value);
        TextView exerciseTime = findViewById(R.id.summary_textview_time_value);

        //Update the current TextView data
        exerciseName.setText(cardioExercise.getExerciseName());
        exerciseDistanceAndUnit.setText(cardioExercise.getDistance() + " " + cardioExercise.getMeasurementSystem());
        exerciseTime.setText(cardioExercise.timeToString());

        //If there is a nickname set for the exercise display that
        if (cardioExercise.getExerciseNickname().length() > 3){

            //Update value of nicknameValue TextView
            TextView nicknameTextViewValue = findViewById(R.id.summary_textview_exercise_nickname_value);
            nicknameTextViewValue.setText(cardioExercise.getExerciseNickname());
        }
    }
}
