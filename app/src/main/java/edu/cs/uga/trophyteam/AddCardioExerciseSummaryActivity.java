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

import org.w3c.dom.Text;

public class AddCardioExerciseSummaryActivity extends AppCompatActivity {

    private static String TAG = "AddCarExerSum_Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cardio_exercise_summary);

        //OnCreate of this Activity, display the relevant attributes of the CardioExercise object to the user
        displaySummary();

        /* - - - Buttons - - - */
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

        //Update the current TextView data for the Exercise Name
        TextView exerciseName = findViewById(R.id.summary_textview_exercise_name_value);
        exerciseName.setText(cardioExercise.getExerciseName());

        //Change Visibility of relevant Rows
        //If there is a Nickname, display it
        if (cardioExercise.getExerciseNickname().length() > 0){
            //Set title visibility to visible
            TextView nickNameTextView = findViewById(R.id.summary_textview_exercise_nickname);
            nickNameTextView.setVisibility(View.VISIBLE);
            //Set value visibility
            TextView nicknameTextViewValue = findViewById(R.id.summary_textview_exercise_nickname_value);
            nicknameTextViewValue.setText(cardioExercise.getExerciseNickname());
            nicknameTextViewValue.setVisibility(View.VISIBLE);
        }
        //If there is a distance, display distance
        if ((!cardioExercise.getMeasurementSystem().equals("null")) && cardioExercise.getDistance() != 0.0){
            //Set title visibility to visible
            TextView exerciseDistanceAndUnitTextView = findViewById(R.id.summary_textview_exercise_distance_and_unit);
            exerciseDistanceAndUnitTextView.setVisibility(View.VISIBLE);
            //Set value visibility
            TextView exerciseDistanceAndUnitValue = findViewById(R.id.summary_textview_exercise_distance_and_unit_value);
            exerciseDistanceAndUnitValue.setText(cardioExercise.getDistance() + " " + cardioExercise.getMeasurementSystem());
            exerciseDistanceAndUnitValue.setVisibility(View.VISIBLE);
        }
        //If there is a time, display time
        if (cardioExercise.getDurationHours() != 0 || cardioExercise.getDurationMinutes() != 0 || cardioExercise.getDurationSeconds() != 0){
            //Set title visibility to visible
            TextView durationTextView = findViewById(R.id.summary_textview_time);
            durationTextView.setVisibility(View.VISIBLE);
            //Set value visibility
            TextView exerciseTime = findViewById(R.id.summary_textview_time_value);
            exerciseTime.setText(cardioExercise.timeToString());
            exerciseTime.setVisibility(View.VISIBLE);
        }
    }
}
