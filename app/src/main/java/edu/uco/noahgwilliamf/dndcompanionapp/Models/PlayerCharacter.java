package edu.uco.noahgwilliamf.dndcompanionapp.Models;

import java.util.ArrayList;

/**
 * Created by Decker on 10/29/2017.
 */

public class PlayerCharacter {


    private String name;
    private int hp;
    private int maxHp;
    private int moveSpeed;
    private int bab;
    private int baseAC;
    private int willSave;
    private int reflexSave;
    private int conSave;
    private int str;
    private int dex;
    private int con;
    private int wis;
    private int inte;
    private int cha;
    private ArrayList<String> spellList;
    private ArrayList<String> itemList;

    //stupid long constructor
    public PlayerCharacter(String name, int hp, int maxHp, int moveSpeed, int bab,
                           int baseAC, int willSave, int reflexSave, int conSave,
                           int str, int dex, int con, int wis, int inte, int cha) {

        this.setName(name);
        this.setHp(hp);
        this.setMaxHp(maxHp);
        this.setMoveSpeed(moveSpeed);
        this.setBab(bab);
        this.setBaseAC(baseAC);
        this.setWillSave(willSave);
        this.setReflexSave(reflexSave);
        this.setConSave(conSave);
        this.setStr(str);
        this.setCon(con);
        this.setDex(dex);
        this.setWis(wis);
        this.setInte(inte);
        this.setCha(cha);

        itemList = new ArrayList<>();
        spellList = new ArrayList<>();
    }

    //basic constructor
    public PlayerCharacter(String name) {

        this.name = name;
        itemList = new ArrayList<>();
        spellList = new ArrayList<>();
    }

    //TODO add in various methods to get derived stats


    //lots of encapsulation here
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getBab() {
        return bab;
    }

    public void setBab(int bab) {
        this.bab = bab;
    }

    public int getBaseAC() {
        return baseAC;
    }

    public void setBaseAC(int baseAC) {
        this.baseAC = baseAC;
    }

    public int getWillSave() {
        return willSave;
    }

    public void setWillSave(int willSave) {
        this.willSave = willSave;
    }

    public int getReflexSave() {
        return reflexSave;
    }

    public void setReflexSave(int reflexSave) {
        this.reflexSave = reflexSave;
    }

    public int getConSave() {
        return conSave;
    }

    public void setConSave(int conSave) {
        this.conSave = conSave;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getInte() {
        return inte;
    }

    public void setInte(int inte) {
        this.inte = inte;
    }

    public int getCha() {
        return cha;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public ArrayList<String> getSpellList() {
        return spellList;
    }

    public void setSpellList(ArrayList<String> spellList) {
        this.spellList = spellList;
    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<String> itemList) {
        this.itemList = itemList;
    }
}//end class
