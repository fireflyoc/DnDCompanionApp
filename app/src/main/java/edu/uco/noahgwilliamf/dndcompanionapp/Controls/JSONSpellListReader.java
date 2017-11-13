package edu.uco.noahgwilliamf.dndcompanionapp.Controls;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDSpell;
import edu.uco.noahgwilliamf.dndcompanionapp.R;


/**
 * Created by Decker on 11/11/2017.
 */

public class JSONSpellListReader extends AsyncTask<Void, Void, Void> {

    //im ok with this being a class-level variable, its only set up once-at the weloome screen
    private static ArrayList<DnDSpell> spellList;
    private Resources res;


    public JSONSpellListReader(Resources res) {

        spellList = new ArrayList<>();
        this.res = res;

    }

    private void readJSON() {
        InputStream resourceReader = res.openRawResource(R.raw.spells);

        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("JSON READER", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e("JSON READER", "Unhandled exception while using JSONResourceReader", e);
            }
        }
        parseData(writer.toString());
    }


    private void parseData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray spells = jsonObject.getJSONArray("spells");
            for (int i = 0; i < spells.length(); i++) {
                JSONObject thisSpell = spells.getJSONObject(i);
                System.out.println("adding new spell: " + thisSpell.getString("name") +
                        " Level : " + thisSpell.getString("level") +" to the list");

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

    public static ArrayList<DnDSpell> getSpellList() {
        return spellList;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        readJSON();
        return null;
    }
} // end class
