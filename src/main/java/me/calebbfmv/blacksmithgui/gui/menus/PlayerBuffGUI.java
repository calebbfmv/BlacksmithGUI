package me.calebbfmv.blacksmithgui.gui.menus;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.ability.Ability;
import me.calebbfmv.blacksmithgui.ability.AbilityType;
import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class PlayerBuffGUI extends GUI {

    public PlayerBuffGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static PlayerBuffGUI create(){
        Button[] buttons = new Button[27];
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
        AbilityType[] enchants = AbilityType.values();
        for(int i = 0; i < enchants.length; i++){
            final AbilityType type = enchants[i];
            final ItemStack item = create(Material.ENCHANTED_BOOK, ChatColor.YELLOW + ChatColor.BOLD.toString() + StringUtils.capitalize(type.name()));
            ItemMeta meta = item.getItemMeta();
            switch(type){
                case SPEED:
                    List<String> sLore = getSpeedLore();
                    sLore.add(ChatColor.RED  + ChatColor.BOLD.toString() + "DONOR ONLY");
                    meta.setLore(sLore);
                    break;
                case FALL:
                    meta.setLore(getFallLore());
                    break;
                case HEALTH:
                    meta.setLore(getHealthLore());
                    break;
                case STREMGTH:
                    meta.setLore(getStrengthLore());
                    break;
            }
            item.setItemMeta(meta);
            buttons[i + 9] = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    int cost = BlacksmithGUI.getInstance().getManager().getCost(1);
                    double balance = BlacksmithGUI.getInstance().getEcon().getBalance(player);
                    if(cost > balance){
                        player.sendMessage(ChatColor.RED + "You cannot purchase this!");
                        return;
                    }
                    Ability ability = Ability.get(type);
                    ability.apply(player);
                    if(ability.getLevel(player) <= 0){ // player died before
                        ability.setLevel(player, 1);
                    }
                    player.closeInventory();
                    BlacksmithGUI.getInstance().getEcon().withdrawPlayer(player, cost);
                    if(type.isDonor() && !player.hasPermission("bs.donor")){
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "You cannot purchase this item! DONORS ONLY");
                        return;
                    }
                }
            });
        }
        return new PlayerBuffGUI("Player Buffs", buttons);
    }

    private static List<String> getHealthLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Apply a health boost to you.");
        lore.add(ChatColor.DARK_AQUA + "  - Each level multiplies the boost by 2");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getSpeedLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Apply a speed boost to you.");
        lore.add(ChatColor.DARK_AQUA + "  - Each level multiplies the boost by 2");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getStrengthLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Increases your damage on hit by 5%%");
        lore.add(ChatColor.DARK_AQUA + "  - Every level increase the chance by 5%.");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getFallLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Decreases the amount of damage done on falling by 10%");
        lore.add(ChatColor.DARK_AQUA + "  - Every level decreases the damage taken by 10%.");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

}
