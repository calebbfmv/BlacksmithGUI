package me.calebbfmv.blacksmithgui.listeners;

import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class EnchantListener implements Listener {

    public static HashMap<UUID, Integer> tries = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        CustomEnchant enchant = CustomEnchant.getFromItem(player);
        if (enchant == null) {
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!enchant.isRightClick()) {
            return;
        }
        if(tries.get(player.getUniqueId()) == null) {
            enchant.action(player);
            tries.put(player.getUniqueId(), 1);
        }
    }

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
        CustomEnchant enchant = CustomEnchant.getFromItem(player);
        if(enchant == null){
            return;
        }
        if(enchant.isRightClick()){
            return;
        }
        if(EPlayer.get(player).hasStrengthAbility()){
            int value = player.getMetadata("str").get(0).asInt();
            event.setDamage(event.getDamage() + value);
        }
        enchant.action(event);
    }

}
