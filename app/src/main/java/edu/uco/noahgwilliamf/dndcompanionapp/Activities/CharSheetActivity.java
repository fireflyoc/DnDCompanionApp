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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.Controls.NavigationDrawerClickListener;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.PlayerCharacter;
import edu.uco.noahgwilliamf.dndcompanionapp.R;

/**
 * Created by Noah G on 10/27/2017.
 */

public class CharSheetActivity extends Activity implements ProfAddFragment.profAddListener,
AttackAddFragment.attackAddListener, ItemAddFragment.addItemListener{

    private DrawerLayout drawer;
    private ListView menuList, charList;
    private ImageButton menuButton;
    private NavigationDrawerClickListener navi;
    private TextView charName, levelClassRace, strBonus, dexBonus, conBonus, intBonus, wisBonus, chaBonus;
    private EditText charStr, charDex, charCon, charInt, charWis, charCha, charCurHP, charMaxHP, charSpeed, charAC;
    private Intent intent;
    public static PlayerCharacter pc1 = null, pc2 = null, pc3 = null;
    private Spinner listSpinner;
    private ArrayList<String> gearList, attackList, otherProfs;
    private android.app.FragmentManager fm;
    ArrayAdapter<String> listAdapter;
    private int curChar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_sheet_stats);
        intent = getIntent();

        fm = getFragmentManager();


        setUpNavi();

        try {
            setUpChars();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setUpChars() throws XmlPullParserException, IOException {
        curChar = intent.getIntExtra("CharNum",0);
        switch (curChar) {
            case 0:
                setUpDisplay(pc1);
                break;
            case 1:
                setUpDisplay(pc2);
                break;
            case 2:
                setUpDisplay(pc3);
                break;
            default:
                System.out.println("Error, got sent an invalid Character");
        }

    }

    private void setUpDisplay(final PlayerCharacter pc) {
        //takes values from the PlayerCharacter passed to it and puts them in the correct spot in the layout
        charName = (TextView) findViewById(R.id.charNameTextView);
        levelClassRace = (TextView) findViewById(R.id.charClassTextView);
        strBonus = (TextView) findViewById(R.id.charStrBonus);
        dexBonus = (TextView) findViewById(R.id.charDexBonus);
        conBonus = (TextView) findViewById(R.id.charConBonus);
        intBonus = (TextView) findViewById(R.id.charIntBonus);
        wisBonus = (TextView) findViewById(R.id.charWisBonus);
        chaBonus = (TextView) findViewById(R.id.charChaBonus);


        charAC = (EditText) findViewById(R.id.char_stats_ac);
        charAC.setText(pc.getBaseAC()+"");
        charAC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ac = charAC.getText().toString();
                if(isParseable(ac)){
                    pc.setBaseAC(Integer.parseInt(ac));

                } else{
                    pc.setBaseAC(0);
                }
            }
        });
        charCurHP = (EditText) findViewById(R.id.char_sheet_curr_hp);
        charCurHP.setText(pc.getHp()+"");
        charCurHP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ac = charCurHP.getText().toString();
                if(isParseable(ac)){
                    pc.setHp(Integer.parseInt(ac));

                } else{
                    pc.setHp(0);
                }
            }
        });
        charMaxHP = (EditText) findViewById(R.id.char_sheet_max_hp);
        charMaxHP.setText(pc.getMaxHp()+"");
        charMaxHP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ac = charMaxHP.getText().toString();
                if(isParseable(ac)){
                    pc.setMaxHp(Integer.parseInt(ac));

                } else{
                    pc.setMaxHp(0);
                }
            }
        });
        charSpeed = (EditText) findViewById(R.id.char_sheet_speed);
        charSpeed.setText(pc.getMoveSpeed()+"");
        charSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ac = charSpeed.getText().toString();
                if(isParseable(ac)){
                    pc.setMoveSpeed(Integer.parseInt(ac));

                } else{
                    pc.setMoveSpeed(0);
                }
            }
        });



        charName.setText(pc.getName());
        levelClassRace.setText("Level "+pc.getLevel() + " "+pc.getRace() + " " + pc.getpClass());
        int bonus = 0;
        bonus = pc.getStr()/2-5;
        strBonus.setText("+"+bonus);
        bonus = pc.getDex()/2-5;
        dexBonus.setText("+"+bonus);
        bonus = pc.getCon()/2-5;
        conBonus.setText("+"+bonus);
        bonus = pc.getInte()/2-5;
        intBonus.setText("+"+bonus);
        bonus = pc.getWis()/2-5;
        wisBonus.setText("+"+bonus);
        bonus = pc.getCha()/2-5;
        chaBonus.setText("+"+bonus);

        charStr = (EditText) findViewById(R.id.charStrValue);
        charDex = (EditText) findViewById(R.id.charDexValue);
        charInt = (EditText) findViewById(R.id.charIntValue);
        charCon = (EditText) findViewById(R.id.charConValue);
        charWis = (EditText) findViewById(R.id.charWisValue);
        charCha = (EditText) findViewById(R.id.charChaValue);

        charStr.setText(String.valueOf(pc.getStr()));
        charDex.setText(String.valueOf(pc.getDex()));
        charCha.setText(String.valueOf(pc.getCha()));
        charCon.setText(String.valueOf(pc.getCon()));
        charInt.setText(String.valueOf(pc.getInte()));
        charWis.setText(String.valueOf(pc.getWis()));

        charStr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = charStr.getText().toString();
                if(isParseable(str)){
                    pc.setStr(Integer.parseInt(str));

                } else{
                    pc.setStr(0);
                }
                int bonus = 0;
                bonus = pc.getStr()/2-5;
                if(bonus<0){
                    strBonus.setText("-"+bonus);
                } else {
                    strBonus.setText("+" + bonus);
                }
            }
        });
        charDex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String dex = charDex.getText().toString();
                if(isParseable(dex)){
                    pc.setDex(Integer.parseInt(dex));

                } else{
                    pc.setDex(0);
                }
                int bonus = 0;
                bonus = pc.getDex()/2-5;
                if(bonus<0){
                    dexBonus.setText("-"+bonus);
                } else {
                    dexBonus.setText("+" + bonus);
                }
            }
        });
        charCon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String con = charCon.getText().toString();
                if(isParseable(con)){
                    pc.setCon(Integer.parseInt(con));

                } else{
                    pc.setCon(0);
                }
                int bonus = 0;
                bonus = pc.getCon()/2-5;
                if(bonus<0){
                    conBonus.setText("-"+bonus);
                } else {
                    conBonus.setText("+" + bonus);
                }
            }
        });
        charInt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inte = charInt.getText().toString();
                if(isParseable(inte)){
                    pc.setInte(Integer.parseInt(inte));

                } else{
                    pc.setInte(0);
                }
                int bonus = 0;
                bonus = pc.getInte()/2-5;
                if(bonus<0){
                    intBonus.setText("-"+bonus);
                } else {
                    intBonus.setText("+" + bonus);
                }
            }
        });
        charWis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String wis = charWis.getText().toString();
                if(isParseable(wis)){
                    pc.setWis(Integer.parseInt(wis));

                } else{
                    pc.setWis(0);
                }
                int bonus = 0;
                bonus = pc.getWis()/2-5;
                if(bonus<0){
                    wisBonus.setText("-"+bonus);
                } else {
                    wisBonus.setText("+" + bonus);
                }
            }
        });
        charCha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cha = charCha.getText().toString();
                if(isParseable(cha)){
                    pc.setCha(Integer.parseInt(cha));

                } else{
                    pc.setCha(0);
                }
                int bonus = 0;
                bonus = pc.getCha()/2-5;
                if(bonus<0){
                    chaBonus.setText("-"+bonus);
                } else {
                    chaBonus.setText("+" + bonus);
                }
            }
        });

        charList = (ListView) findViewById(R.id.charList);

        listSpinner = (Spinner) findViewById(R.id.charListSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.specialties, R.layout.char_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSpinner.setAdapter(adapter);
        listSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                switch (pos){
                    case 0: //Skills and Proficiencies
                        String[] s;
                        s = getResources().getStringArray(R.array.skills_saving_throws);
                        for(int i=0; i<s.length;i++){
                            int gain;
                            if(s[i].contains("(Str)")){
                                gain = pc.getStr()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].contains(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("(Dex)")){
                                gain = pc.getDex()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].contains(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("(Con)")){
                                gain = pc.getCon()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].contains(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("(Int)")){
                                gain = pc.getInte()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].contains(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("(Wis)")){
                                gain = pc.getWis()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].contains(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("(Cha)")){
                                gain = pc.getCha()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].contains(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("Strength")){
                                gain = pc.getStr()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].equalsIgnoreCase(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("Dexterity")){
                                gain = pc.getDex()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].equalsIgnoreCase(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("Constitution")){
                                gain = pc.getCon()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].equalsIgnoreCase(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("Intelligence")){
                                gain = pc.getInte()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].equalsIgnoreCase(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("Wisdom")){
                                gain = pc.getWis()/2-5;
                                for(String t: pc.proficiencies){
                                    System.out.println(t);
                                    if(s[i].equalsIgnoreCase(t)){
                                        gain+=pc.getProfBonus();

                                    }
                                }
                                s[i]+="\t+"+gain;
                            } else if(s[i].contains("Charisma")){
                                gain = pc.getCha()/2-5;
                                for(String t: pc.proficiencies){
                                    if(s[i].equalsIgnoreCase(t)){
                                        gain+=pc.getProfBonus();
                                    }
                                }
                                s[i]+="\t+"+gain;
                            }
                        }
                        listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.char_skill_list_item, s);
                        charList.setAdapter(listAdapter);
                        break;
                    case 1:  //Gear and items
                        gearList = pc.itemList;
                        if(!gearList.contains("Add New")){
                            gearList.add(0, "Add New");
                        }
                        listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.char_skill_list_item, gearList);
                        charList.setAdapter(listAdapter);
                        charList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                                if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("add new")){
                                    ItemAddFragment ifrag = new ItemAddFragment(pc);
                                    ifrag.show(fm, "tag");

                                }
                            }
                        });
                        break;
                    case 2:  //Attacks and Spellcasting
                        attackList = pc.attackList;
                        if(!attackList.contains("Add New")){
                            attackList.add(0, "Add New");
                        }
                        listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.char_skill_list_item, attackList);
                        charList.setAdapter(listAdapter);
                        charList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                                if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("add new")){
                                    AttackAddFragment afrag = new AttackAddFragment(pc);
                                    afrag.show(fm, "tag");

                                }
                            }
                        });
                        break;
                    case 3: //Other Proficiencies
                        otherProfs = pc.languages;
                        otherProfs.addAll(pc.tools);
                        if(!otherProfs.contains("Add New")){
                            otherProfs.add(0, "Add New");
                        }
                        listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.char_skill_list_item, otherProfs);
                        charList.setAdapter(listAdapter);
                        charList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                                if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("add new")){
                                    ProfAddFragment pfrag = new ProfAddFragment(pc);
                                    pfrag.show(fm, "tag");

                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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


    @Override
    public void addProficiency(String prof) {
        otherProfs.add(prof);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void addItem(String s) {
        gearList.add(s);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void addAttack(String s) {
        attackList.add(s);
        listAdapter.notifyDataSetChanged();
    }
}//end class
