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

}
