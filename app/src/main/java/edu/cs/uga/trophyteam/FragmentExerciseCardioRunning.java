package edu.cs.uga.trophyteam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentExerciseCardioRunning extends Fragment {

    private static final String TAG = "FragExerCarRun_Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exercise_cardio_running, container, false);




        /* - - - Buttons - - - */

        //Advances user to Distance and Time Page
        Button nextButton = view.findViewById(R.id.cardio_button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //If the Spinner input is not valid, return
                    if (!(validSpinnerInput(view))) return;
                    //Else, store Spinner values and send user to next Activity
                    else {
                        //Get chosen cardio exercise
                        String exerciseString = ((Spinner) view.findViewById(R.id.cardio_spinner_exercise))
                                .getSelectedItem().toString();

                        //Get nickname String from TextView
                        String nicknameString = ((EditText) view.findViewById(R.id.cardio_edittext_exercise_nickname))
                                .getText().toString();
                        try {
                            //Instantiate intent and put exerciseString and nicknameString inside its extras
                            Intent intent = new Intent(getActivity(), AddCardioExerciseDetailsActivity.class);
                            intent.putExtra("ExerciseBundle", exerciseString);
                            intent.putExtra("NicknameBundle", nicknameString);
                            startActivity(intent);
                        } catch (Exception e){
                            Log.d(TAG, "Error: Could not send user to next activity!");
                            return;
                        }
                    }
            }
        });

        return view;
    }

    /***
     * Method that determines if the user has appropriately selected Spinner values and entered a
     * nickname value within this fragment and the parent activity
     * @param view The view that contains the Spinners used
     * @return true if Spinner and EditText input is valid, false otherwise or if an exception occurs
     */
    public boolean validSpinnerInput(View view){
        try {
            //Check if the exercise type is still entered (From parent activity of this fragment)
            Spinner cardioExerciseType = getActivity().findViewById(R.id.spinner_options_cardio_type);
            if (cardioExerciseType.getSelectedItemPosition() == 0) {
                //If no exercise type selected, display toast
                Toast.makeText(getActivity(), "Please Select an Exercise Type!", Toast.LENGTH_SHORT).show();
                return false;
            }

            //Exercises Spinner
            Spinner cardioExercisesSpinner = view.findViewById(R.id.cardio_spinner_exercise);
            if (cardioExercisesSpinner.getSelectedItemPosition() == 0) {
                //If no exercise selected, display Toast
                Toast.makeText(getActivity(), "Please Select an Exercise!", Toast.LENGTH_SHORT).show();
                return false;
            }

            //Check if there is a nickname entered for the exercise
            EditText cardioExerciseNickname = view.findViewById(R.id.cardio_edittext_exercise_nickname);
            if (cardioExerciseNickname.getText().toString().length() == 0){
                Toast.makeText(getActivity(), "Please Type an Exercise Nickname!", Toast.LENGTH_SHORT).show();
                return false;
            }

            //Return true if all values are found successfully
            return true;
        } catch (Exception e){
            Log.d(TAG, "Error: Could not validate Spinner or EditText input!");
            return false;
        }
    }
}
