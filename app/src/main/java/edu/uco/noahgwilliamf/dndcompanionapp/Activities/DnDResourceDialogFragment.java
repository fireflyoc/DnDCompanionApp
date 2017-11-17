package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Decker on 11/12/2017.
 */

public class DnDResourceDialogFragment extends DialogFragment {

    public DnDResourceDialogFragment() {
        //empty constructor required for a dialog fragment
    }

    public static DnDResourceDialogFragment newInstance(String name, String desc, String details) {
        DnDResourceDialogFragment fragment = new DnDResourceDialogFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("desc", desc);
        args.putString("details", details);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dndresource_dialog_fragment_details, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        TextView nameOutput = (TextView) view.findViewById(R.id.dnd_dialog_frag_name_textview);
        TextView descOuput = (TextView) view.findViewById(R.id.dnd_dialog_frag_desc_textview);
        TextView detailsOutput = (TextView) view.findViewById(R.id.dnd_dialog_frag_detials_textview);

        // Fetch arguments from bundle and set title

        nameOutput.setText(getArguments().getString("name"));
        descOuput.setText(getArguments().getString("desc"));
        detailsOutput.setText(getArguments().getString("details"));

        // Show soft keyboard automatically and request focus to field
        nameOutput.requestFocus();
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

} //end class
