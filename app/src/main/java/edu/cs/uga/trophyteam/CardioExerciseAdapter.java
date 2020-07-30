package edu.cs.uga.trophyteam;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardioExerciseAdapter extends RecyclerView.Adapter<CardioExerciseAdapter.MyViewHolder>{

    private List<CardioExercise> mDataset;
    private static final String TAG = "CardioExer_Adapter";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // each data item's textview goes here
        public TextView nicknameTextView;
        public TextView distanceTextView;
        public TextView timeTextView;

        public MyViewHolder(View v) {
            super(v);
            nicknameTextView = v.findViewById(R.id.recyclerview_row_exercise_nickname);
            distanceTextView = v.findViewById(R.id.recyclerview_row_exercise_distance);
            timeTextView = v.findViewById(R.id.recyclerview_row_exercise_time);

            //Set an OnClickListener for if a row/exercise is selected
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Adapter Positioning: " + getAdapterPosition());
                }
            });
        }
    }

    // Provide a suitable constructor (List<CardioExercise> for this Adapter)
    public CardioExerciseAdapter(List<CardioExercise> myDataset) {
        mDataset = new ArrayList<CardioExercise>();
        mDataset.addAll(myDataset);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardioExerciseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view (This creates a new row)
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row_home_activity, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    /***
     * Replace the contents of a view (invoked by the layout manager. This is where the data for
     * each row is edited/derived
     * @param holder The MyViewHolder object that is the parents of the row views
     * @param position The current position of the {@code mDataset}
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //Display name of the Exercise Nickname
        holder.nicknameTextView.setText(mDataset.get(position).getExerciseNickname());
        //If the user entered a distance and it's greater than 0, display the distance
        if (mDataset.get(position).getDistance() > 0.0){
            holder.distanceTextView.setText(mDataset.get(position).distanceToStringSymbol());
        }
        else {
            holder.distanceTextView.setText("");
        }
        //If the user entered a time and it's greater than 0, display the distance
        if (mDataset.get(position).getDurationHours() > 0.0 || mDataset.get(position).getDurationMinutes() > 0.0 || mDataset.get(position).getDurationSeconds() > 0.0){
            holder.timeTextView.setText(mDataset.get(position).timeToString());
        }
        else {
            holder.timeTextView.setText("");
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
