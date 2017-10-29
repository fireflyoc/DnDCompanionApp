package edu.uco.noahgwilliamf.dndcompanionapp;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ListView menuList;
    private ImageButton menuButton;

    private String[] optionsStringArray;
    private String[] options;

    private ArrayList<String> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get the array of option strings
        characterList = new ArrayList<>();
        options = getResources().getStringArray(R.array.menu);
        optionsStringArray = buildOptions();


        drawer = (DrawerLayout) findViewById(R.id.main_layout);
        menuList = (ListView) findViewById(R.id.main_menu);
        menuButton = (ImageButton) findViewById(R.id.menuButton);

        menuList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, optionsStringArray));
        menuList.setOnItemClickListener(new DrawerItemClickListener());

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

    }

    private String[] buildOptions() {
        ArrayList<String> options = new ArrayList<>();
        readCharactersFile();
        options = characterList;
        for (String s : getResources().getStringArray(R.array.menu)) {
            options.add(s);
        }
        return options.toArray(new String[options.size()]);
    }

    //todo finishthis
    private void readCharactersFile() {
        characterList.add("Bob the Barb");
        characterList.add("Cathy the Cleric");
        characterList.add("TurdMuffin the Gnome Rogue");
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

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
                System.out.println("Character");
            }
        }//end


    }
}
