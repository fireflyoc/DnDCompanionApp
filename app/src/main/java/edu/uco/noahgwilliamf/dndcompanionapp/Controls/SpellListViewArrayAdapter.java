package edu.uco.noahgwilliamf.dndcompanionapp.Controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDLookUpResource;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDSpell;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Decker on 10/30/2017.
 */

public class SpellListViewArrayAdapter extends ArrayAdapter<DnDSpell> {





    public SpellListViewArrayAdapter(Context context, ArrayList<DnDSpell> spellList) {
        super(context, -1, spellList);
    } //contructor

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       //get the item
        DnDSpell spell = getItem(position);

        //check to see if the current vew is begin reused, if not, inflate it

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spell_row_listview_layout,parent,false);
        }

        TextView spellNameTextView = (TextView) convertView.findViewById(R.id.spell_name);
        TextView spellLevelTextView = (TextView) convertView.findViewById(R.id.spell_level);

        spellNameTextView.setText(spell.getName());
        spellLevelTextView.setText(spell.getLevel());

        return convertView;
    }



} //end SpellListViewArrayAdapter
