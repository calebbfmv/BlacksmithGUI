package me.calebbfmv.blacksmithgui.enchants;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class ArrowEnchant extends Enchant {

    private HashMap<UUID, Long> cds = new HashMap<>();

    public ArrowEnchant(int cost, String name, Upgrade upgrades) {
        super(cost, name, upgrades);
    }

    @Override
    public void action(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        EPlayer ePlayer = EPlayer.get(player);
        Upgrade upgrade = getUpgrade();
        if(!can(player)){
            return;
        }
        int level = ePlayer.getLevel(upgrade);
        int decreased = 5 * level;
        long duration = 60 - decreased;
        cds.put(player.getUniqueId(), duration);
        Arrow arrow = player.getWorld().spawn(player.getLocation(), Arrow.class);
    }

    public boolean can(Player player){
        if(cds.get(player.getUniqueId()) != null) {
            long left = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() / 1000 - cds.get(player.getUniqueId()));
            return left == 0;
        }
        return true;
    }

    @Override
    public boolean doOnRight() {
        return true;
    }
}
