package edu.uco.noahgwilliamf.dndcompanionapp.Controls;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDItem;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDSpell;
import edu.uco.noahgwilliamf.dndcompanionapp.R;


/**
 * Created by Decker on 11/11/2017.
 */

public class JSONResourcetReader extends AsyncTask<Void, Void, Void> {

    //im ok with this being a class-level variable, its only set up once-at the weloome screen
    private static ArrayList<DnDSpell> spellList;
    private static ArrayList<DnDItem> itemList;
    private Resources res;


    public JSONResourcetReader(Resources res) {

        spellList = new ArrayList<>();
        itemList = new ArrayList<>();
        this.res = res;

    }

    private void readJSON() {
        InputStream spellResourceReader = res.openRawResource(R.raw.spells);

        Writer spellWriter = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(spellResourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                spellWriter.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("JSON READER", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                spellResourceReader.close();

                System.out.println("RES 1:" +spellWriter.toString());
                 parseSpellData(spellWriter.toString());

            } catch (Exception e) {
                Log.e("JSON READER", "Unhandled exception while using JSONResourceReader", e);
            }

        }

        Writer itemWriter = new StringWriter();

        InputStream itemResourceReader = res.openRawResource(R.raw.items);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(itemResourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                itemWriter.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("JSON READER", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                itemResourceReader.close();
            } catch (Exception e) {
                Log.e("JSON READER", "Unhandled exception while using JSONResourceReader", e);
            }
            System.out.println("RES2: " +itemWriter.toString());
            parseItemData(itemWriter.toString());
        }


    }


    private void parseSpellData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray spells = jsonObject.getJSONArray("spells");
            for (int i = 0; i < spells.length(); i++) {
                JSONObject thisSpell = spells.getJSONObject(i);
                DnDSpell newSpell = new DnDSpell(thisSpell.getString("name"), thisSpell.getString("desc"),
                        thisSpell.getString("page"), thisSpell.getString("range"),
                        thisSpell.getString("components"), thisSpell.getString("ritual"),
                        thisSpell.getString("duration"), thisSpell.getString("concentration"),
                        thisSpell.getString("casting_time"), thisSpell.getString("level"),
                        thisSpell.getString("school"), thisSpell.getString("class"));

                try {
                    newSpell.setMaterial(thisSpell.getString("material"));
                } catch (JSONException e) {
                    // newSpell.setEnabled(false);
                    e.printStackTrace();

                }

                spellList.add(newSpell);

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }


    private void parseItemData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray items = jsonObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject thisItem = items.getJSONObject(i);
                DnDItem newItem = new DnDItem(thisItem.getString("name"), thisItem.getString("desc"),
                        thisItem.getString("value"), thisItem.getString("type"),
                        thisItem.getString("weight"));


                itemList.add(newItem);

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }


    public static ArrayList<DnDSpell> getSpellList() {
        return spellList;
    }

    public static ArrayList<DnDItem> getItemList() {
        return itemList;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        readJSON();
        return null;
    }
} // end class
