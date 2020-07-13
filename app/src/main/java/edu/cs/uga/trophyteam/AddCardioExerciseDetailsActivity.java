package edu.cs.uga.trophyteam;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class AddCardioExerciseDetailsActivity extends AppCompatActivity {

    private static String TAG = "CarExerDet_Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cardio_exercise_details);



        /* - - - TextView Buttons - - - */
        //User clicks to add distance
        final TextView addDistanceTextView = findViewById(R.id.number_pickers_distance_displayer);
        addDistanceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDistanceTextView.setVisibility(View.GONE);

                //Replace the Add Distance TextView with the Distance NumberPickers via Fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_add_exercise_details_distance, new FragmentAddCardioExerciseDetailsDistance());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //User clicks to add time
        final TextView addTimeTextView = findViewById(R.id.number_pickers_time_displayer);
        addTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTimeTextView.setVisibility(View.GONE);

                //Replace the Add Time TextView with the Time NumberPickers via Fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_add_exercise_details_time, new FragmentAddCardioExerciseDetailsTime());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //Buttons
        //User selects to go back to add exercise activity
        Button prevButton = findViewById(R.id.cardio_button_prev);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCardioExerciseDetailsActivity.this, AddCardioExerciseActivity.class));
            }
        });

        //User selects to go to summary page
        Button nextButton = findViewById(R.id.cardio_button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the values from each picker and create CardioExercise Object with the info
                CardioExercise cardioExercise = createNewCardioExercise();
                cardioExercise.setExerciseNickname(getIntent().getStringExtra("NicknameBundle"));

                //Add the CardioExercise POJO to an Intent
                Intent intent = new Intent(AddCardioExerciseDetailsActivity.this, AddCardioExerciseSummaryActivity.class);
                intent.putExtra("BundleCardioExercise",cardioExercise);

                //Send user to Summary Activity
                startActivity(intent);
            }
        });
    }

    /***
     * Method that instantiates a CardioExercise object with discrete distance and time values
     * @return cardioExercise The CardioExercise object created with discrete distance and time values
     */
    private CardioExercise createNewCardioExercise(){
        //Pickers
        NumberPicker numberPickerWhole = findViewById(R.id.number_picker_whole);
        NumberPicker numberPickerFraction = findViewById(R.id.number_picker_fraction);
        NumberPicker numberPickerUnit = findViewById(R.id.number_picker_unit);

        NumberPicker numberPickerHours = findViewById(R.id.number_picker_hours);
        NumberPicker numberPickerMinutes = findViewById(R.id.number_picker_minutes);
        NumberPicker numberPickerSeconds = findViewById(R.id.number_picker_seconds);

        //Distance values
        int wholeNumber = 0;
        int fractionNumber = 0;
        int unit = 0;
        try {
            wholeNumber = numberPickerWhole.getValue();
            fractionNumber = numberPickerFraction.getValue();
            unit = numberPickerUnit.getValue();
        } catch (NullPointerException e){
            Log.d(TAG, "NullPointerException caught for Distance Values");
            wholeNumber = 0;
            fractionNumber = 0;
            unit = 0;
        }

        //Time values
        int hoursNumber = 0;
        int minutesNumber = 0;
        int secondsNumber = 0;
        try {
            hoursNumber = numberPickerHours.getValue();
            minutesNumber = numberPickerMinutes.getValue();
            secondsNumber = numberPickerSeconds.getValue();
        } catch (NullPointerException e){
            Log.d(TAG, "NullPointerException caught for Time Values");
            hoursNumber = 0;
            minutesNumber = 0;
            secondsNumber = 0;
        }

        //Convert values to a CardioExercise object
        CardioExercise newCardioExercise = convertToCardioExercise(wholeNumber, fractionNumber,
                unit, hoursNumber,minutesNumber,secondsNumber);

        return newCardioExercise;
    }

    /***
     * Method that converts NumberPicker positions to discrete values for a CardioExercise object
     * @param wN Position in the Whole Number NumberPicker
     * @param fN Position in the Fraction Number NumberPicker
     * @param unit Position in the Unit NumberPicker
     * @param hN Position in the Hours Number NumberPicker
     * @param mN Position in the Minutes Number NumberPicker
     * @param sN Position in the Seconds Number NumberPicker
     * @return cardioExercise The CardioExercise object that is created based on the parameters
     */
    private CardioExercise convertToCardioExercise(int wN, int fN, int unit, int hN, int mN, int sN){
        //First, add the whole number (wN) to the total distance
        double totalDistance = wN;
        //Next, add the fraction number (fN) to the total distance
        switch (fN){
            //Case 1 - 1/4 fraction distance added
            case 1:
                totalDistance += .25;
                break;
            //Case 2 - 1/2 fraction distance added
            case 2:
                totalDistance += .5;
                break;
            //Case 3 - 3/4 Fraction distance added
            case 3:
                totalDistance += .75;
                break;
            //Default - No fraction distance was added
            default:
                break;
        }
        //Now, set the unit to a String variable, based off its positioning
        String unitString = "null";
        switch(unit){
            case 1:
                unitString = "Yards";
                break;
            case 2:
                unitString = "Miles";
                break;
            case 3:
                unitString = "Meters";
                break;
            case 4:
                unitString = "Kilometers";
                break;
            case 5:
                unitString = "Laps";
                break;
            default:
                //No unit was found?
                break;
        }

        //Get the exercise type chosen
        String exerciseName = getIntent().getStringExtra("ExerciseBundle");
        //Get the exercise nickname, if there is a value
        String exerciseNickname = getIntent().getStringExtra("NicknameBundle");

        //Instantiate CardioExerise object with appropriate attributes
        CardioExercise cardioExercise = new CardioExercise("TempID",exerciseName,totalDistance, hN, mN, sN, unitString);
        if (exerciseNickname.length() > 0){
            cardioExercise.setExerciseNickname(exerciseNickname);
        }
        return cardioExercise;
    }


}
