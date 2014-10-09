package me.calebbfmv.blacksmithgui.enchant.enchants;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import me.calebbfmv.blacksmithgui.listeners.EnchantListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class BlindnessEnchant extends CustomEnchant {

    private HashMap<UUID, Long> cds = new HashMap<>();

    public BlindnessEnchant() {
        super(EnchantmentType.BLINDNESS);
    }

    @Override
    public void action(Player player) {
        if(!can(player)){
            long left = 10 - (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(cds.get(player.getUniqueId())));
            player.sendMessage(ChatColor.RED + "Please wait " + left + " second" + (left == 1 ? "" : "s" )+ " before using this again!" );
            return;
        }
        EnchantListener.tries.remove(player.getUniqueId());
        int level = getLevel(player);
        cds.put(player.getUniqueId(), System.currentTimeMillis());
        Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
        Vector to = player.getEyeLocation().getDirection().multiply(1.46);
        snowball.setShooter(player);
        snowball.setVelocity(to);
        FixedMetadataValue meta = new FixedMetadataValue(BlacksmithGUI.getInstance(), (level * 2));
        snowball.setMetadata("blind", meta);
    }

    public boolean can(Player player){
        if(cds.get(player.getUniqueId()) != null) {
            long left = 10 - (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(cds.get(player.getUniqueId())));
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
