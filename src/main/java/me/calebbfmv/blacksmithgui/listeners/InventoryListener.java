package me.calebbfmv.blacksmithgui.listeners;

import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.gui.menus.EnchantsGUI;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        GUI gui = GUI.get(event.getInventory().getName());
        if(gui == null){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();
        Button button = gui.getButton(event.getRawSlot());
        EPlayer ePlayer = EPlayer.get(player);
        if(ePlayer == null){
            ePlayer = new EPlayer(player);
        }
        if(button == null){
            if(ePlayer.isPromptedForItem()){
                event.setCancelled(true);
                event.setResult(Event.Result.DENY);
                if(gui instanceof EnchantsGUI) {
                    if (gui.getSize(player) < event.getRawSlot()) {
                        ItemStack clicked = event.getCurrentItem();
                        if (clicked == null || clicked.getType() == Material.AIR) {
                            return;
                        }
                        player.getInventory().setItem(event.getSlot(), null);
                        ePlayer.setChosenItem(clicked);
                        ePlayer.setPromptedForItem(false);
                        event.getInventory().setItem(16, clicked);
                    }
                }
                return;
            }
            return;
        }
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        button.onClick(player);
    }
}
