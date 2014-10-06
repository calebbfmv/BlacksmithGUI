package me.calebbfmv.blacksmithgui.enchants;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class DamageEnchant extends Enchant {

    public DamageEnchant(int cost, String name, Upgrade upgrade) {
        super(cost, name, upgrade);
    }

    @Override
    public void action(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        EPlayer ePlayer = EPlayer.get(player);
        Upgrade upgrade = getUpgrade();
        int level = ePlayer.getLevel(upgrade);
        double inc = level / 2;
        event.setDamage(event.getDamage() + (event.getDamage() * inc));
    }

    @Override
    public boolean doOnRight() {
        return false;
    }

    @Override
    public void action(Player player) {

    }
}
