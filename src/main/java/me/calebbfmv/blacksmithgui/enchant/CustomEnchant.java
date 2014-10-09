package me.calebbfmv.blacksmithgui.enchant;

import me.calebbfmv.blacksmithgui.utils.EnchantedItem;
import me.calebbfmv.blacksmithgui.utils.RomanNumeral;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public abstract class CustomEnchant {

    private EnchantmentType type;
    private int level;
    private static HashMap<EnchantmentType, CustomEnchant> enchants = new HashMap<>();
    private HashMap<UUID, Integer> levels;

    public CustomEnchant(EnchantmentType type){
        this.type = type;
        this.levels = new HashMap<>();
        enchants.put(type, this);
    }

    public static CustomEnchant get(EnchantmentType type){
        return enchants.get(type);
    }

    public static CustomEnchant getFromItem(Player player){
        ItemStack item = player.getItemInHand();
        if(item == null || item.getType() == Material.AIR){
            return null;
        }
        if(!item.hasItemMeta()){
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        if(!meta.hasLore()){
            return null;
        }
        String en = "";
        String level = "";
        for(String s : meta.getLore()){
            String check = ChatColor.stripColor(s);
            if(!check.contains("Enchantment: ")){
                continue;
            }
            String past = check.replace("Enchantment: ", "");
            String[] str = past.split(" ");
            en = str[0];
            level = str[1];
            break;
        }
        int lvl = RomanNumeral.valueOf(level).getValue();
        EnchantmentType typ = EnchantmentType.valueOf(en);
        CustomEnchant enchant = enchants.get(typ);
        EnchantedItem enchantedItem = new EnchantedItem(item);
        if(lvl > enchant.getLevel(player)){
            enchant.setLevel(player, lvl);
        }
        enchantedItem.withEnchant(enchant, enchant.getLevel(player));
        if(player.getItemInHand().equals(enchantedItem.toItem())){
            return enchant;
        }
        player.setItemInHand(enchantedItem.toItem());
        return enchant;
    }

    public static CustomEnchant getFromItem(ItemStack item){
        if(item == null || item.getType() == Material.AIR){
            return null;
        }
        if(!item.hasItemMeta()){
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        if(!meta.hasLore()){
            return null;
        }
        String en = "";
        for(String s : meta.getLore()){
            String check = ChatColor.stripColor(s);
            if(!check.contains("Enchantment: ")){
                continue;
            }
            String past = check.replace("Enchantment: ", "");
            String[] str = past.split(" ");
            en = str[0];
            break;
        }
        EnchantmentType typ = EnchantmentType.valueOf(en);
        return enchants.get(typ);
    }

    public static CustomEnchant[] getEnchants(){
        return enchants.values().toArray(new CustomEnchant[enchants.size()]);
    }

    public abstract void action(Player player);
    public abstract void action(EntityDamageByEntityEvent event);
    public abstract boolean isRightClick();

    public int getLevel(Player player){
        if(levels.get(player.getUniqueId()) == null){
            levels.put(player.getUniqueId(), 1);
            return 1;
        }
        return levels.get(player.getUniqueId());
    }

    public void setLevel(Player player, int level){
        levels.put(player.getUniqueId(), level);
    }

    public EnchantmentType getType() {
        return type;
    }
}
