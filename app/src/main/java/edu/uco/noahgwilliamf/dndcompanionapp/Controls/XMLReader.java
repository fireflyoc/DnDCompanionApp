package edu.uco.noahgwilliamf.dndcompanionapp.Controls;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import edu.uco.noahgwilliamf.dndcompanionapp.Models.PlayerCharacter;

/**
 * Created by Noah G on 11/3/2017.
 */

public class XMLReader {
    private PlayerCharacter pc;
    private String text;

    public PlayerCharacter readChar(XmlResourceParser character) throws XmlPullParserException, IOException {
        XmlResourceParser parser = character;

        pc = new PlayerCharacter();

        int eventType = parser.getEventType();

        while(eventType != XmlPullParser.END_DOCUMENT){
            String tagname = parser.getName();
            switch (eventType) {
                case (XmlPullParser.TEXT):
                    text = parser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("str")) {
                        pc.setStr(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("dex")) {
                        pc.setDex(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("con")) {
                        pc.setCon(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("int")) {
                        pc.setInte(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("wis")) {
                        pc.setWis(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("cha")) {
                        pc.setCha(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("armor_class")) {
                        pc.setBaseAC(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("speed")) {
                        pc.setMoveSpeed(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("level")) {
                        pc.setLevel(Integer.parseInt(text));
                    } else if (tagname.equalsIgnoreCase("hit_die")) {
                        pc.setHitDie(text);
                    } else if (tagname.equalsIgnoreCase("name")) {
                        pc.setName(text);
                    } else if (tagname.equalsIgnoreCase("proficiencies")) {
                        pc.proficiencies.add(text);
                    } else if (tagname.equalsIgnoreCase("spell")) {
                        pc.spellList.add(text);
                    } else if (tagname.equalsIgnoreCase("gear")) {
                        pc.itemList.add(text);
                    } else if(tagname.equalsIgnoreCase("race")){
                        pc.setRace(text);
                    } else if(tagname.equalsIgnoreCase("class")){
                        pc.setpClass(text);
                    } else if(tagname.equalsIgnoreCase("attack")){
                        pc.attackList.add(text);
                    } else if(tagname.equalsIgnoreCase("language")){
                        pc.languages.add(text);
                    } else if(tagname.equalsIgnoreCase("tool")){
                        pc.tools.add(text);
                    } else if(tagname.equalsIgnoreCase("hp")){
                        pc.setHp(Integer.parseInt(text));
                    } else if(tagname.equalsIgnoreCase("max_hp")){
                        pc.setMaxHp(Integer.parseInt(text));
                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next();
        }

        if(pc.getpClass() == ""){
            pc = null;
        }

        return pc;
    }
}
