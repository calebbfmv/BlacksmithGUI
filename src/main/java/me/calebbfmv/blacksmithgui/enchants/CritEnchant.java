package me.calebbfmv.blacksmithgui.enchants;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class CritEnchant extends Enchant {

    private Random random = new Random();

    public CritEnchant(int cost, String name, Upgrade upgrades) {
        super(cost, name, upgrades);
    }

    @Override
    public void action(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        EPlayer ePlayer = EPlayer.get(player);
        Upgrade upgrade = getUpgrade();
        int level = ePlayer.getLevel(upgrade);
        if(level == 10){
            event.setDamage(event.getDamage() * 2);
            return;
        }
        int chance = random.nextInt(10);
        if(level >= chance){
            event.setDamage(event.getDamage() * 2);
        }
    }

    @Override
    public boolean doOnRight() {
        return false;
    }

    @Override
    public void action(Player player) {

    }
}
