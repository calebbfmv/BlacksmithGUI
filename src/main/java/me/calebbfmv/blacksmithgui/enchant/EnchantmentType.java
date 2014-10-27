package me.calebbfmv.blacksmithgui.enchant;

import java.util.HashMap;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public enum EnchantmentType {

    ARROW,
    CRITICAL,
    BLINDNESS,
    SLOWNESS(true),
    DAMAGE,
    FIREBALL(true);

    private boolean donor;

    private static HashMap<String, EnchantmentType> names = new HashMap<>();

    static {
        for(EnchantmentType type : values()){
            names.put(type.capitalized(), type);
        }
    }

    private EnchantmentType(boolean donor){
        this.donor = donor;
    }

    private EnchantmentType(){
        this.donor = false;
    }

    public boolean isDonor(){
        return donor;
    }

    public String capitalized(){
        return this.name().toLowerCase().substring(0, 1).toUpperCase() + this.name().toLowerCase().substring(1);
    }

    public static EnchantmentType get(String from){
        return names.get(from);
    }

}
