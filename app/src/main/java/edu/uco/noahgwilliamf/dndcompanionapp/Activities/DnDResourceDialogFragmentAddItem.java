package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Decker on 12/3/2017.
 */

public class DnDResourceDialogFragmentAddItem extends DialogFragment {


    String selection;

    public DnDResourceDialogFragmentAddItem() {
        //empty constructor required for a dialog fragment
    }


    public static DnDResourceDialogFragmentAddItem newInstance(String name, String type) {
        DnDResourceDialogFragmentAddItem fragment = new DnDResourceDialogFragmentAddItem();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("type", type);
        if (CharSheetActivity.pc1 != null) {
            args.putString("pc1", CharSheetActivity.pc1.getName());
        } else{
            args.putString("pc1", "New Character");

        }
        if (CharSheetActivity.pc2 != null) {

            args.putString("pc2", CharSheetActivity.pc2.getName());
        } else{
            args.putString("pc2", "New Character");

        }
        if (CharSheetActivity.pc3!= null) {
            args.putString("pc3", CharSheetActivity.pc3.getName());
        }else{
            args.putString("pc3", "New Character");

        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dndresource_dialog_fragment_addtocharcter, container);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view


        TextView nameOutput = (TextView) view.findViewById(R.id.dnd_additem_dialog_frag_name_textview);
        Button okButon = view.findViewById(R.id.dnd_additem_dialog_frag_okbutton);
        Spinner spinner = view.findViewById(R.id.dnd_additem_dialog_frag_name_spinner);


        nameOutput.setText(getArguments().getString("name"));

        final ArrayList<CharSequence> pcs = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            if (!(getArguments().getString("pc" + i).equalsIgnoreCase("New Character"))) {
                pcs.add(getArguments().getString("pc" + i));
            }
        }


        ArrayAdapter<CharSequence> spinnerAdapter = new ArrayAdapter<CharSequence>(getActivity().getApplicationContext(), R.layout.char_skill_list_item, pcs);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) != null) {
                    selection = adapterView.getItemAtPosition(i) + "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // Fetch arguments from bundle and set title


        okButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(getArguments().getString("type").toLowerCase()){
                    case "spell":
                        if (selection.equalsIgnoreCase(pcs.get(0).toString())) {
                            CharSheetActivity.pc1.spellList.add(getArguments().getString("name"));
                        }
                        else if (selection.equalsIgnoreCase(pcs.get(1).toString())) {
                            CharSheetActivity.pc2.spellList.add(getArguments().getString("name"));
                        }
                        else if (selection.equalsIgnoreCase(pcs.get(2).toString())) {
                            CharSheetActivity.pc3.spellList.add(getArguments().getString("name"));
                        }
                        break;
                    case "item":
                        if (selection.equalsIgnoreCase(pcs.get(0).toString())) {
                            CharSheetActivity.pc1.itemList.add(getArguments().getString("name"));
                        }
                        else if (selection.equalsIgnoreCase(pcs.get(1).toString())) {
                            CharSheetActivity.pc2.itemList.add(getArguments().getString("name"));
                        }
                        else if (selection.equalsIgnoreCase(pcs.get(2).toString())) {
                            CharSheetActivity.pc3.itemList.add(getArguments().getString("name"));
                        }
                        break;
                    default:
                        System.err.println("DNDADDITEMDIALOG FAILURES");
                        break;

                }

                dismiss();
            }
        });


        // Show soft keyboard automatically and request focus to field
        okButon.requestFocus();
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


} //end classs
