package edu.uco.noahgwilliamf.dndcompanionapp.Models;

/**
 * Created by Decker on 10/30/2017.
 */

public class DnDSpell extends DnDLookUpResource {

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


    public DnDSpell(String name, String description, String page, String range,
                    String components, String ritual, String duration,
                    String concentration, String castTime, String level, String school, String classes) {
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
} //end DnDSpell
