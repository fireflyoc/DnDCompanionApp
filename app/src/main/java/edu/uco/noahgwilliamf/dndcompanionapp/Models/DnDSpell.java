package edu.uco.noahgwilliamf.dndcompanionapp.Models;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by Decker on 10/30/2017.
 */

public class DnDSpell extends DnDLookUpResource implements Comparator<DnDSpell>, Comparable<DnDSpell> {

    private String page;
    private String range;
    private String components;
    private String material;
    private String ritual;
    private String duration;
    private String concentration;
    private String castTime;
    private String level;
    private String school;
    private String classes;


    //empty constructor for comparator
    public DnDSpell(){
        super();
    };


    public DnDSpell(String name, String description,
                    String page, String range,
                    String components, String ritual,
                    String duration, String concentration,
                    String castTime, String level,
                    String school, String classes) {
        super(name, description);
        this.setPage(page);
        this.setRange(range);
        this.setComponents(components);
         this.setRitual(ritual);
        this.setDuration(duration);
        this.setConcentration(concentration);
        this.setCastTime(castTime);
        this.setLevel(level);
        this.setSchool(school);
        this.setClasses(classes);
        System.out.println("New SPell: " +getName() + " level: " + getLevelNumeric()+" added");
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getRitual() {
        return ritual;
    }

    public void setRitual(String ritual) {
        this.ritual = ritual;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public String getLevel() {

        return level;
    }

    public int getLevelNumeric(){

        return (getLevel().equalsIgnoreCase("cantrip"))? 0 : Integer.parseInt(getLevel());
    }

    public void setLevel(String level) {
        if (level.equalsIgnoreCase("cantrip")){
            this.level = level;
        }else{
            this.level = level.charAt(0) +"";
        }

    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    //overrind the compareTo method to sort by name
    @Override
    public int compareTo(@NonNull DnDSpell epell) {
        return (this.getName()).compareTo(epell.getName());
    }

    @Override
    public int compare(DnDSpell spell1, DnDSpell spell2) {
        return spell1.getLevelNumeric() - spell2.getLevelNumeric();
    }
} //end DnDSpell
