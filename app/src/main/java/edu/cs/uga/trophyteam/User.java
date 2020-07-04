package edu.cs.uga.trophyteam;

import java.time.LocalDate;

public class User {

    //Instance variables
    private String userID;
    private String email;
    private String name; //Not implemented yet
    private String displayName; //Not yet implemented
    private String dateJoined; //Not yet implemented
    private boolean adminStatus;
    private boolean suspendedStatus;
    //private List<WorkoutTeam> workoutTeams; //Not yet implemented
    private String userBio; //Not yet implemented

    //Default constructor
    public User(){
        userID = null;
        email = null;
        name = null;
        displayName = null;
        dateJoined = null;
        adminStatus = false;
        suspendedStatus = false;
        //workoutTeams = null;
        userBio = null;
    }

    //Create new User with userID
    public User(String userID){
        this.setUserID(userID);
        email = null;
        name = null;
        displayName = null;
        this.setDateJoined(LocalDate.now().toString());//Error displays here, but still runs on API 26+
        adminStatus = false;
        //workoutTeams = null
        suspendedStatus = false;
    }

    //Create new User with userID and email
    public User(String userID, String email){
        this.setUserID(userID);
        this.setEmail(email);
        name = null;
        displayName = null;
        this.setDateJoined(LocalDate.now().toString());//Error displays here, but still runs on API 26+
        adminStatus = false;
        //workoutTeams = null
        suspendedStatus = false;
    }

    /*Setters*/
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public void setSuspendedStatus(boolean suspendedStatus) {
        this.suspendedStatus = suspendedStatus;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    /*Getters*/

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public boolean getAdminStatus(){
        return adminStatus;
    }

    public boolean getSuspendedStatus(){
        return suspendedStatus;
    }

    public String getUserBio() {
        return userBio;
    }
}
