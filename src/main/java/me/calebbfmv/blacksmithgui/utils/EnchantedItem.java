package me.calebbfmv.blacksmithgui.utils;

import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
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

    public EnchantedItem withEnchant(CustomEnchant enchant, int level){
        lore.add(0, ChatColor.RED + "Enchantment: " + enchant.getType().name() + " " + RomanNumeral.get(level).name());
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
