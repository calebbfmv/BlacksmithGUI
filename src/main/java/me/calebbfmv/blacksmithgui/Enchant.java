package me.calebbfmv.blacksmithgui;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Tim [calebbfmv] on 9/12/2014.
 */
public abstract class Enchant implements IEnchant {

    private int tradeValue;
    private String[] lore;
    private String name;
    private static LinkedHashMap<String, Enchant> enchants = new LinkedHashMap<>();

    public Enchant(int tradeValue, String[] lore, String name){
        this.lore = lore;
        this.tradeValue = tradeValue;
        this.name = name;
        enchants.put(name.toLowerCase(), this);
    }

    @Override
    public String[] getLore() {
        return lore;
    }

    @Override
    public int getTradeValue() {
        return tradeValue;
    }

    @Override
    public String getName(){
        return name;
    }

    public static Enchant getFromName(String lore){
        lore = ChatColor.stripColor(lore);
        lore = lore.toLowerCase();
        lore = lore.replace("enchants: ", "");
        return enchants.get(lore);
    }

    public static HashMap<String, Enchant> getEnchants(){
        return enchants;
    }
}
