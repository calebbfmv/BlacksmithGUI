package me.calebbfmv.blacksmithgui.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        lore = lore.replace("enchant: ", "");
        return enchants.get(lore);
    }

    public static Enchant getFromItem(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if(meta == null){
            return null;
        }
        if(!meta.hasLore()){
            return null;
        }
        String e = "";
        for(String s : item.getItemMeta().getLore()){
            if(ChatColor.stripColor(s).contains("Enchant:")){
                e = s;
                break;
            }
        }
        return getFromName(e);
    }

    public static int getLevel(String lore){
        lore = ChatColor.stripColor(lore);
        lore = lore.toLowerCase();
        lore = lore.replace("Upgrade: ", "");
        return Integer.parseInt(lore);
    }

    public static HashMap<String, Enchant> getEnchants(){
        return enchants;
    }
}
