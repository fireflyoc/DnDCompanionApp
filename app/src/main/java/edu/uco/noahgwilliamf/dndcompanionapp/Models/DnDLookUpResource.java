package edu.uco.noahgwilliamf.dndcompanionapp.Models;

/**
 * Created by Decker on 10/30/2017.
 */


//basic class that allows easier collections
public class DnDLookUpResource {

    private String name;
    private String description;

    public DnDLookUpResource(String name, String description){
        this.setName(name);
        this.setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
