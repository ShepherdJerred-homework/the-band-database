package com.shepherdjerred.thebanddatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private Band mBand;

    public static DetailsFragment newInstance(int bandId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("bandId", bandId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the band ID from the intent that started DetailsActivity
        int bandId = 1;
        if (getArguments() != null) {
            bandId = getArguments().getInt("bandId");
        }

        mBand = BandDatabase.get(getContext()).getBand(bandId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.bandName);
        nameTextView.setText(mBand.getName());

        TextView descriptionTextView = (TextView) view.findViewById(R.id.bandDescription);
        descriptionTextView.setText(mBand.getDescription());

        displayRating(view);

        return view;
    }

    public void displayRating(View view) {
        TextView ratingTextView = (TextView) view.findViewById(R.id.bandRating);
        if (mBand.getRating() != -1) {
            ratingTextView.setText("Rating: " + mBand.getRating());
        } else {
            ratingTextView.setText("");
        }
    }
}
