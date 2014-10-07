package me.calebbfmv.blacksmithgui.enchant.enchants;

import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class CriticalEnchant extends CustomEnchant {

    private Random random = new Random();

    public CriticalEnchant() {
        super(EnchantmentType.CRITICAL);
    }

    @Override
    public void action(Player player) {

    }

    @Override
    public void action(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        int level = getLevel(player);
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
    public boolean isRightClick() {
        return false;
    }
}
