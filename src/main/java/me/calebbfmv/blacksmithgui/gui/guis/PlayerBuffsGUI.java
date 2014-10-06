package me.calebbfmv.blacksmithgui.gui.guis;

import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.interfaces.PassiveAbility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Tim [calebbfmv] on 10/2/2014.
 */
public class PlayerBuffsGUI extends GUI {

    public PlayerBuffsGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static PlayerBuffsGUI create(){
        Button[] buttons = new Button[27];
        PassiveAbility[] enchants = PassiveAbility.getAbilities().values().toArray(new PassiveAbility[PassiveAbility.getAbilities().size()]);
        for(int i = 1; i < enchants.length; i++){
            if(i == 9 || i == 10 || i == 11){
                continue;
            }
            final PassiveAbility ability = enchants[i];
            final ItemStack item = create(Material.ENCHANTED_BOOK, ChatColor.LIGHT_PURPLE + ability.getName());
            buttons[i] = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    ability.activate(player);
                    player.sendMessage(ChatColor.GREEN + "Activated: " + ChatColor.GOLD + ability.getName());
                    player.closeInventory();
                }
            });
        }
        buttons[1] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ");
        buttons[9] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ");
        return new PlayerBuffsGUI("Player Buffs", buttons);
    }
}
