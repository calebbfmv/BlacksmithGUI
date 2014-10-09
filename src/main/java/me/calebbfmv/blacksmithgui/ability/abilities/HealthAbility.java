package me.calebbfmv.blacksmithgui.ability.abilities;

import me.calebbfmv.blacksmithgui.ability.Ability;
import me.calebbfmv.blacksmithgui.ability.AbilityType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class HealthAbility extends Ability {

    public HealthAbility() {
        super(AbilityType.HEALTH);
    }

    @Override
    public void apply(Player player) {
        int level = getLevel(player);
        for(PotionEffect effects : player.getActivePotionEffects()){
            if(effects.getType().equals(PotionEffectType.HEALTH_BOOST)){
                player.removePotionEffect(effects.getType());
            }
        }
        player.setMaxHealth(20.0D);
        PotionEffect effect = new PotionEffect(PotionEffectType.HEALTH_BOOST, 10000000, level * 2);
        player.addPotionEffect(effect);
        player.setHealth(player.getMaxHealth());
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + StringUtils.capitalize(type.name()) + ChatColor.GRAY + "] " + ChatColor.RED + "is now activated.");
    }
}
