package edu.cs.uga.trophyteam;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class CardioExercise implements Parcelable {

    //Instance variables
    private String exerciseID;
    private String exerciseName;
    private String exerciseNickname;
    private int durationHours;
    private int durationMinutes;
    private int durationSeconds;
    private double distance;
    private String measurementSystem;

    /*Default Constructor*/
    public CardioExercise(){
        this.exerciseID = null;
        this.exerciseName = "";
        this.exerciseNickname = "";
        this.distance = 0.0;
        this.durationHours = 0;
        this.durationMinutes = 0;
        this.durationSeconds = 0;
    }

    /**
     * Overloaded constructor w/o nickname */
    public CardioExercise(String e_ID, String e_Name, double d, int dH, int dM, int dS, String mS){
        this.exerciseID = e_ID;
        this.exerciseName = e_Name;
        this.exerciseNickname = "";
        this.distance = d;
        this.durationHours = dH;
        this.durationMinutes = dM;
        this.durationSeconds = dS;
        this.measurementSystem = mS;
    }

    /**
     * Overloaded constructor w/ nickname*/
    public CardioExercise(String e_ID, String e_Name, String nickname, double d, int dH, int dM, int dS, String mS){
        this.exerciseID = e_ID;
        this.exerciseName = e_Name;
        this.exerciseNickname = nickname;
        this.distance = d;
        this.durationHours = dH;
        this.durationMinutes = dM;
        this.durationSeconds = dS;
        this.measurementSystem = mS;
    }

    /***
     * Method for assisting the Parceling of a CardioExercise object so it can be placed into an Intent
     * @param in
     */
    protected CardioExercise(Parcel in) {
        exerciseID = in.readString();
        exerciseName = in.readString();
        exerciseNickname = in.readString();
        durationHours = in.readInt();
        durationMinutes = in.readInt();
        durationSeconds = in.readInt();
        distance = in.readDouble();
        measurementSystem = in.readString();
    }

    public static final Creator<CardioExercise> CREATOR = new Creator<CardioExercise>() {
        @Override
        public CardioExercise createFromParcel(Parcel in) {
            return new CardioExercise(in);
        }

        @Override
        public CardioExercise[] newArray(int size) {
            return new CardioExercise[size];
        }
    };

    /* Getters */
    public String getExerciseID() {
        return this.exerciseID;
    }

    public String getExerciseName() {
        return this.exerciseName;
    }

    public String getExerciseNickname() {
        return exerciseNickname;
    }

    public double getDistance() {
        return distance;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getDurationSeconds() {
        return durationSeconds;
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

    public void setExerciseNickname(String exerciseNickname) {
        this.exerciseNickname = exerciseNickname;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public void setMeasurementSystem(String measurementSystem) {
        this.measurementSystem = measurementSystem;
    }

    /* Converters */

    /* Printers */

    /***
     * Method that retrieves all members from a CardioExercise object and creates a presentable String
     * @param cardioExercise
     * @return tagString The formatted String representation of a single CardioExercise object
     */
    public static String toTagString(CardioExercise cardioExercise){
        String tagString = "\n" + "ExerciseID: " + cardioExercise.getExerciseID() + "\n" +
                "Exercise Name: " + cardioExercise.getExerciseName() + "\n" +
                "Exercise Nickname: " + cardioExercise.getExerciseNickname() + "\n" +
                "Distance: " + cardioExercise.getDistance() + "\n" +
                "Hours: " + cardioExercise.getDurationHours() + "\n" +
                "Minutes: " + cardioExercise.getDurationMinutes() + "\n" +
                "Seconds: " + cardioExercise.getDurationSeconds() + "\n" +
                "Unit: " + cardioExercise.measurementSystem + "\n";

        return tagString;
    }

    /***
     *
     * @return The string representation of time in HH:MM:SS format
     */
    @SuppressLint("DefaultLocale")
    public String timeToString(){
        return String.format("%02d:%02d:%02d",
                getDurationHours(),getDurationMinutes(),getDurationSeconds());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(exerciseID);
        dest.writeString(exerciseName);
        dest.writeString(exerciseNickname);
        dest.writeInt(durationHours);
        dest.writeInt(durationMinutes);
        dest.writeInt(durationSeconds);
        dest.writeDouble(distance);
        dest.writeString(measurementSystem);
    }
}
