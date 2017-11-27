package edu.uco.noahgwilliamf.dndcompanionapp.Models;

import java.util.ArrayList;

/**
 * Created by Decker on 11/26/2017.
 */

public class DnDCondition extends DnDLookUpResource {

    ArrayList<String> desc;

    public DnDCondition(String name, ArrayList<String> desc){

        setName(name);
        setDesc(desc);
    }

    public void setDesc(ArrayList<String> desc){
        this.desc = desc;

    }


    public String getDetails() {
        String str = "";
        for(String s: desc){
            str = str +"->" +s +"\n";

        }
        return str.substring(0,str.length()-1);
    }

    public String toString(){
        return getName();
    }


}
