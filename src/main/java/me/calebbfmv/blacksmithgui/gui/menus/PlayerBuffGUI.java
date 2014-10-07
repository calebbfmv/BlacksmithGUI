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
}
