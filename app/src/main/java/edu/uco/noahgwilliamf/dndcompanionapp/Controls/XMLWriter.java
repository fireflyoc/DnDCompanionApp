package edu.uco.noahgwilliamf.dndcompanionapp.Controls;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import edu.uco.noahgwilliamf.dndcompanionapp.Activities.CharSheetActivity;
import edu.uco.noahgwilliamf.dndcompanionapp.Models.PlayerCharacter;

/**
 * Created by Noah G on 11/16/2017.
 */

public class XMLWriter {

    File charFile;
    Context c;
    XmlSerializer serializer;
    FileOutputStream fileos;
    PlayerCharacter pc;

    public XMLWriter(Context c){
        this.c = c;
    }

    public void writeToXML(int i) throws IOException {
        //do stuff here
        serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);
        switch (i){
            case 1:
                charFile = new File(c.getFileStreamPath("character1.xml").getPath());
                pc = CharSheetActivity.pc1;
                break;
            case 2:
                charFile = new File(c.getFileStreamPath("character2.xml").getPath());
                pc = CharSheetActivity.pc2;
                break;
            case 3:
                charFile = new File(c.getFileStreamPath("character3.xml").getPath());
                pc = CharSheetActivity.pc3;
                break;
        }

        fileos = new FileOutputStream(charFile, false);

        serializer.startDocument("UTF-8",true);
        serializer.startTag("","resources");

        serializer.startTag("","class");
        serializer.text(pc.getpClass());
        serializer.endTag("","class");
        serializer.startTag("","Str");
        serializer.text(""+pc.getStr());
        serializer.endTag("","Str");
        serializer.startTag("","Dex");
        serializer.text(""+pc.getDex());
        serializer.endTag("","Dex");
        serializer.startTag("","Con");
        serializer.text(""+pc.getCon());
        serializer.endTag("","Con");
        serializer.startTag("","Int");
        serializer.text(""+pc.getInte());
        serializer.endTag("","Int");
        serializer.startTag("","Wis");
        serializer.text(""+pc.getWis());
        serializer.endTag("","Wis");
        serializer.startTag("","Cha");
        serializer.text(""+pc.getCha());
        serializer.endTag("","Cha");
        serializer.startTag("","Armor_Class");
        serializer.text(""+pc.getBaseAC());
        serializer.endTag("","Armor_Class");
        serializer.startTag("","Speed");
        serializer.text(""+pc.getMoveSpeed());
        serializer.endTag("","Speed");
        serializer.startTag("","Level");
        serializer.text(""+pc.getLevel());
        serializer.endTag("","Level");
        serializer.startTag("","Name");
        serializer.text(""+pc.getName());
        serializer.endTag("","Name");
        serializer.startTag("","Race");
        serializer.text(""+pc.getRace());
        serializer.endTag("","Race");
        serializer.startTag("","HP");
        serializer.text(""+pc.getHp());
        serializer.endTag("","HP");
        serializer.startTag("","Max_HP");
        serializer.text(""+pc.getMaxHp());
        serializer.endTag("","Max_HP");
        for(int j=0; j<pc.proficiencies.size(); j++){
            serializer.startTag("","Proficiencies");
            serializer.text(""+pc.proficiencies.get(j));
            serializer.endTag("","Proficiencies");
        }
        for(int j=0; j<pc.spellList.size(); j++){
            serializer.startTag("","Spell");
            serializer.text(""+pc.spellList.get(j));
            serializer.endTag("","Spell");
        }
        for(int j=0; j<pc.itemList.size(); j++){
            serializer.startTag("","Gear");
            serializer.text(""+pc.itemList.get(j));
            serializer.endTag("","Gear");
        }
        for(int j=0; j<pc.attackList.size(); j++){
            serializer.startTag("","Attack");
            serializer.text(""+pc.attackList.get(j));
            serializer.endTag("","Attack");
        }
        for(int j=0; j<pc.tools.size(); j++){
            serializer.startTag("","Tool");
            serializer.text(""+pc.tools.get(j));
            serializer.endTag("","Tool");
        }
        for(int j=0; j<pc.languages.size(); j++){
            serializer.startTag("","Language");
            serializer.text(""+pc.languages.get(j));
            serializer.endTag("","Language");
        }

        serializer.endTag("","resources");
        serializer.endDocument();
        String data = writer.toString();
        fileos.write(data.getBytes());
        fileos.close();

    }

}
