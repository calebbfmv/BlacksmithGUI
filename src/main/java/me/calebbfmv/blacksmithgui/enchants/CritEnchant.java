package me.calebbfmv.blacksmithgui.enchants;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class CritEnchant extends Enchant {

    public CritEnchant(int cost, String name, Upgrade upgrades) {
        super(cost, name, upgrades);
    }

    @Override
    public void action(EntityDamageByEntityEvent event) {

    }

    @Override
    public boolean doOnRight() {
        return false;
    }
}
