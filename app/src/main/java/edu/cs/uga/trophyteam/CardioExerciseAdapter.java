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
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item's textview goes here
        public TextView nicknameTextView;
        public TextView nameTextView;
        public TextView distanceTextView;
        public TextView timeTextView;

        public MyViewHolder(View v) {
            super(v);
            nicknameTextView = v.findViewById(R.id.recyclerview_row_exercise_nickname);
            nameTextView = v.findViewById(R.id.recyclerview_row_exercise_name);
            distanceTextView = v.findViewById(R.id.recyclerview_row_exercise_distance);
            timeTextView = v.findViewById(R.id.recyclerview_row_exercise_time);
        }

        @Override
        public void onClick(View v) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CardioExerciseAdapter(List<CardioExercise> myDataset) {
        mDataset = new ArrayList<CardioExercise>();
        mDataset.addAll(myDataset);
        Log.d(TAG, "Dataset Length: " + myDataset.size());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardioExerciseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row_home_activity, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nicknameTextView.setText(mDataset.get(position).getExerciseNickname());
        holder.nameTextView.setText(mDataset.get(position).getExerciseName());
        holder.distanceTextView.setText(mDataset.get(position).getDistance()
                + mDataset.get(position).getMeasurementSystem());
        holder.timeTextView.setText(mDataset.get(position).timeToString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
