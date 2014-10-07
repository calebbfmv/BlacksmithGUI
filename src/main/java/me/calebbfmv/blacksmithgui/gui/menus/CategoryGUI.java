package me.calebbfmv.blacksmithgui.gui.menus;

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
public class CategoryGUI extends GUI {

    public CategoryGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static CategoryGUI create(){
        Button[] buttons = new Button[36];
        for(int i = 0; i < 36; i+= 9){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
            if(i != 0){
                buttons[i - 1] = buttons[i];
            }
        }
        for(int i = 0; i < 9; i++){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
        }
        for(int i = 27; i < 36; i++){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
        }
        buttons[13] = create(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), ChatColor.YELLOW.toString() + ChatColor.BOLD + "Player Buffs", new Button.ClickExecutor() {
            @Override
            public void click(Player player) {
                PlayerBuffGUI gui = PlayerBuffGUI.create();
                gui.open(player);
            }
        });
        buttons[12] = create(new ItemStack(Material.IRON_SWORD), ChatColor.YELLOW + ChatColor.BOLD.toString() + "Weapon Enchants", new Button.ClickExecutor() {
            @Override
            public void click(Player player) {
                EnchantsGUI gui = EnchantsGUI.create();
                gui.open(player);
            }
        });
        buttons[14] = create(new ItemStack(Material.GOLD_INGOT), ChatColor.YELLOW.toString() + ChatColor.BOLD + "Upgrades", new Button.ClickExecutor() {
            @Override
            public void click(Player player) {
                UpgradeCentralGUI gui = UpgradeCentralGUI.create();
                gui.open(player);
            }
        });
        return new CategoryGUI("Choose a category", buttons);
    }
}
