package me.calebbfmv.blacksmithgui.enchants;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class BlindnessEnchant extends Enchant {

    private HashMap<UUID, Long> cds = new HashMap<>();

    public BlindnessEnchant(int cost, String name, Upgrade upgrade) {
        super(cost, name, upgrade);
    }

    @Override
    public void action(Player player) {
        EPlayer ePlayer = EPlayer.get(player);
        Upgrade upgrade = getUpgrade();
        if(!can(player)){
            return;
        }
        int level = ePlayer.getLevel(upgrade);
        long duration = 10;
        cds.put(player.getUniqueId(), duration);
        Snowball snowball = player.getWorld().spawn(player.getLocation(), Snowball.class);
        Vector to = player.getEyeLocation().getDirection().multiply(1.46);
        snowball.setShooter(player);
        snowball.setVelocity(to);
        FixedMetadataValue meta = new FixedMetadataValue(BlacksmithGUI.getInstance(), (level * 2));
        snowball.setMetadata("blind", meta);
    }

    public boolean can(Player player){
        if(cds.get(player.getUniqueId()) != null) {
            long left = 10 - (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - cds.get(player.getUniqueId()));
            return left == 0;
        }
        return true;
    }

    @Override
    public void action(EntityDamageByEntityEvent player) {

    }

    @Override
    public boolean doOnRight() {
        return true;
    }
}
