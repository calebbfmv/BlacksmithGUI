package me.calebbfmv.blacksmithgui.enchant;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public enum EnchantmentType {

    ARROW,
    CRITICAL,
    BLINDNESS,
    SLOWNESS(true),
    DAMAGE,
    FIREBAL(true);

    private boolean donor;

    private EnchantmentType(boolean donor){
        this.donor = donor;
    }

    private EnchantmentType(){
        this.donor = false;
    }

    public boolean isDonor(){
        return donor;
    }

}
