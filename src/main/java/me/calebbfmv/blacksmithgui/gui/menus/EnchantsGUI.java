package me.calebbfmv.blacksmithgui.gui.menus;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import me.calebbfmv.blacksmithgui.utils.EnchantedItem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class EnchantsGUI extends GUI {

    public EnchantsGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static EnchantsGUI create(){
        final Button[] buttons = new Button[27];
        for(int i = 0; i < 27; i+= 9){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
            if(i != 0){
                buttons[i - 1] = buttons[i];
            }
        }
        for(int i = 0; i < 9; i++){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
        }
        for(int i = 16; i < 27; i++){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
        }
        EnchantmentType[] enchants = EnchantmentType.values();
        for(int i = 0; i < enchants.length; i++){
            final EnchantmentType type = enchants[i];
            final ItemStack item = create(Material.ENCHANTED_BOOK, ChatColor.YELLOW + ChatColor.BOLD.toString() + StringUtils.capitalize(type.name()));
            buttons[i + 9] = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    int cost = BlacksmithGUI.getInstance().getManager().getCost(1);
                    double balance = BlacksmithGUI.getInstance().getEcon().getBalance(player);
                    if(cost > balance){
                        player.sendMessage(ChatColor.RED + "You cannot purchase this!");
                        return;
                    }
                    ItemStack item = EPlayer.get(player).getChosenItem();
                    if(item == null){
                        player.getOpenInventory().setItem(16, create(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Please select an item!"));
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Please select an item! (1)");
                        return;
                    }
                    if(EPlayer.get(player).isPromptedForItem()){
                        player.getOpenInventory().setItem(16, create(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Please select an item!"));
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Please select an item! (2)");
                        return;
                    }
                    if(type.isDonor() && !player.hasPermission("bs.donor")){
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "You cannot purchase this item! DONORS ONLY");
                        return;
                    }
                    BlacksmithGUI.getInstance().getEcon().withdrawPlayer(player, cost);
                    EnchantedItem enchantedItem = new EnchantedItem(item);
                    enchantedItem.withEnchant(CustomEnchant.get(type), 1);
                    enchantedItem.give(player);
                    player.closeInventory();
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + StringUtils.capitalize(type.name()) + ChatColor.GRAY + "] " + ChatColor.RED + "is now equiped!");
                    EPlayer.get(player).setChosenItem(null);
                    EPlayer.get(player).setPromptedForItem(true);
                }
            });
        }
        return new EnchantsGUI("Weapon Enchantments", buttons);
    }
}
