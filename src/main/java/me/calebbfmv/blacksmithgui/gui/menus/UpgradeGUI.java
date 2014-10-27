package me.calebbfmv.blacksmithgui.gui.menus;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.ability.Ability;
import me.calebbfmv.blacksmithgui.ability.AbilityType;
import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.utils.EnchantedItem;
import me.calebbfmv.blacksmithgui.utils.RomanNumeral;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class UpgradeGUI extends GUI {

    public UpgradeGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public UpgradeGUI(Button[] buttons, AbilityType type){
        this("Upgrade: " + type.name(), buttons);
    }

    public UpgradeGUI(Button[] buttons, EnchantmentType type){
        this("Upgrade: " + type.name(), buttons);
    }

    public static UpgradeGUI create(final AbilityType type){
        Button[] buttons = new Button[10];
        for(int i = 0; i < 10; i++){
            final int fI = i + 1;
            RomanNumeral romanNumeral = RomanNumeral.get(i + 1);
            final int cost = BlacksmithGUI.getInstance().getManager().getCost(fI);
            ItemStack item = create(Material.EMERALD, ChatColor.GOLD + "Level " + romanNumeral.name());
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + " Cost: " + cost);
            ItemMeta meta = item.getItemMeta();
            meta.setLore(lore);
            item.setItemMeta(meta);
            buttons[i] = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    double balance = BlacksmithGUI.getInstance().getEcon().getBalance(player);
                    if(cost >  balance){
                        player.sendMessage(ChatColor.RED + "You cannot purchase this upgrade!");
                        return;
                    }
                    Ability ability = Ability.get(type);
                    ability.setLevel(player, fI);
                    ability.apply(player);
                    BlacksmithGUI.getInstance().getEcon().withdrawPlayer(player, cost);
                    player.closeInventory();
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + type.capitalized() + ChatColor.GRAY + "] " + ChatColor.RED + "is now level " + fI);
                }
            });
        }
        return new UpgradeGUI(buttons, type);
    }

    public static UpgradeGUI create(final EnchantmentType type){
        Button[] buttons = new Button[10];
        for(int i = 0; i < 10; i++){
            final int fI = i + 1;
            RomanNumeral romanNumeral = RomanNumeral.get(i + 1);
            final int cost = BlacksmithGUI.getInstance().getManager().getCost(fI);
            ItemStack item = create(Material.EMERALD, ChatColor.GOLD + "Level " + romanNumeral.name());
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + " Cost: " + cost);
            ItemMeta meta = item.getItemMeta();
            meta.setLore(lore);
            item.setItemMeta(meta);
            buttons[i] = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    double balance = BlacksmithGUI.getInstance().getEcon().getBalance(player);
                    if(cost >  balance){
                        player.sendMessage(ChatColor.RED + "You cannot purchase this upgrade!");
                        return;
                    }
                    BlacksmithGUI.getInstance().getEcon().withdrawPlayer(player, cost);
                    CustomEnchant ability = CustomEnchant.get(type);
                    ItemStack item = null;
                    int slot = -1;
                    for(int is = 0; is < player.getInventory().getContents().length; is++){
                        ItemStack itemStack = player.getInventory().getItem(is);
                        if(itemStack == null ||  itemStack.getType() == Material.AIR){
                            continue;
                        }
                        if(CustomEnchant.getFromItem(itemStack) == null){
                            continue;
                        }
                        item = itemStack;
                        slot = is;
                        break;
                    }
                    ability.setLevel(player, fI);
                    if(item != null) {
                        EnchantedItem enchantedItem = new EnchantedItem(item);
                        enchantedItem.withEnchant(ability, fI);
                        player.getInventory().setItem(slot, enchantedItem.toItem());
                    }
                    player.closeInventory();
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + type.capitalized() + ChatColor.GRAY + "] " + ChatColor.RED + "is now level " + fI);
                }
            });
        }
        return new UpgradeGUI(buttons, type);
    }

}
