package me.calebbfmv.blacksmithgui;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class DamageListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)){
            return;
        }
        if(!(event.getDamager() instanceof Player)){
            return;
        }
        Player player = (Player) event.getDamager();
        ItemStack item = player.getItemInHand();
        if(item == null || item.getType() == Material.AIR){
            return;
        }
        if(!item.hasItemMeta()){
            return;
        }
        if(!item.getItemMeta().hasLore()){
            return;
        }
        String e = "";
        for(String s : item.getItemMeta().getLore()){
            if(ChatColor.stripColor(s).contains("Enchants:")){
                e = s;
                break;
            }
        }
        Enchant enchant = Enchant.getFromName(e);
        enchant.action(event);
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
}
