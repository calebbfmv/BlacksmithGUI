package me.calebbfmv.blacksmithgui;

import me.calebbfmv.blacksmithgui.gui.guis.CategoryGUI;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEntityEvent event){
        if(!(event.getRightClicked() instanceof Villager)){
            return;
        }
        Villager villager = (Villager) event.getRightClicked();
        if(!villager.isCustomNameVisible()){
            return;
        }
        String name = villager.getCustomName();
        List<String> names = BlacksmithGUI.getInstance().getConfig().getStringList("allowed-names");
        if(!names.contains(name)){
            return;
        }
        CategoryGUI gui = CategoryGUI.create();
        gui.open(event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        new EPlayer(event.getPlayer());
    }

    @EventHandler
    public void onSwitch(PlayerItemHeldEvent event){
        ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if(!item.hasItemMeta()){
            return;
        }
        if(!item.getItemMeta().hasDisplayName()){
            return;
        }
    }
}
