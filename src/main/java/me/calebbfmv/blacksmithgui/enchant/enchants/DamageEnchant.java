package me.calebbfmv.blacksmithgui.enchant.enchants;

import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class DamageEnchant extends CustomEnchant {

    public DamageEnchant() {
        super(EnchantmentType.DAMAGE);
    }

    @Override
    public void action(Player player) {

    }

    @Override
    public void action(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        int level = getLevel(player);
        double inc = level / 2;
        event.setDamage(event.getDamage() + (event.getDamage() * inc));
    }

    @Override
    public boolean isRightClick() {
        return false;
    }
}
