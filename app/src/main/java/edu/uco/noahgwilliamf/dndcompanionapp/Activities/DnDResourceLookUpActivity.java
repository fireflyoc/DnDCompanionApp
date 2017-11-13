package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.JSONSpellListReader;
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
    private ArrayList<DnDSpell> spellList;

    private SpellListViewArrayAdapter spellAdapter;
    private android.app.FragmentManager fm;


    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();

        fm = getFragmentManager();

        spellList = new ArrayList<>();

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
       /* resourceList = new ArrayList<>();
      //dummy data for display
        resourceList.add(new DnDSpell("Magic Missle","Shoot a Bolt of Magic at a Target",DnDSpell.EVOCATION,1, page, material, ritual, duration, concentration, castTime, level, school, classes));
        resourceList.add(new DnDSpell("Mage Armor","Gain +2 Deflection AC",DnDSpell.ABJURATION,2, page, material, ritual, duration, concentration, castTime, level, school, classes));
        resourceList.add(new DnDSpell("True Strike","Gain +20 to Attack Roll",DnDSpell.DIVINATION,0, page, material, ritual, duration, concentration, castTime, level, school, classes));
        resourceList.add(new DnDSpell("Cure Minor Wounds","Touch Target gains 1d4 hp",DnDSpell.NECORMANCY,0, page, material, ritual, duration, concentration, castTime, level, school, classes));
        */

        spellList = new ArrayList<>();
   resetSpellList();;
        //   spellList = JSONSpellListReader.getSpellList();


        final ListView spellListView = (ListView) findViewById(R.id.spell_lookup_spellListView);

        spellAdapter = new SpellListViewArrayAdapter(this, spellList);

        spellListView.setAdapter(spellAdapter);

        spellListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //todo show dialog fragment
                //Toast.makeText(getApplicationContext(), "spell: " + spellList.get(i).getName(), Toast.LENGTH_LONG).show();
                DnDResourceDialogFragment frag = DnDResourceDialogFragment.newInstance(spellList.get(i).getName(),
                        spellList.get(i).getDescription(), spellList.get(i).getDetails());
                frag.show(fm, "spell_detials_fragment");


            }

        });

        Button sortByNameButton = (Button) findViewById(R.id.spell_lookup_sortByAlpha);
        Button sortByLevelButton = (Button) findViewById(R.id.spell_lookup_sortByLevel);
        final EditText searchInput = (EditText) findViewById(R.id.spell_lookup_searchInput);

        sortByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortSpellsAlphabetically(spellAdapter);
            }
        });

        sortByLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortSpellsbyLevel(spellAdapter);
            }
        });


        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() == 0) {
                resetSpellList();
                } else {
                    filter(editable.toString());

                }
                spellAdapter.notifyDataSetChanged();
            }
        });


    } //end setUpSpellList


    private void resetSpellList(){
        for (DnDSpell S : JSONSpellListReader.getSpellList()) {
            spellList.add(S);
        }

    }

    private void filter(String filter) {

        if (filter.length() > 0) {

            spellList.clear();
            for (DnDSpell s : JSONSpellListReader.getSpellList()) {
                if (s.getName().toLowerCase().contains(filter)) {
                    spellList.add(s);
                }
            }
            spellAdapter.notifyDataSetChanged();
        }
    }

    private void sortSpellsAlphabetically(SpellListViewArrayAdapter adapter) {

        Collections.sort(spellList);
        adapter.notifyDataSetChanged();
    }

    private void sortSpellsbyLevel(SpellListViewArrayAdapter adapter) {

        Collections.sort(spellList, new DnDSpell());
        adapter.notifyDataSetChanged();
    }

    //scaffolding method used during development
    private ArrayList<DnDSpell> convertToSpellList(ArrayList<DnDLookUpResource> resourceList) {
        ArrayList<DnDSpell> returnArray = new ArrayList<>();
        for (DnDLookUpResource d : resourceList) {
            returnArray.add((DnDSpell) d);
        }
        return returnArray;
    }


    private void setUpNavi() {

        navi = NavigationDrawerClickListener.getInstance(this, getResources());


        drawer = (DrawerLayout) findViewById(R.id.spell_lookup_layout);
        menuList = (ListView) findViewById(R.id.spell_lookup_menu);
        menuButton = (ImageButton) findViewById(R.id.spell_lookup_menu_button);

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
