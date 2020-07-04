package edu.cs.uga.trophyteam;

import android.util.Log;
import java.util.List;

/**
 * Class for Validating a variety of inputs (Email format, password format, etc.)*/
public abstract class Validator {

    private static String TAG = "Log_Validator";

    /**
     * Method that determines if an email meets valid criteria*/
    public static boolean validateEmail(String email){
        if (email.length() == 0) return false;
        else if (email.contains("@") == false) return false;

        return true;
    }
    /**
     * Method that determines if a password meets valid criteria*/
    public static boolean validatePassword(String password){
        if (password.length() < 8 || password.length() > 16) return false;
        return true;
    }

    /**
     * Method that determines if two passwords match*/
    public static boolean validateMatchingPasswords(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    /**
     * Method that determines if a userID meets valid criteria*/
    public static boolean validateUserID(String userID){
        if (userID.length() < 5 || userID.length() > 15) return false;
        return true;
    }

    /**
     * Method that determines if a user does not already exist in the Firebase */
    public static boolean validateNewUser(final User user){
        List<User> userList = FirebaseDataLists.getUserListInstance();
        for (User currentUser : userList){
            if (validateMatchingUsers(currentUser, user)) return false;
        }
        return true;
    }

    /**
     * Method that determines if a User exists and returns User if reference exists*/
    public static User validateExistingUser(User user){
        List<User> userList = FirebaseDataLists.getUserListInstance();
        for (User currentUser : userList){
            if (validateMatchingUsers(currentUser, user)) return currentUser;
        }
        return null;
    }

    /**
     * Method that determines if two Users are the same*/
    public static boolean validateMatchingUsers(User user_A, User user_B){
        if (validateMatchingUsersByUserID(user_A, user_B)) return true;
        else if (validateMatchingUsersByEmail(user_A, user_B)) return true;

        Log.d(TAG, "validateMatchingUsers: User not found");
        return false;
    }

    /**
     * Method that determines if two Users are the same by comparing User ID's */
    private static boolean validateMatchingUsersByUserID(User user_A, User user_B){
        try {
            if (user_A.getUserID().equals(user_B.getUserID())) return true;
        } catch (NullPointerException e){
            Log.d(TAG, "NullPointerException: validateMatchingUserByUserID");
            return false;
        }
        return false;
    }

    /**
     * Method that determines if to Users are the same by comparing Emails*/
    private static boolean validateMatchingUsersByEmail(User user_A, User user_B){
        try {
            if (user_A.getEmail().equals(user_B.getEmail())) return true;
        }
        catch (NullPointerException e){
            Log.d(TAG, "NullPointerException: validateMatchingUserByEmail");
            return false;
        }
        return false;
    }


}
