package edu.uco.noahgwilliamf.dndcompanionapp.Controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDItem;

import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Decker on 11/26/2017.
 */

public class ItemListViewArrayAdapter extends ArrayAdapter<DnDItem> {


        private ArrayList<DnDItem> itemList;


    public ItemListViewArrayAdapter(Context context, ArrayList<DnDItem> itemList) {
        super(context, -1, itemList);
        this.itemList = itemList;
    } //contructor

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //get the item
        DnDItem item = getItem(position);

        //check to see if the current vew is begin reused, if not, inflate it

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_row_listview_layout, parent, false);
        }

        TextView spellNameTextView = (TextView) convertView.findViewById(R.id.item_name);
        TextView spellLevelTextView = (TextView) convertView.findViewById(R.id.item_type);

        spellNameTextView.setText(item.getName());
        spellLevelTextView.setText(item.getType());

        return convertView;
    }


    @Override
    public void notifyDataSetChanged() {


        //System.out.println("spelllIst count: " +  itemList.size());


        System.out.println("Notify was called!!!!!!!!");
        super.notifyDataSetChanged();
    }



}
