package edu.cs.uga.trophyteam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static String TAG = "Home_Activity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Retrieve Primary Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Display existing, non completed exercises
        //TODO: Make a way to know if an exercise has been completed or not

        setRecyclerView();


        //Start Tester for Exercise Classes
        Tester.StartTestExerciseCardio();


    }

    /***
     * Method that sets up the RecyclerView and populates it with data
     */
    private void setRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_exercises);

        //Use this setting to improve performance if you know that changes
        //in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get all workouts from Firebase
        try {
            //Get the current user
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

            //Get Firebase reference to data
            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
            //TODO: Change the location of where exercises are retrieved from when Strength are added
            DatabaseReference mReference = mDatabase.getReference("user_exercises/"
                    +currentUser.getDisplayName()
                    +"/cardio_exercises/single_exercises");

            //List for exercise objects
            final List<CardioExercise> cardioExerciseList = new ArrayList<>();

            //Retrieve data
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //For each exercise, create an object and add it to the list
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        cardioExerciseList.add(0,snapshot.getValue(CardioExercise.class));
                        Log.d(TAG, "Current CardioExercise Name: " +
                                snapshot.getValue(CardioExercise.class).getExerciseName());
                    }

                    //Uncomment this line for dividers between rows
                    //recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

                    // specify an adapter
                    mAdapter = new CardioExerciseAdapter(cardioExerciseList);
                    recyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //Nothing
                }
            });

        } catch(Exception e){
            Log.d(TAG, "Something went wrong with exercise retrieval from Firebase");
        }
    }


    /***
     * Method that inflates the Primary Menu to the Toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_primary, menu);
        return true;
    }

    /***
     * Method that allows the user to navigate between pages via the Toolbar
     *
     * @param item The MenuItem selected by the user
     * @return true If a valid options was selected
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_account:
                //Send user to profile page
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.action_add_exercise:
                //Send user to add exercise page
                startActivity(new Intent(getApplicationContext(), AddCardioExerciseActivity.class));
                break;
            case R.id.action_home:
                //Send user to home page
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                break;
            case R.id.action_logout:
                //Sign current user out and return to login page
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
