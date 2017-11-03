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

public class SpellListViewArrayAdapter extends ArrayAdapter<DnDLookUpResource> {

    private final Context context;
    private ArrayList<DnDSpell> spellList;


    public SpellListViewArrayAdapter(Context context, ArrayList<DnDLookUpResource> spellList) {
        super(context, -1, spellList);
        this.context = context;
        spellList = new ArrayList<>();
       this.setSpellList(spellList);
    } //contructor

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spell_row_listview_layout, parent, false);


        TextView spellNameTextView = (TextView) rowView.findViewById(R.id.spell_name);
        TextView spellLevelTextView = (TextView) rowView.findViewById(R.id.spell_Level);
        spellNameTextView.setText(spellList.get(position).getName());
        if(spellList.get(position).getLevel()==0){
            spellLevelTextView.setText("Cantrips");
        }else{
            spellLevelTextView.setText(spellList.get(position).getLevel());
        }

        return rowView;
    }


    public void setSpellList(ArrayList<DnDLookUpResource> spellList) {
        for(DnDLookUpResource d: spellList){
            spellList.add(d);
        }
    }
} //end SpellListViewArrayAdapter
