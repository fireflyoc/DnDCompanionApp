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

class ItemAddFragment extends DialogFragment{

    public interface addItemListener{
        void addItem(String s);
    }

    private PlayerCharacter pc;
    private EditText et;
    private addItemListener listener;
    public ItemAddFragment(PlayerCharacter pc){
        this.pc = pc;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (addItemListener) getActivity();
            System.out.println("Listener Created!");
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement ProfAddListener");
        }
        et = new EditText(getActivity());
        et.setHint("Add Item");
        et.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        return new AlertDialog.Builder(getActivity()).setTitle("Add Item")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.addItem(et.getText().toString());
                    }
                }).setNegativeButton("Cancel", null)
                .setView(et)
                .create();
    }
}
