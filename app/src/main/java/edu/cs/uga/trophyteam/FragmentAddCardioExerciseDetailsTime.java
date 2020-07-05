package edu.cs.uga.trophyteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAddCardioExerciseDetailsTime extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_cardio_exercise_details_time, container, false);
        //Set the Time NumberPickers
        setNumberPickers(view);
        return view;
    }

    /***
     * Method for setting NumberPicker values
     */
    private void setNumberPickers(View view){
        /********** NumberPickers *********/
        NumberPicker numberPickerHours = view.findViewById(R.id.number_picker_hours);
        NumberPicker numberPickerMinutes = view.findViewById(R.id.number_picker_minutes);
        NumberPicker numberPickerSeconds = view.findViewById(R.id.number_picker_seconds);

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
