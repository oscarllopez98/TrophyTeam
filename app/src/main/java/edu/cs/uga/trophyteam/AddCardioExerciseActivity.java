package edu.cs.uga.trophyteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class AddCardioExerciseActivity extends AppCompatActivity {

    private final String TAG = "Log_AddExerAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cardio_exercise);

        //Retrieve Primary Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_add_exercise, new FragmentExerciseCardio());
        transaction.addToBackStack(null);
        transaction.commit();

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
     *
     * @param item The MenuItem selected by the user (Should redirect to an activity)
     * @return false to allow normal menu processing to proceed, true to consume it here
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add_exercise:
                startActivity(new Intent(AddCardioExerciseActivity.this, AddCardioExerciseActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
