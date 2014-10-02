package me.calebbfmv.blacksmithgui.utils;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class EnchantedItem {

    private ItemStack item;
    private ItemMeta meta;
    private List<String> lore;

    public EnchantedItem(ItemStack item){
        this.item = item;
        this.meta = item.getItemMeta();
        this.lore = new ArrayList<>();
    }

    public EnchantedItem withName(String name){
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public EnchantedItem withEnchant(Enchant enchant){
        lore.set(0, ChatColor.RED + "Enchant: " + enchant.getName());
        return this;
    }

    public EnchantedItem withUpgrade(Upgrade upgrade, int level){
        lore.set(1, ChatColor.RED + "Upgrade: " + RomanNumeral.get(level).name());
        return this;
    }

    public ItemStack toItem(){
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void give(Player player){
        player.getInventory().addItem(toItem());
    }

}
