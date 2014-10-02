package me.calebbfmv.blacksmithgui;

import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.gui.guis.CategoryGUI;
import me.calebbfmv.blacksmithgui.gui.guis.UpgradeGUI;
import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import me.calebbfmv.blacksmithgui.utils.RomanNumeral;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        String l = "";
        String e = "";
        for(String s : item.getItemMeta().getLore()){
            if(ChatColor.stripColor(s).contains("Enchant:")){
                e = s;
            }
            if(ChatColor.stripColor(s).contains("Level:")){
                l = s;
            }
        }
        Enchant enchant = Enchant.getFromName(e);
        int level = RomanNumeral.valueOf(l).getValue();
        EPlayer.get(event.getPlayer()).setLevel(enchant.getUpgrade(), level);
    }

    @EventHandler
    public void onFall(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)){
            return;
        }
        Player player = (Player) event.getEntity();
        if(event.getCause() != EntityDamageEvent.DamageCause.FALL){
            return;
        }
        if(!player.hasMetadata("fall")){
            return;
        }
        int level = player.getMetadata("fall").get(0).asInt();
        event.setDamage(event.getDamage() / level);
    }

    @EventHandler
    public void onClickI(InventoryClickEvent event){
        GUI gui = GUI.get(event.getInventory().getName());
        if(gui == null){
            return;
        }
        Button button = gui.getButton(event.getSlot());
        if(button == null){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        EPlayer ePlayer = EPlayer.get(player);
        if(gui instanceof UpgradeGUI){
            if(event.getSlot() > gui.getSize(player)){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            if(event.getSlot() == 0){
                ItemStack item = event.getInventory().getItem(0);
                if(item == null || item.getType() == Material.AIR){
                    return;
                }
                ePlayer.setChosenItem(item);
                ItemMeta meta = item.getItemMeta();
                if(meta == null){
                    return;
                }
                if(!meta.hasLore()){
                    return;
                }
                String l = "";
                String e = "";
                for(String s : item.getItemMeta().getLore()){
                    if(ChatColor.stripColor(s).contains("Enchant:")){
                        e = s;
                    }
                    if(ChatColor.stripColor(s).contains("Level:")){
                        l = s;
                    }
                }
                Enchant enchant = Enchant.getFromName(e);
                if(RomanNumeral.valueOf(l) == null){
                    return;
                }
                UpgradeGUI gui1 = UpgradeGUI.create(enchant.getUpgrade(), player);
                gui1.open(player);
            }
        }
        button.onClick(player);
    }
}
