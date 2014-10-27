package me.calebbfmv.blacksmithgui.ability;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public enum AbilityType {

    FALL,
    HEALTH,
    SPEED,
    STRENGTH;

    public boolean isDonor(){
        return this == SPEED;
    }

    public String capitalized(){
        return this.name().toLowerCase().substring(0, 1).toUpperCase() + this.name().toLowerCase().substring(1);
    }

}
