package edu.cs.uga.trophyteam;

import android.content.Intent;
import android.os.Bundle;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exercise_cardio_running, container, false);



        /*Buttons*/

        //Advances user to Distance and Time Page
        Button nextButton = view.findViewById(R.id.cardio_button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if the exercise type is still entered (From parent activity of this fragment)
                Spinner cardioExerciseType = getActivity().findViewById(R.id.spinner_options_cardio_type);
                if (cardioExerciseType.getSelectedItemPosition() == 0){
                    //If no exercise type selected, display toast
                    Toast.makeText(getActivity(), "Please Select an Exercise Type!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Exercises Spinner
                Spinner cardioExercisesSpinner = view.findViewById(R.id.cardio_spinner_exercise);

                //Get chosen cardio exercise and check if an exercise has not been selected
                String exerciseString = cardioExercisesSpinner.getSelectedItem().toString();
                if (cardioExercisesSpinner.getSelectedItemPosition() == 0){
                    //If no exercise selected, display Toast
                    Toast.makeText(getActivity(), "Please Select an Exercise!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Get nickname String from TextView
                String nicknameString = ((EditText) view.findViewById(R.id.cardio_edittext_exercise_nickname))
                        .getText().toString();

                //Instantiate intent and put exerciseString and nicknameString inside its extras
                Intent intent = new Intent(getActivity(), AddCardioExerciseDetailsActivity.class);
                intent.putExtra("ExerciseBundle",exerciseString);
                intent.putExtra("NicknameBundle",nicknameString);
                startActivity(intent);
            }
        });

        return view;
    }
}
