package me.calebbfmv.blacksmithgui.gui.guis;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class CategoryGUI extends GUI {

    public CategoryGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static CategoryGUI create(){
        FileConfiguration config = BlacksmithGUI.getInstance().getConfig();
        String path = "guis.categories.itemNames.";
        String playerBuffs = config.getString(path + "playerBuffs");
        String weaponEnchants = config.getString(path + "weaponEnchants");
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = sword.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', weaponEnchants));
        sword.setItemMeta(meta);
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        meta = head.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', playerBuffs));
        head.setItemMeta(meta);
        Button[] buttons = new Button[2];
        buttons[0] = new Button(sword, new Button.ClickExecutor() {
            @Override
            public void click(Player player) {

            }
        });
        buttons[1] = new Button(head, new Button.ClickExecutor() {
            @Override
            public void click(Player player) {

            }
        });
        return new CategoryGUI("Categories", buttons);
    }
}
