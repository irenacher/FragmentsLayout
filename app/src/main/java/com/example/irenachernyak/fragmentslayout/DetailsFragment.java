package com.example.irenachernyak.fragmentslayout;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by irenachernyak on 8/10/15.
 */
public class DetailsFragment extends Fragment {

    // create a fragment instance with a particular index
    public static DetailsFragment newInstance(int index){
        DetailsFragment fragment = new DetailsFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    // provide a way to check what index is associated with this fragment
    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create a ScrollView to put your hero data in
        ScrollView scroller = new ScrollView(getActivity());

        // TextView goes in the ScrollView
        TextView text = new TextView(getActivity());

        // A TypedValue can hold multiple dimension values which can be assigned dynamically
        // applyDimensions receives the unit type to use which is COMPLEX_UNIT_DIP, which
        // is Device Independent Pixels
        // The padding amount being 4
        // The final part is information on the devices size and density
         int padding = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,
                                                        4,
                                                        getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);

        // add textview to the scroller and set proper text
        scroller.addView(text);
        text.setText(SuperHeroInfo.HISTORY[getShownIndex()]);
        return scroller;
    }
}
