package me.calebbfmv.blacksmithgui.utils;

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

    private RomanNumeral(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static RomanNumeral get(int i){
        switch(i){
            case 1:
                return I;
            case 2:
                return II;
            case 3:
                return III;
            case 4:
                return IV;
            case 5:
                return V;
            case 6:
                return VI;
            case 7:
                return VII;
            case 8:
                return VIII;
            case 9:
                return IX;
            case 10:
                return X;
            default:
                return null;
        }
    }
}
