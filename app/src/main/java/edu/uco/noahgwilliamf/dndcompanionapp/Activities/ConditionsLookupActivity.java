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

import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.JSONResourceReader;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDCondition;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Noah G on 12/1/2017.
 */

public class ConditionsLookupActivity extends DnDResourceLookUpActivity {

    private ArrayList<DnDCondition> conditionList;
    private ListView conditionListView;
    private ArrayAdapter conditionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)    {

        setContentView(R.layout.resource_conditionlookup_activity);

        super.onCreate(savedInstanceState);
    }


    protected void doSetup() {

        conditionList = new ArrayList<>();
        resetList();

        conditionListView = (ListView) findViewById(R.id.condition_lookup_conditionListView);

        conditionListAdapter = new ArrayAdapter(getApplicationContext(), R.layout.char_skill_list_item, conditionList);

        conditionListView.setAdapter(conditionListAdapter);


        conditionListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "spell: " + spellList.get(i).getName(), Toast.LENGTH_LONG).show();
                DnDResourceDialogFragment frag = DnDResourceDialogFragment.newInstance(conditionList.get(i).getName(),
                        conditionList.get(i).getDetails(), "");
                frag.show(fm, "condition_details_fragment");


            }

        });

        final EditText searchInput = (EditText) findViewById(R.id.condition_lookup_searchInput);


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


                    filterList(s);
                }
                conditionListAdapter.notifyDataSetChanged();
            }
        });


    }

    protected void filterList(String filter) {

        if (filter.length() > 0) {

            conditionList.clear();
            for (DnDCondition s : JSONResourceReader.getConditionList()) {
                if (s.getName().toLowerCase().contains(filter)) {
                    conditionList.add(s);
                }
            }
            conditionListAdapter.notifyDataSetChanged();
        }
    }

    protected void resetList() {
        conditionList.clear();
        for (DnDCondition S : JSONResourceReader.getConditionList()) {
            conditionList.add(S);
        }

    }

    protected void setUpNavi(){
        super.setUpNavi();
        drawer = (DrawerLayout) findViewById(R.id.condition_lookup_layout);
        menuList = (ListView) findViewById(R.id.condition_lookup_menu);
        menuButton = (ImageButton) findViewById(R.id.condition_lookup_menu_button);
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
