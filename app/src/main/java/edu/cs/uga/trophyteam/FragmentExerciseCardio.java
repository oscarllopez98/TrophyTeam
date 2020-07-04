package edu.cs.uga.trophyteam;

import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentExerciseCardio extends Fragment {

    private final String TAG = "Log_FragExerCar";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_cardio, container, false);

        /*Spinner Interactions*/

        //User is selecting a Cardio Type (Running, Swimming, etc.)
        Spinner cardioTypeSpinner = view.findViewById(R.id.spinner_options_cardio_type);
        cardioTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCardioType = parent.getItemAtPosition(position).toString();
                //Detect which Cardio Type was selected
                switch (selectedCardioType){
                    case "Running":
                        //If running was selected, display running options fragment
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_exercise_cardio_type, new FragmentExerciseCardioRunning());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case "Swimming":
                        //If swimming was selected, display swimming options
                        break;
                    default:
                        Log.d(TAG, "Error: Nothing found for onItemClick (Spinner cardioTypeSpinner)");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
