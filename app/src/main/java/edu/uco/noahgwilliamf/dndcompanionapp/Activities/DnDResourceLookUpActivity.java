package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.SpellListViewArrayAdapter;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDLookUpResource;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDSpell;
import edu.uco.noahgwilliamf.dndcompanionapp.R;


/**
 * Created by Decker on 10/30/2017.
 */

public class DnDResourceLookUpActivity extends Activity {

    private DrawerLayout drawer;
    private ListView menuList;
    private ImageButton menuButton;
    private NavigationDrawerClickListener navi;
    private ArrayList<DnDLookUpResource> resourceList;


    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();

        if (intent.getStringExtra("resource").equalsIgnoreCase("spell")) {
            setContentView(R.layout.resource_spelllookup_activity);
            setUpSpellList();
        } else {
            System.err.println("Couldn't set contentView xml, no matching intent string");
        }

        setUpNavi();

    } //end onCreate

    @Override
    protected void onResume() {
        super.onResume();
        drawer.closeDrawers();
    }

    private void setUpSpellList() {
        resourceList = new ArrayList<>();
        //dummy data for display
        resourceList.add(new DnDSpell("Magic Missle","Shoot a Bolt of Magic at a Target",DnDSpell.EVOCATION,1));
        resourceList.add(new DnDSpell("Mage Armor","Gain +2 Deflection AC",DnDSpell.ABJURATION,2));
        resourceList.add(new DnDSpell("True Strike","Gain +20 to Attack Roll",DnDSpell.DIVINATION,0));
        resourceList.add(new DnDSpell("Cure Minor Wounds","Touch Target gains 1d4 hp",DnDSpell.NECORMANCY,0));

        final ListView spellListView = (ListView) findViewById(R.id.spell_lookup_spellListView);
        spellListView.setAdapter(new SpellListViewArrayAdapter(this,resourceList));

        spellListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(getApplicationContext(), "spell: " + resourceList.get(i).getName(), Toast.LENGTH_LONG).show();;

            }

        });



    } //end setUpSpellList



    private void setUpNavi() {

        navi = NavigationDrawerClickListener.getInstance(this, getResources());


        drawer = (DrawerLayout) findViewById(R.id.charSheet_layout);
        menuList = (ListView) findViewById(R.id.charSheetMenu);
        menuButton = (ImageButton) findViewById(R.id.charSheetMenuButton);

        if (navi != null) {
            System.out.println("navi wasnt null setting up menu");
            if (menuList != null) {
                menuList.setAdapter(navi.getListAdapter());
                menuList.setOnItemClickListener(navi);
            } else {
                System.out.println("menuList was null");
            }
        } else {
            System.out.println("NAVI WAS NULL!");
        }

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CLICKED Me");
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }


}//end class
