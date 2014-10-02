package me.calebbfmv.blacksmithgui.enchants;

import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class BlindnessEnchant extends Enchant {

    public BlindnessEnchant(int cost, String name, Upgrade upgrade) {
        super(cost, name, upgrade);
    }

    @Override
    public void action(EntityDamageByEntityEvent event) {

    }

    @Override
    public boolean doOnRight() {
        return false;
    }
}
