package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

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

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.JSONResourceReader;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.SpellListViewArrayAdapter;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDSpell;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Noah G on 12/1/2017.
 */

public class SpellLookupActivity extends DnDResourceLookUpActivity {

    private ArrayList<DnDSpell> spellList;
    private SpellListViewArrayAdapter spellAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.resource_spelllookup_activity);

        super.onCreate(savedInstanceState);
    }

    protected void doSetup() {
        spellList = new ArrayList<>();
        resetList();

        final ListView spellListView = (ListView) findViewById(R.id.spell_lookup_spellListView);

        spellAdapter = new SpellListViewArrayAdapter(this, spellList);

        spellListView.setAdapter(spellAdapter);

        spellListView.setLongClickable(true);
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

        spellListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v, int i, long arg3) {
                System.out.println("LONG CLICKED");
                DnDResourceDialogFragmentAddItem frag = DnDResourceDialogFragmentAddItem.newInstance(spellList.get(i).getName(), "spell");
                frag.show(fm, "spell_additem_fragment");
                return true;
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
                    resetList();
                } else {
                    filterList(editable.toString().trim());

                }
                spellAdapter.notifyDataSetChanged();
            }
        });

    }

    protected void filterList(String filter) {

        if (filter.length() > 0) {

            spellList.clear();
            for (DnDSpell s : JSONResourceReader.getSpellList()) {
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

    protected void resetList() {
        spellList.clear();
        for (DnDSpell S : JSONResourceReader.getSpellList()) {
            spellList.add(S);
        }

    }

    protected void setUpNavi() {
        super.setUpNavi();
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
}


