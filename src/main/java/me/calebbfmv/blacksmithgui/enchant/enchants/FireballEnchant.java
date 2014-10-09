package me.calebbfmv.blacksmithgui.enchant.enchants;

import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import me.calebbfmv.blacksmithgui.listeners.EnchantListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class FireballEnchant extends CustomEnchant {

    private HashMap<UUID, Long> cds = new HashMap<>();

    public FireballEnchant() {
        super(EnchantmentType.FIREBAL);
    }

    @Override
    public void action(Player player) {
        if(!can(player)){
            int level = getLevel(player);
            int decreased = 5 * level;
            long duration = 60 - decreased;
            long left = duration -(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(cds.get(player.getUniqueId())));
            player.sendMessage(ChatColor.RED + "Please wait " + left + " second" + (left == 1 ? "" : "s" )+ " before using this again!" );
            return;
        }
        EnchantListener.tries.remove(player.getUniqueId());
        cds.put(player.getUniqueId(), System.currentTimeMillis());
        Fireball arrow = player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
        Vector to = player.getEyeLocation().getDirection().multiply(1.46);
        arrow.setVelocity(to);
        arrow.setShooter(player);
    }

    public boolean can(Player player){
        if(cds.get(player.getUniqueId()) != null) {
            int level = getLevel(player);
            int decreased = 5 * level;
            long duration = 60 - decreased;
            long left = duration -(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(cds.get(player.getUniqueId())));
            return left <= 0;
        }
        return true;
    }

    @Override
    public void action(EntityDamageByEntityEvent event) {

    }

    @Override
    public boolean isRightClick() {
        return true;
    }
}
