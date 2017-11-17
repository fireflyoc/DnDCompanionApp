package edu.uco.noahgwilliamf.dndcompanionapp.Controls;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.Activities.CharSheetActivity;
import edu.uco.noahgwilliamf.dndcompanionapp.Activities.DnDResourceLookUpActivity;
import edu.uco.noahgwilliamf.dndcompanionapp.Activities.charCreationActivity;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Decker on 10/29/2017.
 */


public  class NavigationDrawerClickListener implements ListView.OnItemClickListener {

    private String[] optionsStringArray;
    private String[] options;
    private ArrayList<String> characterList;
    private Resources res;
    private ArrayAdapter<String> listAdapter;
    private Context c;
    private XMLReader reader;
    private static NavigationDrawerClickListener instance = null;


    public static NavigationDrawerClickListener getInstance(Context c, Resources res){
        if(instance==null){
            instance = new NavigationDrawerClickListener(c,res);
        }
        return instance;

    }

    private NavigationDrawerClickListener(Context c, Resources res){

        if(res != null) {
       this.res = res;
            System.out.println(res.toString());
        }else{
            System.out.println("passed RES was null");
        }
        if(c != null) {
            this.c = c;
            System.out.println(c.toString());
        }else{
            System.out.println("passed c was null");
        }

        reader = new XMLReader();
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

    private void readCharactersFile() {
        String newChar = "New Character";
        String[] files = c.fileList();
        for(String s: files){
            try {
                if (s.equals("character1.xml")) {
                    CharSheetActivity.pc1 = reader.readChar(res.getXml(R.xml.character1));
                }
                if(s.equals("character2.xml")){
                    CharSheetActivity.pc2 = reader.readChar(res.getXml(R.xml.character2));
                }
                if(s.equals("character3.xml")){
                    CharSheetActivity.pc3 = reader.readChar(res.getXml(R.xml.character3));
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(CharSheetActivity.pc1 !=null){
            characterList.add(CharSheetActivity.pc1.getName());
        } else{
            characterList.add(newChar);
        }
        if(CharSheetActivity.pc2 != null){
            characterList.add(CharSheetActivity.pc2.getName());
        } else{
            characterList.add(newChar);
        }
        if(CharSheetActivity.pc3 != null){
            characterList.add(CharSheetActivity.pc3.getName());
        } else{
            characterList.add(newChar);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        handleSelection(optionsStringArray[position]);
    }



    private void handleSelection(String selection) {
        if (selection.equalsIgnoreCase(options[0])) {
            System.out.println("pushed spell");
            Intent i = new Intent(c,DnDResourceLookUpActivity.class);
            i.putExtra("resource","spell");
            c.startActivity(i);

        } else if (selection.equalsIgnoreCase(options[1])) {
            //feats
            System.out.println("pushed feat");
        } else if (selection.equalsIgnoreCase(options[2])) {
            //items
            System.out.println("pushed items");
        } else if (selection.equalsIgnoreCase(options[3])) {
            //conditions
            System.out.println("pushed conditions");
        } else if (selection.equalsIgnoreCase(options[4])) {
            //dice
            System.out.println("pushed dice");
        } else if(selection.equalsIgnoreCase("new character")){
            Intent i = new Intent(c, charCreationActivity.class);
            if(CharSheetActivity.pc1 == null){
                i.putExtra("character", 1);
            } else if(CharSheetActivity.pc2 == null){
                i.putExtra("character", 2);
            } else{
                i.putExtra("character", 3);
            }
            c.startActivity(i);
        } else {
            System.out.println("pushed Character");
            Intent i = new Intent(c,CharSheetActivity.class);
            for(int j=0; j<characterList.size();j++){
                if(selection.equals(characterList.get(j))){
                    i.putExtra("CharNum", j);
                    c.startActivity(i);
                }
            }

        }
    }//end


}