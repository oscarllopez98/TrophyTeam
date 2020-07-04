package edu.cs.uga.trophyteam;

import android.util.Log;

public abstract class Tester {

    private static final String TAG = "Tester";

    public static void StartTestAll(){
        Log.d(TAG, "Start Test: All");
    }

    public static void StartTestExerciseCardio(){
        Log.d(TAG, "Start Test: Exercise Cardio");
        Log.d(TAG, "Testing: Creating Cardio Exercise");
        CardioExercise cardioExercise = new CardioExercise("Swim",
                "Swimming FUN!", 1.3, 0, 30, 0, "Metric");
        logExercise(cardioExercise);
        //Log.d(TAG, "Testing: ");
    }

    private static void logExercise(CardioExercise exercise){

        Log.d(TAG, "Log Exercise - Cardio: ");
        Log.d(TAG, "" + exercise.getExerciseID());
        Log.d(TAG, "" + exercise.getExerciseName());
        Log.d(TAG, "" + exercise.getDurationHours());
        Log.d(TAG, "" + exercise.getDurationMinutes());
        Log.d(TAG, "" + exercise.getDurationMinutes());
        Log.d(TAG, "" + exercise.getDurationSeconds());
        Log.d(TAG, "" + exercise.getDistance());

    }
}
