package me.calebbfmv.blacksmithgui;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class DamageListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)){
            return;
        }
        Player p = (Player) event.getEntity();
        if(!(event.getDamager() instanceof Player)){
            if(event.getDamager() instanceof Projectile){
                Projectile projectile = (Projectile) event.getDamager();
                if(projectile instanceof Snowball){
                    Snowball snowball = (Snowball) projectile;
                    if(snowball.hasMetadata("blind")){
                        int dur = snowball.getMetadata("blind").get(0).asInt();
                        PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, dur * 20, 1);
                        p.addPotionEffect(effect);
                       return;
                    }
                    if(snowball.hasMetadata("slow")){
                        int dur = snowball.getMetadata("slow").get(0).asInt();
                        PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, dur * 20, 1);
                        p.addPotionEffect(effect);
                        return;
                    }
                }
            }
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
        if(enchant == null){
            return;
        }
        if(enchant.doOnRight()){
            return;
        }
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

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
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
        Enchant enchant = Enchant.getFromItem(item);
        if(enchant == null){
            return;
        }
        if(enchant.doOnRight()){
            return;
        }
        enchant.action(player);
    }
}
