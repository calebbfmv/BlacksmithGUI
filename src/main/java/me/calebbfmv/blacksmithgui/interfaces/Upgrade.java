package me.calebbfmv.blacksmithgui.interfaces;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public enum Upgrade {

    ARROW,

    BLINDNESS,

    CRITICLE,

    DAMAGE,

    FIREBALL(true),

    FALL,

    HEALTH,

    SPEED(true),

    SLOWNESS(true),

    STRENGTH;

    private boolean donorOnly;

    private Upgrade(){
        this.donorOnly = false;
    }

    private Upgrade(boolean donorOnly){
        this.donorOnly = donorOnly;
    }

    public boolean isDonorOnly(){
        return donorOnly;
    }
}
