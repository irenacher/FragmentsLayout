package com.example.irenachernyak.fragmentslayout;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by irenachernyak on 8/10/15.
 */
public class TitlesFragment extends ListFragment {

    boolean mDualpane;
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // An ArrayAdapter connects the array to our ListView
        // getActivity() returns a Context so we have the resources needed
        // We pass a default list item text view to put the data in and the
        // array
        ArrayAdapter<String> connectArrayToListView = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                SuperHeroInfo.Names);
        // Connect the ListView to our data
        setListAdapter(connectArrayToListView);

        // Check if the FrameLayout with the id details exists
        View detailsFrame = getActivity().findViewById(R.id.details);

        // Set mDuelPane based on whether you are in the horizontal layout
        // Check if the detailsFrame exists and if it is visible
        mDualpane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // If the screen is rotated onSaveInstanceState() below will store the
        // hero most recently selected. Get the value attached to curChoice and
        // store it in mCurCheckPosition

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualpane) {
            // CHOICE_MODE_SINGLE allows one item in the ListView to be selected at a time
            // CHOICE_MODE_MULTIPLE allows multiple
            // CHOICE_MODE_NONE is the default and the item won't be highlighted in this case'
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            // Send the item selected to showDetails so the right hero info is shown
            showDetails(mCurCheckPosition);
        }
    }

    // this method will be called by android virtual machine every time
    // when device orientation changes or the activity is killed for some reason
    // in order to preserve resources
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }


    void showDetails(int index){
        mCurCheckPosition = index;
        if(mDualpane) {  // landscape mode
            getListView().setItemChecked(index, true);

            // since we are currently in landscape mode find DetailsFragment
            // and populate it with details data
            DetailsFragment details = (DetailsFragment)getFragmentManager().findFragmentById(R.id.details);

            // if this fragment is not created yet or it is not assigned index yet
            // we are going to do this now and then triggering this new fragment to show up on the screen
            if(details == null || details.getShownIndex() != index){
                details = DetailsFragment.newInstance(index);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else { //portrait mode
            //in this mode we need to create new Details Activity and show it full-screen
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }
}
