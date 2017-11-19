package edu.uco.noahgwilliamf.dndcompanionapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.Controls.XMLWriter;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.PlayerCharacter;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Noah G on 11/14/2017.
 */

public class charCreationActivity extends Activity {

    private DrawerLayout drawer;
    private ListView menuList;
    private ImageButton menuButton;
    private XMLWriter writer;
    private NavigationDrawerClickListener navi;
    private Spinner raceSpinner, classSpinner, levelSpinner;
    private Button create;
    private int charNum, level;
    private String race, mClass;
    private EditText name, str, dex, con, inte, wis, cha;
    private CheckBox acro, animal, arcana, athletics, decept, history, insight, intimi, invest, med, nature, percept, perform, persuasion, religion, sleight, stealth, survival;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_creater_layout);

        Intent i = getIntent();
        writer = new XMLWriter(getApplicationContext());
        charNum = i.getIntExtra("character",0);

        setUpNavi();


        raceSpinner = (Spinner) findViewById(R.id.char_create_race_spinner);
        classSpinner = (Spinner) findViewById(R.id.char_create_class_spinner);
        levelSpinner = (Spinner) findViewById(R.id.char_create_level_spinner);

        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.races, R.layout.char_spinner_item);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i)!=null){
                    race = adapterView.getItemAtPosition(i)+"";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.classTypes, R.layout.char_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i)!=null){
                    mClass = adapterView.getItemAtPosition(i)+"";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.levels, R.layout.char_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i)!=null){
                    level = Integer.parseInt(adapterView.getItemAtPosition(i)+"");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        name = (EditText) findViewById(R.id.char_create_name_edit);
        str = (EditText) findViewById(R.id.char_create_str_edit);
        dex = (EditText) findViewById(R.id.char_create_dex_edit);
        inte = (EditText) findViewById(R.id.char_create_int_edit);
        con = (EditText) findViewById(R.id.char_create_con_edit);
        wis = (EditText) findViewById(R.id.char_create_wis_edit);
        cha = (EditText) findViewById(R.id.char_create_cha_edit);

        acro = (CheckBox) findViewById(R.id.char_create_acrobatics);
        animal = (CheckBox) findViewById(R.id.char_create_animal);
        arcana = (CheckBox) findViewById(R.id.char_create_arcana);
        athletics = (CheckBox) findViewById(R.id.char_create_athletics);
        decept = (CheckBox) findViewById(R.id.char_create_deception);
        history = (CheckBox) findViewById(R.id.char_create_history);
        insight = (CheckBox) findViewById(R.id.char_create_insight);
        intimi = (CheckBox) findViewById(R.id.char_create_intimidation);
        invest = (CheckBox) findViewById(R.id.char_create_investigation);
        med = (CheckBox) findViewById(R.id.char_create_medicine);
        nature = (CheckBox) findViewById(R.id.char_create_nature);
        percept = (CheckBox) findViewById(R.id.char_create_perception);
        perform = (CheckBox) findViewById(R.id.char_create_performance);
        persuasion = (CheckBox) findViewById(R.id.char_create_persuasion);
        religion = (CheckBox) findViewById(R.id.char_create_religion);
        sleight = (CheckBox) findViewById(R.id.char_create_sleight);
        stealth = (CheckBox) findViewById(R.id.char_create_stealth);
        survival = (CheckBox) findViewById(R.id.char_create_survival);

        create = (Button) findViewById(R.id.create_button);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean allGood = true;
                System.out.println("Create Button Pressed");

                Intent i = new Intent(charCreationActivity.this, CharSheetActivity.class);
                PlayerCharacter pc = new PlayerCharacter();
                pc.setRace(race);
                pc.setLevel(level);
                if(name.getText().toString()!=""){
                    pc.setName(name.getText().toString());
                } else{
                    allGood=false;
                }
                pc.setpClass(mClass);
                if (mClass.equalsIgnoreCase("sorcerer") || mClass.equalsIgnoreCase("wizard")) {
                    pc.setHitDie("d6");
                } else if (mClass.equalsIgnoreCase("bard") || mClass.equalsIgnoreCase("cleric") || mClass.equalsIgnoreCase("druid") || mClass.equalsIgnoreCase("monk") || mClass.equalsIgnoreCase("rogue") || mClass.equalsIgnoreCase("warlock")) {
                    pc.setHitDie("d8");
                } else if (mClass.equalsIgnoreCase("fighter") || mClass.equalsIgnoreCase("paladin") || mClass.equalsIgnoreCase("ranger")) {
                    pc.setHitDie("d10");
                } else if (mClass.equalsIgnoreCase("barbarian")) {
                    pc.setHitDie("d12");
                } else {
                    allGood = false;
                }
                if (isParseable(str.getText().toString())) {
                    pc.setStr(Integer.parseInt(str.getText().toString()));
                } else {
                    pc.setStr(0);
                }
                if (isParseable(dex.getText().toString())) {
                    pc.setDex(Integer.parseInt(dex.getText().toString()));
                } else {
                    pc.setDex(0);
                }
                if (isParseable(con.getText().toString())) {
                    pc.setCon(Integer.parseInt(con.getText().toString()));
                } else {
                    pc.setCon(0);
                }
                if (isParseable(wis.getText().toString())) {
                    pc.setWis(Integer.parseInt(wis.getText().toString()));
                } else {
                    pc.setWis(0);
                }
                if (isParseable(inte.getText().toString())) {
                    pc.setInte(Integer.parseInt(inte.getText().toString()));
                } else {
                    pc.setInte(0);
                }
                if (isParseable(cha.getText().toString())) {
                    pc.setCha(Integer.parseInt(cha.getText().toString()));
                } else {
                    pc.setCha(0);
                }
                if (acro.isChecked()) {
                    pc.proficiencies.add(acro.getText().toString());
                }
                if (animal.isChecked()) {
                    pc.proficiencies.add(animal.getText().toString());
                }
                if (arcana.isChecked()) {
                    pc.proficiencies.add(arcana.getText().toString());
                }
                if (athletics.isChecked()) {
                    pc.proficiencies.add(athletics.getText().toString());
                }
                if (decept.isChecked()) {
                    pc.proficiencies.add(decept.getText().toString());
                }
                if (history.isChecked()) {
                    pc.proficiencies.add(history.getText().toString());
                }
                if (insight.isChecked()) {
                    pc.proficiencies.add(insight.getText().toString());
                }
                if (intimi.isChecked()) {
                    pc.proficiencies.add(intimi.getText().toString());
                }
                if (invest.isChecked()) {
                    pc.proficiencies.add(invest.getText().toString());
                }
                if (med.isChecked()) {
                    pc.proficiencies.add(med.getText().toString());
                }
                if (nature.isChecked()) {
                    pc.proficiencies.add(nature.getText().toString());
                }
                if (percept.isChecked()) {
                    pc.proficiencies.add(percept.getText().toString());
                }
                if (perform.isChecked()) {
                    pc.proficiencies.add(perform.getText().toString());
                }
                if (persuasion.isChecked()) {
                    pc.proficiencies.add(persuasion.getText().toString());
                }
                if (religion.isChecked()) {
                    pc.proficiencies.add(religion.getText().toString());
                }
                if (sleight.isChecked()) {
                    pc.proficiencies.add(sleight.getText().toString());
                }
                if (stealth.isChecked()) {
                    pc.proficiencies.add(stealth.getText().toString());
                }
                if (survival.isChecked()) {
                    pc.proficiencies.add(survival.getText().toString());
                }

                try {
                    if (allGood) {
                        switch (charNum) {
                            case 1:
                                CharSheetActivity.pc1 = pc;
                                writer.writeToXML(1);
                                i.putExtra("CharNum", 0);

                                break;
                            case 2:
                                CharSheetActivity.pc2 = pc;
                                writer.writeToXML(2);
                                i.putExtra("CharNum", 1);
                                break;
                            case 3:
                                CharSheetActivity.pc3 = pc;
                                writer.writeToXML(3);
                                i.putExtra("CharNum", 2);
                                break;
                        }
                        startActivity(i);
                    } else {
                        //Toast that something isn't filled in
                        Toast.makeText(getApplicationContext(),"Something isn't filled in",Toast.LENGTH_SHORT);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean isParseable(String input) {
        boolean parsable = true;
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawer.closeDrawers();
    }


    private void setUpNavi() {

        navi = NavigationDrawerClickListener.getInstance(this, getResources());


        drawer = (DrawerLayout) findViewById(R.id.char_create_layout);
        menuList = (ListView) findViewById(R.id.char_create_menu);
        menuButton = (ImageButton) findViewById(R.id.char_create_menuButton);

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
