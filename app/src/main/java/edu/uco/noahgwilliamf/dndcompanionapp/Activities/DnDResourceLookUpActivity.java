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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.ItemListViewArrayAdapter;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.JSONResourcetReader;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.SpellListViewArrayAdapter;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDItem;
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
    private ArrayList<DnDItem> itemList;

    private SpellListViewArrayAdapter spellAdapter;
    private ItemListViewArrayAdapter itemAdapter;

    private android.app.FragmentManager fm;


    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();

        fm = getFragmentManager();

        spellList = new ArrayList<>();
        itemList = new ArrayList<>();


        switch ((intent.getStringExtra("resource")).toLowerCase()) {

            case "spell":
                setContentView(R.layout.resource_spelllookup_activity);

                setUpSpellList();
                break;

            case "item":
                setContentView(R.layout.resource_itemlookup_activity);
                setUpItemList();
            default:
                System.err.println("Couldn't set contentView xml, no matching intent string");
                break;
        }


        setUpNavi();

    } //end onCreate

    @Override
    protected void onResume() {
        super.onResume();
        drawer.closeDrawers();
    }

    private void setUpSpellList() {

        spellList = new ArrayList<>();
        resetSpellList();

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
                sortSpellsByLevel(spellAdapter);
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

                String s = editable.toString();
                s.replace("\n", "");

                if (editable.length() == 0) {
                    resetSpellList();
                } else {
                    filterSpells(editable.toString().trim());

                }
                spellAdapter.notifyDataSetChanged();
            }
        });


    } //end setUpSpellList

    private void resetSpellList() {
        spellList.clear();
        for (DnDSpell S : JSONResourcetReader.getSpellList()) {
            spellList.add(S);
        }

    }

    private void filterSpells(String filter) {

        if (filter.length() > 0) {

            spellList.clear();
            for (DnDSpell s : JSONResourcetReader.getSpellList()) {
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

    private void sortSpellsByLevel(SpellListViewArrayAdapter adapter) {

        Collections.sort(spellList, new DnDSpell());
        adapter.notifyDataSetChanged();
    }

    private void setUpItemList() {

        itemList = new ArrayList<>();
        resetItemList();

        final ListView itemListView = (ListView) findViewById(R.id.item_lookup_itemListView);

        itemAdapter = new ItemListViewArrayAdapter(this, itemList);

        itemListView.setAdapter(itemAdapter);

        itemListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "spell: " + spellList.get(i).getName(), Toast.LENGTH_LONG).show();
                DnDResourceDialogFragment frag = DnDResourceDialogFragment.newInstance(itemList.get(i).getName(),
                        itemList.get(i).getDescription(), itemList.get(i).getDetails());
                frag.show(fm, "item_details_fragment");


            }

        });

        final EditText searchInput = (EditText) findViewById(R.id.item_lookup_searchInput);

        final Spinner itemTypeSpinner = (Spinner) findViewById(R.id.item_lookup_typespinner);

        ArrayAdapter<CharSequence> itemSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.item_types, R.layout.char_spinner_item);
        itemSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        itemTypeSpinner.setAdapter(itemSpinnerAdapter);
        itemTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) != null) {

                    System.out.println("SPINNER SELECTED: " + adapterView.getItemAtPosition(i) + "");
                    filterItems((searchInput.getText()+"").replace("\n", ""),(adapterView.getItemAtPosition(i) + "").toLowerCase());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

                String s = editable.toString();
                s.replace("\n", "");

                if (editable.length() == 0) {
                    resetItemList();
                    filterItemsByType(itemTypeSpinner.getSelectedItem().toString().toLowerCase());
                } else {


                    filterItems(s,(itemTypeSpinner.getSelectedItem().toString()).toLowerCase());

                }
                itemAdapter.notifyDataSetChanged();
            }
        });


    }

    private void filterItems(String nameFilter, String typeFilter) {
        System.out.println("item searching: " + nameFilter + " type: " + typeFilter+"!");
        if (nameFilter.length() > 0) {

            if (typeFilter.equalsIgnoreCase("any")) {
                itemList.clear();
                for (DnDItem s : JSONResourcetReader.getItemList()) {
                    if (s.getName().toLowerCase().contains(nameFilter)) {
                        itemList.add(s);
                    }
                }
            } else {
                itemList.clear();
                for (DnDItem s : JSONResourcetReader.getItemList()) {
                    if (s.getType().toLowerCase().contains(typeFilter.toLowerCase())) {
                        if (s.getName().toLowerCase().contains(nameFilter)) {
                            itemList.add(s);
                        }
                    }
                }
                itemAdapter.notifyDataSetChanged();
            }
        }else{
           filterItemsByType(typeFilter);
        }

    }

    private void filterItemsByName(String filter) {


        if (filter.length() > 0) {


            itemList.clear();
            for (DnDItem s : JSONResourcetReader.getItemList()) {
                if (s.getName().toLowerCase().contains(filter)) {
                    itemList.add(s);
                }
            }
            itemAdapter.notifyDataSetChanged();


        }
    }

    private void filterItemsByType(String filter) {


        if (filter.length() > 0) {

            if (filter.equalsIgnoreCase("any")) {
                resetItemList();
                itemAdapter.notifyDataSetChanged();
            } else {
                itemList.clear();
                for (DnDItem s : JSONResourcetReader.getItemList()) {
                    if (s.getType().toLowerCase().contains(filter)) {
                        itemList.add(s);
                    }
                }
                itemAdapter.notifyDataSetChanged();
            }
        }

    }

    private void resetItemList() {
        itemList.clear();
        for (DnDItem S : JSONResourcetReader.getItemList()) {
            itemList.add(S);
        }
    }


    private void setUpNavi() {

        navi = NavigationDrawerClickListener.getInstance(this, getResources());


        switch ((intent.getStringExtra("resource")).toLowerCase()) {

            case "spell":
                drawer = (DrawerLayout) findViewById(R.id.spell_lookup_layout);
                menuList = (ListView) findViewById(R.id.spell_lookup_menu);
                menuButton = (ImageButton) findViewById(R.id.spell_lookup_menu_button);

                break;

            case "item":
                drawer = (DrawerLayout) findViewById(R.id.item_lookup_layout);
                menuList = (ListView) findViewById(R.id.item_lookup_menu);
                menuButton = (ImageButton) findViewById(R.id.item_lookup_menu_button);

            default:
                System.err.println("Couldn't set contentView xml, no matching intent string");
                break;
        }


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
