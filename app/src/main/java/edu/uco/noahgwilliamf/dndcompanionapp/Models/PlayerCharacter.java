package edu.uco.noahgwilliamf.dndcompanionapp.Models;

import java.util.ArrayList;

/**
 * Created by Decker on 10/29/2017.
 */

public class PlayerCharacter {


    private String name, hitDie;
    private int hp, maxHp, moveSpeed, baseAC, str, dex, con, wis, inte, cha, level;
    public ArrayList<String> spellList, itemList, proficiencies;

    //stupid long constructor
    public PlayerCharacter(String name, int hp, int maxHp, int moveSpeed,
                           int baseAC, int str, int dex, int con, int wis, int inte, int cha) {

        this.setName(name);
        this.setHp(hp);
        this.setMaxHp(maxHp);
        this.setMoveSpeed(moveSpeed);
        this.setBaseAC(baseAC);
        this.setStr(str);
        this.setCon(con);
        this.setDex(dex);
        this.setWis(wis);
        this.setInte(inte);
        this.setCha(cha);

        itemList = new ArrayList<>();
        spellList = new ArrayList<>();
        proficiencies = new ArrayList<>();
    }

    //basic constructor
    public PlayerCharacter() {
        itemList = new ArrayList<>();
        proficiencies = new ArrayList<>();
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

    public void setHitDie(String hd){
        hitDie = hd;
    }

    public String getHitDie(){
        return hitDie;
    }

    public void setLevel(int l){
        level = l;
    }

    public int getLevel(){
        return level;
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

    public int getBaseAC() {
        return baseAC;
    }

    public void setBaseAC(int baseAC) {
        this.baseAC = baseAC;
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

}//end class
