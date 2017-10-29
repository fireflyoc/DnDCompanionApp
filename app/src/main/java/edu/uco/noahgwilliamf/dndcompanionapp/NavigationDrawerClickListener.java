package edu.uco.noahgwilliamf.dndcompanionapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Decker on 10/29/2017.
 */


public class NavigationDrawerClickListener implements ListView.OnItemClickListener {

    private String[] optionsStringArray;
    private String[] options;
    private ArrayList<String> characterList;
    private Resources res;
    private ArrayAdapter<String> listAdapter;

    public NavigationDrawerClickListener(Context c, Resources res){
       this.res = res;
        characterList = new ArrayList<>();
        optionsStringArray = buildOptions();
        options = res.getStringArray(R.array.menu);
        listAdapter = new ArrayAdapter<String>(c, R.layout.drawer_list_item, optionsStringArray);
    }


    public ArrayAdapter<String> getListAdapter(){
        return listAdapter;
    }

    private String[] buildOptions() {
        ArrayList<String> options = new ArrayList<>();
        readCharactersFile();
        options = characterList;
        for (String s : res.getStringArray(R.array.menu)) {
            options.add(s);
        }
        return options.toArray(new String[options.size()]);
    }

    //todo finish this
    private void readCharactersFile() {
        characterList.add("Bob the Barb");
        characterList.add("Cathy the Cleric");
        characterList.add("TurdMuffin the Gnome Rogue");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        handleSelection(optionsStringArray[position]);
    }



    private void handleSelection(String selection) {
        if (selection.equalsIgnoreCase(options[0])) {
            //spell
            System.out.println("pushed spell");
        } else if (selection.equalsIgnoreCase(options[1])) {
            //feats
            System.out.println("pusehd feat");
        } else if (selection.equalsIgnoreCase(options[2])) {
            //items
            System.out.println("items");
        } else if (selection.equalsIgnoreCase(options[3])) {
            //conditions
            System.out.println("conditions");
        } else if (selection.equalsIgnoreCase(options[3])) {
            //dice
            System.out.println("dice");
        } else {
            //its a character they selected, open character sheeet for that character
            System.out.println("PlayerCharacter");
        }
    }//end


}