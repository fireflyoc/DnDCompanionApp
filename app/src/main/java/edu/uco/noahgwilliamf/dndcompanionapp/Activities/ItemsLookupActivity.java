package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.ItemListViewArrayAdapter;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.JSONResourceReader;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDItem;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Noah G on 12/1/2017.
 */

public class ItemsLookupActivity extends DnDResourceLookUpActivity {

    private ArrayList<DnDItem> itemList;
    private ItemListViewArrayAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)    {

        setContentView(R.layout.resource_itemlookup_activity);

        super.onCreate(savedInstanceState);
    }


    protected void doSetup() {

        itemList = new ArrayList<>();
        resetList();

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

        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v, int i, long arg3) {
                System.out.println("LONG CLICKED");
                DnDResourceDialogFragmentAddItem frag = DnDResourceDialogFragmentAddItem.newInstance(itemList.get(i).getName(), "spell");
                frag.show(fm, "item_additem_fragment");
                return true;
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
                    filterList((searchInput.getText() + "").replace("\n", ""), (adapterView.getItemAtPosition(i) + "").toLowerCase());

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
                    resetList();
                    filterList(itemTypeSpinner.getSelectedItem().toString().toLowerCase());
                } else {


                    filterList(s, (itemTypeSpinner.getSelectedItem().toString()).toLowerCase());

                }
                itemAdapter.notifyDataSetChanged();
            }
        });


    }

    protected void filterList(String nameFilter, String typeFilter) {
        System.out.println("item searching: " + nameFilter + " type: " + typeFilter + "!");
        if (nameFilter.length() > 0) {

            if (typeFilter.equalsIgnoreCase("any")) {
                itemList.clear();
                for (DnDItem s : JSONResourceReader.getItemList()) {
                    if (s.getName().toLowerCase().contains(nameFilter)) {
                        itemList.add(s);
                    }
                }
            } else {
                itemList.clear();
                for (DnDItem s : JSONResourceReader.getItemList()) {
                    if (s.getType().toLowerCase().contains(typeFilter.toLowerCase())) {
                        if (s.getName().toLowerCase().contains(nameFilter)) {
                            itemList.add(s);
                        }
                    }
                }
                itemAdapter.notifyDataSetChanged();
            }
        } else {
            filterList(typeFilter);
        }

    }

    protected void filterList(String filter) {


        if (filter.length() > 0) {

            if (filter.equalsIgnoreCase("any")) {
                resetList();
                itemAdapter.notifyDataSetChanged();
            } else {
                itemList.clear();
                for (DnDItem s : JSONResourceReader.getItemList()) {
                    if (s.getType().toLowerCase().contains(filter)) {
                        itemList.add(s);
                    }
                }
                itemAdapter.notifyDataSetChanged();
            }
        }

    }

    protected void resetList() {
        itemList.clear();
        for (DnDItem S : JSONResourceReader.getItemList()) {
            itemList.add(S);
        }
    }

    protected void setUpNavi(){
        super.setUpNavi();
        drawer = (DrawerLayout) findViewById(R.id.item_lookup_layout);
        menuList = (ListView) findViewById(R.id.item_lookup_menu);
        menuButton = (ImageButton) findViewById(R.id.item_lookup_menu_button);
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
