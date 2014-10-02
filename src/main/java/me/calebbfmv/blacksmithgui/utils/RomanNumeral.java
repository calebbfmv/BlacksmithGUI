package me.calebbfmv.blacksmithgui.utils;

import java.util.HashMap;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public enum RomanNumeral {

    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9),
    X(10);

    private int value;
    private static HashMap<Integer, RomanNumeral> numerals = new HashMap<>();

    private RomanNumeral(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    static {
        for(RomanNumeral numeral : values()){
            numerals.put(numeral.getValue(), numeral);
        }
    }

    public static RomanNumeral get(int i){
        return numerals.get(i);
    }
}
