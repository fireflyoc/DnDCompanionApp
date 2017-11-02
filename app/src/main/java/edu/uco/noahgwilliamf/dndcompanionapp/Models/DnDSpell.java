package edu.uco.noahgwilliamf.dndcompanionapp.Models;

import edu.uco.noahgwilliamf.dndcompanionapp.Models.DnDLookUpResource;

/**
 * Created by Decker on 10/30/2017.
 */

public class DnDSpell extends DnDLookUpResource {

    private int school;
    private int level;
    public final static int EVOCATION = 0;
    public final static int ABJURATION = 1;
    public final static int DIVINATION= 2;
    public final static int NECORMANCY= 3;

    public DnDSpell(String name,  String description, int school, int level) {
        super(name, description);
        this.setLevel(level);
        this.setSchool(school);
    } //end DnDSpell

    public String getSchoolNameString() {

        switch (school) {

            case EVOCATION:
                return "Evocation";
            case ABJURATION:
                return "Abjuration";
            case DIVINATION:
                return "Divination";
            case NECORMANCY:
                return "Necromancy";

            default:
                return "some school";

        }

    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
} //end DnDSpell
