package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import edu.uco.noahgwilliamf.dndcompanionapp.Models.PlayerCharacter;

/**
 * Created by Noah G on 12/3/2017.
 */

class ProfAddFragment extends DialogFragment {

    public interface profAddListener{
        void addProficiency(String prof);
    }

    private PlayerCharacter pc;
    private EditText et;
    private profAddListener listener;

    public ProfAddFragment(PlayerCharacter pc) {
        this.pc = pc;


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (profAddListener) getActivity();
            System.out.println("Listener Created!");
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement ProfAddListener");
        }
        et = new EditText(getActivity());
        et.setHint("Add Proficiency");
        et.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        return new AlertDialog.Builder(getActivity()).setTitle("Add Proficiency")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.addProficiency(et.getText().toString());
                    }
                }).setNegativeButton("Cancel", null)
                .setView(et)
                .create();
    }
}
