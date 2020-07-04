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

public class AddCardioExerciseDetailsActivity extends AppCompatActivity {

    private static String TAG = "CarExerDet_Activity";

    private String[] fractionArray = new String[]{"0","1/4","1/2","3/4"};
    private String[] unitArray = new String[]{"-- --","Yards","Miles","Meters","Kilometers"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cardio_exercise_details);

        //Set NumberPicker info
        setNumberPickers();

        //TextView Buttons
        final TextView addDistanceTextView = findViewById(R.id.number_pickers_distance_displayer);
        addDistanceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDistanceTextView.setVisibility(View.GONE);
                findViewById(R.id.number_picker_whole).setVisibility(View.VISIBLE);
                findViewById(R.id.number_picker_fraction).setVisibility(View.VISIBLE);
                findViewById(R.id.number_picker_unit).setVisibility(View.VISIBLE);
            }
        });

        final TextView addTimeTextView = findViewById(R.id.number_pickers_time_displayer);
        addTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTimeTextView.setVisibility(View.GONE);
                findViewById(R.id.number_picker_hours).setVisibility(View.VISIBLE);
                findViewById(R.id.number_picker_minutes).setVisibility(View.VISIBLE);
                findViewById(R.id.number_picker_seconds).setVisibility(View.VISIBLE);
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

                Log.d(TAG,""+cardioExercise.getExerciseNickname().length());

                //Add the CardioExercise POJO to an Intent
                Intent intent = new Intent(AddCardioExerciseDetailsActivity.this, AddCardioExerciseSummaryActivity.class);
                intent.putExtra("BundleCardioExercise",cardioExercise);

                //Send user to Summary Activity
                startActivity(intent);
            }
        });
    }

    private CardioExercise createNewCardioExercise(){
        //Pickers
        NumberPicker numberPickerWhole = findViewById(R.id.number_picker_whole);
        NumberPicker numberPickerFraction = findViewById(R.id.number_picker_fraction);
        NumberPicker numberPickerUnit = findViewById(R.id.number_picker_unit);

        NumberPicker numberPickerHours = findViewById(R.id.number_picker_hours);
        NumberPicker numberPickerMinutes = findViewById(R.id.number_picker_minutes);
        NumberPicker numberPickerSeconds = findViewById(R.id.number_picker_seconds);

        //Distance values
        int wholeNumber = numberPickerWhole.getValue();
        int fractionNumber = numberPickerFraction.getValue();
        int unit = numberPickerUnit.getValue();

        //Time values
        int hoursNumber = numberPickerHours.getValue();
        int minutesNumber = numberPickerMinutes.getValue();
        int secondsNumber = numberPickerSeconds.getValue();

        //Convert values to a CardioExercise object
        CardioExercise newCardioExercise = convertToCardioExercise(wholeNumber, fractionNumber,
                unit, hoursNumber,minutesNumber,secondsNumber);

        return newCardioExercise;
    }

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

    private void setNumberPickers(){
        /********** NumberPickers *********/
        NumberPicker numberPickerWhole = findViewById(R.id.number_picker_whole);
        NumberPicker numberPickerFraction = findViewById(R.id.number_picker_fraction);
        NumberPicker numberPickerUnit = findViewById(R.id.number_picker_unit);

        numberPickerWhole.setMinValue(0);
        numberPickerWhole.setMaxValue(30);
        numberPickerWhole.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerFraction.setMinValue(0);
        numberPickerFraction.setMaxValue(fractionArray.length-1);
        numberPickerFraction.setDisplayedValues(fractionArray);
        numberPickerFraction.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerUnit.setMinValue(0);
        numberPickerUnit.setMaxValue(unitArray.length-1);
        numberPickerUnit.setDisplayedValues(unitArray);
        numberPickerUnit.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        NumberPicker numberPickerHours = findViewById(R.id.number_picker_hours);
        NumberPicker numberPickerMinutes = findViewById(R.id.number_picker_minutes);
        NumberPicker numberPickerSeconds = findViewById(R.id.number_picker_seconds);

        numberPickerHours.setMinValue(0);
        numberPickerHours.setMaxValue(23);
        numberPickerHours.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerMinutes.setMinValue(0);
        numberPickerMinutes.setMaxValue(59);
        numberPickerMinutes.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerSeconds.setMinValue(0);
        numberPickerSeconds.setMaxValue(59);
        numberPickerSeconds.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
}
