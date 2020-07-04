package edu.cs.uga.trophyteam;

public class StrengthExercise{

    //Instance variables
    private String exerciseID;
    private String exerciseName;

    private String measurementSystem;

    /*Default Constructor*/
    public StrengthExercise(){
        this.exerciseID = null;
    }

    /**
     * Overloaded constructor */
    public StrengthExercise(String e_ID, String e_Name, String mS){
        this.exerciseID = e_ID;
        this.exerciseName = e_Name;
        this.measurementSystem = mS;
    }


    /* Getters */
    public String getExerciseID() {
        return this.exerciseID;
    }

    public String getExerciseName() {
        return this.exerciseName;
    }

    public String getMeasurementSystem() {
        return measurementSystem;
    }

    /* Setters */

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setMeasurementSystem(String measurementSystem) {
        this.measurementSystem = measurementSystem;
    }
}
