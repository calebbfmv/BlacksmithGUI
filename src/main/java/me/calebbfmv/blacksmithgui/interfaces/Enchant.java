package me.calebbfmv.blacksmithgui.interfaces;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Tim [calebbfmv] on 9/12/2014.
 */
public abstract class Enchant implements IEnchant {

    private int cost;
    private String name;
    private Upgrade upgrade;
    private static LinkedHashMap<String, Enchant> enchants = new LinkedHashMap<>();

    public Enchant(int cost, String name, Upgrade upgrade){
        this.cost = cost;
        this.name = name;
        this.upgrade = upgrade;
        enchants.put(name.toLowerCase(), this);
    }

    public Upgrade getUpgrade(){
        return upgrade;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getCost(){
        return cost;
    }

    public static Enchant getFromName(String lore){
        lore = ChatColor.stripColor(lore);
        lore = lore.toLowerCase();
        lore = lore.replace("enchants: ", "");
        return enchants.get(lore);
    }

    public int getLevel(String lore){
        lore = ChatColor.stripColor(lore);
        lore = lore.toLowerCase();
        lore = lore.replace("Upgrade Level: ", "");
        return 1;
    }

    public static HashMap<String, Enchant> getEnchants(){
        return enchants;
    }
}
