package edu.uco.noahgwilliamf.dndcompanionapp.Models;

/**
 * Created by Decker on 11/25/2017.
 */

public class DnDItem extends DnDLookUpResource {


    private String value;
    private String type;
    private String weight;

    public DnDItem(String name, String desc, String value, String type, String weight){
        super(name, desc);
        setType(type);
        setValue(value);
        setWeight(weight);
       // System.out.println("New IteM: " + getName() + getDescription() + getValue() + getType()+ getWeight());
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public  String getDetails(){
        return "Value: " + getValue() +"\n" +
                "Type: " + getType()  +"\n" +
                "Weight: " +getWeight();

    }

}
