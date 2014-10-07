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
public class SpeedAbility extends Ability {

    public SpeedAbility() {
        super(AbilityType.SPEED);
    }

    @Override
    public void apply(Player player) {
        int level = getLevel(player);
        for(PotionEffect effect : player.getActivePotionEffects()){
            if(effect.getType() == PotionEffectType.SPEED){
                player.removePotionEffect(effect.getType());
            }
        }
        PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 100000, level / 2);
        player.addPotionEffect(effect);
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + StringUtils.capitalize(type.name()) + ChatColor.GRAY + "] " + ChatColor.RED + "is now activated.");
    }
}
