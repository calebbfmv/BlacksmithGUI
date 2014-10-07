package me.calebbfmv.blacksmithgui.gui.menus;

import me.calebbfmv.blacksmithgui.ability.AbilityType;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class UpgradeCentralGUI extends GUI {

    public UpgradeCentralGUI( Button[] buttons) {
        super("Pick a Type", buttons);
    }

    public static UpgradeCentralGUI create(){
        Button[] buttons = new Button[27];
        for(int i = 0; i < EnchantmentType.values().length; i++){
           final int slot = i;
           buttons[i] = create(new ItemStack(Material.ENCHANTED_BOOK), ChatColor.YELLOW + ChatColor.BOLD.toString() + EnchantmentType.values()[i].name(), new Button.ClickExecutor() {
               @Override
               public void click(Player player) {
                   UpgradeGUI gui = UpgradeGUI.create(EnchantmentType.values()[slot]);
                   gui.open(player);
               }
           });
        }
        for(int i = 9; i < 18; i++ ){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
        }
        final AbilityType[] types = AbilityType.values();
        for(int i = 0; i < types.length; i++){
           final int slot = i;
            buttons[i + 18] = create(new ItemStack(Material.ENCHANTED_BOOK), ChatColor.YELLOW + ChatColor.BOLD.toString() + types[i].name(), new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    UpgradeGUI gui = UpgradeGUI.create(types[slot]);
                    gui.open(player);
                }
            });
        }
        return new UpgradeCentralGUI(buttons);
    }

}
