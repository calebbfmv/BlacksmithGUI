package me.calebbfmv.blacksmithgui.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class GUI {

    private String name;
    private Button[] buttons;
    private static HashMap<String, GUI> guis = new HashMap<>();
    private HashMap<UUID, Inventory> inventories;

    public GUI(String name, Button[] buttons) {
        this.name = name;
        this.buttons = buttons;
        this.inventories = new HashMap<>();
        guis.put(name.toLowerCase(), this);
    }

    public String getName() {
        return ChatColor.translateAlternateColorCodes('&', name);
    }

    public void open(Player player) {
        if (inventories.get(player.getUniqueId()) != null) {
            player.openInventory(inventories.get(player.getUniqueId()));
            return;
        }
        int size = (buttons.length + 8) / 9 * 9;
        Inventory inventory = Bukkit.createInventory(player, size, getName());
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i] == null){
                continue;
            }
            ItemStack item = buttons[i].getItemStack();
            inventory.setItem(i, item);
        }
        inventories.put(player.getUniqueId(), inventory);
        player.openInventory(inventory);
    }

    public Button[] getButtons() {
        return buttons;
    }

    public int getSize(Player player){
        if(inventories.get(player.getUniqueId()) == null){
            return -1;
        }
        return inventories.get(player.getUniqueId()).getSize();
    }

    public Inventory getInventory(Player player){
        return inventories.get(player.getUniqueId());
    }

    public Button getButton(int slot) {
        try {
            return buttons[slot];
        } catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public static GUI get(String name) {
        name = name.toLowerCase();
        name = ChatColor.stripColor(name);
        return guis.get(name);
    }

    protected static ItemStack create(Material mat, String name){
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }

    protected static Button create(ItemStack item, String name, Button.ClickExecutor clickExecutor){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return new Button(item, clickExecutor);
    }

    protected static Button create(ItemStack item, String name){
        return create(item, name, new Button.ClickExecutor() {
            @Override
            public void click(Player player) {

            }
        });
    }

    protected static Button create(ItemStack item, Button.ClickExecutor ce){
        return new Button(item, ce);
    }
}
