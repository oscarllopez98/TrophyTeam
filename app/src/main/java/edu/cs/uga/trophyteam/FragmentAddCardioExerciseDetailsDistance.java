package edu.cs.uga.trophyteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAddCardioExerciseDetailsDistance extends Fragment {

    //NumberPicker values
    private String[] fractionArray = new String[]{"0","1/4","1/2","3/4"};
    private String[] unitArray = new String[]{"-- --","Yards","Miles","Meters","Kilometers"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_cardio_exercise_details_distance, container, false);
        //Set the Distance NumberPickers
        setNumberPickers(view);
        return view;
    }


    /***
     * Method for setting NumberPicker values
     */
    private void setNumberPickers(View view){
        /********** NumberPickers *********/
        NumberPicker numberPickerWhole = view.findViewById(R.id.number_picker_whole);
        NumberPicker numberPickerFraction = view.findViewById(R.id.number_picker_fraction);
        NumberPicker numberPickerUnit = view.findViewById(R.id.number_picker_unit);

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
    }
}
